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
    public static final Prefix PREFIX_MEETING_TIME = new Prefix("m/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_KEY_MILESTONE = new Prefix("k/");

    public static final Prefix PREFIX_NAME_TP = new Prefix("--name");
    public static final Prefix PREFIX_PHONE_TP = new Prefix("--phone");
    public static final Prefix PREFIX_EMAIL_TP = new Prefix("--email");
    public static final Prefix PREFIX_ADDRESS_TP = new Prefix("--address");
    public static final Prefix PREFIX_MEETING_TIME_TP = new Prefix("--meeting-time");
    public static final Prefix PREFIX_TAG_TP = new Prefix("--tag");
    public static final Prefix PREFIX_KEYMILESTONE_TP = new Prefix("--key");
}
