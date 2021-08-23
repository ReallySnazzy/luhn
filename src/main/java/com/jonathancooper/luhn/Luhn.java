package com.jonathancooper.luhn;

public class Luhn {
    private final LuhnCheckDigitGenerator checkDigitGenerator;

    public Luhn() {
        checkDigitGenerator = new LuhnCheckDigitGenerator();
    }    

    /**
     * Accepts a card number and determines if the card number is a valid number with respect to the
     * Luhn algorithm.
     * @param cardNumber the card number
     * @return true if the card number is valid according to the Luhn algorithm, false if not
     */
    public boolean isValidLuhn(String cardNumber) {
        return checkDigitGenerator.validate(cardNumber);
    }

    /**
     * Accepts a partial card number (excluding the last digit) and generates the appropriate Luhn
     * check digit for the number.
     * @param cardNumber the card number (not including a check digit)
     * @return the check digit
     */
    public String generateCheckDigit(String cardNumber) {
        return checkDigitGenerator.generateCheckDigit(cardNumber);
    }

    /**
     * Accepts two card numbers representing the starting and ending numbers of a range of card numbers
     * and counts the number of valid Luhn card numbers that exist in the range, inclusive.
     * @param startRange the starting card number of the range
     * @param endRange the ending card number of the range
     * @return the number of valid Luhn card numbers in the range, inclusive 
     */
    public int countRange(String startRange, String endRange) {
        LuhnRangeGenerator rangeGenerator = new LuhnRangeGenerator(startRange, endRange);
        int count = 0;
        if (checkDigitGenerator.validate(startRange)) {
            count++;
        }
        while (rangeGenerator.hasNext()) {
            rangeGenerator.next();
            if (checkDigitGenerator.validate(rangeGenerator.getCurrentCardNumber())) {
                count++;
            }
        }
        return count;
    }
}
