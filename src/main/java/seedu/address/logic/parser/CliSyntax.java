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

    public static final Prefix PREFIX_EVENT_DESCRIPTION = new Prefix("d/");

    public static final Prefix PREFIX_EVENT_START_DATE_TIME = new Prefix("ts/");

    public static final Prefix PREFIX_EVENT_END_DATE_TIME = new Prefix("te/");
    public static final Prefix PREFIX_CONFIRMATION = new Prefix("c/");
}
