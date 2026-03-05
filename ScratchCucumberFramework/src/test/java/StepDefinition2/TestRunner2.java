package StepDefinition2;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.*;

@RunWith(Cucumber.class)
@CucumberOptions

(
		features ="src/test/java/MyLoginPage",
		glue= {"StepDefinition2"},
		tags = "@sanity",
		dryRun=false,
		monochrome=false,
		
		plugin= {
				"pretty",
				"html:target/HtmlReports/index.html",
				
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
				}
		
//				  plugin = {
//					        "pretty",
//					        "html:target/HtmlReports/index.html",
//					        "json:target/JSONReports/cucumber.json",
//					        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
//					    }
		)





public class TestRunner2 {

}
