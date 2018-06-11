package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageClasses.SignInPage;
import utilities.ParentTest;

public class TC_001_VerifyGmailSignIn extends ParentTest {
	@Test
	public void testGmailLogin() {
		try {
			SignInPage signInPage = new SignInPage(driver);
			signInPage.setEmailId(getData("username"));
			signInPage.clickNext();
			signInPage.setPassword(getData("password"));
			signInPage.clickNext();
			boolean status = signInPage.doesTitleContain(getData("username"));
			Assert.assertTrue(status == true,"User is not logged in");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.fail(e.getMessage());
		} catch (AssertionError e) {
			System.out.println(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}
}