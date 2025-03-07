package com.example; 

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class selenium {
  public static void main(String[] args) {
    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver();

    driver.get("https://staging-web.wise.live ");
    driver.findElement(By.xpath("//button[contains(.,'Continue with Mobile')]")).click();
    driver.findElement(By.cssSelector("input[placeholder='Phone number']")).sendKeys("1111100000");
    driver.findElement(By.xpath("//button[contains(.,'Get OTP')]")).click();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement otpInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("otp-field-box--0")));
    otpInput.sendKeys("0000");
    
    System.out.println("Success");

    // Optional: Wait for a few seconds before closing
    try {
      Thread.sleep(5000); // Wait for 5 seconds
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    driver.quit(); // Close the browser*/
  }
}




 

