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
    public static final Prefix PREFIX_FINANCIAL_PLAN = new Prefix("fp/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_NEXT_OF_KIN_NAME = new Prefix("nk/");
    public static final Prefix PREFIX_NEXT_OF_KIN_PHONE = new Prefix("nkp/");
    public static final Prefix PREFIX_APPOINTMENT = new Prefix("ap/");
    public static final Prefix PREFIX_APPOINTMENT_DATE = new Prefix("d/");
}
