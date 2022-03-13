package autotest;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginViewAndroidTest {

  private AndroidDriver driver;

  @BeforeTest
  public void setUp() throws MalformedURLException {
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    desiredCapabilities.setCapability("platformName", "Android");
    desiredCapabilities.setCapability("platformVersion", "9");
    desiredCapabilities.setCapability("deviceName", "emulator");
    desiredCapabilities.setCapability("automationName", "UiAutomator2");

    //desiredCapabilities.setCapability("automationName", "uiautomator2");
    desiredCapabilities.setCapability("appPackage", "com.example.logindemo");
    desiredCapabilities.setCapability("appActivity", "com.example.logindemo.MainActivity");
    desiredCapabilities.setCapability("autoGrantPermissions", "true");

    URL remoteUrl = new URL("http://localhost:4723/wd/hub");

    driver = new AndroidDriver(remoteUrl, desiredCapabilities);

    System.out.println("driver created successfully.");
  }

  @Test
  public void loginFailTest() {
	  
	//System.out.println(driver.getPageSource());
	MobileElement el9 = (MobileElement) driver.findElementById("com.example.logindemo:id/edit_username");
	el9.clear();
	el9.sendKeys("admin123");
    MobileElement el10 = (MobileElement) driver.findElementById("com.example.logindemo:id/edit_password");
    el10.clear();
    el10.sendKeys("111111");
    MobileElement el11 = (MobileElement) driver.findElementById("com.example.logindemo:id/buttonLogin");
    el11.click();
    
    WebElement errMsg =  driver.findElement(MobileBy.id("com.example.logindemo:id/err_msg"));
    System.out.println("errMsg: " + errMsg.getText());
    Assert.assertEquals("User name or password is not correct",errMsg.getText());
  }
  
  @Test
  public void loginSuccessTest() {

//System.out.println(driver.getPageSource());
    MobileElement el9 = (MobileElement) driver.findElementById("com.example.logindemo:id/edit_username");
    el9.clear();
    el9.sendKeys("admin");
    MobileElement el10 = (MobileElement) driver.findElementById("com.example.logindemo:id/edit_password");
    el10.clear();
    el10.sendKeys("111111");
    MobileElement el11 = (MobileElement) driver.findElementById("com.example.logindemo:id/buttonLogin");
    el11.click();

    WebElement errMsg =  driver.findElement(MobileBy.id("com.example.logindemo:id/err_msg"));
    System.out.println("errMsg: " + errMsg.getText());

    Assert.assertEquals("Login ok",errMsg.getText());
    
  }

  @AfterTest
  public void tearDown() {
    if(driver != null){
      driver.quit();
    }
  }
}
