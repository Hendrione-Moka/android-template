package tests;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {

  AndroidDriver<AndroidElement> driver;

  @Test
  public void loginWithValidData(){
    //inisialisasi appium session
    //1. set up desired capabilities
    //2. inisialisasi AndroidDriver
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
    caps.setCapability(MobileCapabilityType.DEVICE_NAME, "device");
    caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
    caps.setCapability(MobileCapabilityType.APP, "/Users/hendrione/Code/APPS/sample-apk.apk");
    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
    caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);

    try {
      driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub/"), caps);
      //implicit wait
      driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

    //locator & find element
    By inputUsernameLocator = MobileBy.id("username");
    AndroidElement inputUsername = driver.findElement(inputUsernameLocator);
    inputUsername.sendKeys("admin");

    By inputPasswordLocator = MobileBy.id("password");
    AndroidElement inputPassword = driver.findElement(inputPasswordLocator);
    inputPassword.sendKeys("admin");

    By inputButtonSignInLocator = MobileBy.id("login");
    AndroidElement buttonSignIn = driver.findElement(inputButtonSignInLocator);
    buttonSignIn.click();

//    By hamburgerButtonLocator = MobileBy.AccessibilityId("Open navigation drawer");
//    AndroidElement buttonHamburger = driver.findElement(hamburgerButtonLocator);
//    boolean isDisplay = buttonHamburger.isDisplayed();
//    //JUNIT 5 assertions
//    Assertions.assertTrue(isDisplay);

    //find - 1 detik
    //delay - 1 detik

    //iterasi - 1 : find (gagal), delay. total = 2 detik (0-2)
    //iterasi - 2 / find (gagal), delay. total = 2 detil (3-4)
    //***
    //iterasi - 16 / find (gagal), delay. total = 2detik (30-32)
    WebDriverWait wait = new WebDriverWait(driver,30, 1000);
    By hamburgerButtonLocator = MobileBy.AccessibilityId("Open navigation drawer");
    AndroidElement buttonHamburger = (AndroidElement) wait.until(ExpectedConditions.presenceOfElementLocated(hamburgerButtonLocator));
    boolean isDisplay = buttonHamburger.isDisplayed();
    //JUNIT 5 assertions
    Assertions.assertTrue(isDisplay);

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    driver.quit();
  }

}
