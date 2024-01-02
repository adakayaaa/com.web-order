
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

/*   1-) Open the URL.
        2-) Click "WebOrder" button on top bar.
        3-) Enter valid username "Inar" and password "Academy".
        4-) Navigate to the order page.
        5-) Select "FamilyAlbum" from Product dropdown.
        6-) Enter "3" as quantity number.
        7-) Enter "17" as discount percentage.
        8-) Enter "Inar Academy" as Name.
        9-) Enter "1100 Congress Ave" as Street.
        10-) Enter "Austin" as City.
        11-) Enter "TX" State.
        12-) Enter "78701" as Zip Code(number).
        13-) Select "Mastercard" as Card Type.
        14-) Enter "5162738261027163" as Card Number.
        15-) Enter "11/28" Expire Date(mm/yy format).
        16-) Click "Process"" button.
        17-) Verify the invalid Product Information error message is displayed.
    */

public class WO_007_OP_02 extends Hooks {

	List<String> orderInformation = new ArrayList<>();

	@Test
	void orderPlacementWithoutCalculation() throws InterruptedException {

		// 0.Product Name
		orderInformation.add("FamilyAlbum");

		// 1.Quantity Number
		orderInformation.add("3");

		// 2.Discount Percentage
		orderInformation.add("17");

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
		orderInformation.add("5162738261027163");

		// 9.Expire Date(mm/yy format)
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

		// 5-) Select "FamilyAlbum" from Product dropdown.
		WebElement productDropdownElement = driver.findElement(By.id("productSelect"));
		Select productDropdown = new Select(productDropdownElement);
		productDropdown.selectByVisibleText(orderInformation.get(0));

		// 6-) Enter "3" as quantity number
		WebElement quantityInputField = driver.findElement(By.id("quantityInput"));
		quantityInputField.sendKeys(orderInformation.get(1));

		// 7-) Enter "17" as discount percentage.
		WebElement discountInputField = driver.findElement(By.id("discountInput"));
		discountInputField.sendKeys(orderInformation.get(2));

		// 8-) Enter "Inar Academy" as Name.
		WebElement nameField = driver.findElement(By.id("name"));
		nameField.sendKeys(orderInformation.get(3));

		// 9-) Enter "1100 Congress Ave" as Street.
		WebElement streetField = driver.findElement(By.id("street"));
		streetField.sendKeys(orderInformation.get(4));

		// 10-) Enter "Austin" as City.
		WebElement cityField = driver.findElement(By.id("city"));
		cityField.sendKeys(orderInformation.get(5));

		// 11-) Enter "TX" State.
		WebElement stateField = driver.findElement(By.id("state"));
		stateField.sendKeys(orderInformation.get(6));

		// 12-) Enter "78701" as Zip Code(number).
		WebElement zipField = driver.findElement(By.id("zip"));
		zipField.sendKeys(orderInformation.get(7));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,1000)");

		Thread.sleep(1000);

		// 13-) Select "Mastercard" as Card Type.
		WebElement cardType = driver.findElement(By.id("mastercard"));
		cardType.click();

		// 14-) Enter "5162738261027163" as Card Number.
		WebElement cardNumber = driver.findElement(By.id("cardNumber"));
		cardNumber.sendKeys(orderInformation.get(8));

		// 15-) Enter "11/28" Expire Date(mm/yy format).
		WebElement expireDate = driver.findElement(By.id("expiryDate"));
		expireDate.sendKeys(orderInformation.get(9));

		// 16-) Click "Process"" button.
		WebElement processButton = driver.findElement(By.xpath("//button[text()='Process']"));
		processButton.click();

		Thread.sleep(5000);

		// 18-) Verify the invalid Product Information error message is displayed.
		WebElement confirmationMessage = driver
			.findElement(By.xpath("/html/body/div/div/div[1]/div/div/div[2]/div[2]/div/div/div/div/span/em"));
		String message = confirmationMessage.getText();
		Assertions.assertEquals("Fix errors in Product Information", message, "You must to click calculate button.");

	}

}
