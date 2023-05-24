package com.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static com.hooks.Hooks.getDriver;

public class Common_Utils {

    public static String homeWindow = null;
    public static Set s;
    private static String State_Option_Locator = "//div[text()='%s']";

    public static String getState_Option_Locator() {
        return State_Option_Locator;
    }

    private static String District_Option_Locator = "//div[text()='%s']";

    public static String getDistrict_Option_Locator() {
        return District_Option_Locator;
    }

    private static String Tax_Rate_Locator = "//div[text()='%s']";

    public static String getTax_Rate_Locator() {
        return Tax_Rate_Locator;
    }

    /**
     * Method to switch to the newly opened window
     */
    public static void switchToWindow(WebDriver driver) {
        homeWindow = driver.getWindowHandle();
        for (String window : driver.getWindowHandles()) {
            driver.switchTo().window(window);
            System.out.println("Successfully switched to new window");
        }
    }

    /**
     * To navigate to the main window from child window
     */
    public static void switchToMainWindow(WebDriver driver) {
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(homeWindow)) {
                driver.switchTo().window(window);
                driver.close();
            }
            driver.switchTo().window(homeWindow);
            System.out.println("Successfully switched to main window");
        }
    }


    /**
     * Method to accept alert
     */
    public static void accept_Alert(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            System.out.println("alert accepted successfully");
        } catch (Exception e) {
            System.out.println("Alert is not accepted" + e.toString());
        }
    }

    /**
     * This method returns the no.of windows present
     *
     * @return count
     */
    public static int getWindowCount(WebDriver driver) {
        return driver.getWindowHandles().size();
    }

    /**
     * To switch into frame
     */
    public static void frames(WebElement frameElement, WebDriver driver) {
        try {
            driver.switchTo().frame(frameElement);
            System.out.println("successfully switched to frame");
        } catch (Exception e) {
            System.out.println("failed while switching to frame");
        }
    }

    /**
     * Bring back to default frame
     */
    public static void switchToDefaultcontent(WebDriver driver) {
        try {
            driver.switchTo().defaultContent();
            System.out.println("successfully switched to default frame");
        } catch (NoSuchElementException e) {
            System.out.println("failed switched to default frame");
        }
    }

    /**
     * Navigate to Url
     */
    public static void navigateToUrl(String url, WebDriver driver) {
        try {
            driver.navigate().to(url);
            System.out.println("Application launched successfully to" + url);

        } catch (Exception e) {
            System.out.println("Failed to load the url" + url + e.getMessage());
        }
    }

    /**
     * Close the current window
     */
    public static void closeBrowser(WebDriver driver) {
        try {
            driver.close();
            System.out.println("Killing Chrome driver process after");
            driver.quit();
            System.out.println("Browser closed successfully");
        } catch (Exception e) {
            System.out.println("Browser is not closed");
        }
    }

    /**
     * Clear end enter text in text box
     */
    public static void setText(WebElement element, String value, String fieldName) {
        try {
            element.clear();
            element.sendKeys(value);
            System.out.println("Killing Chrome driver process after");
            System.out.println(value + " entered in " + fieldName + " textbox successfully");
        } catch (Exception e) {
            System.out.println("Killing Chrome driver process after");
            System.out.println("failed to enter" + value + "into" + "textbox " + element.toString());
        }
    }

    /**
     * Verifying the visibility of element only for assert conditions
     */

    public static boolean isElementPresent(WebElement element, WebDriver driver) {
        boolean elementPresent = false;
        try {
            waitForElementVisibility(element, driver);
            if (element.isDisplayed()) {
                elementPresent = true;
            }
            System.out.println("Element displayed successfully");
        } catch (Exception e) {
            System.out.println("Verify Element Present failed" + e.toString());
        }
        return elementPresent;
    }

    /**
     * Verifying the visibility of element only for assert conditions
     */

    public static boolean isElementNotPresent(WebElement element) {
        boolean elementNotPresent = true;
        try {
            if (element.isDisplayed()) {
                elementNotPresent = false;
            }
            System.out.println("Element is Displayed successfully");
        } catch (Exception e) {
            System.out.println("Verify Element Present failed" + e.getMessage());
        }
        return elementNotPresent;
    }

    /**
     * Method to click the element
     */
    public static void click(WebElement element, String fieldName) {
        try {
            element.click();
            System.out.println(fieldName + " is clicked successfully");

        } catch (Exception e) {
            System.out.println(element.toString() + "element is not clicked" + e.getMessage());
        }
    }

    /**
     * getting the text from non editable field
     */

    public static String getText(WebElement element, WebDriver driver, String fieldName) {
        String text = null;
        try {
            waitForElementVisibility(element, driver);
            if (element.getText() != null) {
                text = element.getText();
            }
            System.out.println(fieldName + " retrieved successfully from element");
        } catch (Exception e) {
            System.out.println("text is not retrieved from element" + element.toString() + e.getMessage());
        }
        return text;
    }

    /**
     * Method to get the value from textbox
     */
    public static String getValue(WebElement element, WebDriver driver) {
        String value = null;
        try {
            waitForElementVisibility(element, driver);
            if (element.getAttribute("value") != null) {
                value = element.getAttribute("value");
            }
            System.out.println("text retrieved successfully from element" + element.toString());
        } catch (Exception e) {
            System.out.println("text is not retrieved from element" + element.toString() + e.getMessage());
        }
        return value;
    }

    /**
     * Method to select the option from dropdown by value
     */
    public static void selectByValue(WebElement element, String value) {
        try {
            Select obj_select = new Select(element);
            obj_select.selectByValue(value);
            System.out.println(value + "selected from dropdown " + element.toString());
        } catch (Exception e) {
            System.out.println("failed to select" + value + "from " + element.toString());
        }
    }

    /**
     * Method to select the option from drop down by visible text
     */
    public static void selectByText(WebElement element, String text) {
        try {

            Select obj_select = new Select(element);
            System.out.println("Text to select:" + text);

            List<String> allTextInDD = getOptionFromDropDown(element);
            for (String string : allTextInDD) {
                System.out.println("text is:" + string);
                if (string.equalsIgnoreCase(text)) {
                    System.out.println("in if loop");
                    obj_select.selectByVisibleText(string);
                    break;
                } else {
                    System.out.println("in else loop");
                    continue;
                }
            }
            System.out.println(text + "selected from dropdown " + element.toString());
        } catch (Exception e) {
            System.out.println("failed to select" + text + "from " + element.toString());
        }

    }

    /**
     * Method to select the option from dropdown by index
     */
    public static void selectByIndex(WebElement element, int index) {
        try {
            Select obj_select = new Select(element);
            obj_select.selectByIndex(index);
            System.out.println(index + "index selected from dropdown " + element.toString());
        } catch (Exception e) {
            System.out.println("failed to select" + index + "index" + "from " + element.toString());
        }
    }

    /**
     * Method retrieve the value selected in the drop down
     */
    public String getValueSelectedInTheDropDown(WebElement element) {
        String getSelectedItem = "";
        try {
            Select obj_select = new Select(element);
            getSelectedItem = obj_select.getFirstSelectedOption().getText();
            System.out.println("Successfully fetched fetched the selected item from the drop down.");
            return getSelectedItem;
        } catch (Exception e) {
            System.out.println("Failed to retrieve the selected value from the drop down" + element.toString());
            return getSelectedItem;
        }

    }

    /**
     * To pause execution until get expected elements visibility
     */
    public static void waitForElementVisibility(WebElement element, WebDriver driver) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * To pause execution until get expected elements clickable
     */
    public static void waitForElementClickable(WebElement element, WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            System.out.println("Element is clickable at this point");
        } catch (Exception e) {
            System.out.println("Element is not clickable at this point exception");
        }
    }

    /**
     * To pause the execution @throws
     */
	/*public static void pause(int milliSeconds) throws InterruptedException {
		Thread.sleep(milliSeconds);
	}*/

    /**
     * Method to get the available option from dropdown
     */
    public static List<String> getOptionFromDropDown(WebElement element) {
        List<String> AvailableOptions = new ArrayList<String>();
        try {

            Select obj_select = new Select(element);
            List<WebElement> optionElements = obj_select.getOptions();
            for (int i = 0; i < optionElements.size(); i++) {
                AvailableOptions.add(optionElements.get(i).getText());
            }
            System.out.println("get available options from dropdown is success" + element.toString());
        } catch (Exception e) {
            System.out.println("get available options from dropdown is failed" + e.getMessage() + element.toString());
        }
        return AvailableOptions;
    }

    /**
     * Method to perform mouseover action
     */
    public static void mouseOver(WebElement element, WebDriver driver) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element).build().perform();
            System.out.println("Mouseover to the element" + element.toString() + "is success");
        } catch (Exception e) {
            System.out.println("Mouseover to the element" + element.toString() + "is failed");
        }
    }

    /**
     * Method to get random string
     */
    public static String getRandomString(int length) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        if (sb.length() == 0)
            sb.append("test");
        return sb.toString();
    }

    /**
     * Method to get random number
     */
    public static String getRandomNumber(int length) {
        char[] chars = "123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * Method to get first selected value from dropdown
     */
    public String dropDownSelectedValue(WebElement element) {
        Select select = new Select(element);
        String selectedOption = select.getFirstSelectedOption().getText();
        return selectedOption;
    }

    /**
     * To get default selected value from drop down
     */

    public static String getDefaultDropDownValue(WebElement element) throws InterruptedException {

        Select obj_select = new Select(element);
        return obj_select.getFirstSelectedOption().getText();

    }

    /**
     * Method to perform javascript click
     */
    public static void clickjs(WebElement element, WebDriver driver) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            System.out.println(element.toString() + "element " + element.getText() + " is clicked successfully");
        } catch (Exception e) {
            System.out.println(element.toString() + "element is not clicked" + e.getMessage());
        }
    }

    /**
     * Method to perform click
     */
    public static void clickAction(WebElement element, WebDriver driver) {
        try {
            Actions elementToClick = new Actions(driver);
            elementToClick.click().perform();
            System.out.println(element.toString() + "element is clicked successfully Using Actions");

        } catch (Exception e) {
            System.out.println(element.toString() + "element is not clicked" + e.getMessage());
        }
    }

    /**
     * Method to scroll page up for element visibility using java script
     */
    public static void jsScrollPageUp(WebElement element, WebDriver driver) {
        try {
            int yScrollPosition = element.getLocation().getY();
            System.out.println("yScrollPosition:" + yScrollPosition);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, " + -yScrollPosition + ")", "");
            System.out.println("scroll page up" + "page is scrolled up successfully");
        } catch (Exception e) {
            System.out.println("page is not scrolled up ");
        }
    }

    /**
     * Method to scroll page up for element visibility using java script
     */
    public static void jsScrollPageDown(WebElement element, WebDriver driver) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", element);
            System.out.println("scroll page down" + "page is scrolled down successfully");
        } catch (Exception e) {
            System.out.println("page is not scrolled up ");
        }
    }

    /**
     * quit Browser
     */
    public static void quitBrowser(WebDriver driver) {
        try {
            driver.quit();
            System.out.println("Browser quited successfully");
        } catch (Exception e) {
            System.out.println("Browser quited successfully: " + e);
        }
    }

    /**
     * verify Option is available In DropDown
     *
     * @param dropDown and option
     * @return boolean
     */
    public boolean verifyOptionIsAvailableInDropDown(WebElement dropDown, String option) {
        boolean flag = false;
        List<String> TaxSetupOption = getOptionFromDropDown(dropDown);
        for (String string : TaxSetupOption) {
            System.out.println("option is:" + string);
            if (string.contains(option)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * Method to scroll page completely up
     *
     * @param
     * @throws AWTException
     */
    public static void ScrollPageCompletelyUp() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_UP);
    }

    /**
     * Method to validate vertical scroll bar is present
     */
    public boolean isVerticalScrollBarPresent(WebDriver driver) {
        JavascriptExecutor javascript = (JavascriptExecutor) driver;
        Boolean VertscrollStatus = (Boolean) javascript
                .executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;");
        return VertscrollStatus;
    }

    /**
     * Method to validate vertical scroll bar is present
     */
    public boolean isHorizontalScrollBarPresent(WebDriver driver) {
        JavascriptExecutor javascript = (JavascriptExecutor) driver;
        Boolean VertscrollStatus = (Boolean) javascript
                .executeScript("return document.documentElement.scrollWidth>document.documentElement.clientWidth;");
        if (VertscrollStatus)
            System.out.println("Vertical Scroll Bar is present");
        return VertscrollStatus;
    }

    /**
     * select specific Number Of checkBoxes by Index
     *
     * @param *list of checkboxes, no of checkbox to select
     * @throws InterruptedException
     */
    public void selectspecificNumberOfcheckBoxesFromIndex(List<WebElement> checkBox, int NoOfCheckboxesToClick)
            throws InterruptedException {

        for (int i = 0; i < NoOfCheckboxesToClick; i++) {
            checkBox.get(i).click();
            //pause(1000);
        }
    }

    /**
     * deselect all the checkboxes
     *
     * @param *list of checkboxes
     * @throws InterruptedException
     */

    public static void deselectAllTheCheckboxes(List<WebElement> checkBox) throws InterruptedException {

        for (WebElement webElement : checkBox) {
            if (webElement.isSelected()) {
                System.out.println("checkbox is selected");
                webElement.click();
                //pause(1000);
            } else
                continue;
        }
    }

    /**
     * verify if attribute present in element
     */
    public static boolean isAttribtuePresent(WebElement element, String attribute) {
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value != null) {
                result = true;
                System.out.println("inside attribute if:" + result);
            }
        } catch (Exception e) {
        }

        return result;
    }

    /**
     * verify if attribute present in element
     */
    public static boolean isAttribtuePresent(WebElement element, String attribute, String expectedValue) {
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value.equals(expectedValue)) {
                result = true;
                System.out.println("inside attribute if:" + result);
            }
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * wait For Specific element to disappear by locator
     *
     * @param *list
     * @return flag
     */
    public static void waitUntilSpecificEementInvisibile(By locator, WebDriver driver) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMinutes(80))
                .pollingEvery(Duration.ofSeconds(10)).ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * wait For Specific text to be present
     */
    public static void waitForTextToBePresent(WebElement element, String text, WebDriver driver) {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    /**
     * method to scroll down
     */
    public static void scrollDownToElementView(String elementXpath, WebDriver driver) throws Throwable {
        try {
            WebElement element = driver.findElement(By.xpath(elementXpath));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            //Thread.sleep(500);
        } catch (WebDriverException we) {
            System.out.println("Unable to scroll to the web element: " + we);
            System.out.println("Unable to scroll to the webelement: " + we);

        }
    }

    /**
     * method to scroll down
     */
    public static void scrollUpToElementView(String elementXpath, WebDriver driver) throws Throwable {
        try {
            WebElement element = driver.findElement(By.xpath(elementXpath));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
            //Thread.sleep(500);
        } catch (WebDriverException we) {
            System.out.println("Unable to scroll to the web element: " + we);
            System.out.println("Unable to scroll to the webelement: " + we);

        }
    }

    /**
     * method to zoom out
     */
    public static void zoomOut(WebDriver driver, int a) {

        try {
            Robot robot = new Robot();
            for (int i = 0; i < a; i++) {
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_SUBTRACT);
                robot.keyRelease(KeyEvent.VK_SUBTRACT);
                robot.keyRelease(KeyEvent.VK_CONTROL);
            }/*
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.COMMAND, Keys.SUBTRACT));*/
            System.out.println("Zooming Out");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String GetRandomGeneratedPhoneNumber() {
        Random generator = new Random();
        return generator.nextInt(643) + 100 + "" +
                generator.nextInt(643) + 100 + "" +
                generator.nextInt(8999) + 1000;
    }

    public static String getRandomGeneratedEmailID() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString() + "@trycents.com";
    }

    public static String getRandomString() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }

    public static boolean CheckFile(String name) {
        File file = new File("C:\\Users\\user\\Downloads");
        File[] all_Files = file.listFiles();
        boolean flag = false;
        for (File f : all_Files) {
            Assert.assertEquals(f.getName(), name + ".xlsx", "File Not present in the given path");
            if (f.getName().contains("Cents")) {
                flag = true;
                break;
            }
        }
        return flag;
		/*String currentFile = null;
		//static  String name = null;
		boolean result = false;
		currentFile = "C:\\Users\\user\\Downloads"+name+".xlsx";
		if (File.) //helps to check if the zip file is present
		{
			return true; //if the zip file exists return boolean true
		}
		else
		{
			return false; // if the zip file does not exist return boolean false
		}*/
    }

    public static String getRandomEmployeeCode(int bound) {

        Random rnd = new Random();
        int number = rnd.nextInt(bound);
        return String.format("%05d", number);
    }

    public static int getRandom(int bound) {

        Random rnd = new Random();
        int num = rnd.nextInt(bound);
        return num + 1;
    }

    public static String getRandomPrice(float min, float max) {
        Random rand = new Random();
        float rand1;
        rand1 = rand.nextFloat() * (max - min) + min;
        return Float.toString(rand1);
    }

    public static void SelectAll() throws AWTException {
        Robot rbt = new Robot();
        rbt.keyPress(KeyEvent.VK_CONTROL);
        rbt.keyPress(KeyEvent.VK_A);

        rbt.keyRelease(KeyEvent.VK_CONTROL);
        rbt.keyRelease(KeyEvent.VK_A);
    }

    public static void DeleteAll() throws AWTException {
        Robot rbt = new Robot();
        rbt.keyPress(KeyEvent.VK_DELETE);
        rbt.keyRelease(KeyEvent.VK_DELETE);
    }

    public static Set forEach(List<WebElement> name) {
        s = new HashSet();
        for (WebElement r : name) {
            s.add(r.getText());
        }
        ;
        return s;
    }

    public static String generateTenDigitRandomNum() {
        return String.valueOf((long) (Math.random() * 100000 + 9845300000L));
    }

    public static String createNewPassword() {
        String PasswordAppender = String.valueOf((long) (Math.random() * 100000 + 98L));
        return "Test@12" + PasswordAppender;
    }

    public static WebElement stateLocator(String name, WebDriver driver) {
        return driver.findElement(By.xpath("//div[text()='" + name + "']"));
    }

    public static WebElement generateDynamicWebElement(String locatorValue, String replacementValue, WebDriver driver) {
        return driver.findElement(By.xpath(locatorValue.replaceAll("%s", replacementValue)));
    }

    public static void clearTextUsingActions(WebElement element) {
        Actions actions = new Actions(getDriver());
        String operatingSystem = System.getProperty("os.name").toLowerCase();
        if (operatingSystem.toLowerCase().contains("windows")) {
            actions.click(element)
                    .keyDown(Keys.CONTROL)
                    .sendKeys("a")
                    .keyUp(Keys.CONTROL)
                    .sendKeys(Keys.BACK_SPACE)
                    .build()
                    .perform();
        } else if ((operatingSystem.toLowerCase().contains("mac"))) {
            actions.click(element)
                    .keyDown(Keys.COMMAND)
                    .sendKeys("a")
                    .keyUp(Keys.COMMAND)
                    .sendKeys(Keys.BACK_SPACE)
                    .build()
                    .perform();
        }

    }

    public static void waitForPageLoad() {
        getDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }
}
