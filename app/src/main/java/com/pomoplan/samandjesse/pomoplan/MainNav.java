package com.pomoplan.samandjesse.pomoplan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.*;
import android.view.WindowManager;
import android.widget.Toast;

public class MainNav extends AppCompatActivity {

    private TextView mTextMessage;
    private ArrayList<String> listItems = new ArrayList<String>();
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

        //Get the list view for the items list
        scroll = (ListView) findViewById(R.id.list_items);

        //Handle items in the list
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listItems);
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

        taskToList(taskName);
    }

    private void taskToList (String task) {
        listItems.add(task);
        adapter.notifyDataSetChanged();
    }

}
