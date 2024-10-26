import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class HeaderTest {

    @Test
    public void test() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement textBox = driver.findElement(By.name("my-text"));
        textBox.sendKeys("Selenium");

        WebElement textArea = driver.findElement(By.name("my-textarea"));
        textArea.sendKeys("Alex!");

        WebElement submitButton = driver.findElement(By.className("btn"));
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();

        Assert.assertEquals(value, "Received!");

        driver.quit();
    }
}