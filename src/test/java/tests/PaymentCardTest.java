package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQLData;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import pages.OfferPage;
import pages.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentCardTest {
    private OfferPage offerPage;
    private PaymentPage paymentPage;

    @BeforeAll
    static void addReport() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void removeReport() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        offerPage = open(System.getProperty("sut.url"), OfferPage.class);
        paymentPage = offerPage.buyByCard();
    }

    @AfterEach
    void cleanDB() {
        SQLData.cleanDB();
    }

    @Test
    public void regularPurchaseOfATourUsingAnActiveCard() {
        val cardNumber = DataHelper.getValidCardNumberWithApprovedStatus();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageSuccess();
        val expectedStatus = DataHelper.getApprovedStatus();
        val actualStatus = SQLData.getStatusLastPaymentTransaction();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void regularPurchaseOfATourUsingAnInactiveCard() {
        val cardNumber = DataHelper.getValidCardNumberWithDeclinedStatus();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageError();
        val expectedStatus = DataHelper.getDeclinedStatus();
        val actualStatus = SQLData.getStatusLastPaymentTransaction();
        assertEquals(expectedStatus, actualStatus);
    }

    //Validation field:
    //Card Number
    @Test
    public void errorInvalidFormatInTheCardNumberWith1() {
        val cardNumber = DataHelper.getCardNumberFrom1Digit("en");
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageIncorrectFormat();
    }

    @Test
    public void errorInvalidFormatInTheCardNumberWith15() {
        val cardNumber = DataHelper.getCardNumberFrom15Digit("en");
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageIncorrectFormat();
    }

    @Test
    public void errorInvalidFormatInTheCardNumberWith16Zero() {
        val cardNumber = DataHelper.getCardNumberFrom16Zero();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageIncorrectFormat();
    }

    @Test
    public void errorInvalidFormatInTheCardNumberWithEmpty() {
        val cardNumber = DataHelper.getEmptyCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageIncorrectFormat();
    }

    //Month
}