package cucumber.test;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;

public class MyStepdefs implements En {

	private final WebDriver driver=new FirefoxDriver();

	public MyStepdefs() {

		Given("^I am on the Google search page$", () -> {
			driver.get("https://www.google.com");
		});

		When("^I search for \"([^\"]*)\"$", (String query) -> {
			WebElement element=driver.findElement(By.name("q"));
			element.sendKeys(query);
			element.submit();
		});

		Then("^the page title should start with \"([^\"]*)\"$", (String arg1) -> {
			(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>(){
				public Boolean apply(WebDriver d) {
					return d.getTitle().toLowerCase().startsWith("cheese");
				}
			});
			assertTrue(driver.getTitle().toLowerCase().startsWith("cheese"));
		});

		After(()->{
			driver.quit();
		});
	}
}
