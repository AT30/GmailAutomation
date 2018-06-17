package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;

public class ParentPage {
	WebDriver driver;
	WebDriverWait wait = null;
	
	public ParentPage(WebDriver d) {
		driver = d;
		wait = new WebDriverWait(driver, Integer.parseInt(ParentTest.getConfig("WAIT_TIMEOUT_IN_SECONDS")));
	}
	
	public enum Condition {VISIBLE,CLICKABLE};
	
	public void click(WebElement element) {
		element = waitForElement(element, Condition.CLICKABLE);
		element.click();
	}
	
	public void enterText(WebElement element,String text) {
		element = waitForElement(element, Condition.VISIBLE);
		element.clear();
		element.sendKeys(text);
	}
	
	public void selectByVisibleText(WebElement element,String visibleText) {
		element = waitForElement(element, Condition.VISIBLE);
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
		ATUReports.add("Select dropdown by visible text", visibleText, LogAs.PASSED, null);
	}

	public void selectByIndex(WebElement element,int index) {
		element = waitForElement(element, Condition.VISIBLE);
		Select select = new Select(element);
		select.selectByIndex(index);
		ATUReports.add("Select dropdown value by index", index+"", LogAs.PASSED, null);
	}
	
	public void selectByValue(WebElement element,String value) {
		element = waitForElement(element, Condition.VISIBLE);
		Select select = new Select(element);
		select.selectByValue(value);
		ATUReports.add("Select dropdown by value", value, LogAs.PASSED, null);
	}

	public String getText(WebElement element) {
		element = waitForElement(element, Condition.VISIBLE);
		return element.getText();
	}
	
	public WebElement waitForElement(WebElement element,Condition condition) {
		try {
			switch (condition) {
			case VISIBLE:
				return wait.until(ExpectedConditions.visibilityOf(element));
			case CLICKABLE:
				return wait.until(ExpectedConditions.elementToBeClickable(element));
			default:
				return null;
			}	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void dragAndDrop(WebElement dragableElement,WebElement dropableElement) {
		dragableElement = waitForElement(dragableElement, Condition.CLICKABLE);
		dropableElement = waitForElement(dropableElement, Condition.CLICKABLE);
		Actions actions = new Actions(driver);
		actions
		.clickAndHold(dragableElement)
		.moveToElement(dropableElement).
		release(dropableElement)
		.build()
		.perform();
	}

	public boolean doesTitleContain(String text) {
		try {
			return wait.until(ExpectedConditions.titleContains(text));
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isMessageDisplayed(String message) {
		String finalXpath = getDynamicXpath("//*[contains(text(),'replaceThis')]", "replaceThis", message);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(finalXpath)));	
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public String getDynamicXpath(String rawXpath,String replace,String with) {
		return rawXpath.replace(replace, with);
	}
	
}
