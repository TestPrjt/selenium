import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class TestAmazonCart {

    private static String user;
    private static String password;
    private static String url;

    @BeforeClass
    public static void loadProperties() {

        try {
      
            Properties prop = new Properties();
//            prop.load(fileInput);
            InputStream inputStream = TestAmazonCart.class.getResourceAsStream("applicationdata.properties");
            prop.load(inputStream);
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLogin() {
        System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
//        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.navigate().to(url);
        driver.manage().window().maximize();

//        WebElement signInTab = driver.findElement(By.id("nav-link-accountList"));
//        actions.moveToElement(signInTab).perform();

        driver.findElement(By.id("a-autoid-0-announce")).click();

        WebElement email = driver.findElement(By.id("ap_email"));
        wait.until(ExpectedConditions.visibilityOf(email));
        email.sendKeys(user);

        WebElement continueElement = driver.findElement(By.id("continue"));
        continueElement.click();

        WebElement passwordElement = driver.findElement(By.id("ap_password"));

        wait.until(ExpectedConditions.visibilityOf(passwordElement));
        passwordElement.sendKeys(password);

        WebElement login = driver.findElement(By.id("signInSubmit"));
        login.click();

        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        wait.until(ExpectedConditions.visibilityOf(searchBox));

        WebElement accountList = driver.findElement(By.id("nav-link-accountList"));
        List<WebElement> elements = accountList.findElements(By.tagName("span"));
        assertEquals("Hello, TestAcct", elements.get(0).getText());
        driver.close();
    }

    @Test
    public void addItemstocart() {
 
 System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
//        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.navigate().to(url);
        driver.manage().window().maximize();

//        WebElement signInTab = driver.findElement(By.id("nav-link-accountList"));
//        actions.moveToElement(signInTab).perform();

        driver.findElement(By.id("a-autoid-0-announce")).click();

        WebElement email = driver.findElement(By.id("ap_email"));
        wait.until(ExpectedConditions.visibilityOf(email));
        email.sendKeys(user);

        WebElement continueElement = driver.findElement(By.id("continue"));
        continueElement.click();

        WebElement passwordElement = driver.findElement(By.id("ap_password"));

        wait.until(ExpectedConditions.visibilityOf(passwordElement));
        passwordElement.sendKeys(password);

        WebElement login = driver.findElement(By.id("signInSubmit"));
        login.click();

        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("apple iphone 11 black, 128 gb");

        WebElement searchIcon = driver.findElement(By.id("nav-search-submit-text"));
        searchIcon.click();

        WebElement appleElement = driver.findElement(By.xpath("//span[contains(text(), 'Apple iPhone 11 (128GB) - Black')]"));
        wait.until(ExpectedConditions.visibilityOf(appleElement));
        appleElement.click();

        String currentTab = driver.getWindowHandle();
        ArrayList<String> tabList = new ArrayList<String>(driver.getWindowHandles());
        tabList.remove(currentTab);
        driver.switchTo().window(tabList.get(0));

        WebElement addToCart = driver.findElement(By.id("add-to-cart-button"));
        addToCart.click();
        
    }

}
