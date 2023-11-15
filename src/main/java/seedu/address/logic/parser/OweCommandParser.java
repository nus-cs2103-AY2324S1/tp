package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.OweCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Balance;

/**
 * Parses input arguments and creates a new OweCommand object
 */
public class OweCommandParser implements Parser<OweCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the OweCommand
     * and returns a OweCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public OweCommand parse(String args) throws ParseException {
        String[] splitArgs = args.trim().split("\\s", 2);

        if (splitArgs.length < 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    OweCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(splitArgs[0]);
        Balance balance = ParserUtil.parseBalance(splitArgs[1].trim());

        return new OweCommand(index, balance);
    }
}
