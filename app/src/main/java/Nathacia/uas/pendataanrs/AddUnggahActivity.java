package Nathacia.uas.pendataanrs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import Nathacia.uas.pendataanrs.databinding.ActivityAddUnggahBinding;

public class AddUnggahActivity extends AppCompatActivity {
    private ActivityAddUnggahBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddUnggahBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = binding.etNama.getText().toString();
                String alamat = binding.etAlamat.getText().toString();
                String deskripsi= binding.etDeskripsi.getText().toString();

                boolean bolehUnggah = true;

                if (TextUtils.isEmpty(nama)) {
                    bolehUnggah = false;
                    binding.etNama.setError("Nama tidak boleh kosong!");
                }
                if (TextUtils.isEmpty(alamat)) {
                    bolehUnggah = false;
                    binding.etAlamat.setError("Alamat tidak boleh kosong!");
                }
                if (TextUtils.isEmpty(deskripsi)) {
                    bolehUnggah = false;
                    binding.etDeskripsi.setError("Nama tidak boleh kosong!");
                }

                if (bolehUnggah) {
                    String userId = Utilities.getValue(AddUnggahActivity.this, "xUsername");
                    addUnggah(userId,nama,alamat,deskripsi);
                }
            }
        });
    }

    private void addUnggah(String userId, String nama, String alamat, String deskripsi) {
        binding.progressBar.setVisibility(View.VISIBLE);
        // proses untuk mengunggah konten ....
        binding.progressBar.setVisibility(View.GONE);
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