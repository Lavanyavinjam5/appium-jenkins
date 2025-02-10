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

public class UpdateClaimStatusActivityTest {

    public static void main(String[] args) throws InterruptedException {
        
        // Setup desired capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        //caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15"); // Update to the available platform version (Android 15)
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 9 Pro XL API 35"); // Update with the available emulator name
        caps.setCapability("appPackage", "com.example.vehicleclaimapp");
        caps.setCapability("appActivity", "com.example.vehicleclaimapp.ui.Claim.UpdateClaimStatusActivity");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
       // caps.setCapability("noReset", true);  // Optionally keep the app data

        try {
            // Initialize Appium driver after setting up desired capabilities
           
        	 AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

            // Locate elements in the app
            WebElement etClaimId = driver.findElement(By.id("com.example.vehicleclaimapp:id/etClaimId"));
            WebElement etNewStatus = driver.findElement(By.id("com.example.vehicleclaimapp:id/etNewStatus"));
            WebElement btnUpdateStatus = driver.findElement(By.id("com.example.vehicleclaimapp:id/btnUpdateStatus"));
            
            // Interact with elements - fill the form and submit
            etClaimId.sendKeys("123");  // Enter the Claim ID (ensure it exists in your database)
            etNewStatus.sendKeys("Approved");  // Enter new status

            // Click the "Update Status" button
            btnUpdateStatus.click();

            // Wait for toast message to appear (we can use WebDriverWait here, but using Thread.sleep for simplicity)
            Thread.sleep(2000);  // Waiting for 2 seconds to simulate toast visibility

            // Check if toast message is displayed in page source
            boolean toastDisplayed = driver.getPageSource().contains("Claim status updated");

            if (toastDisplayed) {
                System.out.println("Test passed: Claim status updated.");
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
