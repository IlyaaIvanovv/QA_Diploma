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
}
