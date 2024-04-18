package com.example.valorant_loadout_maker.Database;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.valorant_loadout_maker.AddNewLoadout;
import com.example.valorant_loadout_maker.MainActivity;
import com.example.valorant_loadout_maker.R;

import java.util.List;

public class ValoAdapter extends RecyclerView.Adapter<ValoAdapter.ViewHolder> {
    private List<LoadoutModel> loadoutList;
    private MainActivity activity;
    private ValoDBHelper db;

    public ValoAdapter(ValoDBHelper db, MainActivity activity){
        this.db = db;
        this.activity = activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.loadout_layout, parent, false);
        return new ViewHolder(itemView);
    }
    public void onBindViewHolder(ViewHolder holder, int position) {
        db.openDatabase();
        LoadoutModel item = loadoutList.get(position);
        holder.item.setText(item.getLoadout_name());
    }

    public int getItemCount(){
        return  loadoutList.size();
    }

    // Sets tasks
    public void setLoadouts(List<LoadoutModel> todoList){
        this.loadoutList = todoList;
        notifyDataSetChanged();
    }
    public Context getContext(){
        return activity;
    }

    // Delete a task
    public void deleteLoadout(int position){
        LoadoutModel item = loadoutList.get(position);
        db.deleteLoadout(item.getId());
        loadoutList.remove(position);
        notifyDataSetChanged();
    }

    // Edit a task
    public void editLoadout(int position){
        LoadoutModel item = loadoutList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("loadout_name", item.getLoadout_name());
        AddNewLoadout fragment = new AddNewLoadout();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewLoadout.TAG);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item; // Assuming you have a TextView in your loadout_layout

        public ViewHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.tv_valoLoadout); // Replace with your TextView ID
        }
    }
}
