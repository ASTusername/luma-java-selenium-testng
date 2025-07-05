package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.time.Duration;

public class HomePageTest extends BaseTest {

    @Test
    public void testHomePageTitle() {
        WebDriver driver = getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        driver.get("BASE_URL");
        String title = driver.getTitle();

        Assert.assertEquals(title, "Home Page");
    }

    @Test
    public void testHomePageElements() {
        WebDriver driver = getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        driver.get("BASE_URL");

        WebElement logo = driver.findElement(By.cssSelector(".logo"));
        Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed");

        WebElement topMenu = driver.findElement(By.id("ui-id-2"));
        Assert.assertTrue(topMenu.isDisplayed(), "Top menu is not displayed");

        WebElement searchField = driver.findElement(By.id("search"));
        Assert.assertTrue(searchField.isDisplayed(), "Search field is not displayed");
    }

    @Test
    public void testTopMenuNavigation() {
        WebDriver driver = getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        driver.get("BASE_URL");

        WebElement topMenuWhatsNew = driver.findElement(By.id("ui-id-3"));
        topMenuWhatsNew.click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("what-is-new"), "Navigation to 'What's New' failed");
    }
}