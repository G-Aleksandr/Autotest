package webdrivertest.mspui;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchRequestTest {
    private static WebDriver driver;

    @BeforeClass
    public static void dataSetup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void searchTest() throws InterruptedException {
        driver.get("https://test.com");
        driver.findElement(By.xpath(".//*[@id='usernamefield']")).sendKeys("test");
        driver.findElement(By.xpath(".//*[@id='passwordfield']")).sendKeys("test123456");
        driver.findElement(By.xpath(".//*[@class='btn btn-success m-t-xl p-sm p-t-md floatright']")).click();
        driver.findElement(By.xpath(".//*[@name='reqCreateWidget']")).isDisplayed();
        driver.findElement(By.xpath(".//*[@href='/msp/standard/request/home.html']")).click();
        driver.findElement(By.xpath(".//*[@class='x-column-header-text'][contains(text(), 'Request')]")).isDisplayed();
        driver.findElement(By.xpath(".//*[@id='moreOptionsLink']/a")).click();
        driver.findElement(By.xpath(".//*[@id='requestSearchForm']")).isDisplayed();
        driver.findElement(By.xpath(".//*[@id='Pending']")).click();
        driver.findElement(By.xpath(".//*[@id='Staffing-Recruited']")).click();
        driver.findElement(By.xpath(".//*[@id='search-button']")).click();
        List<WebElement> weblist = driver.findElements(By.xpath(".//*[@id='requestResultsGrid-body']//td[contains(@class, ' x-grid-cell-last')]"));
        List<String> list = new ArrayList<String>();
        for (WebElement element : weblist) {
            list.add(element.getText());
        }
        for (String s : list) {
            Assert.assertTrue(s.equals("Pending"));
        }
        list.clear();
        weblist.clear();
        weblist = driver.findElements(By.xpath(".//*[@id='requestResultsGrid-body']//td[count(//*[contains(@id, 'headercontainer')]//*[text()='Request Type']/ancestor::div[2]/preceding-sibling::*)+1]"));
        for (WebElement element : weblist) {
            list.add(element.getText());
        }
        for (String s : list) {
            Assert.assertTrue(s.equals("Staffing - Supplier Sourced"));
        }
    }

    @AfterClass
    public static void close(){
        driver.quit();
    }
}
