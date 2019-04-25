package com.mmt.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
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
public class SearchFlightTests extends TestBase {
	
	FlightSearchPage flightSearchPage;
	Generic generic;
	int departureDate;
	int returnDate;
	FlightsResultsPage flightsResultsPage;
	
	/**
	 * This method is used for all the activities needed before starting any tests.
	 */
	@BeforeClass
	public void setUp() {
		generic = new Generic();
		departureDate = Integer.parseInt(generic.getCurrentDay());
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
	 * This test will print total number of Departure and Return Flights.
	 * @throws InterruptedException
	 */
	@AfterClass
	public void getTotalFlights() throws InterruptedException {
		
		//Gets return flights
		int totalreturnFlights = flightsResultsPage.getTotalReturnFlights();
		logger.info("Total Return flights are "+totalreturnFlights);
		
		//Gets departure flights
		int totalDepartureFlights = flightsResultsPage.getTotalDepartureFlights();
		logger.info("Total Departure Flights are "+totalDepartureFlights);
		
		
	}
		
	/**
	 * This will end the browser instance
	 */
	@AfterSuite
	public void afterTest() {
		driver.quit();
		logger.info("Closed the browser");
	}
}
