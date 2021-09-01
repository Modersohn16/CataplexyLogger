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
        listOfAttacks = new ArrayList<LocalDateTime>();
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
                listOfAttacks.add(time);
                counter++;
                counterTxt.setText(Integer.toString(counter));
                if(!listOfAttacks.isEmpty())
                {
                    timeTxt.setText("Last attack at:" + "\n" + hour_min.format(listOfAttacks.get(listOfAttacks.size() - 1)));
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

                    if(!listOfAttacks.isEmpty())
                    {
                        // delete last element in listOfAttacks list and update last attack text.
                        listOfAttacks.remove(listOfAttacks.size() - 1);
                        adapter.notifyDataSetChanged();
                    }
                    // if after removing the last date list is not empty, then update the text.
                    if(!listOfAttacks.isEmpty())
                    {
                        timeTxt.setText("Last attack at:" + "\n" + hour_min.format(listOfAttacks.get(listOfAttacks.size() - 1)));
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
        adapter = new MyAdapter(this, listOfAttacks, R.layout.item_list_main, R.id.itemTxtMain);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewMain.setLayoutManager(layoutManager);
        recyclerViewMain.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMain.setAdapter(adapter);
    }

}