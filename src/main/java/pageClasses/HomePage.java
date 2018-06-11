package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
	}
	
	public void enterTo(String to) {
		enterText(toTexbox, to);
	}

	public void enterSubject(String subject) {
		enterText(subjectTexbox, subject);
	}
	
	public void enterBody(String body) {
		enterText(bodyTextbox, body);
	}
	
	public void clickSend() {
		click(sendButton);
	}
}