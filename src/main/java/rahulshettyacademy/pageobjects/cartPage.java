package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class cartPage extends AbstractComponent {

	WebDriver driver;

	public cartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	private List<WebElement> productTitles;

	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkoutele;

	By checkout = By.xpath("//button[text()='Checkout']");

	public Boolean getProductByName(String productName) {
		Boolean match = productTitles.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productName));
		return match;
	}

	public checkOut goToCheckout() throws InterruptedException {
		waitForElementToAppear(checkout);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,2000)", "");
		Thread.sleep(3000);
		checkoutele.click();
		return new checkOut(driver);

	}

}
