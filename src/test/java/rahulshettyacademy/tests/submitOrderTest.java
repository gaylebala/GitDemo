package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.pageobjects.checkOut;
import rahulshettyacademy.pageobjects.orderPage;

public class submitOrderTest extends BaseTest {

	String productname = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		ProductCatalogue productcatalogue = landinpage.loginapplication(input.get("email"), input.get("password"));
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("productname"));
		cartPage cartpage = productcatalogue.goToCartPage();
		Boolean match = cartpage.getProductByName(input.get("productname"));
		Assert.assertTrue(match);
		checkOut checkout = cartpage.goToCheckout();
		checkout.selectCountry("India");
		ConfirmationPage confirmationpage = checkout.submitOrder();
		String confirmmessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();

	}

	// To Verify ZARA COAT 3 is displaying in orders page

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalogue productcatalogue = landinpage.loginapplication("balavel@gmail.com", "!228Vishnuarcotva");
		orderPage orderspage = productcatalogue.goToOrdersPage();
		orderspage.verifyOrderDisplay(productname);
		Assert.assertTrue(orderspage.verifyOrderDisplay(productname));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "balavel@gmail.com");
//		map.put("password", "!228Vishnuarcotva");
//		map.put("productname", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "tcsbala@gmail.com");
//		map1.put("password", "!228Vishnuarcotva");
//		map1.put("productname", "ADIDAS ORIGINAL");

		List<HashMap<String, String>> data = getJsonDatatoMap(
				System.getProperty("user.dir") + "//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}
}
