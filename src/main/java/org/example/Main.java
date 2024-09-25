package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

public class Main {
    private static WebDriver driver;
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

        // Game color Sense
        driver.get("https://zzzscore.com/color/en/");
        try {
            // Locate the element you want to click
            WebElement mainElement = driver.findElement(By.xpath("//div[@class='main']"));

            // Keep clicking the element until it is no longer visible
            while (mainElement.isDisplayed()) {
                mainElement.click();
                System.out.println("Element clicked.");

                // Check if the element is still present and visible
                try {
                    mainElement = driver.findElement(By.xpath("//div[@class='main']"));
                } catch (NoSuchElementException e) {
                    // Element is no longer found on the page
                    System.out.println("Element is no longer visible.");
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Element not found or already not visible.");
        }


        // Close the browser after finishing
        driver.quit();
    }

    public static void delay(WebElement xpath){
        WebElement element = new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(xpath));
    }
}