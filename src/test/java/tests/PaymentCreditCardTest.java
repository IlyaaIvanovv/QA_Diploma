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

public class PaymentCreditCardTest {
    private OfferPage offerPage;
    private PaymentPage paymentPage;

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
        offerPage = open(System.getProperty("sut.url"), OfferPage.class);
        paymentPage = offerPage.buyByCreditCard();
    }

    @AfterEach
    void cleanDB() {
        SQLData.cleanDB();
    }

    @Test
    public void regularPurchaseOfATourOnCreditUsingAnActiveCard() {
        val cardNumber = DataHelper.getValidCardNumberWithApprovedStatus();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageSuccess();
        val expectedStatus = DataHelper.getApprovedStatus();
        val actualStatus = SQLData.getStatusLastCreditTransaction();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void regularPurchaseOfATourOnCreditUsingAnInactiveCard() {
        val cardNumber = DataHelper.getValidCardNumberWithDeclinedStatus();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageError();
        val expectedStatus = DataHelper.getDeclinedStatus();
        val actualStatus = SQLData.getStatusLastCreditTransaction();
        assertEquals(expectedStatus, actualStatus);
    }

    //Validation field:
    //Card Number
    @Test
    public void errorInvalidFormatInTheCreditCardNumberWith1() {
        val cardNumber = DataHelper.getCardNumberFrom1Digit("en");
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageIncorrectFormat();
    }

    @Test
    public void errorInvalidFormatInTheCreditCardNumberWith15() {
        val cardNumber = DataHelper.getCardNumberFrom15Digit("en");
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageIncorrectFormat();
    }

    @Test
    public void errorInvalidFormatInTheCreditCardNumberWith16Zero() {
        val cardNumber = DataHelper.getCardNumberFrom16Zero();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageIncorrectFormat();
    }

    @Test
    public void errorInvalidFormatInTheCreditCardNumberWithEmpty() {
        val cardNumber = DataHelper.getEmptyCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageIncorrectFormat();
    }

    //Month
    @Test
    public void errorInvalidFormatInTheCreditCardMonthWith1() {
        val cardNumber = DataHelper.getValidCardNumberWithApprovedStatus();
        val month = DataHelper.getMonthFrom1Digit("en");
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageIncorrectFormat();
    }

    @Test
    public void errorIncorrectIndicatedCardDateInTheCreditCardMonthWithOver12() {
        val cardNumber = DataHelper.getValidCardNumberWithApprovedStatus();
        val month = DataHelper.getMonthOver12();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageIncorrectIndicatedCardDate();
    }

    @Test
    public void errorIncorrectFormatCardDateInTheCreditCardMonthWith2Zero() {
        val cardNumber = DataHelper.getValidCardNumberWithApprovedStatus();
        val month = DataHelper.getMonthFrom2Zero();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageIncorrectFormat();
    }

    @Test
    public void errorInvalidFormatInTheCreditCardMonthWithEmpty() {
        val cardNumber = DataHelper.getValidCardNumberWithApprovedStatus();
        val month = DataHelper.getEmptyMonth();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageIncorrectFormat();
    }

    //Year
    @Test
    public void errorInvalidFormatInTheCreditCardYearWith1() {
        val cardNumber = DataHelper.getValidCardNumberWithApprovedStatus();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getYearFrom1Digit("en");
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageIncorrectFormat();
    }

    @Test
    public void errorCardHasExpiredInTheCreditCardYearWithPastYear() {
        val cardNumber = DataHelper.getValidCardNumberWithApprovedStatus();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getPastYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageCardHasExpired();
    }

    @Test
    public void errorIncorrectIndicatedCardDateInTheCreditCardYearWithFutureYear() {
        val cardNumber = DataHelper.getValidCardNumberWithApprovedStatus();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getFutureYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageIncorrectIndicatedCardDate();
    }

    @Test
    public void errorInvalidFormatInTheCreditCardYearWithEmpty() {
        val cardNumber = DataHelper.getValidCardNumberWithApprovedStatus();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getEmptyYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageIncorrectFormat();
    }

    //CardHolder Name
    @Test
    public void errorConsistsOfLettersInTheCreditCardHolderWithDigits() {
        val cardNumber = DataHelper.getValidCardNumberWithApprovedStatus();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getCardHolderNameFullNumbers("en");
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageConsistsOfLetters();
    }

    @Test
    public void errorConsistsOfLettersInTheCreditCardHolderWithSpecialCharacters() {
        val cardNumber = DataHelper.getValidCardNumberWithApprovedStatus();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getCardHolderNameFromSpecialCharacters();
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageConsistsOfLetters();
    }

    @Test
    public void errorRequiredFieldInTheCreditCardHolderWithSpaces() {
        val cardNumber = DataHelper.getValidCardNumberWithApprovedStatus();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getCardHolderNameFromSpaces();
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageRequiredField();
    }

    @Test
    public void errorRequiredFieldInTheCreditCardHolderWithEmpty() {
        val cardNumber = DataHelper.getValidCardNumberWithApprovedStatus();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getEmptyCardHolderName();
        val CVC = DataHelper.getValidCVC("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageRequiredField();
    }

    //CVC/CVV
    @Test
    public void errorInvalidFormatInTheCreditCardCVCWith1() {
        val cardNumber = DataHelper.getValidCardNumberWithApprovedStatus();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getCVCFrom1Digit("en");
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageIncorrectFormat();
    }

    @Test
    public void errorInvalidFormatInTheCreditCardCVCWithEmpty() {
        val cardNumber = DataHelper.getValidCardNumberWithApprovedStatus();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val cardHolderName = DataHelper.getValidCardHolderName("en");
        val CVC = DataHelper.getEmptyCVC();
        paymentPage.setCardInfo(cardNumber, month, year, cardHolderName, CVC);
        paymentPage.messageIncorrectFormat();
    }
}