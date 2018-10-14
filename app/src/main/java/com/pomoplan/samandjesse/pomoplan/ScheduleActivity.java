package com.pomoplan.samandjesse.pomoplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class ScheduleActivity extends AppCompatActivity {

    private TextView mTextMessage;

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
                    Intent intent3 = new Intent(ScheduleActivity.this, MainNav.class);
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
    }

}
