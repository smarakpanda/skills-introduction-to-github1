package PageObjects;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import static com

public class loginPage {

    //private WebDriver driver;
    public loginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath="//a[contains(text(),'Register')]") public WebElement registerLink;
    @FindBy(id = "gender-male") public WebElement maleRadioButton;
    @FindBy(id = "FirstName") public WebElement firstNameTextField;
    @FindBy(id = "LastName") public WebElement lastNameTextField;
    @FindBy(id = "Email") public WebElement emailTextField;
    @FindBy(id = "Password") public WebElement passwordTextField;
    @FindBy(id = "ConfirmPassword") public WebElement confirmPasswordTextField;
    @FindBy(id="register-button") public WebElement registerButton;
    @FindBy(xpath = "//*[contains(text(),'Your registration completed')]")
    public WebElement registrationMessage;
    @FindBy(xpath = "//a[contains(text(),'Log out')]") public WebElement logoutLink;
    @FindBy(xpath = "//a[contains(text(),'Log in')]") public WebElement loginLink;
    @FindBy(xpath = "//input[@value = 'Log in']") public WebElement loginButton;
}
