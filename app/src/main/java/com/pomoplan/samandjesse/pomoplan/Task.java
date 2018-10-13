package com.pomoplan.samandjesse.pomoplan;

public class Task {

    private String taskName;
    private int taskLengthHours;
    private int taskLengthMinutes;
    private Periods category;

    public Task (String name, int hours, int minutes, Periods cat) {
        taskName = name;
        taskLengthHours = hours;
        taskLengthMinutes = minutes;
        category = cat;
    }

    public String getName () {
        return taskName;
    }

    public int getTaskLengthHours () {
        return taskLengthHours;
    }

    public int getTaskLengthMinutes () {
        return taskLengthMinutes;
    }

    public Periods getCat () {
        return category;
    }

}
