package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.SpecializationsListPage;

@Epic("Тесты на страницу списка специализаций")
@DisplayName("Тесты на страницу списка специализаций")
public class SpecializationListPageTest extends BaseTest {
    MainPage mainPage;
    SpecializationsListPage specializationsListPage;

    @Test
    @Story("Проверка блока специализации PHP разработчик")
    @Description("Тест проверяет заголовок блока специализации по PHP разработке")
    @DisplayName("Проверка блока специализации PHP разработчик")
    public void phpSpecializationBlockTest() {
        String expectedSpecTitle = "Специализация PHP разработчик";

        mainPage = new MainPage(driver);
        specializationsListPage = new SpecializationsListPage(driver);

        mainPage
                .open()
                .clickMainMenuByName("Курсы")
                .clickFirstLevelMenuByName("Специализации");

        Assertions.assertEquals(expectedSpecTitle,
                specializationsListPage.getSpecializationBlockTitle("/promo/spec_php/"),
                String.format("Заголовок страницы должен быть равен %s", expectedSpecTitle));


    }

    @Test
    @Story("Проверка блока специализации Java и Базы данных")
    @Description("Тест проверяет заголовок блока специализации по Java и базам данных")
    @DisplayName("Проверка блока специализации Java и Базы данных")
    public void javaDbSpecializationBlockTest() {
        String expectedSpecTitle = "Специализация Java и Базы данных";

        mainPage = new MainPage(driver);
        specializationsListPage = new SpecializationsListPage(driver);

        mainPage
                .open()
                .clickMainMenuByName("Курсы")
                .clickFirstLevelMenuByName("Специализации");

        Assertions.assertEquals(expectedSpecTitle,
                specializationsListPage.getSpecializationBlockTitle("/promo/java-database/"),
                String.format("Заголовок страницы должен быть равен %s", expectedSpecTitle));


    }
}
