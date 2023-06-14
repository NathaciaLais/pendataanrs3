package Nathacia.uas.pendataanrs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import Nathacia.uas.pendataanrs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private List<Daftar>data;
    private DaftarViewAdapter daftarViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (!Utilities.checkValue(MainActivity.this,"xUsername")){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        daftarViewAdapter = new DaftarViewAdapter();
        binding.rvUnggah.setLayoutManager(new LinearLayoutManager(this));
        binding.rvUnggah.setAdapter(daftarViewAdapter);

        binding.fabInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddUnggahActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllDaftar();
    }

    private void getAllDaftar() {
        binding.progressBar.setVisibility(View.VISIBLE);
        // memanggil data unggah dari server...
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
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