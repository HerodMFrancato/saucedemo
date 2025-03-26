package Test.login;

import org.junit.After;
import org.junit.Assert;
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
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/herodfrancato/drivers/chromedriver");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        navegador.manage().window().maximize();
        navegador.get("https://www.saucedemo.com/v1/");
        this.wait = new WebDriverWait(navegador, 10);
        WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("user-name")));
        username.click();
        username.sendKeys("standard_user");
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
        password.click();
        password.sendKeys("secret_sauce");
        navegador.findElement(By.id("login-button")).click();
    }

    @Test
    public void adicionarNoCarrinhoEValidarValor() {
        this.wait = new WebDriverWait(navegador, 10);
        WebElement botaoAddToCart = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn_primary.btn_inventory")));
        botaoAddToCart.click();
        WebElement iconeCarrinho = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("svg.fa-shopping-cart")));
        iconeCarrinho.click();
        WebElement precoElemento = navegador.findElement(By.cssSelector("div.inventory_item_price"));
        String precoTexto = precoElemento.getText();
        Assert.assertEquals("29.99", precoTexto);
    }

    @Test
    public void removerProdutoDoCarrinho() {
        this.wait = new WebDriverWait(navegador, 10);
        WebElement botaoAddToCart = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn_primary.btn_inventory")));
        botaoAddToCart.click();
        WebElement iconeCarrinho = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("svg.fa-shopping-cart")));
        iconeCarrinho.click();
        navegador.findElement(By.cssSelector("#cart_contents_container > div > div.cart_list > div.cart_item > div.cart_item_label > div.item_pricebar > button")).click();
    }

    @Test
    public void adicionarERemoverProdutoInicial() {
        this.wait = new WebDriverWait(navegador, 10);
        WebElement botaoAddToCart = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn_primary.btn_inventory")));
        botaoAddToCart.click();
        navegador.findElement(By.cssSelector("#inventory_container > div > div:nth-child(1) > div.pricebar > button")).click();
    }

    //@After
    public void tearDown() {
        if (navegador != null) {
            navegador.quit();
        }
    }
}