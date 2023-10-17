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
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");


    //new prefixes for Person
    public static final Prefix PREFIX_NRIC = new Prefix("ic/");
    public static final Prefix PREFIX_GENDER = new Prefix("g/");


    //specific to patient
    public static final Prefix PREFIX_BLOODTYPE = new Prefix("b/");
    public static final Prefix PREFIX_CONDITION = new Prefix("c/");
    public static final Prefix PREFIX_EMERGENCY_CONTACT = new Prefix("ec/");
    public static final Prefix PREFIX_DOCTOR = new Prefix("d/");


    //specific to doctor
    public static final Prefix PREFIX_PATIENTS = new Prefix("pts/");
}
