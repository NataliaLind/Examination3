package registration.feature;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class MyStepdefs {

    private WebDriver driver;

    @Given("I am using {}")
    public void iAmUsing(String browser) throws InterruptedException {
        if (browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);
        } else {
            FirefoxOptions optionsF = new FirefoxOptions();
            optionsF.addArguments("-private");
            driver = new FirefoxDriver(optionsF);
        }
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        Thread.sleep(1000);
    }

    @And("I have filled in the fields partOne {}, {}, {}, email and confirmEmail")

    public void iHaveFilledInTheFieldsPartOne(String dateOfBirth, String name, String lastName) throws InterruptedException {
        String randomEmail = getComplexRandomMail();
        WebElement fieldDateOfBirth = driver.findElement(By.cssSelector("input#dp"));
        fieldDateOfBirth.sendKeys(dateOfBirth);
        Thread.sleep(1000);
        WebElement fieldName = driver.findElement(By.cssSelector("input#member_firstname"));
        fieldName.sendKeys(name);
        Thread.sleep(1000);
        WebElement fieldLastName = driver.findElement(By.cssSelector("input#member_lastname"));
        fieldLastName.sendKeys(lastName);
        Thread.sleep(1000);
        WebElement fieldEmail = driver.findElement(By.cssSelector("input#member_emailaddress"));
        fieldEmail.sendKeys(randomEmail);
        Thread.sleep(1000);
        WebElement fieldConfirmEmail = driver.findElement(By.cssSelector("input#member_confirmemailaddress"));
        fieldConfirmEmail.sendKeys(randomEmail);
        Thread.sleep(1000);

    }

    @And("I have filled in the fields partTwo {} and {}")
    public void iHaveFilledInTheFieldsPartTwo(String password, String confirmPassword) throws InterruptedException {
        WebElement fieldPassword = driver.findElement(By.cssSelector("input#signupunlicenced_password"));
        fieldPassword.sendKeys(password);
        Thread.sleep(1000);
        WebElement fieldRetypePassword = driver.findElement(By.cssSelector("input#signupunlicenced_confirmpassword"));
        fieldRetypePassword.sendKeys(confirmPassword);
        Thread.sleep(3000);

    }

    @And("I have checked checkboxes {}, {} and {}")
    public void iHaveCheckedCheckboxes(String termsConditions, String age18, String ethicsConduct) throws InterruptedException {

        if (termsConditions.equals("checked")) {
            WebElement checkboxTermsConditions = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.
                    elementToBeClickable(By.cssSelector("label[for='sign_up_25']")));
            checkboxTermsConditions.click();
        }

        if (age18.equals("checked")) {
            WebElement checkboxAge18 = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.
                    elementToBeClickable(By.cssSelector("label[for='sign_up_26']")));
            checkboxAge18.click();
        }

        if (ethicsConduct.equals("checked")) {
            WebElement checkboxEthicsConduct = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.
                    elementToBeClickable(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']")));
            checkboxEthicsConduct.click();
        }

    }

    @When("I click on the confirm button")
    public void iClickOnTheConfirmButton() throws InterruptedException {
        WebElement buttonConfirm = driver.findElement(By.cssSelector("input[type='submit']"));
        buttonConfirm.click();
        Thread.sleep(1000);

    }


    @Then("I get the {} account is created\\/not created")
    public void iGetTheAccountIsCreatedNotCreated(String result) {
        WebElement element = driver.findElement(By.xpath("//*[text() = '" + result + "']"));
        String actual = element.getText();
        assertEquals(result, actual);


    }

    public static String getComplexRandomMail() {
        String lowerCharacters = "abcdefghijklmnopqrstuvwxyz";
        String upperCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numberCharacters = "0123456789";
        return RandomStringUtils.random(3, lowerCharacters)
                + RandomStringUtils.random(3, numberCharacters)
                + RandomStringUtils.random(3, upperCharacters)
                + System.currentTimeMillis()
                + "@gmail.com";
    }
}




