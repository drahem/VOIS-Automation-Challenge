package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class searchResultPage {

    WebDriver driver;
    public searchResultPage(WebDriver driver){
        this.driver = driver;
    }

    private By paginationNext = By.id("pnnext");
    private By result = By.className("LC20lb");
    private By resultStatus = By.id("result-stats");
    private By pagination = By.className("d6cvqb");

    public boolean checkIfResultStatusExist(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultStatus));
        boolean status = driver.findElements(resultStatus).size() > 0;
        return status;
    }

    public boolean checkPaginationSectionExists(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(pagination));
        boolean status = driver.findElements(pagination).size() > 0;
        return status;
    }

    public String getResultStatus(){
        return driver.findElement(resultStatus).getAttribute("innerHTML");
    }

    public void clickOnNextPageBtn(){
        driver.findElement(paginationNext).click();
    }

    public int getResultsCount(){
        int count  = driver.findElements(result).size();
        return count;
    }

}
