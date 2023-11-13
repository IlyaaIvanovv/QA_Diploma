package pages;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {

    private final SelenideElement paymentByCard = $$("h3")
            .find(exactText("Оплата по карте"));
    private final SelenideElement paymentByCredit = $$("h3")
            .find(exactText("Кредит по данным карты"));
    private final SelenideElement buttonBuy = $$(".button__content")
            .find(exactText("Купить"));
    private final SelenideElement buttonCredit = $$(".button__content")
            .find(exactText("Купить в кредит"));

    private final SelenideElement fieldCardNumber = $$(".input__control")
            .find(exactText("Номер карты"));
    private final SelenideElement fieldMonth = $$(".input__control")
            .find(exactText("Месяц"));
    private final SelenideElement fieldYear = $$(".input__control")
            .find(exactText("Год"));
    private final SelenideElement fieldCardHolderName = $$(".input__control")
            .find(exactText("Владелец"));
    private final SelenideElement fieldCVC = $$(".input__control")
            .find(exactText("CVC/CVV"));

    public void setCardInfo (DataHelper.CardInfo cardInfo) {
        fieldCardNumber.setValue(cardInfo.getCardNumber());
        fieldMonth.setValue(cardInfo.getCardMonth());
        fieldYear.setValue(cardInfo.getCardYear());
        fieldCardHolderName.setValue(cardInfo.getCardHolder());
        fieldCVC.setValue(cardInfo.getCardCVV());
    }

    public void openPaymentByCard() {
        buttonBuy.click();
        paymentByCard.shouldBe(visible);
    }

    public void openPaymentByCredit() {
        buttonCredit.click();
        paymentByCredit.shouldBe(visible);
    }

    private final SelenideElement buttonContinue = $$(".button__content")
            .find(exactText("Продолжить"));
    public void pushButtonContinue() {
        buttonContinue.click();
    }

    private final SelenideElement success = $$(".notification__title")
            .find(exactText("Операция одобрена"));
    public void messageSuccess() {
        success.shouldBe(visible, Duration.ofSeconds(15));
    }

    private final SelenideElement error = $$(".notification__title")
            .find(exactText("Ошибка!"));
    public void messageError() {
        error.shouldBe(visible, Duration.ofSeconds(15));
    }

    private final SelenideElement incorrectFormat = $$(".input__sub")
            .find(exactText("Неверный формат"));
    public void messageIncorrectFormat() {
        incorrectFormat.shouldBe(visible);
    }

    private final SelenideElement requiredField = $$(".input__sub")
            .find(exactText("Поле обязательно для заполнения"));
    public void messageRequiredField() {
        requiredField.shouldBe(visible);
    }

    private final SelenideElement incorrectIndicatedCardDate = $$(".input__sub")
            .find(exactText("Неверно указан срок действия карты"));
    public void messageIncorrectIndicatedCardDate() {
        incorrectIndicatedCardDate.shouldBe(visible);
    }

    private final SelenideElement cardHasExpired = $$(".input__sub")
            .find(exactText("Истёк срок действия карты"));
    public void messageCardHasExpired() {
        cardHasExpired.shouldBe(visible);
    }

    private final SelenideElement consistsOfLetters = $$(".input__sub")
            .find(exactText("Должно состоять из букв"));
    public void messageConsistsOfLetters() {
        consistsOfLetters.shouldBe(visible);
    }
}