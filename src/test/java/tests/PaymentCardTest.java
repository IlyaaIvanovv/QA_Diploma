package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.SQLData;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import pages.OfferPage;
import pages.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.*;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentCardTest {
    OfferPage offerPage = new OfferPage();
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
        offerPage.openPaymentByCard();
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
}