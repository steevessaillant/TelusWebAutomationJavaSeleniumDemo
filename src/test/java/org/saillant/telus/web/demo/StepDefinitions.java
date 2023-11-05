package org.saillant.telus.web.demo;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class StepDefinitions {
    private WebDriver driver;
    private TelusTVPage telusTVPage;

    private boolean isHeadless = false;

    @Before("@headless")
    public void setup() {
        //initialization now in the first @Given clause
        isHeadless = true;
    }


    @Given("I am on the TELUS TV+ website with {string} browser")
    public void i_am_on_the_telus_tv_website_with_browser(String browser) {
        // Create the WebDriver instance in headless mode based on the browser parameter
        driver = BrowserFactory.createDriver(browser,isHeadless);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));  //(2, TimeUnit.SECONDS);
        // Navigate to the TELUS TV+ website
        telusTVPage = new TelusTVPage(driver);
        telusTVPage.openPage("https://telustvplus.com/#/");
    }

    @When("I close the welcome popup")
    public void iCloseTheWelcomePopup() {
        TelusTVPage telusTVPage = new TelusTVPage(driver);
        telusTVPage.closeWelcomePopup();
    }

    @And("I click on {string}")
    public void iClickOn(String linkText) {
        telusTVPage.clickLinkByText(linkText);
    }

    // Continue to refactor other step definitions...

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @And("I scroll to the {string} section")
    public void iScrollToTheSection(String text) {
        telusTVPage.scrollToAndClickMovies();
    }


    @And("I click on the {string} button")
    public void iClickOnTheButton(String text) {
        telusTVPage.clickFilterButton();
    }

    @And("I select {string} from the filter options")
    public void iSelectFromTheFilterOptions(String text) {
        telusTVPage.selectFilterOption(text);
    }

    @And("I click the {string} button")
    public void iClickTheButton(String text) {
        telusTVPage.clickApplyButton();
    }


    @Then("I should find an asset with a rating of {string} and click on the asset to open the details page")
    public void iShouldFindAnAssetWithARatingOfAndClickOnTheAssetToOpenTheDetailsPage(String rating) {
        telusTVPage.findAssetWithRatingAndClick(rating);
        // Now you should be on the details page
    }


}
