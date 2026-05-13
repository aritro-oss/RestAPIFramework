package cucumber.options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features",
		glue = "StepDefinitions",
		plugin = {"pretty", "json:target/jsonreports/cucumber-reports.json"},
		tags = "@getProduct"
		)
public class TestRunner {

}
