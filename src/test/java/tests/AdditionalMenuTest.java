package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ContactsPage;
import pages.FaqPage;
import pages.JournalPage;
import pages.MainPage;

@Epic("Тесты на открытие страниц из вспомогательного верхнего меню")
@DisplayName("Тесты на открытие страниц из вспомогательного верхнего меню")
public class AdditionalMenuTest extends BaseTest {
    MainPage mainPage;
    ContactsPage contactsPage;
    FaqPage faqPage;
    JournalPage journalPage;

    @Test
    @Story("Проверка страницы с контактами")
    @Description("Тест проверяет заголовок страницы с контактами")
    @DisplayName("Проверка страницы с контактами")
    public void checkContactsPageTest() {
        String expectedTitle = "Контакты";

        mainPage = new MainPage(driver);
        contactsPage = new ContactsPage(driver);

        mainPage
                .open()
                .clickContactsLink();

        Assertions.assertEquals(expectedTitle, contactsPage.getTitleText(),
                String.format("Заголовок страницы должен быть равен %s", expectedTitle));
    }

    @Test
    @Story("Проверка страницы FAQ")
    @Description("Тест проверяет заголовок страницы FAQ")
    @DisplayName("Проверка страницы FAQ")
    public void checkFaqPageTest() {
        String expectedTitle = "Отвечаем на ваши вопросы";

        mainPage = new MainPage(driver);
        faqPage = new FaqPage(driver);

        mainPage
                .open()
                .clickFaqLink();

        Assertions.assertEquals(expectedTitle, faqPage.getTitleText(),
                String.format("Заголовок страницы должен быть равен %s", expectedTitle));
    }

    @Test
    @Story("Проверка страницы OTUS JOURNAL")
    @Description("Тест проверяет заголовок страницы OTUS JOURNAL")
    @DisplayName("Проверка страницы OTUS JOURNAL")
    public void checkJournalPageTest() {
        String expectedTitle = "JOURNAL";

        mainPage = new MainPage(driver);
        journalPage = new JournalPage(driver);

        mainPage
                .open()
                .clickJournalLink();

        Assertions.assertEquals(expectedTitle, journalPage.getTitleText(),
                String.format("Заголовок страницы должен быть равен %s", expectedTitle));
    }
}
