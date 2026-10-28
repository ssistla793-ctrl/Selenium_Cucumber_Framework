package stepDefinitions;

import base.Base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import io.cucumber.java.en.*;

public class validateWebsiteLaunch extends Base{
	private static final Logger log=LogManager.getLogger(validateWebsiteLaunch.class);
	String url=getData("url");
	String pageTitle;
	@Given("the user enters the Demo Web Shop website address")
	public void the_user_enters_the_demo_web_shop_website_address(){
		try {
			getDriver().get(url);
			//Thread.sleep(3000);
			log.debug("Website "+url+" launched");
			getDriver().manage().window().maximize();
			//Thread.sleep(2000);
		}
		catch(Exception e){
			log.error("Exception: "+e.getMessage());
		}
	    
	}
	
	@When("the website title is Demo Web Shop")
	public void the_website_title_is_demo_web_shop(){
		try {
			pageTitle=getDriver().getTitle();
		    //Thread.sleep(2000);
		    System.out.println("Website page title: "+ pageTitle);
		    log.debug("Title of the page: "+pageTitle);
		}
		catch(Exception e) {
			log.error("Exception: "+e.getMessage());
		}
		
	}
	
	@Then("the user navigated to the website {string}")
	public void the_user_navigated_to_the_website(String expectedTitle) {
		try {
			Assert.assertEquals(expectedTitle, pageTitle,
					"Page title didnot match,website launch failed");
			log.debug("Website title is validated successfully!");
		}
		catch (Exception e) {
			log.error("Exception: "+e.getMessage());
		}
	}



}
