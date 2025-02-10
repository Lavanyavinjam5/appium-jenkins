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


public class SmartHomeTest {

    public static void main(String[] args) throws InterruptedException {

        // Setup desired capabilities for Appium
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
      //  caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15"); // Update with the Android version on your device
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 9 Pro XL API 35"); // Update with the emulator or device name
        caps.setCapability("appPackage", "com.example.smarthomecontrollerapp");
        caps.setCapability("appActivity", "com.example.smarthomecontrollerapp.MainActivity");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
      //  caps.setCapability("noReset", true);  // Optionally keep the app data

        try {
            // Initialize Appium driver after setting up desired capabilities
        	 AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

            // Wait for the app to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Locate elements for the "Living Room Light" switch and interact with it
            WebElement switchLivingRoomLight = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.smarthomecontrollerapp:id/switchLivingRoomLight")));
            switchLivingRoomLight.click(); // Toggle the switch ON
            System.out.println("Living Room Light: " + (switchLivingRoomLight.isSelected() ? "ON" : "OFF"));

            // Locate elements for the "Bedroom Light" switch and interact with it
            WebElement switchBedroomLight = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.smarthomecontrollerapp:id/switchBedroomLight")));
            switchBedroomLight.click(); // Toggle the switch ON
            System.out.println("Bedroom Light: " + (switchBedroomLight.isSelected() ? "ON" : "OFF"));

            // Locate the SeekBar for thermostat and set a new value (e.g., 25°C)
            WebElement seekBar = driver.findElement(By.id("com.example.smarthomecontrollerapp:id/seekBarThermostat"));
            seekBar.sendKeys("25");  // Set the thermostat value to 25°C
            WebElement thermostatValue = driver.findElement(By.id("com.example.smarthomecontrollerapp:id/tvThermostatValue"));
            System.out.println("Thermostat set to: " + thermostatValue.getText());

            // Locate and click the "Save Settings" button
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.smarthomecontrollerapp:id/btnSaveSettings")));
            saveButton.click();

            // Wait for a brief moment to ensure the toast message has appeared
            Thread.sleep(2000);  // Wait for 2 seconds to simulate toast visibility

            // Check if toast message is displayed in page source
            boolean toastDisplayed = driver.getPageSource().contains("Settings Saved");

            if (toastDisplayed) {
                System.out.println("Test passed: Settings saved successfully.");
            } else {
                System.out.println("Test failed: Toast message not displayed.");
            }

            // Quit the driver after test is complete
            driver.quit();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();  // Catch any other exceptions
        }
    }
}
