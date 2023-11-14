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
    public static final Prefix PREFIX_APPLICANT = new Prefix("app/");
    public static final Prefix PREFIX_JOB_ROLE = new Prefix("jr/");
    public static final Prefix PREFIX_START_TIME = new Prefix("start/");
    public static final Prefix PREFIX_END_TIME = new Prefix("end/");

}
