package testy;

import java.time.Instant;
import java.time.OffsetTime;
import java.time.ZoneId;

public class ScheduledTask {
	OffsetTime start;
	OffsetTime end;
	public ScheduledTask(int hourStart, int hourEnd, int minuteStart, int minuteEnd) {
		this.start = OffsetTime.of(hourStart, minuteStart, 0, 0, ZoneId.systemDefault().getRules().getOffset(Instant.now()));
		this.end = OffsetTime.of(hourEnd, minuteEnd, 0, 0, ZoneId.systemDefault().getRules().getOffset(Instant.now()));
	}
}
