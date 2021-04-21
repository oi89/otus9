package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.AboutCompanyPage;
import pages.CompanyLegalInfoPage;
import pages.MainPage;

@Epic("Тесты на раздел 'О нас'")
@DisplayName("Тесты на раздел 'О нас'")
public class AboutUsPageTest extends BaseTest {
    MainPage mainPage;
    AboutCompanyPage aboutCompanyPage;
    CompanyLegalInfoPage companyLegalInfoPage;

    @Test
    @Story("Проверка страницы 'О компании'")
    @Description("Тест проверяет заголовок и кнопку записи страницы 'О компании'")
    @DisplayName("Проверка страницы 'О компании'")
    public void aboutCompanyPageTest() {
        String expectedTitle = "О КОМПАНИИ OTUS";
        String expectedButtonName = "Выбрать направления обучения";

        mainPage = new MainPage(driver);
        aboutCompanyPage = new AboutCompanyPage(driver);

        mainPage
                .open()
                .clickMainMenuByName("О нас")
                .clickFirstLevelMenuByName("О компании");

        Assertions.assertEquals(expectedTitle, aboutCompanyPage.getTitleText(),
                String.format("Заголовок страницы должен быть равен %s", expectedTitle));

        Assertions.assertEquals(expectedButtonName, aboutCompanyPage.getButtonText(),
                String.format("На странице 'О компании' должна быть кнопка %s", expectedButtonName));
    }

    @Test
    @Story("Проверка страницы 'Сведения образовательной лицензии'")
    @Description("Тест проверяет заголовок страницы 'Сведения образовательной лицензии'")
    @DisplayName("Проверка страницы 'Сведения образовательной лицензии'")
    public void legalInfoPageTest() {
        String expectedTitle = "Правовая информация и шаблоны документов";

        mainPage = new MainPage(driver);
        companyLegalInfoPage = new CompanyLegalInfoPage(driver);

        mainPage
                .open()
                .clickMainMenuByName("О нас")
                .clickFirstLevelMenuByName("Сведения образовательной лицензии");

        Assertions.assertEquals(expectedTitle, companyLegalInfoPage.getTitleText(),
                String.format("Заголовок страницы должен быть равен %s", expectedTitle));
    }
}
