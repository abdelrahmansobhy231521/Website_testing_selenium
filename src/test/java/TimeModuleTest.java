import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TimeModuleTest extends BaseTest {
    private void loginToApp() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Wait until successfully logged in
        wait.until(ExpectedConditions.urlContains("/dashboard/index"));
    }

    @Test
    public void GotoTest(){
        loginToApp();
        WebElement timeTab = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Time']"))
        );
        timeTab.click();
        boolean isTimePageLoaded=wait.until(ExpectedConditions.urlContains("/time/viewEmployeeTimesheet"));
        Assert.assertTrue(isTimePageLoaded, "Failed to navigate to the Time module!");
        WebElement employeeNameInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type for hints...']"))
        );
        employeeNameInput.sendKeys("a");
        WebElement firstSuggestion = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='listbox']//span"))
        );
        WebElement viewButton = driver.findElement(By.xpath("//button[@type='submit' and text()='View']"));
        viewButton.click();
    }
}
