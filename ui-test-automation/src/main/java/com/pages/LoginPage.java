package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	  public LoginPage(WebDriver driver) {
	        //this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	  @FindBy(xpath="//*[@id='login_Layer']")
	  private WebElement loginButton;
	  
	  @FindBy(xpath="//*[contains (@placeholder,'Enter your active Email ID')]")
	  private WebElement enterUserName;
	  
	  @FindBy(xpath = "//input[@type='password']")
	  private WebElement password;
	  
	  @FindBy(xpath = "//*[contains (@class,'loginButton')]")
	   private WebElement finalLoginButton;
	  
	  @FindBy(xpath = "//*[contains (@class,'info__heading')]")
	  private WebElement profileName;


	public WebElement getLoginButton() {
		return loginButton;
	}

	public WebElement getEnterUserName() {
		return enterUserName;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getFinalLoginButton() {
		return finalLoginButton;
	}
	public WebElement getProfileName() {
		return profileName;
	}
	  

}
