package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;

public class OfferPage {

    private final SelenideElement journeyOfTheDay = $$("h2")
            .find(exactText("Путешествие"));
    private final SelenideElement title = $("[class='heading heading_size_m " +
            "heading_theme_alfa-on-white']");
    private final SelenideElement buttonBuy = $$(".button__content")
            .find(exactText("Купить"));
    private final SelenideElement buttonCredit = $$(".button__content")
            .find(exactText("Купить в кредит"));

    public void openOfferPage() {
        journeyOfTheDay.shouldBe(visible);
    }

    public PaymentPage buyByCard() {
        buttonBuy.click();
        title.shouldHave(exactText("Оплата по карте"));
        return new PaymentPage();
    }

    public PaymentPage buyByCreditCard() {
        buttonCredit.click();
        title.shouldHave(exactText("Кредит по данным карты"));
        return new PaymentPage();
    }
}