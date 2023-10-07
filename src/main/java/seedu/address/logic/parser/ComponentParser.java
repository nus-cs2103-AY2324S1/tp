package seedu.address.logic.parser;

import java.util.regex.Pattern;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents an abstract parser for distinct components (e.g. AddressBook/Calendar).
 */
public abstract class ComponentParser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses a user input string into the appropriate command object.
     *
     * @param userInput The user input string to be parsed.
     * @return A command object corresponding to the user's command.
     * @throws ParseException If the user input cannot be parsed successfully.
     */
    public abstract Command parseCommand(String userInput) throws ParseException;
}
