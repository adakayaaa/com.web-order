
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
        5-) Select "SportsEquipment" from Product dropdown.
        6-) Enter "1" as quantity number.
        7-) Enter "10" as discount percentage.
        8-) Click on the "Calculate" button.
        9-) Enter "Inar Academy" as Name.
        10-) Enter "1100 Congress Ave" as Street.
        11-) Enter "Austin" as City.
        12-) Enter "TX" State.
        13-) Enter "78701" as Zip Code(number).
        14-) Enter "4938220192845" as Card Number.
        15-) Enter "09/26" Expire Date(mm/yy format).
        16-) Click "Process"" button.
        17-) Verify the Card Type error message is displayed.
 */

public class WO_009_OP_04 extends Hooks {

	List<String> orderInformation = new ArrayList<>();

	@Test
	void orderPlacementWithoutCardType() throws InterruptedException {
		// 0.Product Name
		orderInformation.add("SportsEquipment");

		// 1.Quantity Number
		orderInformation.add("1");

		// 2.Discount Percentage
		orderInformation.add("10");

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
		orderInformation.add("4938220192845");

		// 9.Expire Date(mm/yy format)
		orderInformation.add("09/26");

		// 2-)Click "WebOrder" button on top bar.
		WebElement webOrderLink = driver.findElement(By.xpath("/html/body/div/div/header/nav/div/div/a[1]"));
		webOrderLink.click();

		Thread.sleep(2000);

		// 3-)Enter valid username "Inar" and password "Academy".
		WebElement userNameInputField = driver.findElement(By.id("login-username-input"));
		WebElement passwordInputField = driver.findElement(By.id("login-password-input"));

		userNameInputField.sendKeys("Inar");
		passwordInputField.sendKeys("Academy");

		// Click on the "Login" button.
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		Thread.sleep(5000);

		// 4-) Navigate to the order page.
		WebElement orderTabLink = driver.findElement(By.cssSelector("#order-tab > a"));
		orderTabLink.click();

		// 5-) Select "SportsEquipment" from Product dropdown.
		WebElement productDropdownElement = driver.findElement(By.id("productSelect"));
		Select productDropdown = new Select(productDropdownElement);
		productDropdown.selectByVisibleText(orderInformation.get(0));

		// 6-) Enter "1" as quantity number
		WebElement quantityInputField = driver.findElement(By.id("quantityInput"));
		quantityInputField.sendKeys(orderInformation.get(1));

		// 7-) Enter "10" as discount percentage.
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
		WebElement zipField = driver.findElement(By.id("zip"));
		zipField.sendKeys(orderInformation.get(7));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,1000)");

		Thread.sleep(1000);

		// 14-) Enter "4938220192845" as Card Number.
		WebElement cardNumber = driver.findElement(By.id("cardNumber"));
		cardNumber.sendKeys(orderInformation.get(8));

		// 15-) Enter "09/26" Expire Date(mm/yy format).
		WebElement expireDate = driver.findElement(By.id("expiryDate"));
		expireDate.sendKeys(orderInformation.get(9));

		// 16-) Click "Process"" button.
		WebElement processButton = driver.findElement(By.xpath("//button[text()='Process']"));
		processButton.click();

		Thread.sleep(2000);

		// 17-) Verify the Card Type error message is displayed.
		WebElement cardTypeErrorMessage = driver.findElement(By.cssSelector(
				"div.page-content.d-flex.justify-content-center.align-items-start.bg-primary-light div.w-100.d-flex.justify-content-center.align-items-center div.page-container.w-100.weborder-home div.row div.p-0.ps-md-5.col-md-10:nth-child(2) div.tab-content div.fade.order-tab.active.show.tab-pane div.order-page form.order-form div.form-group.align-items-start:nth-child(8) div.d-flex.flex-column.justify-content-start.align-items-start span.error.text-danger:nth-child(4) > em:nth-child(1)"));
		String message = cardTypeErrorMessage.getText();
		Assertions.assertEquals("Card type cannot be empty", message, "Card Type Error Message is not displayed!");

	}

}
