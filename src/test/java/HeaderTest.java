import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.time.Duration;

public class HeaderTest extends BaseTest {

    @Test
    public void test() {

        WebDriver driver = getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        driver.get("https://magento.softwaretestingboard.com/");

        WebElement topMenuWhatsNew = driver.findElement(By.id("ui-id-3"));
        String value = topMenuWhatsNew.getText();

        Assert.assertEquals(value, "What's New");
    }
}