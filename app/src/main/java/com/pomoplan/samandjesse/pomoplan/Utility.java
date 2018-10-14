package com.pomoplan.samandjesse.pomoplan;

import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;

public class Utility {
	public static Integer OffsetTimeToArithmetic(OffsetTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("kmm");
		return Integer.valueOf(time.format(formatter));
	}
}
