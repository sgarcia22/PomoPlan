package com.pomoplan.samandjesse.pomoplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.*;
import android.view.WindowManager;
import java.text.DateFormat;
import java.util.Date;
import java.text.*;
import java.util.regex.Pattern;

public class MainNav extends AppCompatActivity {

    private TextView mTextMessage;
    public static ArrayList<UnscheduledTask> listItems;
    private TaskListAdapter adapter;
    private ListView scroll;
    private Spinner spinner;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent1 = new Intent(MainNav.this, MainNav.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent1);
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent2 = new Intent(MainNav.this, ScheduleActivity.class);
                    intent2.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent2);
                    return true;
                case R.id.navigation_notifications:
                    Intent intent3 = new Intent(MainNav.this, PomodoroTimer.class);
                    intent3.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav);

        //Prevent keyboard from popping up at the beginning
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.menu);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Get the list view
        scroll = (ListView) findViewById(R.id.listView);

        listItems = new ArrayList<UnscheduledTask>();

        adapter = new TaskListAdapter(this, R.layout.adapter_view_layout, listItems);
        scroll.setAdapter(adapter);

        //Drop Down Button
        spinner = findViewById(R.id.spinner);

        //Add a task to the list
        Button addTask = (Button) findViewById(R.id.add_task);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTask();
            }
        });
    }

    //Create a task with the given inputs
    private void createTask () {
        String taskName = ((EditText)findViewById(R.id.editText)).getText().toString();
        String time = ((EditText)findViewById(R.id.time)).getText().toString();
        String spinnerText = spinner.getSelectedItem().toString();

        if (taskName.isEmpty() || time.isEmpty() || spinnerText.isEmpty()) {
            //Add task to the list
            return;
        }

        taskToList(taskName, time, spinnerText);
    }

    private void taskToList (String task, String time, String spinnerText) {

        Period p = new Period (spinnerText);
        int hours = 0, minutes = 0;

        String [] totalTime = time.split("[:]");
        System.out.print(totalTime);
        try {
            if (totalTime.length == 1)
                minutes = Integer.parseInt(totalTime[0].trim());
            if (totalTime.length == 2) {
                hours = Integer.parseInt(totalTime[0].trim());
                minutes = Integer.parseInt(totalTime[1].trim());
            }
        } catch (NumberFormatException e) {
            return;
        }
        ArrayList<Period> periods = new ArrayList<Period>();
        periods.add(p);

        UnscheduledTask inputtedTask = new UnscheduledTask(task, periods, hours, minutes);

        listItems.add(inputtedTask);
        adapter.notifyDataSetChanged();

    }
}
