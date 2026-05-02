package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "StepDefinitions",
    plugin = {"pretty", "json:target/jsonreports/cucumber-report.json"},
    monochrome = true
//    tags = "@DeletePlace"
)
public class TestRunner {
}
