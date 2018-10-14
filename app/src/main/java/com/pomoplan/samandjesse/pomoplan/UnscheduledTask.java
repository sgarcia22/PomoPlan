package com.pomoplan.samandjesse.pomoplan;


import java.time.Duration;
import java.util.ArrayList;

public class UnscheduledTask {
	public String taskName;
	public ArrayList<Period> periods;  
	public Duration estimatedTime;
	public int hours;
	public int minutes;
	
	public UnscheduledTask(String taskName, ArrayList<Period> periods, int hours, int minutes) {
		this.taskName = taskName;
		this.periods = periods; 
		this.estimatedTime = Duration.ofHours(hours).plus(Duration.ofMinutes(minutes));
		this.hours = hours;
		this.minutes = minutes;
	}

	public String getName() {return  taskName;}

	public String getDurationString() {
		long seconds = estimatedTime.getSeconds();
		String duration = String.format("%d:%02d", seconds/3600, (seconds%3600)/60);
		return duration;
	}

	public int getHours() { return hours;}
	public int getMinutes() {return minutes;}
	public ArrayList<Period> getCat () { return periods;}
}
