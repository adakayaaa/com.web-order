import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

/*
   1-) Open the URL.
   2-) Click "WebOrder" button on top bar.
   3-) Enter valid username "Inar" and password "Academy".
   4-) Navigate to the order page.
   5-) Select "ToysGames" from Product dropdown.
   6-) Enter "10" as quantity number.
   7-) Enter "20" as discount percentage.
   8-) Click on the "Calculate" button.
   9-) Enter "Inar Academy" as Name.
   10-) Enter "1100 Congress Ave" as Street.
   11-) Enter "Austin" as City.
   12-) Enter "TX" State.
   13-) Enter "78701" as Zip Code(number).
   14-) Enter "5162738261027163" as Card Number.
   15-) Enter "11/28" Expire Date(mm/yy format).
   16-) Click "Process"" button.
   17-) Verify the invalid card type error message is displayed.

*/
public class WO_014_OP_06 extends Hooks {

	List<String> orderInformation = new ArrayList<>();

	@Test
	void verifyOrderPlacement() throws InterruptedException {
		// 0.Product Name
		orderInformation.add("ToysGames");

		// 1.Quantity Number
		orderInformation.add("10");

		// 2.Discount Percentage
		orderInformation.add("20");

		// 3.Name
		orderInformation.add("Inar Academy");

		// 4.Street
		orderInformation.add("1100 Congress Ave");

		// 5.City
		orderInformation.add("Austin");

		// 6.State
		orderInformation.add("TX");

		// 7.Zip Code
		orderInformation.add("78701");

		// 8.Card Number
		orderInformation.add("242738261027163");

		// 9.Expire Date(mm/yy format)
		orderInformation.add("11/28");

		// 2-) Click "WebOrder" button on top bar.
		WebElement webOrderLink = driver.findElement(By.linkText("Weborder"));
		webOrderLink.click();

		// 3-) Enter valid username "Inar" and password "Academy".
		WebElement userNameInputField = driver.findElement(By.id("login-username-input"));
		WebElement passwordInputField = driver.findElement(By.id("login-password-input"));

		userNameInputField.sendKeys("Inar");
		passwordInputField.sendKeys("Academy");

		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		Thread.sleep(5000);

		// 4-) Navigate to the order page.
		WebElement orderTabLink = driver.findElement(By.cssSelector("#order-tab > a"));
		orderTabLink.click();

		// 5-) Select "ToysGames" from Product dropdown.
		WebElement selectDropDown = driver.findElement(By.id("productSelect"));
		Select productDropDown = new Select(selectDropDown);
		productDropDown.selectByVisibleText(orderInformation.get(0));

		// 6-) Enter "10" as quantity number.
		WebElement quantityInputField = driver.findElement(By.id("quantityInput"));
		quantityInputField.sendKeys(orderInformation.get(1));

		// 7-) Enter "20" as discount percentage.
		WebElement discountInputField = driver.findElement(By.id("discountInput"));
		discountInputField.sendKeys(orderInformation.get(2));

		// 8-) Click on the "Calculate" button.
		WebElement calculateButton = driver.findElement(By.xpath("//button[text()='Calculate']"));
		calculateButton.click();

		// 9-) Enter "Inar Academy" as Name.
		WebElement nameField = driver.findElement(By.id("name"));
		nameField.sendKeys(orderInformation.get(3));

		// 10-) Enter "1100 Congress Ave" as Street.
		WebElement streetField = driver.findElement(By.id("street"));
		streetField.sendKeys(orderInformation.get(4));

		// 11-) Enter "Austin" as City.
		WebElement cityField = driver.findElement(By.id("city"));
		cityField.sendKeys(orderInformation.get(5));

		// 12-) Enter "TX" State.
		WebElement stateField = driver.findElement(By.id("state"));
		stateField.sendKeys(orderInformation.get(6));

		// 13-) Enter "78701" as Zip Code(number).
		WebElement zipCodeField = driver.findElement(By.id("zip"));
		zipCodeField.sendKeys(orderInformation.get(7));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,1000)");

		Thread.sleep(1000);

		// 14-) Enter "5162738261027163" as Card Number.
		WebElement cardNumber = driver.findElement(By.id("cardNumber"));
		cardNumber.sendKeys(orderInformation.get(8));

		// 16-) Enter "11/28" Expire Date(mm/yy format).
		WebElement expireDate = driver.findElement(By.id("expiryDate"));
		expireDate.sendKeys(orderInformation.get(9));

		// 17-) Click "Process"" button.
		WebElement processButton = driver.findElement(By.xpath("//button[contains(text(),'Process')]"));
		processButton.click();

		Thread.sleep(2000);

		// 18-) Verify the invalid Card Number error message is displayed.
		WebElement invalidCardNumberMessage = driver
			.findElement(By.xpath("//em[contains(text(),'Card type cannot be empty')]"));
		boolean isDisplayed = invalidCardNumberMessage.isDisplayed();

		Assertions.assertEquals(true, isDisplayed, "You must to select card type.");

	}

}
