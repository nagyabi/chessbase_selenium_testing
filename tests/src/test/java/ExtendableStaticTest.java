import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


@RunWith(Parameterized.class)
public class ExtendableStaticTest extends BasePageTest{
    private final String id;
    private final String expectedText;

    // Constructor to receive parameters
    public ExtendableStaticTest(String id, String expectedText) {
        this.id = id;
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters(name = "{index}: id={0}, expectedText={1}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
            {"cbTacticsBG", "Solve Tactics"},
            {"cbOpeningsBG", "Managing Chess Opening Made Easy"},
            {"cbLiveBG", "ALL LIVE GAMES"}
        });
    }

    @Test
    public void dymamicPageClickingByIdTest() throws InterruptedException {
        driver.get("https://play.chessbase.com/en");
        removeBannerWithJS();
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("div." + id + " > a")
        ));
        button.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
        Thread.sleep(1_000);
        String pageText = driver.findElement(By.tagName("body")).getText();
        
        assertTrue("Expected text not present in page",
            pageText.contains(expectedText));
    }
}
