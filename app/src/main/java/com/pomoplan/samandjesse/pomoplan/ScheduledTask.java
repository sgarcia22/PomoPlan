package com.pomoplan.samandjesse.pomoplan;

import java.time.Instant;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ScheduledTask {
	String name;
	OffsetTime start;
	OffsetTime end;
	public ScheduledTask(String name, int hourStart, int hourEnd, int minuteStart, int minuteEnd) {
		this.name = name;
		this.start = OffsetTime.of(hourStart, minuteStart, 0, 0, ZoneId.systemDefault().getRules().getOffset(Instant.now()));
		this.end = OffsetTime.of(hourEnd, minuteEnd, 0, 0, ZoneId.systemDefault().getRules().getOffset(Instant.now()));
	}
	
	public String getStartTimeString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
		return start.format(formatter);
	}
	
	public String getEndTimeString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
		return end.format(formatter);
	}
	
	public String getIntervalString() {
		return this.getStartTimeString() + " - " + this.getEndTimeString();
	}
}
