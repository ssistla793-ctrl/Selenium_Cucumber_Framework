package stepDefinitions;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import base.Base;
import io.cucumber.java.en.*;
import pageObjects.SortByPage;

public class validateSortItems extends Base {
	SortByPage sortbypage;
	String sortRange;
	String url=getData("url");
	private static final Logger log=LogManager.getLogger(validateSortItems.class);
	@When("the user clicks Books from top-menu category")
	public void the_user_clicks_books_from_top_menu_category() {
		try {
			sortbypage=new SortByPage(getDriver());
			getDriver().get(url);
			//Thread.sleep(3000);
			log.debug("URL: "+url+" launched");
			getDriver().manage().window().maximize();
			//Thread.sleep(2000);
			sortbypage.gettopMenuBooksOption().click();
		//	getDriver().findElement(By.xpath("//ul[@class='top-menu']//li//a[contains(@href,'books')]")).click();
		//	Thread.sleep(2000);
			log.debug("category 'Books' are selected from top-menu selected");
		} 
		
		catch (Exception e) {
			log.error("Exception: "+e.getMessage());
		}

	}

	@When("{string} category items are displayed")
	public void category_items_are_displayed(String Books) {
		try {
			String categoryTitle = sortbypage.getcategoryTitle().getText();
			//String categoryTitle = getDriver().findElement(By.xpath("//div[@class='page-title']//h1")).getText();
		//	Thread.sleep(3000);
			log.debug("category title validated");
			Assert.assertEquals(Books, categoryTitle, "Wrong category chosen");
		}

		catch (Exception e) {
			log.error("Exception: "+e.getMessage());
		}
	}

	@When("sort {string} is selected")
	public void sort_is_selected(String range) {
		try {
			WebElement sortItem = sortbypage.getsortRange();
			//WebElement sortItem = getDriver().findElement(By.id("products-orderby"));
			sortRange = range;
			System.out.println("Sort by: " + sortRange);
			Select select = new Select(sortItem);
			select.selectByContainsVisibleText(sortRange);
			log.debug("selected "+sortRange+" from sort by option");
		//	Thread.sleep(3000);
		}

		catch (Exception e) {
			log.error("Exception: "+e.getMessage());
		}
	}

	@Then("sort feature is validated")
	public void sort_feature_is_validated() {
		try {
			List<WebElement> products = sortbypage.getProductTitle();
		//	List<WebElement> products = getDriver().findElements(By.xpath("//h2[contains(@class,'product-title')]//a"));
		//	Thread.sleep(3000);
			if (sortRange.equals("A to Z")) {
				for (int i = 0; i < products.size() - 1; i++) {
					String currentProduct = products.get(i).getText();
					String nextProduct = products.get(i + 1).getText();

					Assert.assertTrue(currentProduct.compareToIgnoreCase(nextProduct) <= 0, "Sort didnot happen");

				}
				for (WebElement product : products) {
					System.out.println(product.getText());
				}
				log.debug("sort by feature validated successfully for "+sortRange);
			}
			else if (sortRange.equals("Z to A")) {
				for (int i = 0; i < products.size() - 1; i++) {
					String currentProduct = products.get(i).getText();
					String nextProduct = products.get(i + 1).getText();

					Assert.assertTrue(currentProduct.compareToIgnoreCase(nextProduct) >= 0, "Sort didnot happen");
				}
				for (WebElement product : products) {
					System.out.println(product.getText());
				}
				log.debug("sort by feature validated successfully for "+sortRange);
			}
			
		}
		catch(Exception e) {
			log.error("Exception: "+e.getMessage());
		}
}
}