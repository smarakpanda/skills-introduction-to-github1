package step_definitions;

import PageObjects.loginPage;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.github.bonigarcia.wdm.WebDriverManager;

import io.restassured.RestAssured;
//import io.restassured.http.Method;
import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class step_definition {
    WebDriver driver = initializeEdgeDriver();
    loginPage loginPageObj = new loginPage(driver);
    int statusCode;
    private final String email = "asdfghjkl112364mvn@gmail.com";
    private final String password = "Test@123";
    @Before(order =1)
    public void before() {
        System.out.println("Hello: Before");
    }
    @Given("User is on Home Page")
    public void user_is_on_home_page()  {
        System.out.println("Hello: Given");
        driver.get("https://demowebshop.tricentis.com/");
        loginPageObj.registerLink.click();
    }
    @When("User enters all the details")
    public void user_enters_all_the_details() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gender-male")));
        wait.until(ExpectedConditions.visibilityOf(loginPageObj.maleRadioButton));
        loginPageObj.maleRadioButton.click();
        loginPageObj.firstNameTextField.sendKeys("Smarak");
        loginPageObj.lastNameTextField.sendKeys("Panda");
        loginPageObj.emailTextField.sendKeys(email);
        loginPageObj.passwordTextField.sendKeys(password);
        loginPageObj.confirmPasswordTextField.sendKeys(password);
        loginPageObj.registerButton.click();
        wait.until(ExpectedConditions.visibilityOf(loginPageObj.registrationMessage));
        Assert.assertTrue(loginPageObj.registrationMessage.isDisplayed());
    }
    @Then("User should get registered")
    public void user_should_get_registered() {
        //driver.findElement(By.xpath("//a[contains(text(),'Log out')]")).click();
        loginPageObj.logoutLink.click();
        //driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
        loginPageObj.loginLink.click();
        loginPageObj.emailTextField.sendKeys(email);
        loginPageObj.passwordTextField.sendKeys(password);
        loginPageObj.loginButton.click();
        String xpath = "//a[contains(text(),'" + email + "')]";
        System.out.println("Hey xpath: " + xpath);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                (xpath)));
        Assert.assertTrue(driver.findElement(By.xpath(xpath)).isDisplayed());
    }
//    public void takeScreenshot(WebDriver driver) throws IOException {
//        TakesScreenshot scrShot = ((TakesScreenshot)driver);
//        File scrFile = scrShot.getScreenshotAs(OutputType.FILE);
//        //File snap = new File(filePath);
//        FileUtils.copyFile(scrFile,new File(
//                "C:\\Users\\smarak.a.panda\\IdeaProjects\\demoProject\\target\\screen.png"));
//    }
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
        System.out.println("Hello Git");
    }
    public static WebDriver initializeEdgeDriver(){
        WebDriver driver;
        driver = WebDriverManager.edgedriver().create();
        driver.manage().window().maximize();
        return driver;
    }
    @Given("User sends a get request")
    public void user_sends_a_get_request() {
        System.out.println("Hello: Given");
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        //System.out.println("Peek: "+response.prettyPeek());
        response.prettyPrint();
        statusCode =response.getStatusCode();
    }
    @Then("user should get a response")
    public void user_should_get_a_response() {
        System.out.println("Hello: Then");
        Assert.assertTrue(statusCode == 200);
    }

}