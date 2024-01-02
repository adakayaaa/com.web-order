import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/*
1-) Open the URL.
2-) Click "WebOrder" button on top bar.
3-) Enter invalid username "InvalidUserName" and/or password "InvalidPassword".
4-) Click on the "Login" button
5-) Verify that the appropriate error message is displayed.*/
public class WO_002_LP_02 extends Hooks {

	@Test
	void testLoginProcessWithInvalidCredentials() throws InterruptedException {
		// 2-) Click "WebOrder" button on top bar.
		WebElement webOrderLink = driver.findElement(By.xpath("/html/body/div/div/header/nav/div/div/a[1]"));
		webOrderLink.click();

		// 3-) Enter invalid username "InvalidUserName" and/or password "InvalidPassword".
		WebElement userNameInput = driver.findElement(By.id("login-username-input"));
		WebElement passwordInput = driver.findElement(By.id("login-password-input"));

		userNameInput.sendKeys("inar");
		passwordInput.sendKeys("Academy");

		// 4-) Click on the "Login" button
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		Thread.sleep(5000);

		// 5-) Verify that the appropriate error message is displayed.
		WebElement alertForUsername = driver.findElement(By.id("username-error-alert"));
		String text = alertForUsername.getText();

		Assertions.assertEquals("Invalid username", text, "Invalid username.");

	}

}
