package com.pomoplan.samandjesse.pomoplan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.*;
import android.view.WindowManager;
import java.text.DateFormat;
import java.util.Date;
import java.text.*;

public class MainNav extends AppCompatActivity {

    private TextView mTextMessage;
    private ArrayList<Task> listItems = new ArrayList<Task>();
    private ArrayAdapter<String> adapter;
    private ListView scroll;
    private Spinner spinner;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
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

        //TODO
        TaskListAdapter adapter = new TaskListAdapter(this, R.layout.adapter_view_layout, listItems);
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
        String taskName = findViewById(R.id.editText).toString();
        String time = findViewById(R.id.time).toString();
        String spinnerText = spinner.getSelectedItem().toString();

        if (taskName.isEmpty() || time.isEmpty() || spinnerText.isEmpty()) {
            //Add task to the list
            return;
        }

        taskToList(taskName, time, spinnerText);
    }

    private void taskToList (String task, String time, String spinnerText) {
        DateFormat formatter = new SimpleDateFormat("hh:mm a");
        Date date;
        try {
            date = formatter.parse(time);
        } catch (ParseException e) {
            return;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Period p = new Period (spinnerText);

        Task inputtedTask = new Task(task, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), p);
        listItems.add(inputtedTask);
        adapter.notifyDataSetChanged();
    }
}
