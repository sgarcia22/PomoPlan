package com.pomoplan.samandjesse.pomoplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View;
import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Spinner spinner;
    public ArrayList<ScheduledTask> schedule;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent1 = new Intent(ScheduleActivity.this, MainNav.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent1);
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent2 = new Intent(ScheduleActivity.this, ScheduleActivity.class);
                    intent2.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent2);
                    return true;
                case R.id.navigation_notifications:
                    Intent intent3 = new Intent(ScheduleActivity.this, PomodoroTimer.class);
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
        setContentView(R.layout.activity_schedule);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //Drop Down Button
        spinner = findViewById(R.id.spinner2);

        //Add a task to the list
        Button addTask = (Button) findViewById(R.id.add_task);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTimes();
            }
        });
    }

    //Add a free time period to the user's schedule
    private void addTimes() {
        String startTime = ((TextView)findViewById(R.id.start_time)).getText().toString();
        String endTime = ((TextView)findViewById(R.id.end_time)).getText().toString();
        String spinnerText = spinner.getSelectedItem().toString();

        int startHours = 0, startMinutes = 0, endHours = 0, endMinutes = 0;
        String [] startTotalTime = startTime.split("[:]");
        String [] endTotalTime = startTime.split("[:]");
        try {
            if (startTotalTime.length == 1)
                startMinutes = Integer.parseInt(startTotalTime[0].trim());
            if (startTotalTime.length == 2) {
                startHours = Integer.parseInt(startTotalTime[0].trim());
                startMinutes = Integer.parseInt(startTotalTime[1].trim());
            }
            if (endTotalTime.length == 1)
                endMinutes = Integer.parseInt(endTotalTime[0].trim());
            if (endTotalTime.length == 2) {
                endHours = Integer.parseInt(endTotalTime[0].trim());
                endMinutes = Integer.parseInt(endTotalTime[1].trim());
            }
        } catch (NumberFormatException e) {
            return;
        }

        //Add time to periods
        for(UnscheduledTask task: MainNav.listItems) {
            task.periods.get(0).addTime(0, startHours, endHours, startMinutes, endMinutes);
        }
        schedule = Schedule.scheduleTasks(MainNav.listItems, 0);

    }

}
