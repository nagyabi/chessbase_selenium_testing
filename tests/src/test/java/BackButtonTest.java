import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BackButtonTest extends BasePageTest {
    @Test
    public void backButtonTest() throws InterruptedException {
        driver.get("https://play.chessbase.com/en");
        
        removeBannerWithJS();
        WebElement dbButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("div.cbDatabaseBG > a")
        ));
        dbButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
        driver.navigate().back();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
        String pageText = driver.findElement(By.tagName("body")).getText();
        assertTrue("Previous page must be playing page", pageText.contains("Play Normal Games"));
        assertTrue(driver.getCurrentUrl().contains("play.chessbase.com/en"));
    }
}
