package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MatchCustomerCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Filters and lists all customers in address book whose budget and/or tags are selected.
 */
public class MatchCustomerCommandParser implements Parser<MatchCustomerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MatchCustomerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index index;

        try {
            index = ParserUtil.parseIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MatchCustomerCommand.MESSAGE_USAGE),
                    pe
            );
        }

        return new MatchCustomerCommand(index);
    }

}
