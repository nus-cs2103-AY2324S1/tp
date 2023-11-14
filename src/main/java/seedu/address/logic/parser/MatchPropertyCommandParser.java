package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MatchPropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Match and lists all customers in address book whose budget and/or tags satisfy with the property.
 */
public class MatchPropertyCommandParser implements Parser<MatchPropertyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MatchPropertyCommand
     * and returns a MatchPropertyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MatchPropertyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index index;

        try {
            index = ParserUtil.parseIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MatchPropertyCommand.MESSAGE_USAGE),
                    pe
            );
        }

        return new MatchPropertyCommand(index);
    }

}
