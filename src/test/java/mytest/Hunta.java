package mytest;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Petert on 11/16/15.
 */
public class Hunta {

    private WebDriver driver;

    public Hunta(WebDriver driver)
    {
        this.driver = driver;
    }

    public void goToTestSite()
    {
        driver.get("http://www.puzzles.setgame.com/puzzle/set.htm");
    }

    public List<WebElement> initialyzeItems()
    {
        return driver.findElements(By.cssSelector("input[type='checkbox']"));
    }

    public void checkForCombinations(List<WebElement> list) throws InterruptedException, FileNotFoundException, UnsupportedEncodingException {

        int successCount = 0;
        PrintWriter writer = new PrintWriter("combinations.txt", "UTF-8");

        for (int i=0; i<list.size()-2;i++)
        {
            for (int j=i+1;j<list.size()-1;j++)
            {
                for (int k=j+1;k<list.size();k++)
                {
                        list.get(i).click();
                        list.get(j).click();
                        list.get(k).click();
                        Thread.sleep(100);

                    if (checkCombination(driver))
                    {
                        System.out.println("i = " + (i + 1) + " j= " + (j + 1) + " k= " + (k + 1));
                        writer.write("i = " + (i + 1) + " j= " + (j + 1) + " k= " + (k + 1));
                        successCount++;
                    }

                    if (successCount == 6)
                    {
                        writer.close();
                        return;
                    }

                }
            }
        }
    }

    private boolean checkCombination(WebDriver driver)
    {
        Alert alert = driver.switchTo().alert();
        String answer = alert.getText();
        alert.accept();
        return answer.contains("GREAT");
    }

    public void checkSuccessScreen() throws InterruptedException {
        Thread.sleep(2000);
        if (driver.getCurrentUrl().contains("you-completed"))
        {
            System.out.println("success screen is shown");
        }
        else
        {
            System.out.println("success screen isn't shown");
        }
    }

    public void zrada()
    {
        driver.close();
    }
}
