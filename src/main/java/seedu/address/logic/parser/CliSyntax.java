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

    public static final Prefix PREFIX_VIEW = new Prefix("v/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_SORT = new Prefix("so/");
    public static final Prefix PREFIX_STATUS = new Prefix("st/");
    public static final Prefix PREFIX_USERNAME = new Prefix("u/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_STARTTIME = new Prefix("bt/");
    public static final Prefix PREFIX_ENDTIME = new Prefix("et/");
    public static final Prefix PREFIX_SCORE = new Prefix("sc/");

    public static final Prefix PREFIX_METRIC = new Prefix("met/");
    public static final Prefix PREFIX_VALUE = new Prefix("val/");
}
