package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.TimeoutException;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;

public class Wise {

    static class TooManyAttemptsException extends RuntimeException {
        public TooManyAttemptsException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initializing wait
        
        try {
            driver.get("https://staging-web.wise.live");

            new WebDriverWait(driver, Duration.ofSeconds(10));

            // 1. Continue with Mobile
            WebElement continueMobileButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Continue with Mobile')]")));
            continueMobileButton.click();

            // 2. Phone Number Input
            WebElement phoneNumberInput = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[placeholder='Phone number']")));
            phoneNumberInput.sendKeys("1111100000");

            // 3. Get OTP
            WebElement getOTPButton = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Get OTP')]")));
            getOTPButton.click();

            // 4. Check for "Too many attempts" popup
            try {
                WebElement tooManyAttemptsPopup = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.className("custom-alert-content-message")));
                String popupMessage = tooManyAttemptsPopup.getText();
                if (popupMessage.contains("Too many attempts")) {
                    throw new TooManyAttemptsException(popupMessage);
                }
            } catch (org.openqa.selenium.TimeoutException e) {
                System.out.println("Too Many attempts pop up not found. Proceeding with normal flow");
            }

            // 5. OTP Input
            WebElement otpInput = null;
            try {
                otpInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-35--0")));
            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
                System.out.println("OTP Input ID not found. Trying with class name");
                otpInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("otp-field-box--0")));
            }

            if (otpInput != null) {
                otpInput.sendKeys("0000");
                System.out.println("OTP entered.");
            } else {
                System.out.println("OTP input field not found after multiple tries.");
            }

            // 6. Verify Button Click
            try {
                WebElement verifyButton = wait
                        .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Verify')]")));
                verifyButton.click();
                System.out.println("Verify button clicked.");
            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
                System.out.println("Verify button not found.");
                e.printStackTrace();
            }

            // 7. Assert Name
            try {
                WebElement nameElement = wait.until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//div[@class='name text--24 font-weight--600 ml-3']")));
                String actualName = nameElement.getText();
                String expectedName = "Testing Institute";
                Assert.assertEquals("Name mismatch", expectedName, actualName);
                System.out.println("Name assertion passed.");
            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
                System.err.println("Name element not found.");
                e.printStackTrace();
                Assert.fail("Name element not found.");
            }

            // 8. Click "Group courses"
            try {
                WebElement groupCoursesSpan = wait.until(
                        ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(.,'Group courses')]")));
                groupCoursesSpan.click();
                System.out.println("Group courses span clicked.");
            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
                System.err.println("Group courses span not found.");
                e.printStackTrace();
                Assert.fail("Group courses span not found.");
            }

            // 9. Click "Classroom for Automated testing"
            try {
                WebElement classroomLink = wait.until(ExpectedConditions
                        .elementToBeClickable(By.xpath("//a[contains(.,'Classroom for Automated testing')]")));
                classroomLink.click();
                System.out.println("Classroom link clicked.");
            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
                System.err.println("Classroom link not found.");
                e.printStackTrace();
                Assert.fail("Classroom link not found.");
            }

            // 10. Assert Classroom Name
            try {
                WebElement classroomNameElement = wait.until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//div[@class='text--24 font-weight--600']")));
                String actualClassroomName = classroomNameElement.getText();
                String expectedClassroomName = "Classroom for Automated testing";
                Assert.assertEquals("Classroom name mismatch", expectedClassroomName, actualClassroomName);
                System.out.println("Classroom name assertion passed.");
            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
                System.err.println("Classroom name element not found.");
                e.printStackTrace();
                Assert.fail("Classroom name element not found.");
            }

            // 11. Click "Live Sessions"
            try {
                WebElement liveSessionsLink = wait
                        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Live Sessions']")));
                liveSessionsLink.click();
                System.out.println("Live Sessions link clicked.");
            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
                System.err.println("Live Sessions link not found.");
                e.printStackTrace();
                Assert.fail("Live Sessions link not found.");
            }

            // 12. Click "Schedule sessions"
            try {
                WebElement scheduleSessionsButton = wait.until(
                        ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Schedule sessions')]")));
                scheduleSessionsButton.click();
                System.out.println("Schedule sessions button clicked.");
            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
                System.err.println("Schedule sessions button not found.");
                e.printStackTrace();
                Assert.fail("Schedule sessions button not found.");
            }

            // 13. Click "Add session"
            try {
                WebElement addSessionButton = wait.until(
                        ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Add session')]")));
                addSessionButton.click();
                System.out.println("Add session button clicked.");
            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
                System.err.println("Add session button not found.");
                e.printStackTrace();
                Assert.fail("Add session button not found.");
            }

            // 14 & 15. Select Time & Click Create (Focus on Create Button)
            try {
                // 14. Time Input (Click Dropdown, Select from li - Skip if not found)
                System.out.println("About to find time dropdown...");
                WebElement timeDropdown = driver.findElement(By.xpath("//div[@role='combobox']")); 
                System.out.println("Time dropdown found.");
                System.out.println("About to click time dropdown...");
                timeDropdown.click();  // Click the dropdown
                System.out.println("Time dropdown clicked.");

                // Wait for the menu to be visible
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-menu__content")));
                System.out.println("Time menu became visible.");

                try { // Try to select the time, but skip if not found
                    System.out.println("About to find time to select (10:00)...");
                    WebElement timeToSelect = driver.findElement(By.xpath("//li[contains(.,'10:00')]"));
                    System.out.println("Time to select found.");
                    System.out.println("About to click time to select...");
                    timeToSelect.click();
                    System.out.println("Time '10:00' selected from dropdown.");
                } catch (NoSuchElementException timeNotFoundException) { // Catch the correct Exception type
                    System.out.println("Time '10:00' not found in dropdown. Skipping time selection.");
                    timeNotFoundException.printStackTrace(); // Optional: Print the stack trace for debugging
                }

                // 15. Click Create (Always Execute)
                WebElement createButton = driver.findElement(By.cssSelector("button.v-btn.primary-bg")); // Find directly
                System.out.println("Page source just before Create button click:\n" + driver.getPageSource()); // Debugging
                createButton.click();
                System.out.println("Create button clicked.");

            } catch (NoSuchElementException | TimeoutException | AssertionError e) {
                System.err.println("Time selection/Create interaction failed: " + e.getMessage());
                System.out.println("Page Source at failure:\n" + driver.getPageSource());
                e.printStackTrace();
                Assert.fail("Time selection/Create interaction failed.");
            }

            // 16. Assert "Live session" text
            try {
                WebElement liveSessionTextElement = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='font-weight--600 mr-1']")));
                String actualText = liveSessionTextElement.getText();
                String expectedText = "Live session";

                Assert.assertEquals("Live session text mismatch", expectedText, actualText);
                System.out.println("Live session text assertion passed.");

            } catch (NoSuchElementException | TimeoutException e) {
                System.err.println("Live session text element not found for assertion.");
                e.printStackTrace();
                Assert.fail("Live session text assertion failed.");
            }

            System.out.println("Test Successful");

        } catch (TooManyAttemptsException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Throwable e) { // Catch Throwable to catch all Errors and Exceptions
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}