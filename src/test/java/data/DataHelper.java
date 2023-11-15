package data;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;
import lombok.Value;
import java.util.Locale;

@NoArgsConstructor
public class DataHelper {

    public static String generateCardHolderName(String locale) {
        Locale localeEn = new Locale("en");
        Faker faker = new Faker(localeEn);
        String cardHolderName = faker.name().fullName();
        return cardHolderName;
    }

    public static String generateCVV(String locale) {
        Locale localeEn = new Locale("en");
        Faker faker = new Faker(localeEn);
        String CVV = faker.
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String cardMonth;
        String cardYear;
        String cardHolder;
        String cardCVV;
    }

    public static CardInfo getFullValidCardWithApprovedStatus() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "10",
                "25",
                "Ivan Petrov",
                "545"
        );
    }

    public static CardInfo getFullValidCardWithDeclinedStatus() {
        return new CardInfo(
                "4444 4444 4444 4442",
                "10",
                "25",
                "Ivan Petrov",
                "545"
        );
    }

    public static CardInfo getCardInfoWith1InCardNumber() {
        return new CardInfo(
                "1",
                "10",
                "25",
                "Ivan Petrov",
                "545"
        );
    }

    public static CardInfo getCardInfoWith15InCardNumber() {
        return new CardInfo(
                "4444 4444 4444 444",
                "10",
                "25",
                "Ivan Petrov",
                "545"
        );
    }

    public static CardInfo getCardInfoWith16ZeroInCardNumber() {
        return new CardInfo(
                "0000 0000 0000 0000",
                "10",
                "25",
                "Ivan Petrov",
                "545"
        );
    }

    public static CardInfo getCardInfoWithEmptyCardNumber() {
        return new CardInfo(
                "",
                "10",
                "25",
                "Ivan Petrov",
                "545"
        );
    }

    public static CardInfo getCardInfoWith1InCardMonth() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "1",
                "25",
                "Ivan Petrov",
                "545"
        );
    }

    public static CardInfo getCardInfoWithOverageCardMonth() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "20",
                "25",
                "Ivan Petrov",
                "545"
        );
    }

    public static CardInfo getCardInfoWith2ZeroInCardMonth() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "00",
                "25",
                "Ivan Petrov",
                "545"
        );
    }

    public static CardInfo getCardInfoWithEmptyCardMonth() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "",
                "25",
                "Ivan Petrov",
                "545"
        );
    }

    public static CardInfo getCardInfoWith1InCardYear() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "10",
                "1",
                "Ivan Petrov",
                "545"
        );
    }

    public static CardInfo getCardInfoWithNumberLessThanCurrentYear() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "10",
                "10",
                "Ivan Petrov",
                "545"
        );
    }

    public static CardInfo getCardInfoWithNumberMoreThanCurrentYearBy6() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "10",
                "30",
                "Ivan Petrov",
                "545"
        );
    }

    public static CardInfo getCardInfoWithEmptyCardYear() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "10",
                "",
                "Ivan Petrov",
                "545"
        );
    }

    public static CardInfo getCardInfoWithNumbersInCardHolder() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "10",
                "25",
                "123456789",
                "545"
        );
    }

    public static CardInfo getCardInfoWithSymbolInCardHolder() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "10",
                "25",
                "~!@#$%^&*()_+",
                "545"
        );
    }

    public static CardInfo getCardInfoWithSpaceInCardHolder() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "10",
                "25",
                "   ",
                "545"
        );
    }

    public static CardInfo getCardInfoEmptyCardHolder() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "10",
                "25",
                "",
                "545"
        );
    }

    public static CardInfo getCardInfoWith1InCVC() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "10",
                "25",
                "Ivan Petrov",
                "1"
        );
    }

    public static CardInfo getCardInfoWithEmptyCVC() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "10",
                "25",
                "Ivan Petrov",
                ""
        );
    }
}
