import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class PIMTest extends BaseTest {
    private void loginToApp() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();


        wait.until(ExpectedConditions.urlContains("/dashboard/index"));
    }
    @Test
    public void PIM(){
    loginToApp();
    WebElement pimTab= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'viewPimModule')]")));
    pimTab.click();
    wait.until(ExpectedConditions.urlContains("/pim/viewEmployeeList"));
    WebElement addEmployeeButton= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add Employee']")));
    addEmployeeButton.click();
    String firstName = "John";
    String lastName = "Doe";

    WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName"))
        );
    firstNameInput.sendKeys(firstName);

    WebElement lastNameInput = driver.findElement(By.name("lastName"));
    lastNameInput.sendKeys(lastName);

    WebElement employeeID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Employee Id']/following::input[1]")));
    employeeID.click();
    employeeID.sendKeys(Keys.CONTROL + "a");
    employeeID.sendKeys(Keys.BACK_SPACE);
    employeeID.sendKeys("0526");
    WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
    saveButton.click();

    }

}
