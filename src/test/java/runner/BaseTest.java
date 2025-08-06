package runner;

import com.codeborne.selenide.Configuration;
import helpers.Attach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class BaseTest {
    private static final Path userDataDir = Path.of("/tmp/chrome-profile-" + System.currentTimeMillis());

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

    @AfterAll
    static void afterAll() {
        if (userDataDir != null && Files.exists(userDataDir)) {
            try {
                deleteDirectoryRecursively(userDataDir);
                System.out.println("Deleted temporary Chrome profile directory: " + userDataDir);
            } catch (IOException e) {
                System.err.println("Failed to delete temporary Chrome profile directory: " + userDataDir);
                e.printStackTrace();
            }
        }
    }

    private static void deleteDirectoryRecursively(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (var entries = Files.newDirectoryStream(path)) {
                for (Path entry : entries) {
                    deleteDirectoryRecursively(entry);
                }
            }
        }
        Files.delete(path);
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last Screen");
        Attach.pageSource();
    }
}