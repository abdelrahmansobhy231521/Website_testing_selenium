import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DashboardTest extends BaseTest {

    // Helper method to handle login quickly for dashboard tests
    private void loginToApp() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Wait until successfully logged in
        wait.until(ExpectedConditions.urlContains("/dashboard/index"));
    }

    @Test
    public void testDashboardHeaderIsDisplayed() {
        // 1. Log in first
        loginToApp();

        // 2. Perform your dashboard test assertion
        WebElement header = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-topbar-header-breadcrumb-module"))
        );

        Assert.assertEquals(header.getText(), "Dashboard", "Dashboard header text did not match!");
    }
}