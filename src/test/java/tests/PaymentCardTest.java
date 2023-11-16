package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.SQLData;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import pages.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentCardTest {
    PaymentPage paymentPage = new PaymentPage();

    @BeforeAll
    static void addReport () {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void removeReport() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open(System.getProperty("sut.url"));
        paymentPage.openPaymentByCard();
    }

    @AfterEach
    void cleanDB() {
        SQLData.cleanDB();
    }

    @Test
    public void regularPurchaseOfATourUsingAnActiveCard() {
        val cardInfo = getFullValidCardWithApprovedStatus();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.messageSuccess();
        val expectedStatus = "APPROVED";
        val actualStatus = SQLData.getStatusLastPaymentTransaction();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void regularPurchaseOfATourUsingAnInactiveCard() {
        val cardInfo = getFullValidCardWithDeclinedStatus();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.messageError();
        val expectedStatus = "DECLINED";
        val actualStatus = SQLData.getStatusLastPaymentTransaction();
        assertEquals(expectedStatus, actualStatus);
    }

    //Валидация полей:
    //Номер карты
    @Test
    public void errorInvalidFormatInTheCardNumberWith1() {
        val cardInfo = getCardInfoWith1InCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.messageIncorrectFormat();
    }

    @Test
    public void errorInvalidFormatInTheCardNumberWith15() {
        val cardInfo = getCardInfoWith15InCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.messageIncorrectFormat();
    }

    @Test
    public void errorInvalidFormatInTheCardNumberWith16Zero() {
        val cardInfo = getCardInfoWith16ZeroInCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.messageIncorrectFormat();
    }

    @Test
    public void errorInvalidFormatInTheCardNumberWithEmpty() {
        val cardInfo = getCardInfoWithEmptyCardNumber();
        paymentPage.setCardInfo(cardInfo);
        paymentPage.messageIncorrectFormat();
    }

    //Месяц
}