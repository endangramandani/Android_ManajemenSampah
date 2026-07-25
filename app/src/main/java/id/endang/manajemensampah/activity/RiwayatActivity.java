package id.endang.manajemensampah.activity;


import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


import id.endang.manajemensampah.R;
import id.endang.manajemensampah.adapter.SampahAdapter;
import id.endang.manajemensampah.database.DatabaseHelper;
import id.endang.manajemensampah.model.Sampah;



public class RiwayatActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private SearchView searchView;
    private TextView txtJumlahData;


    private DatabaseHelper db;


    private ArrayList<Sampah> listSampah;
    private SampahAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_riwayat);



        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
        txtJumlahData = findViewById(R.id.txtJumlahData);



        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );



        db = new DatabaseHelper(this);


        listSampah = new ArrayList<>();


        tampilData();




        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {


                    @Override
                    public boolean onQueryTextSubmit(String query) {

                        cariData(query);

                        return true;
                    }



                    @Override
                    public boolean onQueryTextChange(String newText) {

                        cariData(newText);

                        return true;
                    }


                }
        );

    }


    private void tampilData(){


        listSampah.clear();


        Cursor cursor = db.getAllData();



        if(cursor != null && cursor.moveToFirst()){


            do {


                Sampah sampah = new Sampah();



                sampah.setId(
                        cursor.getInt(
                                cursor.getColumnIndexOrThrow(
                                        DatabaseHelper.ID
                                )
                        )
                );



                sampah.setJenis(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                        DatabaseHelper.JENIS
                                )
                        )
                );



                sampah.setKategori(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                        DatabaseHelper.KATEGORI
                                )
                        )
                );



                sampah.setBerat(
                        cursor.getDouble(
                                cursor.getColumnIndexOrThrow(
                                        DatabaseHelper.BERAT
                                )
                        )
                );



                sampah.setTanggal(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                        DatabaseHelper.TANGGAL
                                )
                        )
                );



                listSampah.add(sampah);



            }while(cursor.moveToNext());


        }



        if(cursor != null){

            cursor.close();

        }



        txtJumlahData.setText(
                listSampah.size()+" Data"
        );



        adapter = new SampahAdapter(

                this,

                listSampah,


                new SampahAdapter.OnItemClickListener(){



                    @Override
                    public void onEdit(Sampah sampah){


                        Intent intent =
                                new Intent(
                                        RiwayatActivity.this,
                                        EditSampahActivity.class
                                );


                        intent.putExtra(
                                "id",
                                sampah.getId()
                        );


                        intent.putExtra(
                                "jenis",
                                sampah.getJenis()
                        );


                        intent.putExtra(
                                "kategori",
                                sampah.getKategori()
                        );


                        intent.putExtra(
                                "berat",
                                sampah.getBerat()
                        );


                        intent.putExtra(
                                "tanggal",
                                sampah.getTanggal()
                        );


                        startActivity(intent);


                    }




                    @Override
                    public void onDelete(Sampah sampah){


                        hapusData(sampah);


                    }


                }


        );



        recyclerView.setAdapter(adapter);



    }


    private void cariData(String keyword){


        listSampah.clear();


        Cursor cursor =
                db.searchData(keyword);



        if(cursor != null && cursor.moveToFirst()){



            do {


                Sampah sampah = new Sampah();



                sampah.setId(
                        cursor.getInt(
                                cursor.getColumnIndexOrThrow(
                                        DatabaseHelper.ID
                                )
                        )
                );


                sampah.setJenis(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                        DatabaseHelper.JENIS
                                )
                        )
                );



                sampah.setKategori(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                        DatabaseHelper.KATEGORI
                                )
                        )
                );



                sampah.setBerat(
                        cursor.getDouble(
                                cursor.getColumnIndexOrThrow(
                                        DatabaseHelper.BERAT
                                )
                        )
                );



                sampah.setTanggal(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                        DatabaseHelper.TANGGAL
                                )
                        )
                );



                listSampah.add(sampah);



            }while(cursor.moveToNext());

        }



        if(cursor != null){

            cursor.close();

        }



        txtJumlahData.setText(
                listSampah.size()+" Data"
        );



        if(adapter != null){

            adapter.notifyDataSetChanged();

        }


    }


    private void hapusData(Sampah sampah){
        new AlertDialog.Builder(this)
                .setTitle("Hapus Data")
                .setMessage(
                        "Apakah Anda yakin ingin menghapus data ini?"
                )

                .setPositiveButton(
                        "Hapus",
                        (dialog, which)->{
                            boolean hasil =
                                    db.deleteData(
                                            sampah.getId()
                                    );
                            if(hasil){
                                Toast.makeText(
                                        this,
                                        "Data berhasil dihapus",
                                        Toast.LENGTH_SHORT
                                ).show();
                                tampilData();
                            }
                        }
                ).setNegativeButton(
                        "Batal",
                        null
                )
                .show();

    }

    @Override
    protected void onResume(){

        super.onResume();


        tampilData();


    }


}