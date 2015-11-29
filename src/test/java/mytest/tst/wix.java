package mytest.tst;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;


/**
 * Created by Petert on 11/20/15.
 */

public class wix

    {

        public WebDriver driver;
        public String baseUrl;
        public boolean acceptNextAlert;


        @Before
        public void setUp() throws Exception
        {
            driver = new FirefoxDriver();
            baseUrl = "https://www.google.com.ua/";
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            acceptNextAlert = true;
        }

        @After
        public void tearDown() throws Exception
        {
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.quit();
        }

        @Test
        public void test_1() throws Exception
        {
            driver.get(baseUrl + "/");
            driver.findElement(By.id("lst-ib")).clear();
            driver.findElement(By.id("lst-ib")).sendKeys("wix");
            driver.findElement(By.id("vs0p1")).click();
            String s = driver.getCurrentUrl();
            Assert.assertTrue(s.contains("http://ru.wix.com/freesitebuilder"));
        }
    }
