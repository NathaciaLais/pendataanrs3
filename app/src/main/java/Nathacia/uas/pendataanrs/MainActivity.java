package Nathacia.uas.pendataanrs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.List;

import Nathacia.uas.pendataanrs.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private DaftarViewAdapter daftarViewAdapter;
    private List<Daftar> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (!Utilities.checkValue(MainActivity.this, "xUserId")) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        daftarViewAdapter = new DaftarViewAdapter();
        binding.rvDaftar.setLayoutManager(new LinearLayoutManager(this));
        binding.rvDaftar.setAdapter(daftarViewAdapter);

        daftarViewAdapter.setOnItemLongClickListener(new DaftarViewAdapter.onItemLongClickListener() {
            @Override
            public void onItemLongClickListener(View v, Daftar daftar, int position) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.inflate(R.menu.menu_popup);
                popupMenu.setGravity(Gravity.RIGHT);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int idMenu = item.getItemId();
                        if (idMenu==R.id.action_edit){
                            Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                            intent.putExtra("EXTRA_DATA",daftar);
                            startActivity(intent);
                            return true;
                        } else  if (idMenu==R.id.action_delete){
                            String id = daftar.getId();
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Konfirmasi");
                            builder.setMessage("Yakin Ingin Menghapus "+data.get(position).getNama()+data.get(position).getAlamat()+data.get(position).getDeskripsi()+"?");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteTampil(id);
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();

                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                            return true;
                        }else {
                            return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
        binding.fabInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddUnggahActivity.class);
                startActivity(intent);
            }
        });
            }
    private void deleteTampil(String id) {
        APIService api = Utilities.getRetrofit().create(APIService.class);
        Call<ValueNulData> call = api.deletePendataanrs(id);
        call.enqueue(new Callback<ValueNulData>() {
            @Override
            public void onResponse(Call<ValueNulData> call, Response<ValueNulData> response) {
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();

                    if (success == 1) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        getAllDaftar();
                    } else {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Response " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNulData> call, Throwable t) {
                System.out.println("Retrofit Error" + t.getMessage());
                Toast.makeText(MainActivity.this, "Retrofit Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getAllDaftar() {
        binding.progressBar.setVisibility(View.VISIBLE);
        APIService api = Utilities.getRetrofit().create(APIService.class);
        Call<ValueData<List<Daftar>>> call = api.getPendataanrs();
        call.enqueue(new Callback<ValueData<List<Daftar>>>() {
            @Override
            public void onResponse(Call<ValueData<List<Daftar>>> call, Response<ValueData<List<Daftar>>> response) {
                binding.progressBar.setVisibility(View.GONE);
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();
                    if (success == 1){
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        data = response.body().getData();
                        daftarViewAdapter.setData(data);
                    }else {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueData<List<Daftar>>> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                System.out.println("Retrofit Error :" + t.getMessage());
                Toast.makeText(MainActivity.this, "Retrofit Error" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_rs,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            Utilities.clearUser(this);
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }


}
