import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public abstract class BasePageTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Credentials credentials;


    @Before
    public void setup() throws MalformedURLException, InterruptedException {
        Thread.sleep(15_000);
        credentials = new Credentials();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    public void injectCookies(Boolean refresh) throws InterruptedException{
        Thread.sleep(1_000);
        for (Map.Entry<String, String> entry : credentials.getCookies().entrySet()) {
            driver.manage().addCookie(
                new Cookie(
                    entry.getKey(),
                    entry.getValue(),
                    ".chessbase.com",
                    "/",
                    null
                )
            );
        }
        
        if (refresh) {
            driver.navigate().refresh();
            Thread.sleep(5_000);
        }
            
    }

    protected void removeBannerWithJS() throws InterruptedException {
        try {
            WebElement cookieBanner = driver.findElement(By.id("askForCookies"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", cookieBanner);
        } catch (Exception e) {
            // Banner not present
        }
        Thread.sleep(1_000);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void loginAttempt(String username, String password) {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UID")));
        emailInput.sendKeys(username);
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PWD")));
        passwordInput.sendKeys(password);
        
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("div.cbLoginButton > button.common_button")
        ));
        loginButton.click();
    }
}