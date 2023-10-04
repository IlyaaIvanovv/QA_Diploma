package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private final SelenideElement fieldCardNumber = ;
    private final SelenideElement fieldMonth = ;
    private final SelenideElement fieldYear = ;
    private final SelenideElement fieldCardHolderName = ;
    private final SelenideElement fieldCVC = ;

    private final SelenideElement buttonContinue = $$(".button__content")
            .find(exactText("Продолжить"));
    public void pushButtonContinue() {
        buttonContinue.click();
    }

    private final SelenideElement success = $$(".notification__title")
            .find(exactText("Операция одобрена"));
    public void messageSuccess() {
        success.waitUntil(visible, 15000);
    }

    private final SelenideElement error = $$(".notification__title")
            .find(exactText("Ошибка!"));
    public void messageError() {
        error.waitUntil(visible, 15000);
    }

    private final SelenideElement incorrectFormat = $$(".input__sub")
            .find(exactText("Неверный формат"));
    public void messageIncorrectFormat() {
        incorrectFormat.waitUntil(visible, 15000);
    }

    private final SelenideElement requiredField = $$(".input__sub")
            .find(exactText("Поле обязательно для заполнения"));
    public void messageRequiredField() {
        requiredField.waitUntil(visible, 15000);
    }

    private final SelenideElement incorrectIndicatedCardDate = $$(".input__sub")
            .find(exactText("Неверно указан срок действия карты"));
    public void messageIncorrectIndicatedCardDate() {
        incorrectIndicatedCardDate.waitUntil(visible, 15000);
    }

    private final SelenideElement cardHasExpired = $$(".input__sub")
            .find(exactText("Истёк срок действия карты"));
    public void messageCardHasExpired() {
        cardHasExpired.waitUntil(visible, 15000);
    }
}