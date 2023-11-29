package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */

    // Commands for both patients and dentists.
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("h/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_BIRTHDATE = new Prefix("b/");
    public static final Prefix PREFIX_GENDER = new Prefix("g/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");

    // Commands for dentists only
    public static final Prefix PREFIX_DENTIST = new Prefix("dentist/");
    public static final Prefix PREFIX_SPECIALIZATION = new Prefix("s/");
    public static final Prefix PREFIX_YOE = new Prefix("y/");

    // Commands for patients only
    public static final Prefix PREFIX_PATIENT = new Prefix("patient/");

    public static final Prefix PREFIX_START = new Prefix("start/");

    // Commands for Treatment
    public static final Prefix PREFIX_TREATMENT = new Prefix("tr/");
    public static final Prefix PREFIX_COST = new Prefix("cs/");
    public static final Prefix PREFIX_TIME = new Prefix("ti/");


}
