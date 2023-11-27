package data;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@NoArgsConstructor
public class DataHelper {

    // Status:
    public static String getApprovedStatus() {
        return "APPROVED";
    }

    public static String getDeclinedStatus() {
        return "DECLINED";
    }

    // CardNumber:
    public static String getValidCardNumberWithApprovedStatus() {
        return "4444 4444 4444 4441";
    }

    public static String getValidCardNumberWithDeclinedStatus() {
        return "4444 4444 4444 4442";
    }

    public static String getCardNumberFrom1Digit(String locale) {
        Locale localeEn = new Locale("en");
        Faker faker = new Faker(localeEn);
        String cardNumber = faker.number().digits(1);
        return cardNumber;
    }

    public static String getCardNumberFrom15Digit(String locale) {
        Locale localeEn = new Locale("en");
        Faker faker = new Faker(localeEn);
        String cardNumber = faker.number().digits(15);
        return cardNumber;
    }

    public static String getCardNumberFrom16Zero() {
        return "0000 0000 0000 0000";
    }

    public static String getEmptyCardNumber() {
        return "";
    }

    // Month:
    public static String getValidMonth() {
        String validMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        return validMonth;
    }

    public static String getMonthFrom1Digit(String locale) {
        Locale localeEn = new Locale("en");
        Faker faker = new Faker(localeEn);
        String month = faker.number().digits(1);
        return month;
    }

    public static String getEmptyMonth() {
        return "";
    }

    public static String getMonthFrom2Zero() {
        return "00";
    }

    public static String getMonthOver12() {
        String monthOver12 = LocalDate.now().plusMonths(20).format(DateTimeFormatter.ofPattern("MM"));
        return monthOver12;
    }

    // Year:
    public static String getValidYear() {
        String validYear = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("yy"));
        return validYear;
    }

    public static String getYearFrom1Digit(String locale) {
        Locale localeEn = new Locale("en");
        Faker faker = new Faker(localeEn);
        String year = faker.number().digits(1);
        return year;
    }

    public static String getEmptyYear() {
        return "";
    }

    public static String getPastYear() {
        String pastYear = LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
        return pastYear;
    }

    public static String getFutureYear() {
        String futureYear = LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
        return futureYear;
    }

    // CardHolderName:
    public static String getValidCardHolderName(String locale) {
        Locale localeEn = new Locale("en");
        Faker faker = new Faker(localeEn);
        String cardHolderName = faker.name().fullName();
        return cardHolderName;
    }

    public static String getCardHolderNameFullNumbers(String locale) {
        Locale localeEn = new Locale("en");
        Faker faker = new Faker(localeEn);
        String fullNumbers = faker.number().digits(5);
        return fullNumbers;
    }

    public static String getCardHolderNameFromSpecialCharacters() {
        return "#!№&:%;*";
    }

    public static String getCardHolderNameFromSpaces() {
        return "   ";
    }

    public static String getEmptyCardHolderName() {
        return "";
    }

    // CVC/CVV:
    public static String getValidCVC(String locale) {
        Locale localeEn = new Locale("en");
        Faker faker = new Faker(localeEn);
        String CVC = faker.number().digits(3);
        return CVC;
    }

    public static String getCVCFrom1Digit(String locale) {
        Locale localeEn = new Locale("en");
        Faker faker = new Faker(localeEn);
        String CVC = faker.number().digits(1);
        return CVC;
    }

    public static String getEmptyCVC() {
        return "";
    }
}