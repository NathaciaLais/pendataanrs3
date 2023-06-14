package Nathacia.uas.pendataanrs;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Nathacia.uas.pendataanrs.databinding.ItemDaftarBinding;

public class DaftarViewAdapter extends RecyclerView.Adapter<DaftarViewAdapter.ViewHolder> {
    private List<Daftar> data = new ArrayList<>();

    public void setData(List<Daftar>data){
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DaftarViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemDaftarBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarViewAdapter.ViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();
        Daftar daftar = data.get(pos);
        holder.itemDaftarBinding.tvNama.setText(daftar.getNama());
        holder.itemDaftarBinding.tvAlamat.setText(daftar.getAlamat());
        holder.itemDaftarBinding.tvDeskripsi.setText(daftar.getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ItemDaftarBinding itemDaftarBinding;

        public ViewHolder(@NonNull ItemDaftarBinding itemView) {
            super(itemView.getRoot());
            itemDaftarBinding=itemView;
        }
    }
}
