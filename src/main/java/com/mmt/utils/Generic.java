package com.mmt.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Suhail
 *
 */
public class Generic {

	String currentDate;
	
	/**
	 * The below constructor will generate current date.
	 */
	public Generic() {
		DateFormat df = new SimpleDateFormat("dd/MMM/yyyy"); 
		Date date =new Date();
		currentDate = df.format(date);
	}
	
	/**
	 * This method gets the Current date.
	 * @return
	 */
	public String getCurrentDay() {
		return currentDate.split("/")[0];
	}
	
	/**
	 * This method gets the current month
	 * @return
	 */
	public String getCurrentMonth() {
		return currentDate.split("/")[1];
	}
	
	/**
	 * This method gets the current year
	 * @return
	 */
	public String getCurrentYear() {
		return currentDate.split("/")[2];
	}

	/**
	 * This method calculates the returnDate from departure date
	 * @param departureDate
	 * @return
	 */
	public int getReturnDate(int departureDate) {
		ArrayList<String> Days30Months = new ArrayList<String>();
		Days30Months.add("Apr");
		Days30Months.add("Jun");
		Days30Months.add("Sep");
		Days30Months.add("Nov");
		ArrayList<String> Days31Months = new ArrayList<String>();
		Days31Months.add("Jan");
		Days31Months.add("Mar");
		Days31Months.add("Jul");
		Days31Months.add("Aug");
		Days31Months.add("Oct");
		Days31Months.add("Dec");
		int retDate = departureDate;
		String curMonth = getCurrentMonth();
		if(Days30Months.contains(curMonth)) {
			if(retDate>23) {
				retDate = (retDate+7) % 30;
			}
			else {
				retDate = retDate+7;
			}
		}
		else if(Days31Months.contains(curMonth)) {
			if(retDate>24) {
				retDate = (retDate+7) % 31;
			}
			else {
				retDate = retDate+7;
			}
		}
		else {
			if(retDate>21) {
				retDate = (retDate+7) % 28;
			}
			else {
				retDate = retDate+7;
			}
		}
		return retDate;
	}
}
