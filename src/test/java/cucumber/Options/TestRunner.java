package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features/placeValidations.feature",
plugin="json:target/jsonReports/cucumber-report.json",
glue= {"stepDefinations"},
tags= "@AddPlace or @DeletePlace or @Regression")

public class TestRunner {

}
