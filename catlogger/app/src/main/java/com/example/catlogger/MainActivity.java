package com.example.catlogger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int counter = 0;
    static List<LocalDateTime> listOfAttacks;
    static List<Log> listOfLogs;
    Button plusButton;
    Button minusButton;
    Button listScreenButton;
    TextView counterTxt;
    TextView timeTxt;
    RecyclerView recyclerViewMain;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plusButton = (Button)findViewById(R.id.plusButton);
        minusButton = (Button)findViewById(R.id.minusButton);
        counterTxt = (TextView)findViewById(R.id.counterTxt);
        recyclerViewMain = (RecyclerView)findViewById(R.id.recyclerMain);
        timeTxt = (TextView)findViewById(R.id.timeTxt);
        listScreenButton = (Button)findViewById(R.id.toListBtn);
        listOfLogs = new ArrayList<Log>();
        DateTimeFormatter month_day = DateTimeFormatter.ofPattern("MMM dd");
        DateTimeFormatter hour_min = DateTimeFormatter.ofPattern("MMM dd - HH:mm");
        DateTimeFormatter month_day_hour_min = DateTimeFormatter.ofPattern("HH:mm");
        setAdapterMain();


        plusButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                LocalDateTime time = LocalDateTime.now();
                Log log = new Log(time);
                listOfLogs.add(log);
                counter++;
                counterTxt.setText(Integer.toString(counter));
                if(!listOfLogs.isEmpty())
                {
                    String str = listOfLogs.get(listOfLogs.size() - 1).getTime().format(hour_min);
                    System.out.println(str);
                    timeTxt.setText("Last attack at:" + "\n" + str);
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    timeTxt.setText("No attacks recorded");
                }
                // System.out.println(listOfAttacks.size());
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(counter > 0)
                {
                    counter--;

                    if(!listOfLogs.isEmpty())
                    {
                        // delete last element in listOfAttacks list and update last attack text.
                        listOfLogs.remove(listOfLogs.size() - 1);
                        adapter.notifyDataSetChanged();
                    }
                    // if after removing the last date list is not empty, then update the text.
                    if(!listOfLogs.isEmpty())
                    {
                        String str = listOfLogs.get(listOfLogs.size() - 1).getTime().format(hour_min);
                        System.out.println(str);
                        timeTxt.setText("Last attack at:" + "\n" + str);
                    }
                    else
                    {
                        timeTxt.setText("No attacks recorded");
                    }
                    counterTxt.setText(Integer.toString(counter));
                }
                //System.out.println(listOfAttacks.size());

            }
        });

        listScreenButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, LogListActivity.class));
            }
        });




    }
    private void setAdapterMain()
    {
        adapter = new MyAdapter(this, listOfLogs, R.layout.item_list_main, R.id.itemTxtMain);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewMain.setLayoutManager(layoutManager);
        recyclerViewMain.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMain.setAdapter(adapter);
    }

}