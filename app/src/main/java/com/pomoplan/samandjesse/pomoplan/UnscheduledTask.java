package testy;

import java.util.ArrayList;

public class UnscheduledTask {
	public String taskName;
	public ArrayList<Period> periods; //ordered by priority 
	public int estimatedTime;
	
	public UnscheduledTask(String taskName, ArrayList<Period> periods, int hours, int minutes) {
		this.taskName = taskName;
		this.periods = periods; 
		String temp = Integer.toString(hours) + Integer.toString(minutes);
		this.estimatedTime = Integer.parseInt(temp); //format hm [hours,minutes]
	}
}
