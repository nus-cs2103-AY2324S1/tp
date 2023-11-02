package transact.logic.parser;

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
    public static final Prefix PREFIX_TYPE = new Prefix("ty/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_AMOUNT = new Prefix("amt/");
    public static final Prefix PREFIX_DATE = new Prefix("on/");
    public static final Prefix PREFIX_DATE_SORTING = new Prefix("date/");
    public static final Prefix PREFIX_STAFF = new Prefix("s/");
    public static final Prefix PREFIX_DESCRIPTION_HAS = new Prefix("has/");
    public static final Prefix PREFIX_AFTER_DATE = new Prefix("after/");
    public static final Prefix PREFIX_BEFORE_DATE = new Prefix("before/");
    public static final Prefix PREFIX_MORE_THAN_AMOUNT = new Prefix("more/");
    public static final Prefix PREFIX_LESS_THAN_AMOUNT = new Prefix("less/");
    public static final Prefix PREFIX_BY_PERSON = new Prefix("by/");
    public static final Prefix PREFIX_DIRECTORY = new Prefix("f/");

}
