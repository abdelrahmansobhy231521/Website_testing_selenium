import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AdminTest extends BaseTest {
    private void loginToApp() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Wait until successfully logged in
        wait.until(ExpectedConditions.urlContains("/dashboard/index"));
    }
    @Test
    public void Go_to_admin(){
        loginToApp();


        WebElement adminTab = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("a.oxd-main-menu-item[href*='viewAdminModule']"))
        );
        adminTab.click();

        boolean isAdminPageLoaded = wait.until(ExpectedConditions.urlContains("/admin/viewSystemUsers"));
        Assert.assertTrue(isAdminPageLoaded, "Failed to navigate to the Admin page!");


        WebElement usernameInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Username']/following::input[1]"))
        );
        usernameInput.sendKeys("Admin");

        WebElement userRoleDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='User Role']/following::div[contains(@class, 'oxd-select-text')][1]"))
        );
        userRoleDropdown.click();

        WebElement adminRoleOption = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='option']//span[text()='Admin']"))
        );
        adminRoleOption.click();

        WebElement employeeNameInput = driver.findElement(By.xpath("//label[text()='Employee Name']/following::input[1]"));
        employeeNameInput.sendKeys("a");

        WebElement firstAutocompleteOption = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='listbox']//span"))
        );
        firstAutocompleteOption.click();

        WebElement statusDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Status']/following::div[contains(@class, 'oxd-select-text')][1]"))
        );
        statusDropdown.click();

        WebElement enabledStatusOption = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='option']//span[text()='Enabled']"))
        );
        enabledStatusOption.click();

        WebElement searchButton = driver.findElement(By.cssSelector("button[type='submit']"));
        searchButton.click();

        WebElement recordsFoundText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'orangehrm-horizontal-padding')]//span[contains(@class, 'oxd-text')]"))
        );

        Assert.assertTrue(recordsFoundText.isDisplayed(), "Records count banner was not displayed after searching!");



    }

}
