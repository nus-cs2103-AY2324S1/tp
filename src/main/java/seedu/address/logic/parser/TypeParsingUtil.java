package seedu.address.logic.parser;

import static seedu.address.logic.parser.RegularExpressionUtil.STARTING_WITH_ONE_TO_FIVE_DIGITS;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.parser.exceptions.FlagNotFoundException;
import seedu.address.logic.parser.exceptions.InvalidInputException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.exceptions.RepeatedFlagException;
import seedu.address.model.ListEntryField;
import seedu.address.model.util.Of;

/**
 * Contains utility methods used for parsing strings into various desirable values, and validating them.
 */
public class TypeParsingUtil {
    /**
     * Parses the number from the input string
     * @param input the input string where the flag is to be parsed from
     * @param min the minimum value of the number
     * @param max the maximum value of the number
     * @return the number parsed
     * @throws ParseException if the input is not a valid number
     */
    public static Integer parseNum(String input, int min, int max) throws ParseException {
        try {
            int num = Integer.parseInt(input.trim());
            if (num < min || num > max) {
                throw new InvalidInputException("Number " + input + " is not of range: " + min + "-" + max);
            }
            return num;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(input + " is not a number");
        }
    }

    /**
     * overloading parseNum assuming no min and max
     */
    public static Integer parseNum(String input) throws ParseException {
        return parseNum(input, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }


    /**
     * Parses the flag from the input string
     * @param flag the flag to parse
     * @param input the input string where the flag is to be parsed from
     * @return the string after the flag
     * @throws FlagNotFoundException if the flag is not found
     * @throws RepeatedFlagException if more than one flag is found
     */
    public static String parseFlag(String flag, String input, boolean isOptional) throws ParseException {
        Pattern p = Pattern.compile("-" + flag + "\\s*([\\w-:,._/@#$%&! ]*)");
        Matcher m = p.matcher(input);
        if (m.find()) {
            String flagValue = m.group(1).trim();
            if (input.indexOf("-" + flag) != input.lastIndexOf("-" + flag)) {
                throw new RepeatedFlagException("Flag " + flag + " is repeated");
            }
            if (flagValue.contains(" -")) {
                flagValue = flagValue.substring(0, flagValue.indexOf(" -"));
            }
            return flagValue;
        } else {
            if (isOptional) {
                return null;
            }
            throw new FlagNotFoundException("Flag " + flag + " not found");
        }
    }
    public static String parseFlag(String flag, String input) throws ParseException {
        return parseFlag(flag, input, false);
    }
    //todo add to ug how index has a range of 1-99999
    /**
     * Parses the index from the input string
     */
    public static Integer parseIndex(String input, boolean isOptional) throws ParseException {
        Pattern p1 = Pattern.compile(STARTING_WITH_ONE_TO_FIVE_DIGITS);
        Pattern p2 = Pattern.compile(RegularExpressionUtil.STARTING_WITH_MORE_THAN_FIVE_DIGITS);
        Pattern p3 = Pattern.compile(RegularExpressionUtil.STARTING_WITH_NEGATIVE_NUMBER);
        Pattern p4 = Pattern.compile(RegularExpressionUtil.ABUSING_INDEX_WITH_DECIMAL_OR_DIVISION);
        Matcher m = p1.matcher(input);
        if (p4.matcher(input).find()) {
            throw new InvalidInputException("Index input cannot be a decimal or fraction, allowed range: 1-99999.");
        } else if (p2.matcher(input).find()) {
            throw new InvalidInputException("Index input is too large(allowed range: 1-99999) or exceeds five digits(starting zeros included)");
        } else if (m.find()) {
            String found = m.group(1);
            int ans = parseNum(found);
            if (ans == 0) {
                throw new InvalidInputException("Index input can not be zero, allowed range: 1-99999");
            } else {
                return ans;
            }
        } else if (p3.matcher(input).find()) {
            throw new InvalidInputException("Index cannot be negative, allowed range: 1-99999");
        } else {
            if (isOptional) {
                return null;
            }
            throw new FlagNotFoundException("Index not found");
        }
    }

    /**
     * Parses the flag's value from the input string
     */
    public static <T extends ListEntryField> T parseField(String flagName,
                                                          String input,
                                                          Of<T> of,
                                                          boolean isOptional) throws ParseException {
        String result = parseFlag(flagName, input, isOptional);
        if (result == null) {
            return null;
        } else {
            try {
                return of.apply(result);
            } catch (IllegalArgumentException e) {
                throw new InvalidInputException(result + " is not a valid " + flagName + " . " + e.getMessage());
            }
        }
    }
    public static <T extends ListEntryField> T parseField(String flagName,
                                                          String input, Of<T> of) throws ParseException {
        return parseField(flagName, input, of, true);
    }
}
