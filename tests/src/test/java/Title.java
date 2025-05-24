import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Title extends BasePageTest {
    @Test
    public void titleTest() throws InterruptedException{
        driver.get("https://play.chessbase.com/en");
        Thread.sleep(1_000);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        String actualTitle = driver.getTitle();
        assertTrue(actualTitle.toLowerCase().contains("play chess online for free"));
    }
}
