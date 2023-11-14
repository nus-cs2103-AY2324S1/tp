package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */

    /* Contacts */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_COMPANY = new Prefix("c/");
    public static final Prefix PREFIX_TELEGRAM_NAME = new Prefix("t/");

    /* Finance */
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_CLIENT = new Prefix("c/");
    public static final Prefix PREFIX_AMOUNT = new Prefix("a/");
    public static final Prefix PREFIX_TIME_DUE = new Prefix("t/");

    /* Events */
    public static final Prefix PREFIX_EVENT_NAME = new Prefix("n/");

    public static final Prefix PREFIX_TIME_START = new Prefix("s/");

    public static final Prefix PREFIX_TIME_END = new Prefix("e/");
    public static final Prefix PREFIX_LOCATION = new Prefix("l/");
}
