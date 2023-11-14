package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("/n");
    public static final Prefix PREFIX_PHONE = new Prefix("/p");
    public static final Prefix PREFIX_EMAIL = new Prefix("/e");
    public static final Prefix PREFIX_ADDRESS = new Prefix("/a");
    public static final Prefix PREFIX_BANK_ACCOUNT = new Prefix("/b");
    public static final Prefix PREFIX_JOIN_DATE = new Prefix("/jd");
    public static final Prefix PREFIX_SALARY = new Prefix("/s");
    public static final Prefix PREFIX_ANNUAL_LEAVE = new Prefix("/l");
    public static final Prefix PREFIX_ATTENDANCE_TYPE = new Prefix("/at");
    public static final Prefix PREFIX_ADD_ANNUAL_LEAVE_ON = new Prefix("/on");
    public static final Prefix PREFIX_ADD_ANNUAL_LEAVE_FROM = new Prefix("/from");
    public static final Prefix PREFIX_ADD_ANNUAL_LEAVE_TO = new Prefix("/to");
    public static final Prefix PREFIX_VALUE = new Prefix("/v");
    public static final Prefix PREFIX_REASON = new Prefix("/r");
    public static final Prefix PREFIX_MONTH_YEAR = new Prefix("/t");
    public static final Prefix PREFIX_SLASH = new Prefix("/");

}
