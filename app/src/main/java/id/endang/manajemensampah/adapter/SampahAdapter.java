package id.endang.manajemensampah.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.endang.manajemensampah.R;
import id.endang.manajemensampah.model.Sampah;

public class SampahAdapter extends RecyclerView.Adapter<SampahAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Sampah> listSampah;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onEdit(Sampah sampah);
        void onDelete(Sampah sampah);
    }

    public SampahAdapter(Context context,
                         ArrayList<Sampah> listSampah,
                         OnItemClickListener listener) {

        this.context = context;
        this.listSampah = listSampah;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_sampah, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Sampah sampah = listSampah.get(position);

        holder.txtJenis.setText(sampah.getJenis());
        holder.txtKategori.setText("Kategori : " + sampah.getKategori());
        holder.txtBerat.setText("Berat : " + sampah.getBerat() + " Kg");
        holder.txtTanggal.setText("Tanggal : " + sampah.getTanggal());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {
                    listener.onEdit(sampah);
                }

            }
        });

        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {
                    listener.onDelete(sampah);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return listSampah.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtJenis, txtKategori, txtBerat, txtTanggal;
        Button btnEdit, btnHapus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtJenis = itemView.findViewById(R.id.txtJenis);
            txtKategori = itemView.findViewById(R.id.txtKategori);
            txtBerat = itemView.findViewById(R.id.txtBerat);
            txtTanggal = itemView.findViewById(R.id.txtTanggal);

            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnHapus = itemView.findViewById(R.id.btnHapus);
        }
    }
}