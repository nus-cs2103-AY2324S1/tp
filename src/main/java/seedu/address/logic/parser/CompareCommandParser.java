package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CompareCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CompareCommand object
 */
public class CompareCommandParser implements Parser<CompareCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CompareCommand
     * and returns a CompareCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public CompareCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = args.trim().split("\\s+");
            if (splitArgs.length != 2) {
                throw new ParseException("Invalid command format!\n"
                        + "Please follow the format: compare INDEX1 INDEX2.\n"
                        + "Parameters: INDEX (must be positive integers)");
            }
            Index index1 = ParserUtil.parseIndex(splitArgs[0]);
            Index index2 = ParserUtil.parseIndex(splitArgs[1]);
            return new CompareCommand(index1, index2);
        } catch (ParseException pe) {
            throw new ParseException("Invalid command format!\n"
                    + "Please follow the format: compare INDEX1 INDEX2.\n"
                    + "Parameters: INDEX (must be positive integers)");
        }
    }
}
