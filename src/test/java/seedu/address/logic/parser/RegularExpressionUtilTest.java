package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

class RegularExpressionUtilTest {
    private static boolean match(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input).matches();
    }

    /*
    Partition:
    empty string,
    string contains only space,small and large letter, number, special character,
    string with not allowed character.
    */
    @Test
    public void testNormalText() {
        assertFalse(match(RegularExpressionUtil.NORMAL_TEXT, ""));
        assertTrue(match(RegularExpressionUtil.NORMAL_TEXT, "hello"));
        assertTrue(match(RegularExpressionUtil.NORMAL_TEXT, "H e llo 12, . ? ! .."));
        assertFalse(match(RegularExpressionUtil.NORMAL_TEXT, "hello -123"));
    }

    @Test
    public void testStartingWithOneToFiveDigits() {
        assertFalse(match(RegularExpressionUtil.STARTING_WITH_ONE_TO_FIVE_DIGITS, ""));
        assertTrue(match(RegularExpressionUtil.STARTING_WITH_ONE_TO_FIVE_DIGITS, "0"));
        assertTrue(match(RegularExpressionUtil.STARTING_WITH_ONE_TO_FIVE_DIGITS, "12345"));
        assertTrue(match(RegularExpressionUtil.STARTING_WITH_ONE_TO_FIVE_DIGITS, "99999"));
        assertTrue(match(RegularExpressionUtil.STARTING_WITH_ONE_TO_FIVE_DIGITS, "00000"));
        assertFalse(match(RegularExpressionUtil.STARTING_WITH_ONE_TO_FIVE_DIGITS, "123456"));
        assertFalse(match(RegularExpressionUtil.STARTING_WITH_ONE_TO_FIVE_DIGITS, "a12345"));
        assertFalse(match(RegularExpressionUtil.STARTING_WITH_ONE_TO_FIVE_DIGITS, "12345a"));
        assertFalse(match(RegularExpressionUtil.STARTING_WITH_ONE_TO_FIVE_DIGITS, "-12345a"));
        assertTrue(match(RegularExpressionUtil.STARTING_WITH_ONE_TO_FIVE_DIGITS, "123 "));
        assertTrue(match(RegularExpressionUtil.STARTING_WITH_ONE_TO_FIVE_DIGITS, "123 a"));
        assertFalse(match(RegularExpressionUtil.STARTING_WITH_ONE_TO_FIVE_DIGITS, "123a"));
    }

    @Test
    public void testTwoDigits() {
        assertFalse(match(RegularExpressionUtil.TWO_DIGITS, ""));
        assertTrue(match(RegularExpressionUtil.TWO_DIGITS, "12"));
        assertFalse(match(RegularExpressionUtil.TWO_DIGITS, "0"));
        assertFalse(match(RegularExpressionUtil.TWO_DIGITS, "123"));
        assertTrue(match(RegularExpressionUtil.TWO_DIGITS, "99"));
        assertFalse(match(RegularExpressionUtil.TWO_DIGITS, "12a"));
        assertFalse(match(RegularExpressionUtil.TWO_DIGITS, "a12"));
        assertFalse(match(RegularExpressionUtil.TWO_DIGITS, "-12"));
    }

    @Test
    public void testOneToTwoDigits() {
        assertFalse(match(RegularExpressionUtil.ONE_TO_TWO_DIGITS, ""));
        assertTrue(match(RegularExpressionUtil.ONE_TO_TWO_DIGITS, "0"));
        assertTrue(match(RegularExpressionUtil.ONE_TO_TWO_DIGITS, "9"));
        assertTrue(match(RegularExpressionUtil.ONE_TO_TWO_DIGITS, "99"));
        assertFalse(match(RegularExpressionUtil.ONE_TO_TWO_DIGITS, "100"));
        assertFalse(match(RegularExpressionUtil.ONE_TO_TWO_DIGITS, "123"));
        assertFalse(match(RegularExpressionUtil.ONE_TO_TWO_DIGITS, "a1"));
        assertFalse(match(RegularExpressionUtil.ONE_TO_TWO_DIGITS, "1a"));
        assertFalse(match(RegularExpressionUtil.ONE_TO_TWO_DIGITS, "-1"));
    }
    @Test
    public void testFourDigits() {
        assertFalse(match(RegularExpressionUtil.FOUR_DIGITS, ""));
        assertTrue(match(RegularExpressionUtil.FOUR_DIGITS, "1234"));
        assertTrue(match(RegularExpressionUtil.FOUR_DIGITS, "0000"));
        assertTrue(match(RegularExpressionUtil.FOUR_DIGITS, "9999"));
        assertFalse(match(RegularExpressionUtil.FOUR_DIGITS, "0"));
        assertFalse(match(RegularExpressionUtil.FOUR_DIGITS, "12345"));
        assertFalse(match(RegularExpressionUtil.FOUR_DIGITS, "a1234"));
        assertFalse(match(RegularExpressionUtil.FOUR_DIGITS, "1234a"));
        assertFalse(match(RegularExpressionUtil.FOUR_DIGITS, "-1"));
    }

    @Test
    public void testStartWithNegativeNumber() {
        assertFalse(match(RegularExpressionUtil.STARTING_WITH_NEGATIVE_NUMBER, ""));
        assertTrue(match(RegularExpressionUtil.STARTING_WITH_NEGATIVE_NUMBER, "-1"));
        assertTrue(match(RegularExpressionUtil.STARTING_WITH_NEGATIVE_NUMBER, "-123"));
        assertFalse(match(RegularExpressionUtil.STARTING_WITH_NEGATIVE_NUMBER, "0"));
        assertFalse(match(RegularExpressionUtil.STARTING_WITH_NEGATIVE_NUMBER, "123"));
        assertFalse(match(RegularExpressionUtil.STARTING_WITH_NEGATIVE_NUMBER, "a123"));
        assertFalse(match(RegularExpressionUtil.STARTING_WITH_NEGATIVE_NUMBER, "123a"));
        assertFalse(match(RegularExpressionUtil.STARTING_WITH_NEGATIVE_NUMBER, "1-23"));
    }
    @Test
    public void test_decimalIndex_divisionIndex() {
        assertFalse(match(RegularExpressionUtil.ABUSING_INDEX_WITH_DECIMAL_OR_DIVISION, ""));
        assertTrue(match(RegularExpressionUtil.ABUSING_INDEX_WITH_DECIMAL_OR_DIVISION, "1.2"));
        assertTrue(match(RegularExpressionUtil.ABUSING_INDEX_WITH_DECIMAL_OR_DIVISION, "1/2"));
        assertFalse(match(RegularExpressionUtil.ABUSING_INDEX_WITH_DECIMAL_OR_DIVISION, "1"));
        assertTrue(match(RegularExpressionUtil.ABUSING_INDEX_WITH_DECIMAL_OR_DIVISION, "1/ 2"));
        assertTrue(match(RegularExpressionUtil.ABUSING_INDEX_WITH_DECIMAL_OR_DIVISION, "1. 2"));
    }
}

