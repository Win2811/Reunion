package com.example.myrecyclerview2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Mes_ReunionAdapter extends RecyclerView.Adapter<Mes_ReunionAdapter.MyViewHolder> implements Filterable{
    private ArrayList<Liste_Reunion> listData;
    private Context context;
    private final OnEditListener onEditListener;
    public ArrayList<Liste_Reunion> getListDataFilter = new ArrayList<>();


    public Mes_ReunionAdapter(Context context, ArrayList<Liste_Reunion> list, OnEditListener onEditListener){
        this.listData = list;
        this.getListDataFilter = list;
        this.context = context;
        this.onEditListener = onEditListener;


    }





    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reunion_items, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder (@NonNull MyViewHolder holder, int position){
        Liste_Reunion dataObj = listData.get(position);

        holder.mNameTV.setText(dataObj.getNameReunion());
        holder.mliste_emailTV.setText(dataObj.getListe_emailReunion());
        holder.Heure.setText(dataObj.getHeureReunion());


        switch (listData.get(position).getSalle()){
            case "Salle 1":
                holder.mRound.setImageResource(R.drawable.salle_1_baseline);
                holder.mc.setText(R.string.LesSalles);
                break;
            case "Salle 2":
                holder.mRound.setImageResource(R.drawable.salle_2_baseline);
                holder.mc.setText(R.string.Mario);
                break;
            case "Salle 3":
                holder.mRound.setImageResource(R.drawable.salle_3_baseline);
                holder.mc.setText(R.string.Luigi);
                break;
            case "Salle 4":
                holder.mRound.setImageResource(R.drawable.salle_4_baseline);
                holder.mc.setText(R.string.Robert);
                break;
            case "Salle 5":
                holder.mRound.setImageResource(R.drawable.salle_5_baseline);
                holder.mc.setText(R.string.Marcus);
                break;
            case "Salle 6":
                holder.mRound.setImageResource(R.drawable.salle_6_baseline);
                holder.mc.setText(R.string.Marc);
                break;
            case "Salle 7":
                holder.mRound.setImageResource(R.drawable.salle_7_baseline);
                holder.mc.setText(R.string.Moy);
                break;
            case "Salle 8":
                holder.mRound.setImageResource(R.drawable.salle_8_baseline);
                holder.mc.setText(R.string.Straff);
                break;
            case "Salle 9":
                holder.mRound.setImageResource(R.drawable.salle_9_baseline);
                holder.mc.setText(R.string.Roy);
                break;
            case "Salle 10":
                holder.mRound.setImageResource(R.drawable.salle_10_baseline);
                holder.mc.setText(R.string.Mari);
                break;
        }




        holder.mimage_Delete.setOnClickListener(view -> {
            listData.remove(position);
            notifyDataSetChanged();
        });
        holder.mimage_Edit.setOnClickListener(view -> {
            onEditListener.onEditClick(listData.get(position), position);
        });



    }


    @Override
    public int getItemCount() {

        return listData.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if (charSequence == null || charSequence.length() == 0){
                    filterResults.values = getListDataFilter;
                    filterResults.count = getListDataFilter.size();
                }else{
                    String searchStr = charSequence.toString().toLowerCase();
                    ArrayList<Liste_Reunion> list = new ArrayList<>();
                    for (Liste_Reunion liste_reunion: getListDataFilter){
                        if(liste_reunion.getNameReunion().toLowerCase().contains(searchStr) || liste_reunion.getHeureReunion().toLowerCase().contains(searchStr) || liste_reunion.getSalle().toLowerCase().contains(searchStr)){
                            list.add(liste_reunion);
                        }
                    }
                    filterResults.values = list;
                    filterResults.count = list.size();
                }
                return filterResults;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                listData = (ArrayList<Liste_Reunion>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
         TextView mNameTV;
         TextView mliste_emailTV;
         ImageView mimage_Edit;
         ImageView mimage_Delete;
        TextView Heure;
        ImageView mRound;
        TextView mc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mNameTV = itemView.findViewById(R.id.nameTV);
            mliste_emailTV =itemView.findViewById(R.id.liste_emailTV);
            mimage_Edit = itemView.findViewById(R.id.image_Edit);
            mimage_Delete = itemView.findViewById(R.id.image_Delete);
            Heure = itemView.findViewById(R.id.Heure);
            mRound = itemView.findViewById(R.id.rlImage);
            mc = itemView.findViewById(R.id.dirigePar);

        }

    }

    @SuppressLint("NotifyDataSetChanged")
    public void editData(Liste_Reunion listDataObj, int currentPosition){

        listData.get(currentPosition).setNameReunion(listDataObj.getNameReunion());
        listData.get(currentPosition).setListe_emailReunion(listDataObj.getListe_emailReunion());
        notifyDataSetChanged();
    }


    public interface OnEditListener{
        void onEditClick(Liste_Reunion listCurrentData,int CurrentPosition);
    }
}
