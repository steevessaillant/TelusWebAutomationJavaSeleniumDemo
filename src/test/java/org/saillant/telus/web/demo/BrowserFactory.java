package org.saillant.telus.web.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {
    public static WebDriver createDriver(String browser, boolean isHeadless) {
        return switch (browser) {
            case "Chrome" -> createChromeDriver(isHeadless);
            case "Edge" -> createEdgeDriver(isHeadless);
            case "Firefox" -> createFirefoxDriver(isHeadless);
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    private static WebDriver createChromeDriver(boolean isHeadless) {
        ChromeOptions options = new ChromeOptions();
        if (isHeadless) {
            options.addArguments("--headless");
        }
        return new ChromeDriver(options);
    }

    private static WebDriver createEdgeDriver(boolean isHeadless) {
        EdgeOptions options = new EdgeOptions();
        if (isHeadless) {
            options.addArguments("--headless");
        }
        return new EdgeDriver(options);
    }

    private static WebDriver createFirefoxDriver(boolean isHeadless) {
        FirefoxOptions options = new FirefoxOptions();
        if (isHeadless) {
            options.addArguments("--headless");
        }
        return new FirefoxDriver(options);
    }
}
