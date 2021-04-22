package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;

public class MainPage extends BasePage {
    private final String BASE_URL = "https://otus.ru/";

    private String coursesMenuLocator = "//div[contains(@class, 'header2-menu_main')]//p[contains(text(), '%s')]";
    private WebElement coursesMenu;

    private String firstLevelMenuLocator = "//div[contains(@class, 'header2-menu_main')]//a[@title='%s']";
    private WebElement firstLevelMenu;

    private String firstLevelMenuTriggerLocator = "//div[contains(@class, 'header2-menu_main')]//a[@title='%s']//div[contains(@class, 'js-menu-subdropdown-trigger')]";
    private WebElement firstLevelMenuTrigger;

    private String secondLevelMenuLocator = "//div[contains(@class, 'header2-menu__subdropdown')]//a[@title='%s']";
    private WebElement secondLevelMenu;

    private String cookiesAcceptButtonLocator = "button.cookies__button";
    private WebElement cookiesAcceptButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие главной страницы Отус")
    public MainPage open() {
        driver.get(BASE_URL);
        logger.info("Открыта главная страница otus.ru");

        tryToCloseCookiePanel();

        Allure.addAttachment("MainPage", new ByteArrayInputStream(
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        return this;
    }

    @Step("Клик на пункте главного меню {menuName}")
    public MainPage clickMainMenuByName(String menuName) {
        coursesMenu = getWebElementByName(coursesMenuLocator, menuName);
        coursesMenu.click();

        Allure.addAttachment("MainPage", new ByteArrayInputStream(
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        logger.info(String.format("Клик в меню '%s'", menuName));

        return this;
    }

    @Step("Клик на пункте меню {subMenuName} первого уровня вложенности")
    public MainPage clickFirstLevelMenuByName(String subMenuName) {
        firstLevelMenu = getWebElementByName(firstLevelMenuLocator, subMenuName);

        firstLevelMenu.click();

        Allure.addAttachment("MainPage", new ByteArrayInputStream(
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        logger.info(String.format("Клик на подпункт '%s'", subMenuName));

        return this;
    }

    @Step("Открытие меню {subMenuName} второго уровня вложенности")
    public MainPage openSecondLevelMenuByName(String subMenuName) {
        firstLevelMenuTrigger = getWebElementByName(firstLevelMenuTriggerLocator, subMenuName);

        actions.moveToElement(firstLevelMenuTrigger).build().perform();

        Allure.addAttachment("MainPage", new ByteArrayInputStream(
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        logger.info(String.format("Курсор наведен на пункт '%s'", subMenuName));

        return this;
    }

    @Step("Клик на пункте меню {subMenuName} второго уровня вложенности")
    public MainPage clickSecondLevelMenuByName(String subMenuName) {
        secondLevelMenu = getWebElementByName(secondLevelMenuLocator, subMenuName);

        actions
                .moveByOffset(100, 0)
                .moveToElement(secondLevelMenu)
                .click()
                .build()
                .perform();

        Allure.addAttachment("MainPage", new ByteArrayInputStream(
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        logger.info(String.format("Клик на подпункт '%s'", subMenuName));

        return this;
    }

    void tryToCloseCookiePanel() {
        try {
            Thread.sleep(3000);
            cookiesAcceptButton = new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cookiesAcceptButtonLocator)));
            cookiesAcceptButton.click();
            logger.info("Нажата кнопка OK с подтверждением куки");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            logger.info("Окно с согласием на обработку куки не было показано");
        }
    }
}
