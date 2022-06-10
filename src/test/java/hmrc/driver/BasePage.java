package hmrc.driver;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static org.awaitility.Awaitility.await;

public class BasePage {

    public WebDriver driver;

    public WebDriverWait wait;

    public BasePage () {
        driver = Driver.getChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(getProperty("wait"))));
        PageFactory.initElements(driver, this);
    }

    public static String getProperty(String property) {
        Properties properties;
        try {
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/properties/test.properties");
            properties.load(fileInputStream);
        } catch(FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }
        return properties.getProperty(property);
    }

    public void openUrl(String url) {
        driver.navigate().to(url);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public static void waitForWebElement(WebElement element) {
        await().atLeast(Duration.ZERO)
                .atMost(Duration.ofMinutes(Integer.parseInt(getProperty("await.max.minutes"))))
                .pollInterval(Duration.ofSeconds(Integer.parseInt(getProperty("await.interval.seconds"))))
                .ignoreExceptions()
                .untilAsserted(() -> Assert.assertTrue("WebElement not found in 1 minute: " + element, element.isEnabled()));
    }

}
