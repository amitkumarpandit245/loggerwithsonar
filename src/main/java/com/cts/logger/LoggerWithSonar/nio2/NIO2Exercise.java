package com.cts.logger.LoggerWithSonar.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cts.logger.LoggerWithSonar.dateTimeApi.WorkingDaysExercise;

public class NIO2Exercise {
	static Logger logger=LoggerFactory.getLogger(WorkingDaysExercise.class);
	public static Tablet getTabletDetails(String file)
	{
		logger.debug("Debug Message From getTabletDetails");
		String[] ar=file.split(",");
		Tablet obj=new Tablet(ar[0],ar[1],LocalDate.of(Integer.parseInt(ar[2].split("/")[2]),Integer.parseInt(ar[2].split("/")[1]),Integer.parseInt(ar[2].split("/")[0])),LocalDate.of(Integer.parseInt(ar[3].split("/")[2]),Integer.parseInt(ar[3].split("/")[1]),Integer.parseInt(ar[3].split("/")[0])));
		return obj;
		
	}
	
	  public static Map<String,LocalDate> getExpiredTablets(String filename, String manufacturer)
	  { 
			logger.error("Error Message From getExpiredTablets");
		  Map<String,LocalDate> res=new LinkedHashMap<>();
	  try(Stream<String> data=Files.lines(Paths.get("src/data/",filename))){
		  res=data.map(NIO2Exercise::getTabletDetails)
				  .filter(f -> f.getManufacturer().equalsIgnoreCase(manufacturer)).filter(d-> d.getExpiryDate().isBefore(LocalDate.now()))
				  .collect(Collectors.toMap(Tablet::getTabletName,Tablet::getExpiryDate));
	 
	 } catch(IOException e) {
		 System.out.println("Files not found");
	 
	 } 
	  return res;
	  
	 }
	 
	public static void listAllJavaFiles() {
		logger.error("Error Message from listAllJavaFiles");
		try(Stream<Path> data=Files.walk(Paths.get("src"))){
			data.filter(f -> f.toString().contains(".java"))
			.forEach(System.out::println);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static boolean searchFile(String fileName,String dir) {
		logger.trace("Trace Message From searchFile");
		try(Stream<Path> data=Files.walk(Paths.get(dir))){
			data.filter(f -> f.toString().contains(fileName))
			.forEach(System.out::println);
		}
		catch(IOException e) {
			System.out.println("Files cannot be found");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static void main(String[] args) {
		System.out.println("**************Listing All Java Files*************");
		listAllJavaFiles();
		System.out.println("***********Searching File Inside Directory*********");
		searchFile("hidden.txt","src//file//data//");
		System.out.println("**********Get Expired Tablets*********");
		Map<String,LocalDate> res=getExpiredTablets("tablet_details.txt", "def");
		System.out.println(res);
	}

}