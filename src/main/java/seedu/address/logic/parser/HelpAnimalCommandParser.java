package seedu.address.logic.parser;

import seedu.address.logic.commands.HelpAnimalCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@link HelpAnimalCommand} object.
 */
public class HelpAnimalCommandParser implements AnimalParser<HelpAnimalCommand> {
    private static final String VALIDATION_REGEX = "^[a-zA-Z]+$"; // Only alphabets, no whitespaces.

    /**
     * Parses the given {@code String} of arguments in the context of the HelpAnimalCommand
     * and returns a HelpAnimalCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpAnimalCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (!trimmedArgs.isEmpty() && !trimmedArgs.matches(VALIDATION_REGEX)) {
            throw new ParseException(HelpAnimalCommand.MESSAGE_USAGE);
        }
        return new HelpAnimalCommand(trimmedArgs);
    }
}
