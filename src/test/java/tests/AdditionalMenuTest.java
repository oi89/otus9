package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ContactsPage;
import pages.MainPage;

@Epic("Тесты на открытие страниц из вспомогательного верхнего меню")
@DisplayName("Тесты на открытие страниц из вспомогательного верхнего меню")
public class AdditionalMenuTest extends BaseTest {
    MainPage mainPage;
    ContactsPage contactsPage;

    @Test
    @Story("Проверка страницы с контактами")
    @Description("Тест проверяет заголовок страницы с контактами")
    @DisplayName("Проверка страницы с контактами")
    public void checkTeamLeadCoursePageTest() {
        String expectedTitle = "Контакты";

        mainPage = new MainPage(driver);
        contactsPage = new ContactsPage(driver);

        mainPage
                .open()
                .clickContactsLink();

        Assertions.assertEquals(expectedTitle, contactsPage.getTitleText(),
                String.format("Заголовок страницы должен быть равен %s", expectedTitle));
    }
}
