package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import utilities.ParentPage;

public class SignInPage extends ParentPage {
	
	WebDriver pageDriver;
	
	@FindBy(id="identifierId")
	WebElement emailIdTextbox;
	
	@FindBy(xpath="//span[text()='Next']")
	WebElement nextButton;
	
	@FindBy(name="password")
	WebElement passwordTextbox;

	public SignInPage(WebDriver d) {
		super(d);
		pageDriver = d;
		PageFactory.initElements(pageDriver, this);
	}
	
	public String getEmailId() {
		/*waitForElement(emailIdTextbox, Condition.VISIBLE);
		return emailIdTextbox.getText();*/
		String text = getText(emailIdTextbox);
		return text;
	}
	
	public void setEmailId(String emailId) {
		enterText(emailIdTextbox, emailId);
		ATUReports.add("Enter Email Id", emailId, LogAs.PASSED, null);
	}
	
	public String getPassword() {
		return getText(passwordTextbox);
	}
	
	public void setPassword(String password) {
		enterText(passwordTextbox, password);
		ATUReports.add("Enter Password", password, LogAs.PASSED, null);
	}

	public void clickNext() {
		click(nextButton);
		ATUReports.add("Click Next", LogAs.PASSED, null);
	}
	
	public HomePage clickSignIn() {
		click(nextButton);
		System.out.println("Clicked SignIn");
		ATUReports.add("Click SignIn", LogAs.PASSED, null);
		return new HomePage(pageDriver);
	}
}
