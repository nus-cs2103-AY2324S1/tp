package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.ListCommand;

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

    public static final ArrayList<String> COMMAND_LIST = new ArrayList<String>(Arrays.asList(
        AddCommand.COMMAND_WORD,
        EditCommand.COMMAND_WORD, 
        DeleteCommand.COMMAND_WORD, 
        ClearCommand.COMMAND_WORD, 
        FindCommand.COMMAND_WORD,
        ListCommand.COMMAND_WORD));
}
