package com.pomoplan.samandjesse.pomoplan;


import java.time.Duration;
import java.util.ArrayList;

public class UnscheduledTask {
	public String taskName;
	public ArrayList<Period> periods;  
	public Duration estimatedTime;
	
	public UnscheduledTask(String taskName, ArrayList<Period> periods, int hours, int minutes) {
		this.taskName = taskName;
		this.periods = periods; 
		this.estimatedTime = Duration.ofHours(hours).plus(Duration.ofMinutes(minutes));
		
	}
}
