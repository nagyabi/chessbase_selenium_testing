import static org.junit.Assert.assertTrue;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import org.junit.*;

public class Logout extends BasePageTest{
    @Test
    public void logoutTest() throws InterruptedException{
        driver.get("https://play.chessbase.com/en");
        injectCookies(true);
        driver.manage().deleteAllCookies();
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[contains(@href, '/logout')]")
        ));

        logoutButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        String pageText = driver.findElement(By.tagName("body")).getText();

        assertTrue("Expected logout message not found.",
            pageText.contains("Hello, you are not logged in. Just start playing as a guest."));
    }
}
