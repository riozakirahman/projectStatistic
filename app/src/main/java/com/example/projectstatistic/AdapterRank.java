package com.example.projectstatistic;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectstatistic.model.PersonRank;

import java.util.List;

public class AdapterRank extends RecyclerView.Adapter<AdapterRank.myViewHolder>{

    private List<PersonRank> mList;
    private Activity activity;

    public AdapterRank(List<PersonRank> mList, Activity activity) {
        this.mList = mList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdapterRank.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.list_item,parent,false);
        return new myViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRank.myViewHolder holder, int position) {
        final PersonRank data = mList.get(position);
        int rank = position + 1;
        holder.tvnama.setText("Nama: " + data.name());
        holder.tvmean.setText("Mean: " + data.mean());
        holder.tvrank.setText("Ranking: " + rank);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView tvnama, tvrank, tvmean;
        CardView container;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnama = itemView.findViewById(R.id.idTVnama);
            tvmean = itemView.findViewById(R.id.idTVmean);
            tvrank = itemView.findViewById(R.id.idTVrank);
            container = itemView.findViewById(R.id.idCVhasil);


        }
    }

}
