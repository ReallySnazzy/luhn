package com.jonathancooper.luhn;

class LuhnRangeGenerator {
    private char[] currentCardNumber;

    private String endCardNumber;


    /**
     * Creates a new LuhnRangeGenerator that can generate card numbers between an inclusive range.
     *
     * @param startCardNumber The starting card number.
     * @param endCardNumber The ending card number.
     */
    public LuhnRangeGenerator(String startCardNumber, String endCardNumber) {
        startCardNumber = LuhnUtil.removeNonDigits(startCardNumber);
        this.currentCardNumber = startCardNumber.toCharArray();
        this.endCardNumber = LuhnUtil.removeNonDigits(endCardNumber);
    }

    
    /**
     * @return Gets the current card number that has been generated.
     */
    public String getCurrentCardNumber() {
        return new String(currentCardNumber);
    }


    /**
     * Updates the current card number in this generator to the next card number.
     * @throws RuntimeException If there are no more card numbers in the range.
     */
    public void next() {
        // Throw an exception if trying to go past the ending card number
        if (!hasNext()) {
            throw new RuntimeException("Out of card numbers to generate.");
        }
        // Keep increasing digits from right to left until there is no carry.
        for (int i = currentCardNumber.length - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(currentCardNumber[i]);
            digit += 1;
            currentCardNumber[i] = Character.forDigit(digit % 10, 10);
            if (digit < 10) {
                break;
            }
        }
    }


    /**
     * @return True if there are more card numbers in the range.
     */
    public boolean hasNext() {
        if (getCurrentCardNumber().equals(endCardNumber)) {
            return false;
        }
        return true;
    }
}