package seedu.address.logic.parser;

/**
 * Contains all the regular expression in this repo.
 */
public class RegularExpressionUtil {
    public static final String NORMAL_TEXT = "([0-9a-zA-Z.,?! ]+)";
    public static final String STARTING_WITH_ONE_TO_FIVE_DIGITS = "(^\\d{1,5})(?:\\s+(.*))?$";
    public static final String STARTING_WITH_MORE_THAN_FIVE_DIGITS = "(^\\d{6})\\s*";
    public static final String TWO_DIGITS = "\\s*([0-9]{2})\\s*";
    public static final String ONE_TO_TWO_DIGITS = "\\s*([0-9]{1,2})\\s*";
    public static final String FOUR_DIGITS = "\\s*([0-9]{4})\\s*";
    public static final String STARTING_WITH_NEGATIVE_NUMBER = "^-(\\d+)\\s*";
    public static final String ABUSING_INDEX_WITH_DECIMAL_OR_DIVISION = "^\\d+\\.\\s*\\d+\\s*|^\\d+\\/\\s*\\d+\\s*";
}
