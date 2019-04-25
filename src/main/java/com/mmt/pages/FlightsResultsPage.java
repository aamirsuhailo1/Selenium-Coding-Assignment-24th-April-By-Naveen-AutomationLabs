package com.mmt.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mmt.utils.SeleniumTestHelper;

/**
 * @author Suhail
 *
 */
public class FlightsResultsPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//div/input[starts-with(@id,'splitowJourney')]")
	public List<WebElement> dprtFlights;
	
	@FindBy(xpath="//div[@id='rt-domrt-jrny']/div/div[@class='fli-list splitVw-listing']")
	public List<WebElement> rtrnFlights;
	
	@FindBy(xpath="(//div/label[starts-with(@for,'splitowJourney')])[2]/div/div[@class='pull-right marL5 text-right split-price-sctn']/p/span")
	public WebElement owFlightPrice;	
	
	@FindBy(xpath="(//div[@class='pull-right marL5 text-right']/p/span)[1]")
	public WebElement owFlightpricesummary;	
	
	@FindBy(xpath="(//div[@class='pull-right marL5 text-right']/p/span)[2]")
	public WebElement rtFlightpricesummary;		
	
	@FindBy(xpath="//div[@class='footer-fare']/p/span/span")
	public WebElement totalFlightpricesummary;	
			
	public FlightsResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * This method is used to get total number of departure flights
	 * @return
	 */
	public int getTotalDepartureFlights() {
		int totaldeptFlights = dprtFlights.size();
		return totaldeptFlights;
	}
	
	/**
	 * This method gives total number of return flights
	 * @return
	 * @throws InterruptedException
	 */
	public int getTotalReturnFlights() throws InterruptedException {
		int totalReturnFlights = scrollReturnFlightsView();
		return totalReturnFlights;
	}

	/**
	 * This method scrolls the page till end and gets the count of return flights
	 * @return
	 * @throws InterruptedException
	 */
	private int scrollReturnFlightsView() throws InterruptedException {		
		int totalRet=0,i=0;
		while(i<13) {				
			List<WebElement> setFlights = driver.findElements(By.xpath("//div[@id='rt-domrt-jrny']/div/div[@class='fli-list splitVw-listing']"));
			int count = setFlights.size();
			WebElement lastElement = setFlights.get(count-1);
			JavascriptExecutor js = ((JavascriptExecutor)driver);
			js.executeScript("arguments[0].scrollIntoView(true);", lastElement);
			Thread.sleep(3000);			 
			totalRet =  count;
			i++;
		}
		return totalRet;
	}

	/**
	 * This method gives total number of non stop return flights
	 * @return
	 */
	public int getNonStopReturnFlights() {
		int totalReturnFlights = rtrnFlights.size();
		return totalReturnFlights;
	}

	/**
	 * This method gives total number of non stop departure flights
	 * @return
	 */
	public int getNonStopDepartureFlights() {
		int totaldeptFlights = dprtFlights.size();
		return totaldeptFlights;
	}

	/**
	 * This method selects any one of departure and return flights
	 * @param dprtNum
	 * @param retNum
	 */
	public void selectDrptAndReturnFlight(String dprtNum, String retNum) {
		//select departure flight
		try {
			WebElement dptFlightNum = driver.findElement(By.xpath("(//div/label[starts-with(@for,'splitowJourney')]/div/span[@class='splitVw-outer append_right9'])["+dprtNum+"]"));
			if(dptFlightNum.isDisplayed()) {
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("window.scrollBy(0,200);");
				SeleniumTestHelper.clickOnRadioBtn(dptFlightNum);
			}
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,200);");
		}
		//select return flight
		WebElement rtrnFlightNum = driver.findElement(By.xpath("(//div/label[starts-with(@for,'splitrtJourney')]/div/span[@class='splitVw-outer append_right9'])["+retNum+"]"));
		if(rtrnFlightNum.isDisplayed()) {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,200);");
			SeleniumTestHelper.clickOnRadioBtn(rtrnFlightNum);
		}
	}

	/**
	 * This method gets the departure flight price 
	 * @param dprtNum
	 * @return
	 */
	public String getPriceofOnwardJourney(String dprtNum) {
		WebElement owpriceElement = driver.findElement(By.xpath("(//div/label[starts-with(@for,'splitowJourney')])["+dprtNum+"]/div/div[@class='pull-right marL5 text-right split-price-sctn']/p/span"));		
		return owpriceElement.getText();
	}

	/**
	 * This method gets the return flight price
	 * @param retNum
	 * @return
	 */
	public String getPriceofreturnJourney(String retNum) {
		WebElement owpriceElement = driver.findElement(By.xpath("(//div/label[starts-with(@for,'splitrtJourney')])["+retNum+"]/div/div[@class='pull-right marL5 text-right split-price-sctn']/p/span"));		
		return owpriceElement.getText();
	}

	/**
	 * This method gets the departure flight price from summary
	 * @return
	 */
	public String getowFlightPriceFrmSummary() {		
		return owFlightpricesummary.getText();
	}

	/**
	 * This method gets the return flight price from summary
	 * @return
	 */
	public String getrtFlightPriceFrmSummary() {
		return rtFlightpricesummary.getText();
	}

	/**
	 * This method gets the total flight price from summary
	 * @return
	 */
	public String getTotalFlightPriceFrmSummary() {	
		String tp = totalFlightpricesummary.getText();
		return tp;
	}
	
}
