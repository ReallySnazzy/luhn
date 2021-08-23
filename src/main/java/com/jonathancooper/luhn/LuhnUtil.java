package com.jonathancooper.luhn;

class LuhnUtil {
    /**
     * Removes all non-digit characters
     *
     * @param cardNumber The card number to remove everything except digits from.
     */
    public static String removeNonDigits(String cardNumber) {
        return cardNumber.replaceAll("[^\\d]", "");
    }
}