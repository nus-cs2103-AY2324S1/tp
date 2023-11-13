package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PaidCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new PaidCommand object
 * (Make use of the template of DeleteCommandParser and did some modifications)
 */
public class PaidCommandParser implements Parser<PaidCommand> {
    private final Logger logger = LogsCenter.getLogger(getClass());
    /**
     * Parses the given {@code String} of arguments in the context of the PaidCommand
     * and returns a PaidCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PaidCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new PaidCommand(index);
        } catch (ParseException pe) {
            logger.info("[PaidCommandParser.parse()]: Invalid extraneous parameter");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PaidCommand.MESSAGE_USAGE), pe);
        }
    }

}
