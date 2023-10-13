package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_ID = new Prefix("id=");
    public static final Prefix PREFIX_NAME = new Prefix("name=");
    public static final Prefix PREFIX_GENDER = new Prefix("gender=");
    public static final Prefix PREFIX_BIRTHDATE = new Prefix("birthdate=");
    public static final Prefix PREFIX_PHONE = new Prefix("phone=");
    public static final Prefix PREFIX_EMAIL = new Prefix("email=");
    public static final Prefix PREFIX_ADDRESS = new Prefix("address=");
    public static final Prefix PREFIX_TAG = new Prefix("illness=");


    /* Prefixes for Appointment */
    public static final Prefix PREFIX_APPOINTMENT_DATE = new Prefix("date=");
    public static final Prefix PREFIX_APPOINTMENT_TIME = new Prefix("time=");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("description=");

}
