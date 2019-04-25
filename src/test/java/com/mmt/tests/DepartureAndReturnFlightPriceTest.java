package com.mmt.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mmt.base.TestBase;
import com.mmt.pages.FlightSearchPage;
import com.mmt.pages.FlightsResultsPage;
import com.mmt.utils.Generic;

/**
 * @author Suhail
 *
 */
public class DepartureAndReturnFlightPriceTest extends TestBase {

	FlightSearchPage flightSearchPage;
	Generic generic;
	int departureDate;
	int returnDate;
	int totalFp;
	String onwFlightPrice;
	String rtnFlightPrice;
	String owflightpriceSummary;
	String rtflightpriceSummary;
	String totalFpSummary;
	String dprtNum = "4"; 
	String retNum = "5";
	FlightsResultsPage flightsResultsPage;
	
	/**
	 * This method is used for all the activities needed before starting any tests.
	 */
	@BeforeClass
	public void setUp() {
		generic = new Generic();
		departureDate = Integer.parseInt(generic.getCurrentDay())+1;
		returnDate = generic.getReturnDate(departureDate);
		flightSearchPage = new FlightSearchPage(driver);
		flightsResultsPage = new FlightsResultsPage(driver);
	}
	
	
	/**
	 * This test is used to search the flight based on location and date.
	 * @throws InterruptedException
	 */
	@Test
	public void searchFlight() throws InterruptedException {
		
		//Click on flights.
		Boolean isClickedOnFlightsLink = flightSearchPage.clickOnFlightsLink();
		Assert.assertTrue(isClickedOnFlightsLink,"Cannot click on Flights link");
		logger.info("Clicked on Flights");
		
		//Click on Round trip.
		Boolean isClickedOnRoundtrip = flightSearchPage.clickOnRoundTrip();
		Assert.assertTrue(isClickedOnRoundtrip,"Cannot click on Round trip");
		logger.info("Clicked on RoundTrip");
		
		//Select From City.
		Boolean isFromCitySelected = flightSearchPage.selectFromCity("Delhi");
		Assert.assertTrue(isFromCitySelected,"Cannot select From city");
		logger.info("Selected From City");
		
		//Select To City.
		Boolean isToCitySelected =flightSearchPage.selectToCity("Bangalore");
		Assert.assertTrue(isToCitySelected,"Cannot select To city");
		logger.info("Selected To City");
		
		//Select Departure date.
		Boolean isDprtDateselected = flightSearchPage.selectDepartureDate(departureDate);
		Assert.assertTrue(isDprtDateselected,"Cannot select Departure date");
		logger.info("Selected Departure Date");
		
		//Select Return date.
		Boolean isReturndateselected = flightSearchPage.selectReturnDate(returnDate);
		Assert.assertTrue(isReturndateselected,"Cannot select Departure date");
		logger.info("Selected Return Date");
		
		//Click on Search button.
		Boolean isSearchBtnClicked = flightSearchPage.clickOnSearch();
		Assert.assertTrue(isSearchBtnClicked,"Cannot select Departure date");
		logger.info("Clicked on Search Button");
		
		Thread.sleep(5000);
	}
	/**
	 * This method prints the flight prices
	 * @throws InterruptedException
	 */
	@Test(dependsOnMethods= {"searchFlight"})
	public void flightPriceTest() throws InterruptedException {
		
		//Selects the departure and return flights
		flightsResultsPage.selectDrptAndReturnFlight(dprtNum,retNum);	
		
		//Gets the price of onward journey
		onwFlightPrice = flightsResultsPage.getPriceofOnwardJourney(dprtNum).split(" ")[1].replace(",", "");	
		logger.info("Onward flight price : "+onwFlightPrice);
		
		//Gets the flight price of return journey
		rtnFlightPrice = flightsResultsPage.getPriceofreturnJourney(retNum).split(" ")[1].replace(",", "");		
		logger.info("return flight price : "+rtnFlightPrice);
	}

	/**
	 * This method verifies the departure and return flight ticket prices from summary
	 */
	@Test(dependsOnMethods= {"flightPriceTest"})
	public void verifyFlightPricesFromFlightSummary() {
		
		//Gets the price of onward journey from summary
		owflightpriceSummary = flightsResultsPage.getowFlightPriceFrmSummary().split(" ")[1].replace(",", "");
		logger.info("Onward flight price from summary is : "+owflightpriceSummary);
		Assert.assertEquals(Integer.parseInt(onwFlightPrice), Integer.parseInt(owflightpriceSummary));
		
		//Gets the flight price of return journey from summary
		rtflightpriceSummary = flightsResultsPage.getrtFlightPriceFrmSummary().split(" ")[1].replace(",", "");
		logger.info("Return flight price from summary is : "+rtflightpriceSummary);
		Assert.assertEquals(Integer.parseInt(rtnFlightPrice), Integer.parseInt(rtflightpriceSummary));
		
	}
	
	/**
	 * This method verifies the departure and return total flight ticket price from summary
	 */
	@Test(dependsOnMethods= {"verifyFlightPricesFromFlightSummary"})
	public void verifyTotalFlightPricesFromFlightSummary() {
		
		//Gets the total flight ticket price from summary
		totalFpSummary = flightsResultsPage.getTotalFlightPriceFrmSummary().split(" ")[1].replace(",", ""); 
		logger.info("Total flight price in Summary : "+totalFpSummary);
		
		//Gives total flight tickets price
		totalFp = (Integer.parseInt(onwFlightPrice)+Integer.parseInt(rtnFlightPrice));
		Assert.assertEquals(Integer.parseInt(totalFpSummary), totalFp);
	}

	/**
	 * This will end the browser instance
	 */
	@AfterClass
	public void afterTest() {
		driver.quit();
		logger.info("Closed the browser");
	}
	
}
