package shoppingapp;
/**
 *
 * @author ozgunkasapoglu
 */

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;

    public class CreditCard implements Serializable {
        private String cardNumber;
        private User creditCardUser;
        private String securityCode;
        private String expirationDate;
        private String cardholderName;

        public CreditCard() {
            generateRandomCardData();
        }

        public CreditCard(User user) {
            this.creditCardUser = user;
            generateRandomCardData();

            // If user is provided, use their name as cardholder
            if (user != null) {
                this.cardholderName = user.getName() + " " + user.getSurname();
            }
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public User getCreditCardUser() {
            return creditCardUser;
        }

        public void setCreditCardUser(User creditCardUser) {
            this.creditCardUser = creditCardUser;
        }

        public String getSecurityCode() {
            return securityCode;
        }

        public void setSecurityCode(String securityCode) {
            this.securityCode = securityCode;
        }

        public String getExpirationDate() {
            return expirationDate;
        }

        public void setExpirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
        }

        public String getCardholderName() {
            return cardholderName;
        }

        public void setCardholderName(String cardholderName) {
            this.cardholderName = cardholderName;
        }

        private void generateRandomCardData() {
            Random random = new Random();

            String[] prefixes = {"4", "51", "52", "53", "54", "55", "37", "34", "6011"};
            StringBuilder cardNumber = new StringBuilder(prefixes[random.nextInt(prefixes.length)]);

            while (cardNumber.length() < 16) {
                cardNumber.append(random.nextInt(10));
            }
            this.cardNumber = cardNumber.toString();

            // Always 3 digits for CVV
            this.securityCode = String.format("%03d", random.nextInt(1000));

            // Expiry: month 1-12, year >= current year
            int currentYear = LocalDate.now().getYear() % 100;
            int expiryYear = currentYear + random.nextInt(6); // 0-5 years ahead
            int expiryMonth = 6 + random.nextInt(7); // 1-12
            this.expirationDate = String.format("%02d/%02d", expiryMonth, expiryYear);

            if (this.cardholderName == null) {
                this.cardholderName = "Card Holder";
            }
        }

        public String getFormattedCardNumber() {
            if (cardNumber == null || cardNumber.length() < 16) {
                return cardNumber;
            }

            StringBuilder formatted = new StringBuilder();
            for (int i = 0; i < cardNumber.length(); i++) {
                if (i > 0 && i % 4 == 0) {
                    formatted.append(" ");
                }
                formatted.append(cardNumber.charAt(i));
            }
            return formatted.toString();
        }
    }