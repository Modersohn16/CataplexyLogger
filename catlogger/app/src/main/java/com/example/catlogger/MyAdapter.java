package com.example.catlogger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{

    Context context;
    List<LocalDateTime> listOfAttacks;
    int list_itemsId;
    int txtId;

    public MyAdapter(Context ct, List<LocalDateTime> ListOfAttacks, int list_itemsId, int txtId)
    {
        context = ct;
        this.listOfAttacks = listOfAttacks;
        this.list_itemsId = list_itemsId;
        this.txtId = txtId;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(list_itemsId, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        DateTimeFormatter hour_min = DateTimeFormatter.ofPattern("MMM dd - HH:mm");
        holder.dateTxt.setText(hour_min.format(listOfAttacks.get(listOfAttacks.size() - 1 - position )));
    }

    @Override
    public int getItemCount()
    {
        return listOfAttacks.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView dateTxt;
        public MyViewHolder(final View itemView)
        {
            super(itemView);
            dateTxt = itemView.findViewById(txtId);
        }
    }

    public List<LocalDateTime> filterByDay(List<LocalDateTime> listOfAttacks, int month)
    {
        List<LocalDateTime> filteredList = new ArrayList<LocalDateTime>();
        for(LocalDateTime attack : listOfAttacks)
        {
            if(attack.getMonthValue() == month)
            {
                filteredList.add(attack);
            }
        }
        return filteredList;
    }
}
