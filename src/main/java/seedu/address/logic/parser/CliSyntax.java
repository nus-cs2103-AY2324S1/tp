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
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_TELEGRAM = new Prefix("tg/");
    public static final Prefix PREFIX_SECONDARY_EMAIL = new Prefix("e2/");
    public static final Prefix PREFIX_LINKEDIN = new Prefix("li/");

    public static final Prefix PREFIX_AVATAR = new Prefix("path/");

    public static final Prefix PREFIX_BIRTHDAY = new Prefix("b/");
    public static final Prefix PREFIX_NOTE = new Prefix("nt/");
    public static final Prefix PREFIX_BALANCE = new Prefix("bal/");

}
