package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import io.qameta.allure.*;

import java.time.Duration;

@Epic("Авторизация")
@Feature("Логин")
public class HeaderTest extends BaseTest {

    @Test(description = "Проверка логина с валидными данными")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Позитивный сценарий логина")
    @Description("Тест проверяет, что пользователь может войти с правильными логином и паролем")
    public void test() {

        WebDriver driver = getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        driver.get("https://magento.softwaretestingboard.com/");

        WebElement topMenuWhatsNew = driver.findElement(By.id("ui-id-3"));
        String value = topMenuWhatsNew.getText();

        Assert.assertEquals(value, "What's New");

        WebElement search = driver.findElement(By.id("search"));
        search.sendKeys("Jackets");
        search.sendKeys(Keys.ENTER);

        WebElement searchResult = driver.findElement(By.className("base"));
        String valueSearchResult = searchResult.getText();

        Assert.assertEquals(valueSearchResult, "Search results for: 'Jackets'");
    }
}