package id.endang.manajemensampah.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import id.endang.manajemensampah.R;
import id.endang.manajemensampah.database.DatabaseHelper;


public class DashboardActivity extends AppCompatActivity {

    private CardView btnInput;
    private CardView btnRiwayat;
    private CardView btnStatistik;
    private TextView txtTotalData;
    private TextView txtTotalBerat;
    private DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        databaseHelper = new DatabaseHelper(this);
        txtTotalData = findViewById(R.id.txtTotalData);
        txtTotalBerat = findViewById(R.id.txtTotalBerat);
        btnInput = findViewById(R.id.btnInput);
        btnRiwayat = findViewById(R.id.btnRiwayat);
        btnStatistik = findViewById(R.id.btnStatistik);


        loadDashboard();
        btnInput.setOnClickListener(v -> {
            Intent intent = new Intent(
                    DashboardActivity.this,
                    TambahSampahActivity.class
            );

            startActivity(intent);


        });

        btnRiwayat.setOnClickListener(v -> {
            Intent intent = new Intent(
                    DashboardActivity.this,
                    RiwayatActivity.class
            );


            startActivity(intent);


        });

        btnStatistik.setOnClickListener(v -> {


            Intent intent = new Intent(
                    DashboardActivity.this,
                    StatistikActivity.class
            );


            startActivity(intent);


        });



    }

    private void loadDashboard(){
        int jumlahData =
                databaseHelper.getJumlahData();
        double totalBerat =
                databaseHelper.getTotalBerat();
        txtTotalData.setText(
                String.valueOf(jumlahData)
        );
        txtTotalBerat.setText(
                String.format("%.1f Kg", totalBerat)
        );


    }

    @Override
    protected void onResume() {

        super.onResume();


        if(databaseHelper != null){

            loadDashboard();

        }

    }

    @Override
    public void onBackPressed() {


        new AlertDialog.Builder(this)

                .setTitle("Keluar Aplikasi")

                .setMessage(
                        "Apakah Anda ingin keluar?"
                )


                .setPositiveButton(
                        "Ya",
                        (dialog, which) -> finishAffinity()
                )


                .setNegativeButton(
                        "Tidak",
                        null
                )


                .show();


    }


}