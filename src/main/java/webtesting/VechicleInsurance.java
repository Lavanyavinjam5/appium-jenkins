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

public class VechicleInsurance {

    public static void main(String[] args) throws InterruptedException {
        // Setup DesiredCapabilities for Appium
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
       // caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0"); // Ensure this matches your device/emulator version
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_9 Pro Xl_API 35"); // Update with your actual device/emulator name
        caps.setCapability("appPackage", "com.example.vehicleclaimapp_userstory12");  // Update with your app's package name
        caps.setCapability("appActivity", "com.example.vehicleclaimapp_userstory12.MainActivity");  // Update with your app's activity name
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        try {
            // Initialize Appium Driver
           
        	 AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

        	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
             WebElement recyclerView = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.example.vehicleclaimapp_userstory12:id/recyclerView")));

             // Verify that RecyclerView is displayed
             if (recyclerView.isDisplayed()) {
                 System.out.println("RecyclerView is displayed.");

                 // Scroll through the RecyclerView and interact with its items
                 // Interact with the first item in the list
                 WebElement firstItem = driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]"));
                 System.out.println("First item in the list: " + firstItem.getText());  // Get the text of the first item

                 // Optionally, scroll further if needed, or perform other actions like clicking on an item
                 // Example of scrolling to the last item (depending on the setup of your RecyclerView)
                 WebElement lastItem = driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[last()]"));
                 lastItem.click(); // This can be modified based on the actions you want to test
                 System.out.println("Clicked on last item in the list.");
             } else {
                 System.out.println("RecyclerView is not displayed.");
             }

             // Additional checks or interactions can be added here, like form submissions or verification
             // For example, check if a 'Save' button exists and click it
             WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.example.vehicleclaimapp_userstory12:id/saveButton")));
             saveButton.click();
             System.out.println("Clicked Save Button");

             // Simulate checking for a success toast or confirmation
             Thread.sleep(2000);  // Wait for 2 seconds to simulate toast visibility

             boolean toastDisplayed = driver.getPageSource().contains("Data Saved");

             if (toastDisplayed) {
                 System.out.println("Test passed: Data saved successfully.");
             } else {
                 System.out.println("Test failed: Data save toast not displayed.");
             }

         } catch (MalformedURLException e) {
             e.printStackTrace();
         } catch (Exception e) {
             e.printStackTrace();  // Catch any other exceptions
         } 
     }
 }