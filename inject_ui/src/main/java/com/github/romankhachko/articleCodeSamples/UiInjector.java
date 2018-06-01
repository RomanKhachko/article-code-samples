package com.github.romankhachko.articleCodeSamples;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by roman on 5/31/18.
 */
public class UiInjector {

    public static void injectUi(DriverExecutor executor) {
        WebDriver driver = getDriver();
        try {
            executor.executeActions(driver);
        } catch (Exception e) {
            // handle exception or skip
        } finally {
            driver.quit();
        }
    }

    private static WebDriver getDriver() {
        // In this example it's chrome for the sake of simplicity.
        // However, in production code it can be different configurations
        System.setProperty("webdriver.chrome.driver", "path_to_chrome_executable_dir/chromedriver");
        return new ChromeDriver();
    }
}
