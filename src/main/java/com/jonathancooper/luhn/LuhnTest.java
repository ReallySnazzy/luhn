package com.jonathancooper.luhn;

public class LuhnTest {
    public static void main(String[] args) {
        Luhn luhn = new Luhn();

        // Test if this is a valid card number
        String testValidNumber = "79927398713";
        if (!luhn.isValidLuhn(testValidNumber)) {
            throw new RuntimeException("Valid card number test failed");
        } else {
            System.out.println("Valid card number test passed");
        }

        // Test if this is an invalid card number
        String testInvalidNumber = "79927398723";
        if (luhn.isValidLuhn(testInvalidNumber)) {
            throw new RuntimeException("Invalid card number test failed");
        } else {
            System.out.println("Invalid card number test passed");
        }

        // Test if the generated check digit is as expected
        String testMissingCheckDigit = "7992739871";
        int checkDigit = 3;
        if (!luhn.generateCheckDigit(testMissingCheckDigit).equals(testMissingCheckDigit + checkDigit)) {
            throw new RuntimeException("Invalid check digit");
        } else {
            System.out.println("Check digit test passed");
        }

        // Test card generation
        LuhnRangeGenerator rangeGenerator = new LuhnRangeGenerator("79927398717", "79927398722");
        System.out.println(rangeGenerator.getCurrentCardNumber());
        while (rangeGenerator.hasNext()) {
            rangeGenerator.next();
            System.out.println(rangeGenerator.getCurrentCardNumber());
        }

        // Count valid range tests
        if (luhn.countRange("79927398713", "79927398713") != 1) {
            throw new RuntimeException("Count range test failed");
        } else if (luhn.countRange("79927398714", "79927398714") > 0) {
            throw new RuntimeException("Count range test failed");
        } else if (luhn.countRange("79927398713", "79927398900") <= 1) {
            throw new RuntimeException("Count range test failed");
        } else {
            System.out.println("Count range test passed");
        }
    }
}