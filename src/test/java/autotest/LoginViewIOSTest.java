package autotest;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginViewIOSTest {

  private static IOSDriver driver;
  

  /**
   * 整个测试类只跑一次
   * */
  @BeforeClass
  public static void setUp() throws MalformedURLException {
      // From CI env
    String deviceModel = System.getProperty("deviceModel");
    String osVersion = System.getProperty("osVersion");
    String appPath = System.getProperty("appPath");

    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    desiredCapabilities.setCapability("platformName", "iOS");
   if (osVersion != null) {
        desiredCapabilities.setCapability("platformVersion", osVersion);
    } else {
        desiredCapabilities.setCapability("platformVersion", "15.2");
    }
    desiredCapabilities.setCapability("deviceName", "iPhone 13 Pro Max");
    desiredCapabilities.setCapability("automationName", "XCUITest");
    desiredCapabilities.setCapability("autoGrantPermissions", "true");

    if(appPath != null) {
      desiredCapabilities.setCapability("appium:app", appPath);
    } else if (true) {
        desiredCapabilities.setCapability("appium:app", "");
    } else {
        // no-po
    }

    desiredCapabilities.setCapability("bundleId", "com.auto.LoginDemo");
    desiredCapabilities.setCapability("launchTimeout","20000");
    

    URL remoteUrl = new URL("http://localhost:4723/wd/hub");

    driver = new IOSDriver(remoteUrl, desiredCapabilities);

    System.out.println("driver created successfully.");
  }

  @Test
  public void loginFailTest() {
	  
	//System.out.println(driver.getPageSource());
	MobileElement el9 = (MobileElement) driver.findElementByAccessibilityId("username");
	el9.clear();
	el9.sendKeys("admin123");
    MobileElement el10 = (MobileElement) driver.findElementByAccessibilityId("password");
    el10.clear();
    el10.sendKeys("111111");
    MobileElement el11 = (MobileElement) driver.findElementByAccessibilityId("Login");
    el11.click();
    
    WebElement errMsg =  driver.findElement(MobileBy.AccessibilityId("errMsg"));
    System.out.println("Message: " + errMsg.getText());
    Assert.assertEquals("Username or password is not correct.", errMsg.getText());
    
  }
  
  @Test
  public void loginSuccessTest() {
	  
	//System.out.println(driver.getPageSource());
	MobileElement el9 = (MobileElement) driver.findElementByAccessibilityId("username");
	el9.clear();
	el9.sendKeys("admin");
    MobileElement el10 = (MobileElement) driver.findElementByAccessibilityId("password");
    el10.clear();
    el10.sendKeys("111111");
    MobileElement el11 = (MobileElement) driver.findElementByAccessibilityId("Login");
    el11.click();
    
    WebElement errMsg =  driver.findElement(MobileBy.AccessibilityId("errMsg"));
    System.out.println("Message: " + errMsg.getText());
    Assert.assertEquals("Login ok", errMsg.getText());
  }

  /**
   * 整个测试类只跑一次
   * */
  @AfterClass
  public static void tearDown() {
    if(driver != null){
      driver.quit();
    }
  }
}
