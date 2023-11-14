package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.clearcommands.ClearAllCommand;
import seedu.address.logic.commands.clearcommands.ClearCommand;
import seedu.address.logic.commands.clearcommands.ClearInternshipCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ClearCommand object
 */
public class ClearCommandParser implements Parser<ClearCommand> {
    /**
     * The argument for clearing internships.
     */
    public static final String CLEAR_INTERNSHIPS_ARG_WORD = "i";
    private static final Pattern ARGUMENT_REGEX_PATTERN =
            Pattern.compile("^(" + CLEAR_INTERNSHIPS_ARG_WORD + ")$");

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    /**
     * Parses the given {@code String} of arguments in the context of the ClearCommand
     * and returns a ClearCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new ClearAllCommand();
        }

        String[] typeIndex = trimmedArgs.split("\\s+");
        if (typeIndex.length != 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        }
        String type = typeIndex[0];
        logger.info("type: " + type);

        // Used to check if type is i.
        Matcher matcher = ARGUMENT_REGEX_PATTERN.matcher(type);
        if (!matcher.matches()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        }

        // Get index to delete from
        Index index = Index.fromOneBased(1);
        try {
            index = ParserUtil.parseIndex(typeIndex[1]);
            logger.info("index: " + index.toString());
        } catch (ParseException e) {
            // If index provided is not an integer
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        }
        return new ClearInternshipCommand(index);
    }

}
