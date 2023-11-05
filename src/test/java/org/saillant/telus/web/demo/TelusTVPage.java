package org.saillant.telus.web.demo;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TelusTVPage {
    private final WebDriver driver;


    public TelusTVPage(WebDriver driver) {
        this.driver = driver;
        //set implicit timeouts and maximize
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();

    }

    public void openPage(String url) {
        driver.get(url);
    }

    // Locator for the close button in the popup
    private final By closePopupButton = By.cssSelector(".welcome-slide-container .close-modal button");

    // Method to close the popup
    public void closeWelcomePopup() {
        WebElement closeButton = driver.findElement(closePopupButton);
        closeButton.click();
    }

    // Method to click a link by its text
    public void clickLinkByText(String linkText) {
        // Construct an XPath selector to locate the link by its text
        String xpathSelector = "//a[text()='" + linkText + "']";

        // Find the element using the XPath selector
        //WebElement link = driver.findElement(By.xpath(xpathSelector));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathSelector)));
        //element.click();
        // Click the element
        link.click();
    }

    // Method to scroll to the "Movies" section and click it
    public void scrollToAndClickMovies() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[text()='Movies']")));
        driver.findElement(By.xpath("//span[text()='Movies']")).click();
    }

    public void clickFilterButton() {
        WebElement filterButton = driver.findElement(By.cssSelector("button.imageButton.guide-filter-icon"));
        filterButton.click();

    }

    public void selectFilterOption(String optionText) {
        WebElement filterOption = driver.findElement(By.xpath("//span[@class='label' and text()='" + optionText + "']"));
        filterOption.click();
    }

    public void clickApplyButton() {
        WebElement applyButton = driver.findElement(By.cssSelector("div.filter-button.filter-apply-button"));
        applyButton.click();
    }

    // Method to find and click the first asset with a specific rating
    public void findAssetWithRatingAndClick(String rating) {
        // Find all the assets with the specified rating
        List<WebElement> assets = driver.findElements(By.xpath("//p[@class='subtitle' and text()='" + rating + "']"));

        // Check if any assets are found
        if (!assets.isEmpty()) {
            // Click on the first asset with the specified rating
            WebElement firstAsset = assets.get(0);
            firstAsset.click();
            //we could take a screenshot
        }

    }


}