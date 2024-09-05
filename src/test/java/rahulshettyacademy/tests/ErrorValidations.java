package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.pageobjects.checkOut;

public class ErrorValidations extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = rahulshettyacademy.TestComponents.Retry.class)
	public void LoginErrorValidations() throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		String productname = "ZARA COAT 3";
		landinpage.loginapplication("balavel@gmail.com", "!228Visarcotva");
		Assert.assertEquals("Incorrect email or password.", landinpage.getErrorMessage());
	}

	@Test
	public void ProductErrorValidations() throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		String productname = "ZARA COAT 3";
		ProductCatalogue productcatalogue = landinpage.loginapplication("tcsbala@gmail.com", "!228Vishnuarcotva");
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productname);
		cartPage cartpage = productcatalogue.goToCartPage();

		Boolean match = cartpage.getProductByName("ZARA COAT 33");
		Assert.assertFalse(match);

	}

}
