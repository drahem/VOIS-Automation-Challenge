package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.googleSeachPage;
import pages.searchResultPage;
import util.testBase;

import java.io.IOException;

public class testSearch extends testBase {

    @Test(priority = 0)
    public void testGoogleSearch() throws IOException {
        // open google search and search for vodafone
        googleSeachPage searchPage = new googleSeachPage(driver);
        searchPage.openGooglePage(loadData("googleURL"));
        Assert.assertEquals(driver.getCurrentUrl(), loadData("googleURL"), "loaded page URL is incorrect");
        searchPage.enterSearchData(loadData("searchData"));

        searchResultPage result = new searchResultPage(driver);
        // assert existence of element in page
        Assert.assertTrue(result.checkIfResultStatusExist());
    }


    @Test(dependsOnMethods = "testGoogleSearch")
    public void openSecondPage() throws InterruptedException, IOException {

        searchResultPage result = new searchResultPage(driver);

        Assert.assertEquals(result.checkPaginationSectionExists(), true, "pagination section does not exist");

        // navigate to page 2
        result.clickOnNextPageBtn();
        Assert.assertEquals(result.getResultStatus().substring(0,6),loadData("page2Assert"), "navigation to page 2 failed");
        // get number of results in page 2
        int page2ResultsCount = result.getResultsCount();

        // navigate to page 3
        result.clickOnNextPageBtn();
        Assert.assertEquals(result.getResultStatus().substring(0,6),loadData("page3Assert"), "navigation to page 3 failed");

        // get number of results in page 3
        int page3ResultsCount = result.getResultsCount();

        Assert.assertEquals(page2ResultsCount, page3ResultsCount, "results in page 2 and 3 are not the same");
    }


}
