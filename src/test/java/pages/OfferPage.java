package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

public class OfferPage {
    private final SelenideElement buttonBuy = $$(".button__content")
            .find(exactText("Купить"));
    private final SelenideElement buttonCredit = $$(".button__content")
            .find(exactText("Купить в кредит"));
}