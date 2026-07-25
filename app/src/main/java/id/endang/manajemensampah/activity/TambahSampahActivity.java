package id.endang.manajemensampah.activity;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


import id.endang.manajemensampah.R;
import id.endang.manajemensampah.database.DatabaseHelper;



public class TambahSampahActivity extends AppCompatActivity {


    private EditText etJenis;
    private EditText etBerat;
    private EditText etTanggal;
    private Spinner spKategori;
    private Button btnSimpan;
    private Button btnReset;
    private DatabaseHelper db;
    private final String[] kategori = {

            "Organik",
            "Anorganik",
            "B3"

    };






    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_tambah_sampah);
        initView();
        db = new DatabaseHelper(this);
        loadKategori();
        setTanggalOtomatis();
        btnSimpan.setOnClickListener(v -> {
            validasiData();


        });
        btnReset.setOnClickListener(v -> {
            resetForm();


        });
        etTanggal.setOnClickListener(v -> {


            pilihTanggal();


        });



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
        btnSimpan =
                findViewById(R.id.btnSimpan);
        btnReset =
                findViewById(R.id.btnReset);
    }
    private void loadKategori(){
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(

                        this,

                        android.R.layout.simple_spinner_dropdown_item,

                        kategori

                );

        spKategori.setAdapter(adapter);


    }

    private void setTanggalOtomatis(){
        Calendar calendar =
                Calendar.getInstance();
        SimpleDateFormat sdf =
                new SimpleDateFormat(

                        "dd-MM-yyyy",

                        Locale.getDefault()

                );

        etTanggal.setText(

                sdf.format(
                        calendar.getTime()
                )

        );

    }
    private void pilihTanggal(){
        Calendar calendar =
                Calendar.getInstance();
        DatePickerDialog dialog =
                new DatePickerDialog(
                        this,
                        (view, year, month, dayOfMonth) -> {
                            String tanggal =

                                    dayOfMonth
                                            + "-"
                                            + (month + 1)
                                            + "-"
                                            + year;
                            etTanggal.setText(tanggal);
                        },

                        calendar.get(Calendar.YEAR),

                        calendar.get(Calendar.MONTH),

                        calendar.get(Calendar.DAY_OF_MONTH)

                );



        dialog.show();


    }

    private void validasiData(){
        String jenis =

                etJenis.getText()
                        .toString()
                        .trim();

        String beratText =

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

        if(beratText.isEmpty()){


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
                            beratText
                    );



        }
        catch(Exception e){



            etBerat.setError(
                    "Masukkan angka yang benar"
            );


            etBerat.requestFocus();


            return;


        }

        if(berat <= 0){


            etBerat.setError(
                    "Berat harus lebih dari 0"
            );


            return;


        }

        dialogSimpan();




    }

    private void dialogSimpan(){
        new AlertDialog.Builder(this)

                .setTitle("Simpan Data")

                .setMessage(
                        "Apakah data sampah sudah benar?"
                )
                .setPositiveButton(

                        "Simpan",

                        (dialog, which) -> {


                            simpanData();


                        }

                )

                .setNegativeButton(

                        "Batal",

                        null

                )


                .show();



    }

    private void simpanData(){
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

                        etBerat.getText()
                                .toString()

                );
        String tanggal =
                etTanggal.getText()
                        .toString();
        boolean berhasil =
                db.insertData(

                        jenis,

                        kategori,

                        berat,

                        tanggal

                );
        if(berhasil){
            Toast.makeText(

                    this,

                    "Data sampah berhasil disimpan",

                    Toast.LENGTH_SHORT

            ).show();
            finish();
        }
        else{


            Toast.makeText(

                    this,

                    "Data gagal disimpan",

                    Toast.LENGTH_SHORT

            ).show();



        }


    }

    private void resetForm(){
        etJenis.setText("");
        etBerat.setText("");
        spKategori.setSelection(0);
        setTanggalOtomatis();
        Toast.makeText(

                this,

                "Form berhasil dikosongkan",

                Toast.LENGTH_SHORT

        ).show();



    }

    @Override
    public void onBackPressed() {


        finish();


    }


}