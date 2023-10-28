package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_LOCATION = new Prefix("l/");

    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_AGE = new Prefix("a/");
    public static final Prefix PREFIX_MEDICALHISTORY = new Prefix("m/");
    public static final Prefix PREFIX_SPECIALTY = new Prefix("s/");

    public static final Prefix PREFIX_COMMAND_WORD = new Prefix("kw/");
    public static final Prefix PREFIX_SHORTCUT = new Prefix("sc/");
    public static final Prefix PREFIX_THEME = new Prefix("th/");

    /* Tag definitions */
    public static final String PATIENT_TAG = "-pa";
    public static final String SPECIALIST_TAG = "-sp";

}
