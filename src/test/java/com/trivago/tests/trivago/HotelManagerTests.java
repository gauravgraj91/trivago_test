package com.trivago.tests.trivago;

import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class HotelManagerTests {

    WebDriver driver = new ChromeDriver();

    @Test(description = "Launch the browser and URL.")
    public void launchAndRunTests() {
        setDriverPath();
        driver.get("https://www.trivago.in/hotelmanager/");
        implicitlyWait(20);
        driver.manage().window().maximize();
        WebElement registerButton = driver.findElement(By.id("register-wideMenu"));
        registerButton.click();
        implicitlyWait(40);
        WebElement searchElement = driver.findElement(By.id("registration[hotel_input]"));
        searchElement.click();
        searchElement.sendKeys("Dusseldorf");
        implicitlyWait(40);

    }

    @Test(description = "Select the value from dropdown.", dependsOnMethods = "launchAndRunTests")
    public void selectValueFromSearchList(){
        waitFor(4000);
        WebElement searchValue = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[1]/div/div/div/ul/li[5]/span"));
        searchValue.click();
    }

    @Test(description = "Hotel Manager Pro Section.", dependsOnMethods = "selectValueFromSearchList")
    public void hotelManagerProVideo(){
        WebElement proButton = driver.findElement(By.id("upgrade-wideMenu"));
        proButton.click();
        implicitlyWait(30);
        WebElement watchVideoBtn = driver.findElement(By.xpath("//*[@id=\"pro-video-button\"]"));
        watchVideoBtn.click();
        implicitlyWait(30);
        WebElement closeVideoBtn = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/span"));
        closeVideoBtn.click();
        implicitlyWait(30);
    }

    @Test(description = "Request Consultation Form Filling.", dependsOnMethods = "hotelManagerProVideo")
    public void requestConsultationForm(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-250)");
        waitFor(9000);
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        js.executeScript("window.scrollBy(0,-170)");
        WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"hotel_name\"]"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView();", searchBox);
        waitFor(3000);
        searchBox.click();
        searchBox.sendKeys("Dusseldorf");
    }

    @AfterClass
    public void tearDown(){
        waitFor(4000);
        driver.quit();
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void implicitlyWait(int sec){
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }

}
