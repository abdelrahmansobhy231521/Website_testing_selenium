import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class RecruitmentModuleTest extends BaseTest{
    private void loginToApp() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Wait until successfully logged in
        wait.until(ExpectedConditions.urlContains("/dashboard/index"));
    }

    @Test
    public void goToRecruitment(){
        loginToApp();
        WebElement recruitmentTab = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Recruitment']")));
        recruitmentTab.click();
        boolean isRecruitmentLoaded = wait.until(
                ExpectedConditions.urlContains("/recruitment/viewCandidates")
        );
        Assert.assertTrue(isRecruitmentLoaded, "Failed to navigate to the Recruitment page!");
        WebElement jobTitleDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Job Title']/following::div[contains(@class, 'oxd-select-text')][1]"))
        );
        jobTitleDropdown.click();
        WebElement candidateNameInput = driver.findElement(By.xpath("//label[text()='Candidate Name']/following::input[1]"));
        candidateNameInput.sendKeys("a"); // Type a hint letter to populate suggestions

        WebElement candidateSuggestion = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='listbox']//span"))
        );
        candidateSuggestion.click();
        WebElement keywordsInput = driver.findElement(By.xpath("//label[text()='Keywords']/following::input[1]"));
        keywordsInput.sendKeys("Java, Automation");
        WebElement searchButton = driver.findElement(By.cssSelector("button[type='submit']"));
        searchButton.click();
        WebElement recordsBanner = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'orangehrm-horizontal-padding')]//span[contains(@class, 'oxd-text')]"))
        );
        Assert.assertTrue(recordsBanner.isDisplayed(), "Search results table banner did not load after searching candidates!");
    }
}
