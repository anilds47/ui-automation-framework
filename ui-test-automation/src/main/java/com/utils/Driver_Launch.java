package com.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.constants.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.util.Constant;

public class Driver_Launch extends ConfigReader {


    public static WebDriver newWebDriverInstance() {
        WebDriver localDriver = null;
        try {
            //readProperty();
            String browserType = ConfigReader.prop.getProperty("browserType");
            System.out.println(browserType);
            switch (browserType) {

                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--test-type");
                    options.addArguments("start-maximized");
                    //options.addArguments("--headless");
                    options.addArguments("--ignore-certificate-errors");
                    options.addArguments("--disable-extensions");
                    options.addArguments("--disable-web-security");
                    options.addArguments("disable-popup-blocking");
                    options.addArguments("--always-authorize-plugins");
                    options.addArguments("--allow-running-insecure-content");
                    options.addArguments("--remote-allow-origins=*");
                    WebDriverManager.chromedriver().setup();
                    localDriver = new ChromeDriver(options);
                    ((JavascriptExecutor) localDriver).executeScript("window.resizeTo(screen.width, screen.height);");
                    localDriver.manage().deleteAllCookies();
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    localDriver = new FirefoxDriver();
                    localDriver.manage().deleteAllCookies();
                    break;

                case "ie":
                    WebDriverManager.iedriver().setup();
                    localDriver = new InternetExplorerDriver();
                    localDriver.manage().deleteAllCookies();
                    break;

                case "chromium_edge":
                    WebDriverManager.edgedriver().setup();
                    localDriver = new EdgeDriver();
                    localDriver.manage().deleteAllCookies();
                    break;

            }
        } catch (Exception e) {
            System.out.println("Driver not launched properly");
        }
        return localDriver;

    }
}
