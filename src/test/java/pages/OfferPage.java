package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class OfferPage {

    private final SelenideElement journeyOfTheDay = $$("h2").find(exactText("Путешествие"));
    private final SelenideElement buttonBuy = $$(".button__content").find(exactText("Купить"));
    private final SelenideElement buttonCredit = $$(".button__content").find(exactText("Купить в кредит"));

    public void openOfferPage() {
        journeyOfTheDay.shouldBe(visible);
    }

    public PaymentPage buyByCard() {
        buttonBuy.click();
        return new PaymentPage();
    }

    public PaymentPage buyByCreditCard() {
        buttonCredit.click();
        return new PaymentPage();
    }
}