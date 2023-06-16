package Nathacia.uas.pendataanrs;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.core.view.WindowCompat;


import Nathacia.uas.pendataanrs.databinding.ActivityUpdateBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {

    private ActivityUpdateBinding binding;
    private Daftar daftar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        daftar = getIntent().getParcelableExtra("EXTRA_DATA");
        String id = daftar.getId();

        binding.etNama.setText(daftar.getNama());
        binding.etAlamat.setText(daftar.getAlamat());
        binding.etDeskripsi.setText(daftar.getDeskripsi());
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = binding.etNama.getText().toString();
                String alamat = binding.etAlamat.getText().toString();
                String deskripsi = binding.etDeskripsi.getText().toString();

                boolean bolehUpdate = true;
                if (TextUtils.isEmpty(nama)) {
                    bolehUpdate = false;
                    binding.etNama.setError("Nama tidak boleh kosong!");
                }
                if (TextUtils.isEmpty(alamat)) {
                    bolehUpdate = false;
                    binding.etAlamat.setError("Alamat tidak boleh kosong!");
                }
                if (TextUtils.isEmpty(deskripsi)) {
                    bolehUpdate = false;
                    binding.etDeskripsi.setError("Nama tidak boleh kosong!");
                }


                if (bolehUpdate) {
                    updateUnggah(id, nama, alamat, deskripsi);
                }
            }
        });
    }

    private void updateUnggah(String id, String nama, String alamat, String deskripsi) {
        binding.progressBar.setVisibility(View.VISIBLE);
        APIService api = Utilities.getRetrofit().create(APIService.class);
        Call<ValueNulData> call = api.updatePendataanrs(id, nama, alamat, deskripsi);
        call.enqueue(new Callback<ValueNulData>() {
            @Override
            public void onResponse(Call<ValueNulData> call, Response<ValueNulData> response) {
                binding.progressBar.setVisibility(View.GONE);
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();

                    if (success == 1) {
                        Toast.makeText(UpdateActivity.this, message, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(UpdateActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(UpdateActivity.this, "Response " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNulData> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                System.out.println("Retrofit Error :" + t.getMessage());
                Toast.makeText(UpdateActivity.this, "Retrofit Error :" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}