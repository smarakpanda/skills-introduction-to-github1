package step_definitions;

//import io.cucumber.java.Scenario;
import org.junit.Before;
import org.openqa.selenium.TakesScreenshot;
//import io.cucumber.Before;

public class hooksClass {
    private TakesScreenshot webDriver;
    //private Scenario scenario;Scenario scenario
    @Before
    public void before() {
        //this.scenario = scenario;
        System.out.println("Hello: Before");
    }
}
