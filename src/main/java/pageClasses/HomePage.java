package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import utilities.ParentPage;

public class HomePage extends ParentPage {

	WebDriver driver;
	
	@FindBy(xpath="//div[text()='COMPOSE']") WebElement composeButton;
	@FindBy(name="to") WebElement toTexbox;
	@FindBy(name="subjectbox") WebElement subjectTexbox;
	@FindBy(xpath="//div[@aria-label='Message Body']") WebElement bodyTextbox;
	@FindBy(xpath="//div[text()='Send']") WebElement sendButton;
	
	public HomePage(WebDriver d) {
		super(d);
		driver = d;
		PageFactory.initElements(driver, this);
	}
	
	public void clickCompose() {
		click(composeButton);
		ATUReports.add("Click Compose button", LogAs.PASSED, null);
	}
	
	public void enterTo(String to) {
		enterText(toTexbox, to);
		ATUReports.add("Enter to", to, LogAs.PASSED, null);
	}

	public void enterSubject(String subject) {
		enterText(subjectTexbox, subject);
		ATUReports.add("Enter subject", subject, LogAs.PASSED, null);
	}
	
	public void enterBody(String body) {
		enterText(bodyTextbox, body);
		ATUReports.add("Enter body", body, LogAs.PASSED, null);
	}
	
	public void clickSend() {
		click(sendButton);
		ATUReports.add("Click Send", LogAs.PASSED, null);
	}
}
