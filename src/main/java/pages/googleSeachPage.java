package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class googleSeachPage {

    WebDriver driver;
    protected By searchBar = By.name("q");

    public googleSeachPage(WebDriver driver){
        this.driver = driver;
    }

    public void openGooglePage(String url){
        driver.get(url);
    }

    public void enterSearchData(String searchData){
        driver.findElement(searchBar).sendKeys(searchData);
        driver.findElement(searchBar).sendKeys(Keys.ENTER);
    }

}
