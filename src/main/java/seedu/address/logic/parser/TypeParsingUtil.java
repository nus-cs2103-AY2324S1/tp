package seedu.address.logic.parser;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.parser.exceptions.FlagNotFoundException;
import seedu.address.logic.parser.exceptions.InvalidInputException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.exceptions.RepeatedFlagException;
import seedu.address.model.ListEntryField;
import seedu.address.model.util.Of;

// I am considering probably make sense to write specific parser inside each class.
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
            int num = Integer.parseInt(input);
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
     * Parses the strings from the input string
     * @param input the input string of substr seperated by ,
     */
    public static String[] parseStrs(String flagName, String input) throws ParseException {
        String strs = parseFlag(flagName, input);
        String[] strList = Arrays.stream(strs.split(",")).map(String::trim).toArray(String[]::new);
        if (strList.length < 1) {
            throw new InvalidInputException(strs + " is not a valid list of inputs");
        }
        return strList;
    }
    /**
     * overloading to not throw exception if the flag is not found when isOptional is true
     */
    public static String[] parseStrs(String flagName, String input, boolean isOptional) throws ParseException {
        if (isOptional) {
            try {
                parseFlag(flagName, input);
            } catch (FlagNotFoundException e) {
                return null;
            }
        }
        return parseStrs(flagName, input);
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
    public static String getValueImmediatelyAfterCommandName(String commandWord,
                                                             String errorFieldName,
                                                             String input,
                                                             boolean isOptional) throws ParseException {
        Pattern p = Pattern.compile(commandWord + "\\s+([\\w]+)");
        Pattern debugNegativeNumber = Pattern.compile(commandWord + "\\s+(-\\d+)");
        Matcher debugNegativeNumberMatcher = debugNegativeNumber.matcher(input);
        if (debugNegativeNumberMatcher.find()) {
            throw new InvalidInputException("Negative number is not allowed for " + errorFieldName + ".");
        }
        Matcher m = p.matcher(input);
        if (m.find()) {
            return m.group(1).trim();
        } else {
            if (isOptional) {
                return null;
            }
            throw new FlagNotFoundException(errorFieldName + " not found");
        }
    }

    public static String getValueImmediatelyAfterCommandName(String commandWord,
                                                             String errorFieldName,
                                                             String input) throws ParseException {
        return getValueImmediatelyAfterCommandName(commandWord, errorFieldName, input, false);
    }

    public static String getNumberImmediatelyAfterCommandName(String commandWord,
                                                             String errorFieldName,
                                                             String input, boolean isOptional) throws ParseException {
        Pattern p = Pattern.compile(commandWord + "\\s+(\\d+)");
        Pattern nagativeNumberPattern = Pattern.compile(commandWord + "\\s+(-\\d+)");
        Matcher m = p.matcher(input);
        if (m.find()) {
            return m.group(1).trim();
        } else if (nagativeNumberPattern.matcher(input).find()) {
            throw new InvalidInputException(errorFieldName + " cannot be negative");
        } else {
            if (isOptional) {
                return null;
            }
            throw new FlagNotFoundException(errorFieldName + " not found");
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
