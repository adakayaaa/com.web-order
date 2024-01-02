import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/*
1-) Open the URL
2-) Click "WebOrder" button on top bar.
3-) Enter valid username "Inar" and password "Academy".
4-) Click "Logout" button.
5-) Verify logout successfully.
*/
public class WO_003_LP_03 extends Hooks {

	@Test
	void testLogoutProcess() throws InterruptedException {
		WebElement webOrderLink = driver.findElement(By.xpath("/html/body/div/div/header/nav/div/div/a[1]"));
		webOrderLink.click();

		Thread.sleep(3000);

		// 3-) Enter "Inar" as username and "Academy" password.
		WebElement userNameInputField = driver.findElement(By.id("login-username-input"));
		WebElement passwordInputField = driver.findElement(By.id("login-password-input"));

		userNameInputField.sendKeys("Inar");
		passwordInputField.sendKeys("Academy");

		// 4-) Click on the "Login" button.
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		Thread.sleep(5000);

		// 4-) Click "Logout" button.
		WebElement logoutButton = driver.findElement(By.id("logout-button"));
		logoutButton.click();

		Thread.sleep(2000);

		// 5-) Verify logout successfully.

		try {
			WebElement usernameText = driver.findElement(By.id("login-username"));
			String text = usernameText.getText();

			Assertions.assertEquals("Inar", text, "Logout button is not working. ");

		}
		catch (NoSuchElementException exception) {
			throw new NoSuchElementException("Logout button is not working.");
		}

	}

}
