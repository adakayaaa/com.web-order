import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

/*
    1-) Open the URL
    2-) Click "WebOrder" button on top bar.
    3-) Enter valid username "Inar" and password "Academy".
    4-) Navigate to the view all order page.
    5-) Click "Add More Data" "8" times.
    6-) Click 1st, 3rd and 5th orders checkbox's.
    7-) Click "Delete All" button.
    8-) Verify the orders are deleted.
*/

public class WO_012_VAO_03 extends Hooks {
	@Test
	void deleteFunctionalityInViewAllOrderPage() throws InterruptedException {
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

		Thread.sleep(3000);

		// 4-) Navigate to the view all order page.
		WebElement viewAllOrdersTab = driver.findElement(By.xpath("//a[@href='/weborder/view-orders']"));
		viewAllOrdersTab.click();

		// 5-) Click "Add More Data" "8" times.
		WebElement addMoreDataButton = driver
			.findElement(By.xpath("//button[@class=\"fs-4 btn btn-primary text-fifth me-3\"]"));
		for (int i = 0; i < 8; i++) {
			addMoreDataButton.click();
		}

		// 6-) Click 1st, 3rd and 5th orders checkbox's.
		List<WebElement> ordersCheckbox = driver.findElements(By.xpath("//input[@class=\"form-check-input\"]"));
		double size = ordersCheckbox.size();
		System.out.println(size);
		ordersCheckbox.get(0).click();
		ordersCheckbox.get(2).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,300)");
		Thread.sleep(1000);

		ordersCheckbox.get(4).click();

		// 7-) Click "Delete All" button.
		WebElement deleteButton = driver.findElement(By.xpath("//button[@class=\"btn btn-danger fs-4 text-white \"]"));
		deleteButton.click();
		Thread.sleep(2000);

		List<WebElement> ordersCheckboxCheck = driver.findElements(By.xpath("//input[@class=\"form-check-input\"]"));
		double sizeCheck = ordersCheckboxCheck.size();
		System.out.println(sizeCheck);

		// 8-) Verify the orders are deleted.
		boolean isDeleted = true;
		if ((size - 3) != sizeCheck) {
			isDeleted = false;
		}
		Assertions.assertTrue(isDeleted, "The orders are not deleted!");
	}

}
