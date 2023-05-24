package stepdefination;

import static com.hooks.Hooks.getDriver;
import static com.utils.ConfigReader.Url;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import com.constants.Constants;
import com.pages.LoginPage;
import com.utils.Common_Utils;
import com.utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginTest {
	
	LoginPage login=new LoginPage(getDriver());
	
	@Given("User launches the browser and navigates to the application url")
	public void user_launches_the_browser_and_navigates_to_the_application_url() {
		  getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.ImplicitWaitTime));
	      getDriver().get(ConfigReader.prop.getProperty("url"));   
	       
	}
	
	@When("User click on login button")
	public void user_click_on_login_button() throws InterruptedException {
		 Common_Utils.click(login.getLoginButton(), "Login Button"); 
		 
	}

	@Then("User enters the username and password")
	public void user_enters_the_username_and_password() throws InterruptedException {
	   Common_Utils.setText(login.getEnterUserName(), ConfigReader.prop.getProperty("username"),"username");
	   Common_Utils.setText(login.getPassword(), ConfigReader.prop.getProperty("password"), "password");
	 
	}

	@Then("user click on submit button")
	public void user_click_on_submit_button() throws InterruptedException {
	   Common_Utils.click(login.getFinalLoginButton(), "Final Login button");
	   
	}
	
	@Then("Home page is displayed")
	public void home_page_is_displayed() throws InterruptedException {
	Common_Utils.isElementPresent(login.getProfileName(), getDriver());
	 
	}
	
	@Then("User quits the browser")
	public void user_quits_the_browser() {
		Common_Utils.closeBrowser(getDriver());
        Common_Utils.quitBrowser(getDriver());
	}

}
