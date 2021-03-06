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
    List<Log> listOfAttacks;
    int list_itemsId;
    int txtId;

    public MyAdapter(Context ct, List<Log> listOfAttacks, int list_itemsId, int txtId)
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
        holder.dateTxt.setText(listOfAttacks.get(listOfAttacks.size() - 1 - position).getTime().format(hour_min));
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

    public List<Log> filterByDay(List<Log> listOfAttacks, int month)
    {
        List<Log> filteredList = new ArrayList<Log>();
        for(Log attack : listOfAttacks)
        {
            if(attack.getTime().getMonthValue() == month)
            {
                filteredList.add(attack);
            }
        }
        return filteredList;
    }
}
