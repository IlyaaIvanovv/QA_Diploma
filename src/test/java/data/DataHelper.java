package data;

import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor
public class DataHelper {
    @Value
    public static class CardInfo {
        String cardNumber;
        String cardMonth;
        String cardYear;
        String cardHolder;
        String cardCVV;
    }

    public static CardInfo getAllValidCardWithApprovedStatus() {
        return new CardInfo(
                "4444 4444 4444 4441",
                "10",
                "25",
                "Ivan Petrov",
                "545"
        );
    }

    public static CardInfo getAllValidCardWithDeclinedStatus() {
        return new CardInfo(
                "4444 4444 4444 4442",
                "10",
                "25",
                "Ivan Petrov",
                "545"
        );
    }
}
