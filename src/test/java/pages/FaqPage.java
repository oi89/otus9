package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FaqPage extends BasePage {
    @FindBy(css = "h1.faq-header__text")
    private WebElement title;

    public FaqPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getTitleText() {
        return title.getText().replaceAll("\n", " ");
    }
}
