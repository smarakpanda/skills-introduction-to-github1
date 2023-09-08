package step_definitions;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:Features",
        glue={"step_definitions"},
        plugin= {"pretty","html:target/cucumber.html","json:target/report.json"},
        tags = ("@restAssured"),
        monochrome = true)
public class runnerClass {
}