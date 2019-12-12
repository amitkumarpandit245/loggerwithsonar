package com.cts.logger.LoggerWithSonar;

import java.time.Period;

import com.cts.logger.LoggerWithSonar.dateTimeApi.BankDepositExercise;
import com.cts.logger.LoggerWithSonar.dateTimeApi.DateTimeExercise;
import com.cts.logger.LoggerWithSonar.dateTimeApi.WorkingDaysExercise;
import com.cts.logger.LoggerWithSonar.nio2.NIO2Exercise;

public class DemoLoggerWithSonar {
	public static void main(String[] args) {
		System.out.println(WorkingDaysExercise.getNextMonthsWorkingDays());

		System.out.println(DateTimeExercise.getExpiringTables(2));

		System.out.println(DateTimeExercise.getExpiringTabletsSorted());

		System.out.println(DateTimeExercise.getTabletExpiryPeriod());

		System.out.println(DateTimeExercise.getSameYearExpiry());

		System.out.println(BankDepositExercise.getMaturityDate("11/01/2018", Period.of(1, 1, 1)));

		System.out.println(BankDepositExercise.getInvestmentPeriod("11/10/2019", "12/11/2020"));
		NIO2Exercise.listAllJavaFiles();

		System.out.println(NIO2Exercise.searchFile("hidden.txt", "src\\file\\data\\"));

		System.out.println(NIO2Exercise.getExpiredTablets("tablet_details.txt", "def"));

	}
}
