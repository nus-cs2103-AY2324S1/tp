package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddGCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Github;

/**
 * Parses input arguments and creates a new AddLCommand object
 */
public class AddGCommandParser implements Parser<AddGCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddLCommand
     * and returns an AddLCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddGCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_USERNAME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultiMap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGCommand.MESSAGE_USAGE), pe);
        }

        index = ParserUtil.parseIndex(argMultiMap.getPreamble());
        String username = argMultiMap.getValue(PREFIX_USERNAME).orElse("");

        return new AddGCommand(index, new Github(username));
    }
}
