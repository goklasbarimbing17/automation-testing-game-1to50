package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //setup gecko driver
        System.setProperty("webdriver.gecko.driver", "src/main/resources/browserDrivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://zzzscore.com/1to50/en/");

        // Loop through numbers from 1 to 50
        for (int i = 1; i <= 50; i++) {
            // Custom the elements/locators into dynamic XPath
            String locatorNumberDynamic = "//div[normalize-space()='" + i + "']//span[@class='box']";
            // Keep the dynamic xpath in a List variable
            List<WebElement> numbers = driver.findElements(By.xpath(locatorNumberDynamic));

            // If the element is found, click on it
            if (!numbers.isEmpty()) {
                numbers.get(0).click();  // Click the first matching element
                System.out.println("Clicked number: " + i);
            } else {
                System.out.println("Number " + i + " not found!");
            }
        }

        // Close the browser after finishing
        driver.quit();
    }
}