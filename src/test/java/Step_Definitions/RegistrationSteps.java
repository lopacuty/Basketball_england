package Step_Definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.EdgeDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class RegistrationSteps {

    private WebDriver driver;

    @Given("initializeDriver {string}")
    public void initializeDriver(String browser) throws InterruptedException {
        if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("IE")) {
            EdgeDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        Thread.sleep(2000);
    }

    @Given("user on the registration page")
    public void user_on_the_registration_page() {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }
    @And("user enter {string}")
    public void user_enter(String DOB) throws InterruptedException {

        WebElement Date_of_birth = driver.findElement(By.id("dp"));
        Date_of_birth.sendKeys(DOB);
        Thread.sleep(2000);
    }
    @When("user enter name {string} and {string}")
    public void user_enter_name_and(String First_name, String Last_name) throws InterruptedException {

        WebElement fn = driver.findElement(By.id("member_firstname"));
        WebElement ln = driver.findElement(By.id("member_lastname"));

        fn.click();

        fn.sendKeys(First_name);
        ln.sendKeys(Last_name);
        Thread.sleep(2000);
    }

    @Then("user enter email {string} and {string}")
    public void user_enter_email_and(String email, String confirm_email) throws InterruptedException {
        WebElement email_address = driver.findElement(By.id("member_emailaddress"));
        WebElement confirm_emailaddress = driver.findElement(By.id("member_confirmemailaddress"));

        email_address.sendKeys(email);
        confirm_emailaddress.sendKeys(confirm_email);
        Thread.sleep(2000);
    }
    @Then("user  enter password {string} and {string}")
    public void user_enter_password_and(String password, String con_password) throws InterruptedException {
        WebElement pass = driver.findElement(By.id("signupunlicenced_password"));
        WebElement con_pass = driver.findElement(By.id("signupunlicenced_confirmpassword"));

        pass.sendKeys(password);
        con_pass.sendKeys(con_password);
        Thread.sleep(2000);
    }

    @Then("user click on the your role checkbox")
    public void user_click_on_the_your_role_checkbox() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"signup_form\"]/div[10]/div/div/div[1]/div/label/span[3]")).click();
        Thread.sleep(2000);
    }

    @And("user click on the Account Conformation checkbox")
    public void user_click_on_the_account_conformation_checkbox() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"signup_form\"]/div[11]/div/div[2]/div[1]/label/span[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"signup_form\"]/div[11]/div/div[2]/div[2]/label/span[3]")).click();
        Thread.sleep(2000);
    }

    @And("user click on COMMUNICATION PREFERENCES checkbox")
    public void user_click_on_communication_preferences_checkbox() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"signup_form\"]/div[11]/div/div[4]/label/span[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"signup_form\"]/div[11]/div/div[5]/label/span[3]")).click();
        Thread.sleep(2000);
    }

    @And("user click on ETHICS AND CONDUCT checkbox")
    public void user_click_on_ethics_and_conduct_checkbox() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"signup_form\"]/div[11]/div/div[7]/label/span[3]")).click();
        Thread.sleep(2000);
    }

    @Then("user click on Confirm and join button")
    public void user_click_on_confirm_and_join_button() throws InterruptedException {
        By confirmButtonLocator = By.name("join");
        WebElement confirmButton = waitForElementVisible(confirmButtonLocator, 5);
        confirmButton.click();

        By errorMessageLocator = By.xpath("//*[@id='signup_form']/div[6]/div[1]/div/span/span");
        WebElement errorMessageElement = waitForElementVisible(errorMessageLocator, 5);

        String errorMessage = errorMessageElement.getText();

        if (!errorMessage.isEmpty()) {
            assertEquals("Email Address is required", errorMessage);
        }else{
            driver.navigate().to("https://membership.basketballengland.co.uk/NewSupporterAccount");
        }
    }

    private WebElement waitForElementVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Element not found: " + locator.toString());
        }
    }

    @After
    public void closeDriver() {
        driver.close();
    }
}
