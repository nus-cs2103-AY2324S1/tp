package profplan.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PRIORITY = new Prefix("p/");
    public static final Prefix PREFIX_RECURRING = new Prefix("recur/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_STATUS = new Prefix("s/");
    public static final Prefix PREFIX_LINK = new Prefix("l/");
    public static final Prefix PREFIX_DUEDATE = new Prefix("d/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("des/");

}
