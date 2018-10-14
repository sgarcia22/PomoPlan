package com.pomoplan.samandjesse.pomoplan;

import java.util.*;

import java.time.Duration;
import java.time.OffsetTime;

public class Schedule {
	public static ArrayList<ScheduledTask> scheduleTasks(ArrayList<UnscheduledTask> toBeScheduled, int day) {
		ArrayList<ScheduledTask> returnTasks = new ArrayList<ScheduledTask>();
		ArrayList<PriorityQueue<ArrayList<Integer>>> taskIntervals = new ArrayList<PriorityQueue<ArrayList<Integer>>>();
		for(UnscheduledTask task: toBeScheduled) {
			taskIntervals.add(generateSortedIntervals(task, day));
		}

		//Charging argument greedy solution
		
		Set<Integer> unscheduledTasks = new HashSet<Integer>();
		
		for(int i = 0; i < toBeScheduled.size(); ++i) {
			unscheduledTasks.add(i);
		}
		
		//find earliest finishing task
		while(!unscheduledTasks.isEmpty()) {
			int smallestStart = Integer.MIN_VALUE;
			int smallest = Integer.MAX_VALUE;
			int smallIndex = -1;
			
			//remove tasks that cannot be scheduled at all
			ArrayList<Integer> toBeRemoved = new ArrayList<Integer>();
			for(int i = 0; i < unscheduledTasks.size(); ++i) {
				if(taskIntervals.get(i).isEmpty())
					toBeRemoved.add(i);
			}
			
			for(int index: toBeRemoved)
				unscheduledTasks.remove(index);
			
			for(int index: unscheduledTasks) { //for every unscheduled task
				if(taskIntervals.get(index).isEmpty()) {
					System.err.println("Something bad happened and an empty interval snuck into scheduler!");
				}
				else {
					int observedValue = taskIntervals.get(index).peek().get(1);
					if(observedValue < smallest) { //find task that finishes earliest 
						smallestStart = taskIntervals.get(index).peek().get(0);
						smallest = observedValue;
						smallIndex = index;
					}
				}
			}
			unscheduledTasks.remove(smallIndex);
			
			for(int index: unscheduledTasks) { //for every unscheduled task
				while(smallestStart >= taskIntervals.get(index).peek().get(0) && smallest <= taskIntervals.get(index).peek().get(1) ) { //overlapping interval
					if(taskIntervals.get(index).poll() == null) { //if we empty priority queue
						unscheduledTasks.remove(index);
						break;
					}
					if(taskIntervals.get(index).isEmpty()) {
						unscheduledTasks.remove(index);
						break;
					}
				}
			}
			if(smallIndex != -1)
				returnTasks.add(new ScheduledTask(toBeScheduled.get(smallIndex).taskName, (smallestStart - smallestStart%100)/100, (smallest - smallest%100)/100, smallestStart%100 ,smallest%100 ));
		}
		
		return returnTasks;
	}
	
	public static PriorityQueue<ArrayList<Integer>> generateSortedIntervals(UnscheduledTask task, int day) {
		Comparator<ArrayList<Integer>> comparator = new TupleComparator();
		PriorityQueue<ArrayList<Integer>> intervals = new PriorityQueue<ArrayList<Integer>>(comparator);
		Duration time = task.estimatedTime;
		if(time.toMinutes() > 25) {
			time = time.plus(Duration.ofMinutes((long)(5*Math.floor(time.toMinutes()/25))));
		}
		
		for(Period period: task.periods) {
			ArrayList<OffsetTime> times = period.availability.get(day);
			
			for(int i = 0; i < times.size(); i+=2) {
				for(OffsetTime j = times.get(i); Utility.OffsetTimeToArithmetic(j.plusMinutes(time.toMinutes())) <= Utility.OffsetTimeToArithmetic(times.get(i+1)); j = j.plusMinutes(1)) {
					ArrayList<Integer> interval = new ArrayList<Integer>();
					interval.add(Utility.OffsetTimeToArithmetic(j));
					interval.add(Utility.OffsetTimeToArithmetic(j.plusMinutes(time.toMinutes())));
					intervals.add(interval);
				}
			}
		}
		return intervals;
	}
	
	public static ArrayList<String> scheduleOutput(ArrayList<ScheduledTask> scheduledTasks) {
		ArrayList<String> schedule = new ArrayList<String>();
		for(ScheduledTask task: scheduledTasks) {
			StringBuilder stringBuilder = new StringBuilder(task.name);
			stringBuilder.append(": (");
			stringBuilder.append(task.getStartTimeString());
			stringBuilder.append(" - ");
			stringBuilder.append(task.getEndTimeString());
			stringBuilder.append(")");
			schedule.add(stringBuilder.toString());
		}
		return schedule;
	}
}

