package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("-n");
    public static final Prefix PREFIX_PERSON_ID = new Prefix("-id");
    public static final Prefix PREFIX_NOTE_ID = new Prefix("-nid");
    public static final Prefix PREFIX_EVENT_ID = new Prefix("-eid");
    public static final Prefix PREFIX_PHONE = new Prefix("-p");
    public static final Prefix PREFIX_EMAIL = new Prefix("-e");
    public static final Prefix PREFIX_ADDRESS = new Prefix("-a");
    public static final Prefix PREFIX_TAG = new Prefix("-t");
    public static final Prefix PREFIX_NOTE_TITLE = new Prefix("-tit");
    public static final Prefix PREFIX_NOTE_CONTENT = new Prefix("-con");
    public static final Prefix PREFIX_EVENT_NAME = new Prefix("-en");
    public static final Prefix PREFIX_EVENT_START_TIME = new Prefix("-st");
    public static final Prefix PREFIX_EVENT_END_TIME = new Prefix("-et");
    public static final Prefix PREFIX_EVENT_LOCATION = new Prefix("-loc");
    public static final Prefix PREFIX_EVENT_INFORMATION = new Prefix("-info");

}
