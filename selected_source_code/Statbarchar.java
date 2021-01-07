package com.rough.tuber.tuber;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static com.rough.tuber.tuber.Statistic.first;
import static com.rough.tuber.tuber.Statistic.five;
import static com.rough.tuber.tuber.Statistic.fourth;
import static com.rough.tuber.tuber.Statistic.second;
import static com.rough.tuber.tuber.Statistic.third;
//import com.xxmassdeveloper.mpchartexample.notimportant.DemoBase;

public class Statbarchar extends AppCompatActivity {

    BarChart barChart;
    ArrayList<String> dates;
    ArrayList<BarEntry> barEntries;
    int year1counter;
    int year2counter;
    int year3counter;
    int year4counter;
    int year5counter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statbarchar);
        barChart = (BarChart) findViewById(R.id.bargraph);

        createBarGraph("Date1","Date2");

       // Bundle bundle = IntentBundle.getExtras();
        Intent intentExtras = getIntent();
      /*Bundle extrasBundle = intentExtras.getExtras();
        if(!extrasBundle.isEmpty())
            {

            }
        */


    }


    public void createBarGraph(String Date1, String Date2){

        int year1counter = Integer.parseInt(first);
        int year2counter = Integer.parseInt(second);
        int year3counter = Integer.parseInt(third);
        int year4counter = Integer.parseInt(fourth);
        int year5counter = Integer.parseInt(five);

        BarChart barChart = (BarChart) findViewById(R.id.bargraph);

        ArrayList<BarEntry> entries = new ArrayList<>();


        entries.add(new BarEntry(2015, year1counter));
        entries.add(new BarEntry(2016, year2counter));
        entries.add(new BarEntry(2017, year3counter));
        entries.add(new BarEntry(2018, year4counter));
        entries.add(new BarEntry(2019, year5counter));

        BarDataSet bardataset = new BarDataSet(entries, "Appointments");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("2014");
        labels.add("2015");
        labels.add("2016");
        labels.add("2017");
        labels.add("2018");
        labels.add("2019");


     BarData data = new BarData(bardataset);
     barChart.setData(data); // set the data and list of lables into chart
     data.setBarWidth(0.9f);
     barChart.setFitBars(true);


        //barChart.getDescription(Number_of_Appiontments_per_year);  // set the description
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

        barChart.animateY(5000);

    }

}