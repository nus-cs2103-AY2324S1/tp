package seedu.flashlingo.logic.parser;

import seedu.flashlingo.commons.core.LogsCenter;
import seedu.flashlingo.logic.newcommands.NewCommand;
import seedu.flashlingo.logic.parser.exceptions.ParseException;

import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Parses user input.
 */
public class FlashlingoParser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(FlashlingoParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public NewCommand parseCommand(String userInput) throws ParseException {
        //TODO:
        return null;
    }
}