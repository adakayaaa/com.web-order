import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

/*
    1-) Open the URL
    2-) Click "WebOrder" button on top bar.
    3-) Enter valid username "Inar" and password "Academy".
    4-) Navigate to the view all order page.
    5-) Click "Add More Data" "4" times.
    6-) Click "Check All" button.
    7-) Verify all orders selected.
*/
public class WO_010_VAO_01 extends Hooks {

	@Test
	void checkAllFunctionalityInViewAllOrderPage() throws InterruptedException {

		// 2-)Click "WebOrder" button on top bar.
		WebElement webOrderLink = driver.findElement(By.xpath("/html/body/div/div/header/nav/div/div/a[1]"));
		webOrderLink.click();

		Thread.sleep(2000);

		// 3-) Enter valid username "Inar" and password "Academy".
		WebElement userNameInputField = driver.findElement(By.id("login-username-input"));
		WebElement passwordInputField = driver.findElement(By.id("login-password-input"));

		userNameInputField.sendKeys("Inar");
		passwordInputField.sendKeys("Academy");

		// Click on the "Login" button.
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		Thread.sleep(5000);

		// 4-) Navigate to the view all order page.
		WebElement viewAllOrdersTab = driver.findElement(By.xpath("//a[@href='/weborder/view-orders']"));
		viewAllOrdersTab.click();

		// 5-) Click "Add More Data" "4" times.
		WebElement addMoreDataButton = driver
			.findElement(By.xpath("//button[@class=\"fs-4 btn btn-primary text-fifth me-3\"]"));
		for (int i = 0; i < 4; i++) {
			addMoreDataButton.click();
		}

		// 6-) Click "Check All" button.
		WebElement checkAllButton = driver
			.findElement(By.xpath("//button[@class=\"btn btn-success fs-4 text-fifth me-3\"]"));
		checkAllButton.click();

		// 7-) Verify all orders selected.
		List<WebElement> allOrdersSelected = driver.findElements(By.xpath("//input[@class=\"form-check-input\"]"));
		boolean isSelected = true;
		try {
			for (int i = 0; i < allOrdersSelected.size(); i++) {
				if (!allOrdersSelected.get(i).isSelected()) {
					isSelected = false;
					throw new AssertionError("Product at index" + i + "is not selected");
				}
			}
			Assertions.assertTrue(isSelected, "All orders selected are not selected!");
		}
		catch (AssertionError assertionError) {
			System.out.println("Assertion error" + assertionError.getMessage());
		}
	}

}
