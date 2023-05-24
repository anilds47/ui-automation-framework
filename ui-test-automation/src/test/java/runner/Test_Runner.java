package runner;

import com.utils.ConfigReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/resources/feature", dryRun = false,
        glue = {"stepdefination","com/hooks"}, tags = ("@Login"),
        monochrome = true, plugin = {"pretty","html:target/cucumber-report-html/report.html", "json:test-output/JsonReport/jsonReport.json", 
        		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        		 "timeline:test-output-thread"})

public class Test_Runner extends AbstractTestNGCucumberTests {

    @Test
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
       return super.scenarios();
    }

    /*@AfterClass
    public static void reportGenerate(){
        JvmReport.jvmReportGeneration(ConfigReader.jsonReportPath);
    }*/

}

