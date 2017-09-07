package webdrivertest.mspui;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationTest {

    private static WebDriver driver;

    @BeforeClass
    public static void dataSetup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void navigationTest() throws InterruptedException {
        driver.get("https://test.com");
        driver.findElement(By.xpath(".//*[@id='usernamefield']")).sendKeys("test");
        driver.findElement(By.xpath(".//*[@id='passwordfield']")).sendKeys("test123456");
        driver.findElement(By.xpath(".//*[@class='btn btn-success m-t-xl p-sm p-t-md floatright']")).click();
        driver.findElement(By.xpath(".//*[@name='reqCreateWidget']")).isDisplayed();
        driver.findElement(By.xpath(".//*[@href='/msp/standard/request/home.html']")).click();
        driver.findElement(By.xpath(".//*[@class='x-column-header-text'][contains(text(), 'Request')]")).isDisplayed();
        driver.findElement(By.xpath(".//*[@href='/msp/standard/engagement/home.html']")).click();
        driver.findElement(By.xpath(".//*[@class='x-column-header-text'][contains(text(), " +
                "'Requisition Number')]")).isDisplayed();
    }

    @AfterClass
    public static void close(){
        driver.quit();
    }
}
