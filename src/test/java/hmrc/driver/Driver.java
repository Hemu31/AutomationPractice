package hmrc.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Driver {

    private static WebDriver chromeDriver;

    public static WebDriver getChromeDriver() {
        if (chromeDriver == null) {
            WebDriverManager.chromedriver().setup();
            chromeDriver = new ChromeDriver();
            chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            chromeDriver.manage().window().maximize();
        }
        return chromeDriver;
    }

    public static void stopDriver() {
        if (chromeDriver != null) {
            chromeDriver.quit();
            chromeDriver = null;
        }
    }

}