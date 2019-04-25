package com.mmt.utils;

import org.openqa.selenium.WebElement;

/**
 * @author Aamir Mohammed Suhail
 * @email  aamirsuhail01@yahoo.com
 */
public class SeleniumTestHelper {

	/**
	 * This method is used to enter text in textbox
	 * @param txtSearchBox
	 * @param city
	 */
	public static void enterTextInTextBox(WebElement txtSearchBox, String city) {
		
		try {
			if(txtSearchBox.isDisplayed()) {
				txtSearchBox.click();
				txtSearchBox.clear();
				txtSearchBox.sendKeys(city);
			}
			else {
				System.out.println("Text box is not displayed");
			}
		}catch(Exception e) {
			System.out.println("Unable to enter text" + e.getMessage());
		}
		
	}

	/**
	 * This method is used to click on the button
	 * @param btnSearchBtutton
	 * @return 
	 */
	public static boolean clickOnButton(WebElement btnSearchBtutton) {
		try {
			btnSearchBtutton.click();
			return true;
		}catch(Exception e) {
			System.out.println("Cannot click on search button" + e);
			return false;
		}
		
	}
	
	/**
	 * This method is used to click on the textBox
	 * @param btnSearchBtutton
	 */
	public static void clickOnTextBox(WebElement txtBox) {
		try {
			txtBox.click();
		}catch(Exception e) {
			//System.out.println("Cannot click on text box" + e.getMessage());
			//System.out.println(e.getStackTrace());
		}
		
	}

	/**
	 * This method is used to click on the link
	 * @param PartialLink
	 * @return 
	 */
	public static boolean clickOnLink(WebElement PartialLink) {		
		try {
			PartialLink.click();
			return true;
		} catch (Exception e) {
			System.out.println("Cannot click on link");
			return false;
		}
	}

	/**
	 * 
	 * This method clicks on the check box
	 * @param chckBox
	 * @return
	 */
	public static boolean clickOnCheckBox(WebElement chckBox) {
		try {
			chckBox.click();
			return true;
		} catch (Exception e) {
			System.out.println("Cannot click checkBox");
			return false;
		}
		
	}

	/**
	 * This method checks the radio button
	 * @param radioBtn
	 * @return
	 */
	public static boolean clickOnRadioBtn(WebElement radioBtn) {
		try {
			radioBtn.click();
			return true;
		} catch (Exception e) {
			System.out.println("Cannot click Radio Button");
			return false;
		}
		
	}

}
