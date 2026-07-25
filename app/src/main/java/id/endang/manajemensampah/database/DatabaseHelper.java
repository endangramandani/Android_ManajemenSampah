package id.endang.manajemensampah.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db_sampah.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_SAMPAH = "tbl_sampah";
    public static final String ID = "id";
    public static final String JENIS = "jenis";
    public static final String KATEGORI = "kategori";
    public static final String BERAT = "berat";
    public static final String TANGGAL = "tanggal";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABLE_SAMPAH + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + JENIS + " TEXT,"
                + KATEGORI + " TEXT,"
                + BERAT + " REAL,"
                + TANGGAL + " TEXT)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAMPAH);
        onCreate(db);

    }

    public boolean insertData(String jenis,
                              String kategori,
                              double berat,
                              String tanggal) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(JENIS, jenis);
        values.put(KATEGORI, kategori);
        values.put(BERAT, berat);
        values.put(TANGGAL, tanggal);

        long result = db.insert(TABLE_SAMPAH, null, values);

        return result != -1;

    }

    public Cursor getAllData() {

        SQLiteDatabase db = getReadableDatabase();

        return db.query(
                TABLE_SAMPAH,
                null,
                null,
                null,
                null,
                null,
                ID + " DESC"
        );

    }

    public Cursor searchData(String keyword) {

        SQLiteDatabase db = getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLE_SAMPAH +
                        " WHERE " +
                        JENIS + " LIKE ? OR " +
                        KATEGORI + " LIKE ?",
                new String[]{
                        "%" + keyword + "%",
                        "%" + keyword + "%"
                });

    }

    public Cursor getDataById(int id) {

        SQLiteDatabase db = getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM "
                        + TABLE_SAMPAH +
                        " WHERE id=?",
                new String[]{
                        String.valueOf(id)
                });

    }

    public boolean updateData(int id,
                              String jenis,
                              String kategori,
                              double berat,
                              String tanggal) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(JENIS, jenis);
        values.put(KATEGORI, kategori);
        values.put(BERAT, berat);
        values.put(TANGGAL, tanggal);

        int result = db.update(
                TABLE_SAMPAH,
                values,
                ID + "=?",
                new String[]{
                        String.valueOf(id)
                });

        return result > 0;

    }

    public boolean deleteData(int id) {

        SQLiteDatabase db = getWritableDatabase();

        int result = db.delete(
                TABLE_SAMPAH,
                ID + "=?",
                new String[]{
                        String.valueOf(id)
                });

        return result > 0;

    }

    public int getJumlahData() {

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT COUNT(*) FROM " + TABLE_SAMPAH,
                null);

        int total = 0;

        if (c.moveToFirst())
            total = c.getInt(0);

        c.close();

        return total;

    }

    public double getTotalBerat() {

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT IFNULL(SUM(" + BERAT + "),0) FROM " + TABLE_SAMPAH,
                null);

        double total = 0;

        if (c.moveToFirst())
            total = c.getDouble(0);

        c.close();

        return total;

    }

    public int getJumlahKategori(String kategori){

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT COUNT(*) FROM "
                        + TABLE_SAMPAH +
                        " WHERE " +
                        KATEGORI + "=?",
                new String[]{kategori});

        int jumlah = 0;

        if(c.moveToFirst())
            jumlah = c.getInt(0);

        c.close();

        return jumlah;

    }

    public double getBeratKategori(String kategori){

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT IFNULL(SUM("
                        + BERAT +
                        "),0) FROM "
                        + TABLE_SAMPAH +
                        " WHERE " +
                        KATEGORI +
                        "=?",
                new String[]{kategori});

        double total = 0;

        if(c.moveToFirst())
            total = c.getDouble(0);

        c.close();

        return total;

    }

}