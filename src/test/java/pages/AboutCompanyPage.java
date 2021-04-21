package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AboutCompanyPage extends BasePage {
    @FindBy(css = "div[class*='styles__Background'] div[class*='styles__Title']")
    private WebElement title;

    @FindBy(css = "div[class*='styles__Background'] a[href='/lessons'] button")
    private WebElement chooseCourseButton;

    public AboutCompanyPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getTitleText() {
        return title.getText().replaceAll("\n", " ");
    }

    public String getButtonText() {
        return chooseCourseButton.getText();
    }
}
