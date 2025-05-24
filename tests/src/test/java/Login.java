import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;


import org.junit.*;

public class Login extends BasePageTest{
    Boolean loginSuccesful() throws InterruptedException {
        Thread.sleep(3000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        return driver.findElements(By.cssSelector("div.cbLogonError")).size() == 0;
    }

    @Test
    public void failedLoginTest() throws InterruptedException {
        driver.get("https://play.chessbase.com/en/logon?returnurl=%2F");
        loginAttempt(credentials.getUsername(), "bad-password");
        assertFalse(loginSuccesful());
        
    }
    
    @Test
    public void succesfulLoginTest() throws InterruptedException {
        driver.get("https://play.chessbase.com/en/logon?returnurl=%2F");
        injectCookies(true);
        loginAttempt(credentials.getUsername(), credentials.getPassword());
        assertTrue(loginSuccesful());
        //Thread.sleep(3000_0000);
    }


}
