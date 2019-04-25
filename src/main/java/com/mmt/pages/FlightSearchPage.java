package com.mmt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mmt.utils.SeleniumTestHelper;

/**
 * @author Suhail
 *
 */
public class FlightSearchPage {
	
	WebDriver driver;

	@FindBy(xpath="//span[text()='Flights']")
	public WebElement flightsLink;	
	
	@FindBy(xpath="//li[text()='Round Trip']")
	public WebElement roundTripOption;
	
	@FindBy(xpath="//div[@class='fsw_inputBox searchCity inactiveWidget ']")
	public WebElement fromCitytxtBox;
	
	@FindBy(xpath="//input[@placeholder='From']")
	public WebElement fromCityinput;
	
	@FindBy(xpath="//input[@placeholder='To']")
	public WebElement toCityinput;
	
	@FindBy(id="toCity")
	public WebElement toCitytxtBox;
	
	@FindBy(xpath="//div[text()='DEL']")
	public WebElement chooseFromCity;
	
	@FindBy(id="departure")
	public WebElement dptdateinput;
	
	@FindBy(id="return")
	public WebElement returndateinput;
	
	@FindBy(xpath="//a[text()='Search']")
	public WebElement searchBtn;
	
	@FindBy(xpath="//label[@for='filter_stop0']/span[@class='box']")
	public WebElement nonStopChkBox;
	
	@FindBy(xpath="//label[@for='filter_stop1']/span[@class='box']")
	public WebElement oneStopChkBox;
	
	public FlightSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
    
	/**
	 * This method clicks on the Flights link.
	 * @return
	 */
	public Boolean clickOnFlightsLink() {		
		Boolean isClicked = SeleniumTestHelper.clickOnLink(flightsLink);
		return isClicked;
	}

	/**
	 * This method clicks on the Round Trip.
	 * @return
	 */
	public Boolean clickOnRoundTrip() {		
		Boolean isClicked = SeleniumTestHelper.clickOnLink(roundTripOption);
		return isClicked;
	}

	/**
	 * This method is used to select  Tocity
	 * @param fromCity
	 * @return
	 */
	public boolean selectFromCity(String fromCity) {
		try {
			fromCitytxtBox.click();
			fromCityinput.sendKeys(fromCity);
			WebElement frmCity = driver.findElement(By.xpath("//p[@class='font16 appendBottom8' and contains(text(),'Delhi')]"));
			SeleniumTestHelper.clickOnLink(frmCity);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	/**
	 * This method is used to select Fromcity
	 * @param toCity
	 * @return
	 */
	public boolean selectToCity(String toCity) {		
		try {
			toCityinput.sendKeys(toCity);
			Thread.sleep(3000);
			WebElement tooCity = driver.findElement(By.xpath("//div[@class='calc60']/p[contains(text(),'Bangalore')]"));
			SeleniumTestHelper.clickOnLink(tooCity);
			Thread.sleep(3000);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * This method is used to select the Departure date
	 */
	public boolean selectDepartureDate(int departureDate) {	
		try {
			SeleniumTestHelper.clickOnTextBox(dptdateinput);
			WebElement dptDate = driver.findElement(By.xpath("(//div[@class='dateInnerCell']/p[text()="+departureDate+"])[1]"));
			SeleniumTestHelper.clickOnButton(dptDate);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * This method is used to select the return date
	 * @param returnDate
	 * @return
	 */
	public boolean selectReturnDate(int returnDate) {	
		try {
			SeleniumTestHelper.clickOnTextBox(returndateinput);
			WebElement retrnDate = driver.findElement(By.xpath("(//div[@aria-disabled='false']/div[@class='dateInnerCell']/p[text()="+returnDate+"])[1]"));
			SeleniumTestHelper.clickOnButton(retrnDate);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This method is used to click on Search Button
	 * @return
	 */
	public Boolean clickOnSearch() {
		Boolean isClickedSearchBtn = SeleniumTestHelper.clickOnButton(searchBtn);		
		return isClickedSearchBtn;
	}

	/**
	 *  This method is used to click on Non stop filter
	 * @return 
	 */
	public Boolean clickOnNonStop() {
		Boolean isChecked = SeleniumTestHelper.clickOnCheckBox(nonStopChkBox);
		return isChecked;
	}

	/**
	 * This method is used to click on one stop filter
	 * @return 
	 */
	public Boolean clickOnOneStop() {
		Boolean isChecked = SeleniumTestHelper.clickOnCheckBox(oneStopChkBox);
		return isChecked;
	}

	
}
