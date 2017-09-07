package webdrivertest.mspui;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    private static WebDriver driver;

    @BeforeClass
    public static void dataSetup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void invalidCredTest(){
        driver.get("https://test.com");
        driver.findElement(By.xpath(".//*[@id='usernamefield']")).sendKeys("test");
        driver.findElement(By.xpath(".//*[@id='passwordfield']")).sendKeys("test12345");
        driver.findElement(By.xpath(".//*[@class='btn btn-success m-t-xl p-sm p-t-md floatright']")).click();
        driver.findElement(By.xpath(".//*[@id='div_wand_validation_errors']")).isDisplayed();
    }

    @Test
    public void loginTest(){
        driver.get("https://test.com");
        driver.findElement(By.xpath(".//*[@id='usernamefield']")).sendKeys("test");
        driver.findElement(By.xpath(".//*[@id='passwordfield']")).sendKeys("test123456");
        driver.findElement(By.xpath(".//*[@class='btn btn-success m-t-xl p-sm p-t-md floatright']")).click();
        driver.findElement(By.xpath(".//*[@name='reqCreateWidget']")).isDisplayed();
    }

    @AfterClass
    public static void close(){
        driver.quit();
    }
}
