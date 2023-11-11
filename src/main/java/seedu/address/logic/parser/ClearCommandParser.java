package seedu.address.logic.parser;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class ClearCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the ClearCommand
     * and returns a ClearCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearCommand parse(String args) throws ParseException {
        if (args.trim().isEmpty()) {
            return new ClearCommand("reset prompt");
        }
        try {
            String confirmation = ParserUtil.parseSimpleString(args);
            if (!confirmation.equals("confirm")) {
                throw new ParseException(
                        String.format(ClearCommand.MESSAGE_USAGE));
            }
            return new ClearCommand(confirmation);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(ClearCommand.MESSAGE_USAGE), pe);
        }
    }
}
