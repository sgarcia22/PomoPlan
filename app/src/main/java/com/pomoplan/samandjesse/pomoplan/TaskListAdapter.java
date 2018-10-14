package com.pomoplan.samandjesse.pomoplan;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class TaskListAdapter extends ArrayAdapter<Task> {

    public TaskListAdapter(@androidx.annotation.NonNull Context context, int resource, @androidx.annotation.NonNull List<Task> objects) {
        super(context, resource, objects);
    }

    @androidx.annotation.NonNull
    @Override
    public View getView(int position, @androidx.annotation.Nullable View convertView, @androidx.annotation.NonNull ViewGroup parent) {

    }
}
