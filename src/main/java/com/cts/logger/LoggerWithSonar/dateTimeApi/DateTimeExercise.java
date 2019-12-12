package com.cts.logger.LoggerWithSonar.dateTimeApi;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTimeExercise {
	static Logger logger=LoggerFactory.getLogger(WorkingDaysExercise.class);
	static List<Tablet> tablets=new ArrayList<>(Arrays.asList(
			new Tablet("Dizine","abc",LocalDate.of(2019, 04, 23),LocalDate.of(2019, 11, 21)),
			new Tablet("Bikosule","def",LocalDate.of(2019, 02, 23),LocalDate.of(2019, 05, 20)),
			new Tablet("Pantids","ghi",LocalDate.of(2019, 04, 23),LocalDate.of(2019, 05, 23)),
			new Tablet("Vicks","jkl",LocalDate.of(2019, 03, 23),LocalDate.of(2020, 12, 24))
			));
	public static List<String> getExpiringTables(int months){
		logger.error("Error Message From getExpiringTables");
		List<String> result=tablets.stream().filter(f -> f.getExpiryDate().isBefore(LocalDate.now().plusMonths(months))).map(Tablet::getTablet_name).collect(Collectors.toList());
		return result;
	}
	public static List<String> getExpiringTabletsSorted(){
		logger.trace("Trace Message From getExpiringTabletsSorted Method");
		List<String> result=tablets.stream().sorted((a,b)-> a.getExpiryDate().compareTo(b.getExpiryDate())).map(Tablet::getTablet_name).collect(Collectors.toList());
		return result;
	}
	public static Map<String, String>getTabletExpiryPeriod(){
		logger.warn("Warning Message From getTabletExpiryPeriod");
		Map<String, String> result=tablets.stream().collect(
				Collectors.toMap(Tablet::getTablet_name, v -> (Period.between(v.getManufactureDate(),v.getExpiryDate())).getYears()
				+" year/s "+(Period.between(v.getManufactureDate(),v.getExpiryDate())).getMonths()
				+" month/s "+(Period.between(v.getManufactureDate(),v.getExpiryDate())).getDays()+" day/s "));
		
		return result;
	}
	public static List<String> getTablets(Tablet t){
		logger.info("Information Message from getTablets Method");
		List<String> tablet=new ArrayList<>();
		tablet.add(t.getTablet_name());
		return tablet;
	}
	public static Map<String,List<String>> getSameYearExpiry(){
		logger.debug("Debug Message From getSameYearExpiry Method");
		Map<String, List<String>> result=tablets
				.stream()
				.filter(f ->(f.getManufactureDate().getYear())==(LocalDate.now().getYear()))
				.filter(s -> s.getExpiryDate().isBefore(LocalDate.now()))
				.collect(Collectors.toMap(Tablet::getManufacturer, DateTimeExercise::getTablets));
		return result;
	}
	public static void main(String[] args) {
		System.out.println("***************************Get Expiring Tablets********************");
		System.out.println(getExpiringTables(2));
		System.out.println("***************************Get Sorted Expiring Tablets based on Expiry Date*********************");
		System.out.println(getExpiringTabletsSorted());
		System.out.println("***************************Get Tablet Expiry Period********************");
		System.out.println(getTabletExpiryPeriod());
		System.out.println("***************************Same Year Expiry*********************");
		System.out.println(getSameYearExpiry());
	}

}
