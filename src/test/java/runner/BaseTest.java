package runner;

import com.codeborne.selenide.Configuration;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://magento.softwaretestingboard.com/";
        Configuration.pageLoadStrategy = "eager";

        ChromeOptions options = new ChromeOptions();

        // Добавляем уже имеющиеся аргументы (если нужны)
        options.addArguments(
                "--proxy-bypass-list=<-loopback>",
                "--disable-dev-shm-usage",
                "--window-size=1920,1080"
        );

        // Добавляем уникальный user-data-dir для каждого запуска
        String userDataDir = System.getProperty("java.io.tmpdir") + "/chrome-profile-" + System.currentTimeMillis();
        options.addArguments("--user-data-dir=" + userDataDir);

        // Отключаем автоматизацию (если требуется)
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation", "load-extension"});

        // Передаем опции в Selenide
        Configuration.browserCapabilities = options;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last Screen");
        Attach.pageSource();
    }
}