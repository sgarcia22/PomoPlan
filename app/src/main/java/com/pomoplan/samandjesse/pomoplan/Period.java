package com.pomoplan.samandjesse.pomoplan;

import java.time.Instant;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Period {
	ArrayList<ArrayList<OffsetTime>> availability; 
	String name; 
	public Period(String name) { //generates empty list with no times
		availability = new ArrayList<ArrayList<OffsetTime>>(); 
		for(int i = 0; i<7; ++i) { //7 days for week Sun->Sat
			availability.add(new ArrayList<OffsetTime>());
		}
		this.name = name;
	}
	
	public void addTime(int day, int hourStart, int hourEnd, int minuteStart, int minuteEnd) {	    
		OffsetTime start = OffsetTime.of(hourStart, minuteStart, 0, 0, ZoneId.systemDefault().getRules().getOffset(Instant.now()));
		OffsetTime end = OffsetTime.of(hourEnd, minuteEnd, 0, 0, ZoneId.systemDefault().getRules().getOffset(Instant.now()));
		
		//Sun = 0, Mon = 1, ... Sat = 6
		availability.get(day).add(start);
		availability.get(day).add(end);

	}
	
	public String getStringFormattedTime(int day) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
		int counter = 1; 
		String returnString = "";
		for(OffsetTime time: availability.get(day)) {
			returnString += time.format(formatter);
			if(counter%2 == 1) {
				returnString += " - ";
			}
			else {
				returnString += "\n";
			}
			++counter;
		}
		return returnString;
	}
	
	public ArrayList<Integer> getArithmeticFormattedTime(int day) {
		ArrayList<Integer> timeSlots = new ArrayList<Integer>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("kmm");
		for(OffsetTime time: availability.get(day)) {
			timeSlots.add(Integer.valueOf(time.format(formatter)));
		}
		return timeSlots;
	}
}
