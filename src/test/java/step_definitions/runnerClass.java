package step_definitions;
//import cucumber.api.CucumberOptions;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
//import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:Features",
        glue={"step_definitions"},
        plugin= {"pretty","html:target/cucumber.html",
        "json:target/report.json"},
        monochrome = true)
public class runnerClass {
}
