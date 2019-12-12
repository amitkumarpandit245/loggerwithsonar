package com.cts.logger.LoggerWithSonar.dateTimeApi;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkingDaysExercise {
	static Logger logger=LoggerFactory.getLogger(WorkingDaysExercise.class);
	public static List<String> getNextMonthsWorkingDays(){
		logger.info("Inside getNextMonthsWorkingDays Method");
		long days=LocalDate.now().plusMonths(1).lengthOfMonth();
		List<LocalDate> list=IntStream.iterate(0, i->i+1).mapToObj(i->(LocalDate.now().plusMonths(1).withDayOfMonth(1)).plusDays(i)).limit(days)
				.filter(d->!d.getDayOfWeek().equals(d.getDayOfWeek().SATURDAY)).filter(d-> !d.getDayOfWeek().equals(d.getDayOfWeek().SUNDAY))
				.collect(Collectors.toList());
	
		List<String> result=new ArrayList<>();
		for (LocalDate localDate : list) {
			String fdate=localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			result.add(fdate);
		}
		return result;
	}
	public static void main(String[] args) {
		logger.debug("Printing  getNextMonthsWorkingDays Method Output");
		//System.out.println("**************************Get Next Month Working days*****************");
		System.out.println(getNextMonthsWorkingDays());
	}
	
}
