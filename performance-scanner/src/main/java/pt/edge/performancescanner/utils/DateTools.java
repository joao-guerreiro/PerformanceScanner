package pt.edge.performancescanner.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTools {
	private static final long ONE_WEEK = 604800000;

	public static String getCurrentWeek() {
		Date currentDate = new Date();
		return parseDateToString(getWeekStart(currentDate)) + " - " + parseDateToString(getWeekEnd(currentDate));
	}

	public static String previousWeek(String reportWeek) {
		String[] currentWeekArray = reportWeek.split(" - ");
		String newReportWeekFirstDay = changeWeekDay(currentWeekArray[0], "SUB");
		String newReportWeekLastDay = changeWeekDay(currentWeekArray[1], "SUB");
		String newReportWeek = newReportWeekFirstDay + " - " + newReportWeekLastDay;
		return newReportWeek;
	}

	public static String nextWeek(String reportWeek) {
		String[] currentWeekArray = reportWeek.split(" - ");
		String newReportWeekFirstDay = changeWeekDay(currentWeekArray[0], "ADD");
		String newReportWeekLastDay = changeWeekDay(currentWeekArray[1], "ADD");
		String newReportWeek = newReportWeekFirstDay + " - " + newReportWeekLastDay;
		return newReportWeek;
	}

	private static String changeWeekDay(String currentWeekDay, String changeType) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String newWeekDay = null;
		try {
			Date date = dateFormat.parse(currentWeekDay);
			if (changeType == "SUB") {
				date.setTime(date.getTime() - ONE_WEEK);
			} else if (changeType == "ADD") {
				date.setTime(date.getTime() + ONE_WEEK);
			}
			newWeekDay = dateFormat.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newWeekDay;
	}

	public static String parseDateToString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}

	public static Date getWeekStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			calendar.add(Calendar.DATE, -1);
		}
		return calendar.getTime();
	}

	public static Date getWeekEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			calendar.add(Calendar.DATE, 1);
		}

		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	public static boolean isCurrentWeek(String date) {
		String[] dateParts = date.split(" - ");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date newDate = null;
		try {
		 newDate = dateFormat.parse(dateParts[0]);	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dateFormat = new SimpleDateFormat("YYYYww");
		Date currentDate = new Date();

		String currentWeek = dateFormat.format(currentDate);
		String newWeek = null;
		newWeek = dateFormat.format(newDate);
		return currentWeek == newWeek;
	}
}
