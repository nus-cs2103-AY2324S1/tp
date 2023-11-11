package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands.
 * These definitions consist of prefixes used for parsing different types of user inputs.
 */
public class CliSyntax {

    /**
     * Prefix definition for room.
     */
    public static final Prefix PREFIX_ROOM = new Prefix("r/");

    /**
     * Prefix definition for name.
     */
    public static final Prefix PREFIX_NAME = new Prefix("n/");

    /**
     * Prefix definition for phone.
     */
    public static final Prefix PREFIX_PHONE = new Prefix("p/");

    /**
     * Prefix definition for email.
     */
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");

    /**
     * Prefix definition for booking period.
     */
    public static final Prefix PREFIX_BOOKING_PERIOD = new Prefix("d/");

    /**
     * Prefix definition for remark.
     */
    public static final Prefix PREFIX_REMARK = new Prefix("m/");
}
