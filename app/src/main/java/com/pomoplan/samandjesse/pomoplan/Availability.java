package com.pomoplan.samandjesse.pomoplan;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.text.format.DateFormat;

import java.util.ArrayList;
import java.util.Calendar;

public class Availability extends AppCompatActivity {

    private TextView mTextMessage;
    ArrayList<Integer> hourMinute;
    String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Friday", "Saturday"};
    boolean[] checked = {false, false, false, false, false, false, false};
    String name = "";
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
        setContentView(R.layout.activity_availability);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Button button = (Button) findViewById(R.id.floatingActionButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timeIntervalProcessStart();
            }
        });
    }

    public void timeIntervalProcessStart() {
        //day of week dialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        final EditText input = new EditText(Availability.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialogBuilder.setView(input);
        alertDialogBuilder.setIcon(R.drawable.key);

        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Friday", "Saturday"};
        alertDialogBuilder.setMultiChoiceItems(days, checked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checked[which] = !checked[which];
            }
        });
        alertDialogBuilder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = input.getText().toString();
                timeIntervalProcessChoose();
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = alertDialogBuilder.create();

        dialog.show();
    }

    public void timeIntervalProcessChoose() {
        //start of interval dialog
        final Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);

        hourMinute = new ArrayList<Integer>();

        TimePickerDialog start = new TimePickerDialog(this, timePickerListener, hour, minute, false);
        start.show();

        //end of interval dialog
        TimePickerDialog end = new TimePickerDialog(this, timePickerListener, hour, minute,  false);
        end.show();
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hourMinute.add(hourOfDay);
            hourMinute.add(minute);
            if(hourMinute.size() == 4) {
                Period period = new Period(name);
                for(int i = 0; i < 7; ++i) {
                    if(checked[i]) {
                        period.addTime(i, hourMinute.get(3), hourMinute.get(1), hourMinute.get(2), hourMinute.get(0));
                    }
                }
            }
        }
    };

}
