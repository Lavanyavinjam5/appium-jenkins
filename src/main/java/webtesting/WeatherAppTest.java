package webtesting;

/*import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class WeatherAppTest {
	// AndroidDriver<AndroidElement> driver;
	public static void main(String[]args) throws InterruptedException {
    // Setup desired capabilities
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
    caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 9 Pro XL API 35");
    caps.setCapability("appPackage", "com.example.weatherappretrofitnavigation");
    caps.setCapability("appActivity", "com.example.weatherappretrofitnavigation.ui.MainActivity");
    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

    try {
        // Initialize the Appium driver
    	 AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

        // Perform addition operation
    	 WebElement fetchWeatherButton = driver.findElement(By.id("com.example.weatherappretrofitnavigation:id/btnFetchWeather"));
         fetchWeatherButton.click();
         
    	 WebElement num1Field = driver.findElement(By.id("com.example.weatherappretrofitnavigation:id/tvTitle"));
    	

          WebElement num2Field = driver.findElement(By.id("com.example.weatherapppretrofitnavigation:id/tvWeather"));
          
         // Thread.sleep(2000);

          //WebElement addButton = driver.findElement(By.id("com.example.todolistapp:id/btn_add_task"));
        //addButton.click();

        // Navigate to the second screen
        

     
        	System.out.println("Successfully Fetched Weather!!!");
            


        driver.quit();

    }
    catch (MalformedURLException e) {
        e.printStackTrace();}
	}

	private static void sendkeys(String string) {
		// TODO Auto-generated method stub
		
	}
    
}

*/

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.time.Duration;

public class WeatherAppTest {
    public static void main(String[] args) {
        // Setup desired capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 9 Pro XL API 35");
        caps.setCapability("appPackage", "com.example.weatherappretrofitnavigation");
        caps.setCapability("appActivity", "com.example.weatherappretrofitnavigation.ui.MainActivity");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        try {
            // Initialize the Appium driver
            AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

            // Perform weather fetch operation
            WebElement fetchWeatherButton = driver.findElement(By.id("com.example.weatherappretrofitnavigation:id/btnFetchWeather"));
            fetchWeatherButton.click();

            // Wait for the tvWeather TextView to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement tvWeather = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.example.weatherappretrofitnavigation:id/tvWeather")));

            // Printing the weather data (or perform any other verification here)
            System.out.println("Weather Info: " + tvWeather.getText());

            // Optionally, you can add more assertions or interactions here
            System.out.println("Successfully Fetched Weather!!!");

            // Quit the driver after the test
            driver.quit();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace(); // Adding more generic exception handling
        }
    }
}
