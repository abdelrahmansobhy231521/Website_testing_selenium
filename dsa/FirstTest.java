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

public class FirstTest {
    protected WebDriver driver;
    WebDriverWait wait ;
    @BeforeMethod
    public void setUp(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }
    @Test

    public void login(){
        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("username"))
        );
        usernameField.sendKeys("Admin");
        WebElement PasswordField = driver.findElement(By.name("password"));
        PasswordField.sendKeys("admin123");
        WebElement Login_Button=driver.findElement(By.cssSelector("button[type='submit']"));
        Login_Button.click();
        boolean isDashboardLoaded = wait.until(
                ExpectedConditions.urlContains("/dashboard/index")
        );
        Assert.assertTrue(isDashboardLoaded, "Login failed or dashboard did not load!");

    }

}
