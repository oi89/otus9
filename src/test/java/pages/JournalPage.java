package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class JournalPage extends BasePage {
    @FindBy(css = "div.title span")
    private WebElement title;

    public JournalPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getTitleText() {
        // переходим на соседнюю вкладку, где открылась ссылка
        ArrayList<String> tabsWindows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabsWindows.get(1));

        return title.getText();
    }
}
