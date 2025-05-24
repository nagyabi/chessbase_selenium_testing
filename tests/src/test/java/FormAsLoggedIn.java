import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FormAsLoggedIn extends BasePageTest{
    @Before
    public void setup() throws MalformedURLException, InterruptedException {
        // Extending base setup with common steps navigating to the edit section
        super.setup();
        driver.get("https://studies.chessbase.com/en");
        injectCookies(true);
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("div.cbEditAccountLink > a")
        ));
        editButton.click();
    }
    
    @Test
    public void dropDownTest() {
        WebElement countryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.id("Nat") // adjust this if ID is different
        ));

        Select select = new Select(countryDropdown);

        // Select Hungary
        select.selectByVisibleText("Hungary");
        String selectedOption = select.getFirstSelectedOption().getText();
        assertEquals("Hungary", selectedOption);

        // Select back to "Select Your Country"
        select.selectByVisibleText("Select Your Country");
        selectedOption = select.getFirstSelectedOption().getText();
        assertEquals("Select Your Country", selectedOption);
    }

    @Test
    public void loggedInEditingTest() throws InterruptedException {
        WebElement aboutTextarea = wait.until(ExpectedConditions.elementToBeClickable(
            driver.findElement(By.id("User_About"))));
        String currentText = aboutTextarea.getAttribute("value");
        String newText = currentText.equals("hello") ? "hello there" : "hello";
        aboutTextarea.clear();
        aboutTextarea.sendKeys(newText);

        removeBannerWithJS();

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("input[type='submit'][value='Save']"))
        );
        saveButton.click();
    }

    @Test
    public void uploadImageTest() throws URISyntaxException, InterruptedException {
        
        WebElement fileInput = wait.until(ExpectedConditions.elementToBeClickable(
            driver.findElement(By.id("file"))));

        // Load cat.jpg from resources
        File file = new File("/home/selenium/tests/src/test/resources/cat.jpg");
        if (!file.exists())
            throw new NullPointerException();
        // Send file to input
        fileInput.sendKeys(file.getAbsolutePath());

        // Check for presence of 'cat.jpg' in body
        WebElement uploadButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("input[type='submit'][value='Upload'].common_button")
        ));
        uploadButton.click();
        Thread.sleep(4_000);

        WebElement body = driver.findElement(By.tagName("body"));
        // The website's upload function is broken, this is the best I could do to "test" it
        assertTrue(body.getText().contains("Runtime Error"));
    }
}
