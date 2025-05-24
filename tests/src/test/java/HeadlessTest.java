import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeadlessTest extends BasePageTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testForBeginnersVisible() {
        driver.get("https://play.chessbase.com/en/");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        String bodyText = driver.findElement(By.tagName("body")).getText();
        assertTrue("Page should contain 'For Beginners'", bodyText.contains("For Beginners"));
    }
}