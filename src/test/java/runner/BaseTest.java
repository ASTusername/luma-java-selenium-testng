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
import java.util.UUID;

public abstract class BaseTest {
    private static Path userDataDir;  // НЕ final, чтобы присвоить в beforeAll()

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://magento.softwaretestingboard.com/";
        Configuration.pageLoadStrategy = "eager";

        ChromeOptions options = new ChromeOptions();

        options.addArguments(
                "--proxy-bypass-list=<-loopback>",
                "--disable-dev-shm-usage",
                "--window-size=1920,1080"
        );

        // Инициализируем поле с уникальной папкой
        userDataDir = Path.of(System.getProperty("java.io.tmpdir") + "/chrome-profile-" + UUID.randomUUID());

        if (Files.exists(userDataDir)) {
            try {
                deleteDirectoryRecursively(userDataDir);
            } catch (IOException e) {
                System.err.println("Failed to clean up before tests: " + userDataDir);
                e.printStackTrace();
            }
        }

        options.addArguments("--user-data-dir=" + userDataDir.toString());

        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation", "load-extension"});

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