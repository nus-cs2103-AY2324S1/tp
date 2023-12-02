package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */

    public static final Prefix PREFIX_USER = new Prefix("--user");
    public static final Prefix PREFIX_PASSWORD = new Prefix("--password");
    public static final Prefix PREFIX_PASSWORD_CONFIRM = new Prefix("--confirmPass");
    public static final Prefix PREFIX_NAME = new Prefix("--name");
    public static final Prefix PREFIX_PHONE = new Prefix("--phone");
    public static final Prefix PREFIX_EMAIL = new Prefix("--email");
    public static final Prefix PREFIX_ADDRESS = new Prefix("--address");
    public static final Prefix PREFIX_CUSTOMER_ID = new Prefix("--customer");
    public static final Prefix PREFIX_DATE = new Prefix("--date");
    public static final Prefix PREFIX_NOTE = new Prefix("--note");
    public static final Prefix PREFIX_SORT = new Prefix("--sort");
    public static final Prefix PREFIX_STATUS = new Prefix("--status");
    public static final Prefix PREFIX_SECRET_QUESTION = new Prefix("--secretQn");
    public static final Prefix PREFIX_ANSWER = new Prefix("--answer");

}
