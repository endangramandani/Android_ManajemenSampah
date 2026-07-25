package id.endang.manajemensampah.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import id.endang.manajemensampah.R;
import id.endang.manajemensampah.database.DatabaseHelper;

public class StatistikActivity extends AppCompatActivity {

    private TextView txtJumlahData;
    private TextView txtTotalBerat;
    private TextView txtOrganik;
    private TextView txtAnorganik;
    private TextView txtB3;
    private TextView txtPersenOrganik;
    private TextView txtPersenAnorganik;
    private TextView txtPersenB3;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik);

        db = new DatabaseHelper(this);

        txtJumlahData = findViewById(R.id.txtJumlahData);
        txtTotalBerat = findViewById(R.id.txtTotalBerat);

        txtOrganik = findViewById(R.id.txtOrganik);
        txtAnorganik = findViewById(R.id.txtAnorganik);
        txtB3 = findViewById(R.id.txtB3);

        txtPersenOrganik = findViewById(R.id.txtPersenOrganik);
        txtPersenAnorganik = findViewById(R.id.txtPersenAnorganik);
        txtPersenB3 = findViewById(R.id.txtPersenB3);

        tampilStatistik();
    }

    private void tampilStatistik() {

        int jumlahData = db.getJumlahData();
        double totalBerat = db.getTotalBerat();

        int organik = db.getJumlahKategori("Organik");
        int anorganik = db.getJumlahKategori("Anorganik");
        int b3 = db.getJumlahKategori("B3");

        txtJumlahData.setText(jumlahData + " Data");
        txtTotalBerat.setText(String.format("%.1f Kg", totalBerat));

        txtOrganik.setText(String.valueOf(organik));
        txtAnorganik.setText(String.valueOf(anorganik));
        txtB3.setText(String.valueOf(b3));

        if (jumlahData > 0) {

            int persenOrganik = (organik * 100) / jumlahData;
            int persenAnorganik = (anorganik * 100) / jumlahData;
            int persenB3 = (b3 * 100) / jumlahData;

            txtPersenOrganik.setText(persenOrganik + "%");
            txtPersenAnorganik.setText(persenAnorganik + "%");
            txtPersenB3.setText(persenB3 + "%");

        } else {

            txtPersenOrganik.setText("0%");
            txtPersenAnorganik.setText("0%");
            txtPersenB3.setText("0%");

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilStatistik();
    }
}