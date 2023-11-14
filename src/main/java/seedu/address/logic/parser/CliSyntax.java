package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_SCHEDULED = new Prefix("s/");
    public static final Prefix PREFIX_COMPANY_INDEX = new Prefix("c/");
    public static final Prefix PREFIX_INTERNSHIP_INDEX = new Prefix("i/");
    public static final Prefix PREFIX_RANGE_START = new Prefix("start/");
    public static final Prefix PREFIX_RANGE_END = new Prefix("end/");
}
