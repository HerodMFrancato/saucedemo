package Test.login;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class LoginTest {
    private WebDriver navegador;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/herodfrancato/drivers/chromedriver");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        navegador.manage().window().maximize();
        navegador.get("https://www.saucedemo.com/v1/");
    }

    @Test
    public void fazendoLogin() {
        WebDriverWait wait = new WebDriverWait(navegador, 10);
        WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("user-name")));
        username.click();
        username.sendKeys("standard_user");
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
        password.click();
        password.sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();
    }

    //@After
    public void tearDown() {
        if (navegador != null) {
            navegador.quit();
        }
    }
}