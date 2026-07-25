package id.endang.manajemensampah.activity;


import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import androidx.cardview.widget.CardView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import id.endang.manajemensampah.R;
import id.endang.manajemensampah.database.DatabaseHelper;



public class EditSampahActivity extends AppCompatActivity {



    private EditText etJenis;
    private EditText etBerat;
    private EditText etTanggal;
    private Spinner spKategori;
    private CardView btnUpdate;
    private DatabaseHelper db;
    private int id = 0;

    private final String[] kategori = {
            "Organik",
            "Anorganik",
            "B3"

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sampah);
        initView();
        loadSpinner();
        ambilDataIntent();
        btnUpdate.setOnClickListener(v -> validasiData());
    }

    private void initView(){
        etJenis =
                findViewById(R.id.etJenis);
        etBerat =
                findViewById(R.id.etBerat);
        etTanggal =
                findViewById(R.id.etTanggal);
        spKategori =
                findViewById(R.id.spKategori);
        btnUpdate =
                findViewById(R.id.btnUpdate);
        db =
                new DatabaseHelper(this);



    }

    private void loadSpinner(){
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(

                        this,

                        android.R.layout.simple_spinner_dropdown_item,

                        kategori

                );

        spKategori.setAdapter(adapter);
    }

    private void ambilDataIntent(){
        id =
                getIntent()
                        .getIntExtra(
                                "id",
                                0
                        );
        if(id == 0){
            Toast.makeText(

                    this,

                    "Data tidak ditemukan",

                    Toast.LENGTH_SHORT

            ).show();
            finish();
            return;

        }

        String jenis =
                getIntent()
                        .getStringExtra("jenis");
        String kategoriData =
                getIntent()
                        .getStringExtra("kategori");
        double berat =
                getIntent()
                        .getDoubleExtra(
                                "berat",
                                0
                        );



        String tanggal =
                getIntent()
                        .getStringExtra("tanggal");

        etJenis.setText(jenis);
        etBerat.setText(
                String.valueOf(berat)
        );
        etTanggal.setText(tanggal);
        for(int i = 0; i < kategori.length; i++){
            if(kategori[i].equals(kategoriData)){
                spKategori.setSelection(i);
                break;


            }


        }



    }
    private void validasiData(){
        String jenis =
                etJenis.getText()
                        .toString()
                        .trim();

        String beratString =
                etBerat.getText()
                        .toString()
                        .trim();

        if(jenis.isEmpty()){
            etJenis.setError(
                    "Jenis sampah wajib diisi"
            );
            etJenis.requestFocus();
            return;


        }

        if(beratString.isEmpty()){
            etBerat.setError(
                    "Berat wajib diisi"
            );
            etBerat.requestFocus();
            return;
        }
        double berat;

        try{
            berat =
                    Double.parseDouble(
                            beratString
                    );
        }
        catch(Exception e){
            etBerat.setError(
                    "Format berat tidak valid"
            );
            etBerat.requestFocus();
            return;
        }

        if(berat <= 0){
            etBerat.setError(
                    "Berat harus lebih dari 0"
            );
            etBerat.requestFocus();
            return;


        }
        konfirmasiUpdate();
    }

    private void konfirmasiUpdate(){
        new AlertDialog.Builder(this)
                .setTitle("Update Data")
                .setMessage(
                        "Apakah data sampah akan diperbarui?"
                )
                .setPositiveButton(

                        "Update",

                        (dialog, which) -> updateData()

                )


                .setNegativeButton(

                        "Batal",

                        null

                )


                .show();



    }


    private void updateData(){



        String jenis =
                etJenis.getText()
                        .toString()
                        .trim();

        String kategori =
                spKategori
                        .getSelectedItem()
                        .toString();


        double berat =
                Double.parseDouble(
                        etBerat
                                .getText()
                                .toString()
                );

        String tanggal =
                etTanggal.getText()
                        .toString()
                        .trim();
        boolean berhasil =
                db.updateData(

                        id,

                        jenis,

                        kategori,

                        berat,

                        tanggal

                );

        if(berhasil){
            Toast.makeText(

                    this,

                    "Data berhasil diperbarui",

                    Toast.LENGTH_SHORT

            ).show();
            finish();
        }
        else{
            Toast.makeText(

                    this,

                    "Data gagal diperbarui",

                    Toast.LENGTH_SHORT

            ).show();



        }



    }

    @Override
    public void onBackPressed() {


        finish();


    }



}