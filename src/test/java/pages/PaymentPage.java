package pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selectors.byText;

public class PaymentPage {

    private final SelenideElement paymentByCard = $$("h3").find(exactText("Оплата по карте"));
    private final SelenideElement paymentByCredit = $$("h3").find(exactText("Кредит по данным карты"));
    private final SelenideElement buttonBuy = $$(".button__content").find(exactText("Купить"));
    private final SelenideElement buttonCredit = $$(".button__content").find(exactText("Купить в кредит"));
    private final SelenideElement buttonContinue = $$(".button__content").find(exactText("Продолжить"));

    private final SelenideElement fieldCardNumber = $$(".input__inner").findBy(text("Номер карты")).$(".input__control");
    private final SelenideElement fieldMonth = $$(".input__inner").findBy(text("Месяц")).$(".input__control");
    private final SelenideElement fieldYear = $$(".input__inner").findBy(text("Год")).$(".input__control");
    private final SelenideElement fieldCardHolderName = $$(".input__inner").findBy(text("Владелец")).$(".input__control");
    private final SelenideElement fieldCVC = $$(".input__inner").findBy(text("CVC/CVV")).$(".input__control");

    public void setCardInfo(String cardNumber, String month, String year, String cardHolderName, String CVC) {
        fieldCardNumber.setValue(cardNumber);
        fieldMonth.setValue(month);
        fieldYear.setValue(year);
        fieldCardHolderName.setValue(cardHolderName);
        fieldCVC.setValue(CVC);
        buttonContinue.click();
    }

    public void openPaymentByCard() {
        buttonBuy.click();
        paymentByCard.shouldBe(visible);
    }

    public void openPaymentByCredit() {
        buttonCredit.click();
        paymentByCredit.shouldBe(visible);
    }

    private final SelenideElement success = $(byText("Операция одобрена Банком."));

    public void messageSuccess() {
        success.shouldBe(visible, Duration.ofSeconds(15));
    }

    private final SelenideElement error = $(byText("Ошибка! Банк отказал в проведении операции."));

    public void messageError() {
        error.shouldBe(visible, Duration.ofSeconds(15));
    }

    private final SelenideElement incorrectFormat = $(byText("Неверный формат"));

    public void messageIncorrectFormat() {
        incorrectFormat.shouldBe(visible);
    }

    private final SelenideElement requiredField = $(byText("Поле обязательно для заполнения"));

    public void messageRequiredField() {
        requiredField.shouldBe(visible);
    }

    private final SelenideElement incorrectIndicatedCardDate = $(byText("Неверно указан срок действия карты"));

    public void messageIncorrectIndicatedCardDate() {
        incorrectIndicatedCardDate.shouldBe(visible);
    }

    private final SelenideElement cardHasExpired = $(byText("Истёк срок действия карты"));

    public void messageCardHasExpired() {
        cardHasExpired.shouldBe(visible);
    }

    private final SelenideElement consistsOfLetters = $(byText("Должно состоять из букв"));

    public void messageConsistsOfLetters() {
        consistsOfLetters.shouldBe(visible);
    }
}