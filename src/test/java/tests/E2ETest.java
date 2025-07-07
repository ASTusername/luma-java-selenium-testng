package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class E2ETest extends BaseTest {
    @Test
    public void test() throws InterruptedException {

        WebDriver driver = getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        driver.get("BASE_URL");

        WebElement topMenuMen = driver.findElement(By.id("ui-id-5"));
        topMenuMen.click();
        WebElement topsJackets = driver.findElement(By.linkText("Jackets"));
        topsJackets.click();

        List<WebElement> listJackets = driver.findElements(By.cssSelector(".item.product.product-item"));
        listJackets.get(0).click();

        WebElement sizeXL = driver.findElement(By.xpath("//div[@option-label='XL']"));
        sizeXL.click();
        WebElement blackColor = driver.findElement(By.xpath("//div[@option-label='Black']"));
        blackColor.click();

        WebElement addToCard = driver.findElement(By.id("product-addtocart-button"));
        addToCard.click();

        sleep(5000);

        WebElement shoppingCard = driver.findElement(By.linkText("shopping cart"));
        shoppingCard.click();

        WebElement shoppingCardTitle = driver.findElement(By.className("base"));
        String actualTitle = shoppingCardTitle.getText();

        Assert.assertEquals(actualTitle, "Shopping Cart");

        List<WebElement> countItems = driver.findElements(By.className("item-info"));

        Assert.assertEquals(countItems.size(), 1);



////        WebElement firstJacket = listJackets.get(0);
//
////        WebElement hoverable = driver.findElement(By.id("hover"));
//        new Actions(driver)
//                .moveToElement(listJackets.get(0))
//                .perform();

//        WebElement proteusFitnessJackshirt = driver.findElement(By.cssSelector("img[alt='Proteus Fitness Jackshirt']"));
//        proteusFitnessJackshirt.click();

//        WebElement topMenuWhatsNew = driver.findElement(By.cssSelector("#ui-id-3"));
//        String value = topMenuWhatsNew.getText();

//        Assert.assertEquals(value, "What's New");
//
//        WebElement search = driver.findElement(By.id("search"));
////        WebElement search = driver.findElement(By.cssSelector("#search"));
//        search.sendKeys("Jackets");
//        search.sendKeys(Keys.ENTER);
//
//        WebElement searchResult = driver.findElement(By.className("base"));
////        WebElement searchResult = driver.findElement(By.cssSelector(".base"));
//        String valueSearchResult = searchResult.getText();

//        Assert.assertEquals(valueSearchResult, "Search results for: 'Jackets'");
    }
}