package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContextMenu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Alert;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class ContextMenuSteps {
    private WebDriver driver;
    private ContextMenu contextPage;
    private Alert alert;

    @Given("I am on the heroku app context menu page")
    public void i_am_on_the_heroku_app_context_menu_page() {
        try {
            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://the-internet.herokuapp.com/context_menu");
            contextPage = new ContextMenu(driver);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize ChromeDriver", e);
        }
    }


    @When("I click on the box")
    public void i_search_for() {
        contextPage.rightClick();
    }

    @Then("I should see the js alert")
    public void i_should_see_the_js_alert() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Alert alertDriver = wait.until(ExpectedConditions.alertIsPresent());
        this.alert = alertDriver;
        assert alert != null;

    }

    @Then("I should see {string} text in the alert")
    public void i_should_see_string_in_the_alert(String text) {
        assert alert.getText().equals(text);
        alert.accept();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}