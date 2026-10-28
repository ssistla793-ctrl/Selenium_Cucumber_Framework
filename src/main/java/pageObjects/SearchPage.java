package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {
	WebDriver driver;
	By search = By.id("small-searchterms");
	By submitBtn = By.xpath("//*[@type='submit']");
	By productTitle = By.xpath("//h2[@class='product-title']");
	public SearchPage(WebDriver driver) {
		this.driver=driver;
	}
	public WebElement getSearch() {
		return this.driver.findElement(this.search);
	}
	public WebElement getSubmitBtn() {
		return this.driver.findElement(this.submitBtn);
	}
	public List<WebElement> getProductTitle() {
		return this.driver.findElements(this.productTitle);
	}
}
