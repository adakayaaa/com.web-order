
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/*1-) Open the URL.
2-) Click "WebOrder" button on top bar.
3-) Enter valid username "Inar" and password "Academy".
4-) Navigate to the order page.
5-) Select "MyMoney" from Product dropdown.
6-) Enter "8" as quantity number.
7-) Enter "20" as discount percentage.
8-) Click on the "Calculate" button.
9-) Enter "Inar Academy" as Name.
10-) Enter "1100 Congress Ave" as Street.
11-) Enter "Austin" as City.
12-) Enter "TX" State.
13-) Enter "78701" as Zip Code(number).
14-) Select "Visa" as Card Type.
15-) Enter "4938281746192845" as Card Number.
16-) Enter "11/28" Expire Date(mm/yy format).
17-) Click "Process"" button.
18-) Verify the confirmation message is displayed.
19-) Navigate to view all orders page.
20-) Verify the order is successfully placed.
*/

public class WO_006_OP_01 extends Hooks {

	List<String> orderInformation = new ArrayList<>();

	@Test
	void verifyOrderPlacement() throws InterruptedException {
		// Name
		orderInformation.add("Inar Academy");

		// Product Name
		orderInformation.add("MyMoney");

		// Quantity Number
		orderInformation.add("8");

		// Date
		orderInformation.add(DateTimeFormatter.ofPattern("MM/dd/yyyy").format(LocalDate.now()));

		// Street
		orderInformation.add("1100 Congress Ave");

		// City
		orderInformation.add("Austin");

		// State
		orderInformation.add("TX");

		// Zip Code
		orderInformation.add("78701");

		// Card Type
		orderInformation.add("Visa");

		// Card Number
		orderInformation.add("4938281746192845");

		// Expire Date(mm/yy format)
		orderInformation.add("11/28");

		// 2-) Click "WebOrder" button on top bar.
		WebElement webOrderLink = driver.findElement(By.xpath("/html/body/div/div/header/nav/div/div/a[1]"));
		webOrderLink.click();

		Thread.sleep(3000);

		// 3-) Enter "Inar" as username and "Academy" password.
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

		// 5-) Select "MyMoney" from Product dropdown.
		WebElement productDropdownElement = driver.findElement(By.id("productSelect"));
		Select productDropdown = new Select(productDropdownElement);
		productDropdown.selectByVisibleText(orderInformation.get(1));

		// 6-) Enter "8" as quantity number.
		WebElement quantityInputField = driver.findElement(By.id("quantityInput"));
		quantityInputField.sendKeys(orderInformation.get(2));

		// 7-) Enter "20" as discount percentage.
		WebElement discountInputField = driver.findElement(By.id("discountInput"));
		discountInputField.sendKeys("20");

		// 8-) Click on the "Calculate" button.
		WebElement calculateButton = driver.findElement(By.xpath("//button[text()='Calculate']"));
		calculateButton.click();

		// 9-) Enter "Inar Academy" as Name.
		WebElement nameField = driver.findElement(By.id("name"));
		nameField.sendKeys(orderInformation.get(0));

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

		// 14-) Select "Visa" as Card Type.
		WebElement cardType = driver.findElement(By.id("visa"));
		cardType.click();

		// 15-) Enter "4938281746192845" as Card Number.
		WebElement cardNumber = driver.findElement(By.id("cardNumber"));
		cardNumber.sendKeys(orderInformation.get(9));

		// 16-) Enter "11/28" Expire Date(mm/yy format).
		WebElement expireDate = driver.findElement(By.id("expiryDate"));
		expireDate.sendKeys(orderInformation.get(10));

		// 17-) Click "Process"" button.
		WebElement processButton = driver.findElement(By.xpath("//button[text()='Process']"));
		processButton.click();

		Thread.sleep(5000);

		// 18-) Verify the confirmation message is displayed.
		WebElement confirmationMessage = driver.findElement(By.cssSelector("div[role='alert']"));
		String message = confirmationMessage.getText();
		Assertions.assertEquals("New order has been successfully added.", message, "Confirmation message is wrong!");

		js.executeScript("window.scroll(0,-1000)");
		// Actions actions = new Actions(driver);
		// actions.keyDown(Keys.PAGE_UP).release().build().perform();

		Thread.sleep(1000);

		// 19-) Navigate to view all orders page.
		WebElement viewAllOrdersTab = driver.findElement(By.cssSelector("#view-orders-tab > a"));
		viewAllOrdersTab.click();

		// 20-) Verify the order is successfully placed.
		List<WebElement> tableRows = driver.findElements(By.cssSelector("tbody > tr"));
		List<WebElement> columnValuesInLastRow = tableRows.get(tableRows.size() - 1).findElements(By.xpath("td"));

		for (int i = 0; i < orderInformation.size(); i++) {
			String expectedValue = orderInformation.get(i);
			String actualValue = columnValuesInLastRow.get(i + 1).getText();

			Assertions.assertEquals(expectedValue, actualValue, "Wrong Order Information");
		}

	}

}
