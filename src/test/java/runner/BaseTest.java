package runner;

import com.codeborne.selenide.Configuration;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://magento.softwaretestingboard.com/";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last Screen");
        Attach.pageSource();
    }
}