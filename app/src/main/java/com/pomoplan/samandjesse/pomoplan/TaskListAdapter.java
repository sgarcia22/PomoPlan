package com.pomoplan.samandjesse.pomoplan;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TaskListAdapter extends ArrayAdapter<UnscheduledTask> {

    private Context currContext;
    private int currResource;
    public TaskListAdapter(Context context, int resource, List<UnscheduledTask> objects) {
        super(context, resource, objects);
        currContext = context;
        currResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name = getItem(position).getName();
        int hours = getItem(position).getHours();
        String hoursText = Integer.toString(hours);
        int minutes = getItem(position).getMinutes();
        String minutesText = Integer.toString(minutes);
        Period period = getItem(position).getCat().get(0);
        String periodText = period.name;
        String totaltime = hoursText + ":" + minutesText;

        LayoutInflater inflator = LayoutInflater.from(currContext);
        convertView = inflator.inflate(currResource, parent, false);

        TextView taskName = (TextView) convertView.findViewById(R.id.task_name);
        TextView taskTime = (TextView) convertView.findViewById(R.id.task_time);
        TextView taskPeriod = (TextView) convertView.findViewById(R.id.task_period);

        taskName.setText(name);
        taskTime.setText(totaltime);
        taskPeriod.setText(periodText);

        return convertView;
    }
}
