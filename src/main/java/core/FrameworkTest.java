package core;

import core.config.Config;
import core.remote.BrowserUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FrameworkTest {

    public static RemoteWebDriver driver;

    public static RemoteWebDriver getDriver() {
        return driver;
    }

    private static final Logger logger = Logger.getLogger("GlobalWorldLogger");

    @BeforeAll
    public static void setup() {
        Config.readConfigFile();
    }

    @BeforeEach
    public void before(TestInfo info) {
        driver = BrowserUtils.configureBrowser();
        logger.log(Level.INFO, "\nStarting Test: \"" + info.getDisplayName() + "\"\nRemote Viewer: " + BrowserUtils.videoURL);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void after() {
        driver.close();
    }
}
