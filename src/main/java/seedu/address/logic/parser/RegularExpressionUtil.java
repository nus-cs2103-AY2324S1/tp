package seedu.address.logic.parser;

/**
 * Contains all the regular expression in this repo.
 */
public class RegularExpressionUtil {
    public static final String NORMAL_TEXT = "([0-9a-zA-Z.,?]+)";
    public static final String ONE_TO_FIVE_DIGITS = "([0-9]{1,5})";
    public static final String TWO_DIGITS = "([0-9]{2})";
    public static final String ONE_TO_TWO_DIGITS = "([0-9]{1,2})";
    public static final String FOUR_DIGITS = "([0-9]{4})";
    public static final String NEGATIVE_NUMBER = "(-[0-9]+)";
}
