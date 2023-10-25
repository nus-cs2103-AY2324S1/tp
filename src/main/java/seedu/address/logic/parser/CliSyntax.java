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
    public static final Prefix PREFIX_NRIC = new Prefix("i/");
    public static final Prefix PREFIX_LICENCE_PLATE = new Prefix("l/");
    public static final Prefix PREFIX_COMPANY = new Prefix("c/");
    public static final Prefix PREFIX_POLICY_NUMBER = new Prefix("pn/");
    public static final Prefix PREFIX_POLICY_ISSUE_DATE = new Prefix("pi/");
    public static final Prefix PREFIX_POLICY_EXPIRY_DATE = new Prefix("pe/");
    public static final Prefix PREFIX_DELETE_MONTH = new Prefix("dm/");
}
