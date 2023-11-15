package seedu.cc.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_NRIC = new Prefix("ic/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_AGE = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    public static final Prefix PREFIX_APPT_DATE = new Prefix("d/");
    public static final Prefix PREFIX_APPT_TIME = new Prefix("t/");

    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_MEDICAL_CONDITION = new Prefix("mc/");
    public static final Prefix PREFIX_TREATMENT = new Prefix("t/");
    public static final Prefix PREFIX_PATIENT_INDEX = new Prefix("pi/");

    public static final Prefix PREFIX_MEDICINE_NAME = new Prefix("mn/");
}
