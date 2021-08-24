package com.example.cataplexylogger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class LogList extends AppCompatActivity {

    Button toMainButton;
    RecyclerView recyclerView;
    List<LocalDateTime> listOfAttacks;
    List<LocalDateTime> filteredListOfAttacks;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_list);
        toMainButton = (Button)findViewById(R.id.toMainButton);
        listOfAttacks = MainActivity.listOfAttacks;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        setListOfAttacksDummy(listOfAttacks);
        filteredListOfAttacks = filterByMonth(listOfAttacks,7);
        setAdapter();
        toMainButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(LogList.this, MainActivity.class));
            }
        });
    }

    private void setAdapter()
    {

        adapter = new MyAdapter(this, filteredListOfAttacks, R.layout.list_items, R.id.dateTxtId);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setListOfAttacksDummy(List <LocalDateTime> listOfAttacks)
    {
        List <String> strList = new ArrayList<>();
        Collections.addAll(strList,
                "2019-04-08 12:30",
                "2020-04-08 12:30",
                "2020-04-08 13:30",
                "2020-04-08 14:30",
                "2020-04-08 15:30",
                "2020-04-10 10:30",
                "2020-04-10 11:32",
                "2020-04-10 12:30",
                "2020-06-08 12:30",
                "2020-06-10 12:30",
                "2020-06-10 12:30",
                "2020-06-10 12:30",
                "2020-06-10 12:30",
                "2020-06-16 20:30",
                "2021-01-08 12:30",
                "2021-01-08 12:30",
                "2021-01-08 12:30",
                "2021-01-08 12:30",
                "2021-01-08 12:30",
                "2021-02-08 12:30",
                "2021-02-08 12:30",
                "2021-02-08 12:30",
                "2021-02-08 12:30",
                "2021-02-08 12:30",
                "2021-03-11 22:30",
                "2021-03-11 23:30",
                "2021-03-11 20:30",
                "2021-04-18 12:30",
                "2021-04-18 12:31",
                "2021-04-18 12:32",
                "2021-04-18 12:33",
                "2021-05-28 12:34",
                "2021-05-28 12:30",
                "2021-05-28 12:30",
                "2021-05-28 12:30",
                "2021-05-29 12:30",
                "2021-06-16 16:16",
                "2021-06-16 16:30",
                "2021-06-16 16:30",
                "2021-06-16 16:30",
                "2021-06-18 16:30",
                "2021-06-18 16:30",
                "2021-06-19 06:30",
                "2021-06-19 07:30",
                "2021-06-19 08:30",
                "2021-06-19 09:30",
                "2021-06-19 10:30",
                "2021-06-19 11:30",
                "2021-06-20 12:30",
                "2021-07-08 12:30",
                "2021-07-08 12:30",
                "2021-07-08 12:30",
                "2021-07-08 12:30",
                "2021-07-08 12:30",
                "2021-07-09 12:30",
                "2021-07-09 12:30",
                "2021-07-09 12:30",
                "2021-07-09 12:30",
                "2021-07-09 12:30",
                "2021-07-28 12:30",
                "2021-07-28 12:30",
                "2021-07-28 12:30",
                "2021-07-28 12:30",
                "2021-07-28 12:30",
                "2021-07-28 12:30",
                "2021-07-28 12:30",
                "2021-07-28 12:30",
                "2021-07-28 12:30",
                "2021-07-28 12:30",
                "2021-07-28 12:30",
                "2021-07-28 12:30",
                "2021-07-28 12:30",
                "2021-07-28 12:30",
                "2021-08-28 12:30",
                "2021-08-08 12:30",
                "2021-08-08 12:30",
                "2021-08-11 01:30",
                "2021-08-11 02:30",
                "2021-08-11 03:30",
                "2021-08-11 04:30",
                "2021-08-16 05:30",
                "2021-08-16 06:30",
                "2021-08-16 07:30",
                "2021-08-17 08:30",
                "2021-08-18 09:30",
                "2021-08-18 11:30",
                "2021-08-18 12:30",
                "2021-08-18 13:30"

                );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for(String str : strList)
        {
            LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
            listOfAttacks.add(dateTime);
        }
    }

    public List<LocalDateTime> filterByMonth(List<LocalDateTime> listOfAttacks, int month)
    {
        List<LocalDateTime> filteredList = new ArrayList<LocalDateTime>();
        for(LocalDateTime attack : listOfAttacks)
        {
            System.out.println("inside loop");
            if(attack.getMonthValue() == month && attack.getYear() == 2021)
            {
                filteredList.add(attack);
                System.out.println("inside if");
            }
        }
        return filteredList;
    }

}