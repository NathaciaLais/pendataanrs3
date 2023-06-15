package Nathacia.uas.pendataanrs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Nathacia.uas.pendataanrs.databinding.ItemDaftarBinding;

public class DaftarViewAdapter extends RecyclerView.Adapter<DaftarViewAdapter.ViewHolder> {
    private List<Daftar> data = new ArrayList<>();
    private onItemLongClickListener onItemLongClickListener;

    public  void setData(List<Daftar> data) {
        this.data = data;
        notifyDataSetChanged();
    }
    public void setOnItemLongClickListener(DaftarViewAdapter.onItemLongClickListener onItemLongClickListener){
        this.onItemLongClickListener=onItemLongClickListener;
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

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemLongClickListener.onItemLongClickListener(v,daftar,pos);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemDaftarBinding itemDaftarBinding;

        public ViewHolder(@NonNull ItemDaftarBinding itemView) {
            super(itemView.getRoot());
            itemDaftarBinding=itemView;
        }
    }
    public interface onItemLongClickListener{
        void onItemLongClickListener(View v,Daftar daftar,int position);
    }
}
