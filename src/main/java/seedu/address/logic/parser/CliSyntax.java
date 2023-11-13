package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("/n ");
    public static final Prefix PREFIX_PHONE = new Prefix("/p ");
    public static final Prefix PREFIX_EMAIL = new Prefix("/e ");
    public static final Prefix PREFIX_NRIC = new Prefix("/ic ");
    public static final Prefix PREFIX_GENDER = new Prefix("/g ");
    public static final Prefix PREFIX_AGE = new Prefix("/age ");
    public static final Prefix PREFIX_ETHNIC = new Prefix("/eth ");

    public static final Prefix PREFIX_ADDRESS = new Prefix("/a ");
    public static final Prefix PREFIX_TAG = new Prefix("/t");
    public static final Prefix PREFIX_FOR = new Prefix("/for ");
    public static final Prefix PREFIX_DOC = new Prefix("/doc ");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("/d ");
    public static final Prefix PREFIX_APPOINTMENT = new Prefix("/appt ");
    public static final Prefix PREFIX_DATE = new Prefix("/on ");

}
