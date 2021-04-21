package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SpecializationsListPage extends BasePage {
    private String specializationBlockTitleLocator = "//a[@href='%s']//div[contains(@class, 'lessons__new-item-title')]";
    private WebElement specializationBlockTitle;

    private String specializationBlockLocator = "//a[@href='%s']";
    private WebElement specializationBlock;

    public SpecializationsListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getSpecializationBlockTitle(String blockHrefValue) {
        specializationBlockTitle = getWebElementByName(specializationBlockTitleLocator, blockHrefValue);

        return specializationBlockTitle.getText();
    }
}
