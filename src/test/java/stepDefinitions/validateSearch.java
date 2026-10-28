package stepDefinitions;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import io.cucumber.java.en.*;
import pageObjects.SearchPage;
import base.Base;

public class validateSearch extends Base {
	SearchPage searchPage;
	String searchProduct;
	private static final Logger log = LogManager.getLogger(validateSearch.class);
	String url = getData("url");

	@When("the user enters {string} in the search box")
	public void the_user_enters_in_the_search_box(String searchProduct) {
		try {
			searchPage = new SearchPage(getDriver());
			getDriver().get(url);
		//	Thread.sleep(3000);
			log.debug("URL: " + url + " launched");
			searchPage.getSearch().sendKeys(searchProduct);
		//	getDriver().findElement(By.id("small-searchterms")).sendKeys(searchProduct);
		//	Thread.sleep(2000);
			log.debug("Search word " + searchProduct + " is given in search bar");
		} catch (Exception e) {
			log.error("Exception: " + e.getMessage());
		}
	}

	@When("the user clicks the Search button")
	public void the_user_clicks_the_search_button() {
		try {
			searchPage.getSubmitBtn().click();
		//	getDriver().findElement(By.xpath("//*[@type='submit']")).click();
		//	Thread.sleep(2000);
			log.debug("Submit button clicked");
		} catch (Exception e) {
			log.error("Exception: " + e.getMessage());
		}
	}

	@Then("the relevant products should be displayed in the search results")
	public void the_relevant_products_should_be_displayed_in_the_search_results() {
		try {
			List<WebElement> displayedProducts = searchPage.getProductTitle();
			//List<WebElement> displayedProducts = getDriver().findElements(By.xpath("//h2[@class='product-title']"));
			Thread.sleep(3000);
			log.debug("All products are listed and captured");
			Assert.assertTrue(displayedProducts.size() > 0, "No relevant products found");
			System.out.println("Product search successful displaying " + displayedProducts.size() + " items.");
			log.debug("Relevant products are displayed");

		} catch (Exception e) {
			log.error("Exception: " + e.getMessage());
		}

	}
}
