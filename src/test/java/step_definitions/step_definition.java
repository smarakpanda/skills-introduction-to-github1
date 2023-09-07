package step_definitions;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
public class step_definition {
    private WebDriver driver;
    private final String email = "asdfghjkl112363mvn@gmail.com";
    private final String password = "Test@123";
    @Before(order =1)
    public void before() {
        System.out.println("Hello: Before");
    }
    @Given("User is on Home Page")
    public void user_is_on_home_page() throws IOException {
        System.out.println("Hello: Given");
        driver = WebDriverManager.edgedriver().create();
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com/");
        driver.findElement(By.xpath("//a[contains(text(),'Register')]")).click();
        takeScreenshot(driver);
    }
    @When("User enters all the details")
    public void user_enters_all_the_details() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gender-male")));
        driver.findElement(By.id("gender-male")).click();
        driver.findElement(By.id("FirstName")).sendKeys("Smarak");
        driver.findElement(By.id("LastName")).sendKeys("Panda");
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
        driver.findElement(By.id("register-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//*[contains(text(),'Your registration completed')]")));
        Assert.assertTrue(driver.findElement(By.xpath(
                "//*[contains(text(),'Your registration completed')]")).isDisplayed());
    }

    @Then("User should get registered")
    public void user_should_get_registered() {
        driver.findElement(By.xpath("//a[contains(text(),'Log out')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value = 'Log in']")).click();
        String xpath = "//a[contains(text(),'" + email + "')]";
        System.out.println("Hey xpath: " + xpath);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                (xpath)));
        Assert.assertTrue(driver.findElement(By.xpath(xpath)).isDisplayed());
    }

    public void takeScreenshot(WebDriver driver) throws IOException {
        TakesScreenshot scrShot = ((TakesScreenshot)driver);
        File scrFile = scrShot.getScreenshotAs(OutputType.FILE);
        //File snap = new File(filePath);
        FileUtils.copyFile(scrFile,new File(
                "C:\\Users\\smarak.a.panda\\IdeaProjects\\demoProject\\target\\screen.png"));
    }

    @After
    public void after(Scenario scenario) {
            TakesScreenshot scrShot = ((TakesScreenshot)driver);
            byte[] screenshot = scrShot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
    }
    @AfterStep
    public void afterStep(Scenario scenario) {
        TakesScreenshot scrShot = ((TakesScreenshot)driver);
        byte[] screenshot = scrShot.getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());
    }
}
