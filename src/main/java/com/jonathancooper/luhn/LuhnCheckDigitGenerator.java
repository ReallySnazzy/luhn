package com.jonathancooper.luhn;

class LuhnCheckDigitGenerator {
    /**
     * Calculates the check digit when provided a card number missing the check digit.
     *
     * @param cardNumber The card number with the check digit missing.
     * @return The calculated check digit
     */
    public String generateCheckDigit(String cardNumber) {
        int sum = sumDigits(cardNumber);
        int missingDigit = 10 - (sum % 10);
        return cardNumber + missingDigit;
    }


    /**
     * @return True if the provided card number is valid according to Luhn algorithm.
     */
    public boolean validate(String cardNumber) {
        // Keep only the digits
        String cardNumberNoExtras = LuhnUtil.removeNonDigits(cardNumber);
        // Check if the current check digit matches the expected check digit
        String cardNumberNoCheckDigit = cardNumberNoExtras.substring(0, cardNumber.length() - 1);
        return generateCheckDigit(cardNumberNoCheckDigit).equals(cardNumberNoExtras);
    }


    /**
     * @return The sum of the digits in the provided string.
     */
    private int sumDigits(String cardDigits) {
        // Keep only the digits
        String cardNumberNoExtras = LuhnUtil.removeNonDigits(cardDigits);
        // Count the sum of the digits
        int sum = 0;
        for (int i = 0; i < cardNumberNoExtras.length(); i++) {
            // Extract a single number from the card number
            char digitChar = cardNumberNoExtras.charAt(i);
            int digit = Character.getNumericValue(digitChar);
            // Increase the sum
            if (i % 2 == 1) {
                int doubleDigitValue = digit * 2;
                if (doubleDigitValue >= 10) {
                    sum += (doubleDigitValue % 10) + (doubleDigitValue / 10);
                } else {
                    sum += doubleDigitValue;
                }
            } else {
                sum += digit;
            }
        }
        return sum;
    }
}