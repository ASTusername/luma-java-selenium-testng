package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(className = "logo")
    private WebElement logo;

    @FindBy(id = "ui-id-2")
    private WebElement topMenu;

    @FindBy(id = "ui-id-3")
    private WebElement whatsNewMenu;

    @FindBy(id = "search")
    private WebElement searchField;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoDisplayed() {
        return isDisplayed(logo);
    }

    public boolean isTopMenuDisplayed() {
        return isDisplayed(topMenu);
    }

    public void clickWhatsNewMenu() {
        whatsNewMenu.click();
    }
}
