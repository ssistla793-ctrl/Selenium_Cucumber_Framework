package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SortByPage {
	WebDriver driver;
	By topMenuBooksOption = By.xpath("//ul[@class='top-menu']//li//a[contains(@href,'books')]");
	By categoryTitle = By.xpath("//div[@class='page-title']//h1");
	By sortRange = By.id("products-orderby");
	By productTitle = By.xpath("//h2[contains(@class,'product-title')]//a");
	public SortByPage(WebDriver driver) {
		this.driver=driver;
	}
	public WebElement gettopMenuBooksOption() {
		return this.driver.findElement(this.topMenuBooksOption);
	}
	public WebElement getcategoryTitle() {
		return this.driver.findElement(this.categoryTitle);
	}
	public WebElement getsortRange() {
		return this.driver.findElement(this.sortRange);
	}
	public List<WebElement> getProductTitle() {
		return this.driver.findElements(this.productTitle);
	}
}
