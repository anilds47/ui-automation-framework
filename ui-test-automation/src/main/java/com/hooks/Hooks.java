package com.hooks;


import com.utils.Common_Utils;
import com.utils.ConfigReader;
import com.utils.Driver_Launch;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class Hooks {

	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();


    @Before
    public void openBrowser() {
    	ConfigReader.readProperty();
        WebDriver localDriver = Driver_Launch.newWebDriverInstance();
        driver.set(localDriver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    @After
    public void attach_screenshot(Scenario scenario) {
       // ArrayList<String> caseId = hooks.logResultToTestRail(scenario);
        if (scenario.isFailed()) {
            byte[] src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(src, "img/png", "Error Screen"/*scenario.getName()*/);
            Common_Utils.closeBrowser(getDriver());
            Common_Utils.quitBrowser(getDriver());
        }

    }


}

