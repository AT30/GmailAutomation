package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import pageClasses.HomePage;
import pageClasses.SignInPage;
import utilities.ParentTest;

public class TC_002_VerifyComposeEmail extends ParentTest {

	@Test
	public void composeEmail() {
		try {
			SignInPage signInPage = new SignInPage(driver);
			signInPage.setEmailId(getData("username"));
			signInPage.clickNext();
			signInPage.setPassword(getData("password"));
			HomePage homePage = signInPage.clickSignIn();
			
			boolean status = homePage.doesTitleContain(getData("username"));
			Assert.assertTrue(status == true,"User is not logged in");
			
			homePage.clickCompose();
			homePage.enterTo(getData("to"));
			homePage.enterSubject(getData("subject"));
			homePage.enterBody(getData("body"));
			homePage.clickSend();

	/*		boolean messageDisplayed = homePage.isMessageDisplayed("Your message has been sent");
			Assert.assertTrue(messageDisplayed == true, "Message is not sent");
	*/		
			Assert.assertTrue(homePage.isMessageDisplayed(getData("message")), "Message is not sent");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ATUReports.add("Failed", e.getMessage(), "", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
//			Assert.fail(e.getMessage());
		} catch (AssertionError e) {
			System.out.println(e.getMessage());
			ATUReports.add("Failed", e.getMessage(), "", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
//			Assert.fail(e.getMessage());
		}
	}
}
