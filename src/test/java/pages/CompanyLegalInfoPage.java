package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompanyLegalInfoPage extends BasePage {
    @FindBy(css = "div.title-new__text h1")
    private WebElement title;

    public CompanyLegalInfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getTitleText() {
        return title.getText();
    }
}
