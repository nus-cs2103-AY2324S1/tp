package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple
 * commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_PREFERRED_MEETING_REGION = new Prefix("pmr/");
    public static final Prefix PREFIX_PREFERRED_CONTACT = new Prefix("pc/");
    public static final Prefix PREFIX_OLD_PASSWORD = new Prefix("op/");
    public static final Prefix PREFIX_NEW_PASSWORD = new Prefix("np/");
    public static final Prefix PREFIX_POLICY_NAME = new Prefix("pn/");
    public static final Prefix PREFIX_POLICY_DESCRIPTION = new Prefix("pd/");
    public static final Prefix PREFIX_POLICY_VALUE = new Prefix("pv/");
    public static final Prefix PREFIX_POLICY_START_DATE = new Prefix("psd/");
    public static final Prefix PREFIX_POLICY_END_DATE = new Prefix("ped/");
}
