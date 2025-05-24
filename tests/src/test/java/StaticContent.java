import static org.junit.Assert.assertTrue;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StaticContent extends BasePageTest {
    @Test
    public void playersPagesTest() throws InterruptedException {
        driver.get("https://play.chessbase.com/en");
        
        removeBannerWithJS();
        // Players segment
        WebElement playersButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("div.cbPlayersBG > a")
        ));
        playersButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        String pageText = driver.findElement(By.tagName("body")).getText();
        //Thread.sleep(100_000);
        assertTrue("In players Magnus should be present",
            pageText.contains("Magnus Carlsen"));
        assertTrue("In players Hikaru should be present",
            pageText.contains("Hikaru Nakamura"));
        
    }

    @Test
    public void studiesPagesTest() throws InterruptedException {
        driver.get("https://play.chessbase.com/en");
        
        removeBannerWithJS();
        // Players segment
        WebElement playersButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("div.cbStudiesBG > a")
        ));
        playersButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        String pageText = driver.findElement(By.tagName("body")).getText();
        //Thread.sleep(100_000);
        assertTrue("Expected text not present in studies page",
            pageText.contains("ChessBase Online Studies"));
        
    }
}
