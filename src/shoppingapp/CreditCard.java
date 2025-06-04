package shoppingapp;
/**
 *
 * @author ozgunkasapoglu
 */
    import java.time.LocalDate;
    import java.util.Random;

    public class CreditCard {
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

            this.securityCode = String.format("%03d", 100 + random.nextInt(900));

            int currentYear = LocalDate.now().getYear() % 100;
            int expiryYear = currentYear + 1 + random.nextInt(5);
            int expiryMonth = 1 + random.nextInt(12);
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