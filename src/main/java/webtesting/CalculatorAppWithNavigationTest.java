package webtesting;

    


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class CalculatorAppWithNavigationTest {

    public static void main(String[] args) {
        // Setup desired capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 9 Pro XL API 35");
        caps.setCapability("appPackage", "com.example.mycalculator");
        caps.setCapability("appActivity", "com.example.mycalculator.MainActivity");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        try {
            // Initialize the Appium driver
           
            AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

            // Wait for elements to be visible or clickable using WebDriverWait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Perform addition operation
            WebElement num1Field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.example.mycalculator:id/etFirstNumber")));
            num1Field.sendKeys("5");

            WebElement num2Field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.example.mycalculator:id/etSecondNumber")));
            num2Field.sendKeys("3");

            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.mycalculator:id/btnAdd")));
            addButton.click();

            // Navigate to the second screen
            WebElement navigateButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.mycalculator:id/btnNavigate")));
            navigateButton.click();

            // Verify the result on the second screen
            WebElement resultView = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.example.mycalculator:id/textViewMessage")));
            String resultText = resultView.getText();
            System.out.println("Result Text: " + resultText);

            if(resultText.equalsIgnoreCase("Welcome to the Second Screen!")) {
                System.out.println("Successfully navigated to the SecondActivity!!!");
            } else {
                System.out.println("Navigation to SecondActivity failed.");
            }

            // Quit the driver
            driver.quit();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

