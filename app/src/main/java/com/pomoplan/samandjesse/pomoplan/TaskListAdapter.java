package com.pomoplan.samandjesse.pomoplan;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TaskListAdapter extends ArrayAdapter<Task> {

    private Context currContext;
    private int currResource;
    public TaskListAdapter(@androidx.annotation.NonNull Context context, int resource, @androidx.annotation.NonNull List<Task> objects) {
        super(context, resource, objects);
        currContext = context;
        currResource = resource;
    }

    @androidx.annotation.NonNull
    @Override
    public View getView(int position, @androidx.annotation.Nullable View convertView, @androidx.annotation.NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        int hours = getItem(position).getTaskLengthHours();
        String hoursText = Integer.toString(hours);
        int minutes = getItem(position).getTaskLengthMinutes();
        String minutesText = Integer.toString(minutes);
        Period period = getItem(position).getCat();
        String periodText = period.name;
        String totaltime = hoursText + ":" + minutesText;

        Task newTask = new Task(name, hours, minutes, period);
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
