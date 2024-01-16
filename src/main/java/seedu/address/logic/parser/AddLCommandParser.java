package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddLCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.LinkedIn;

/**
 * Parses input arguments and creates a new AddLCommand object
 */
public class AddLCommandParser implements Parser<AddLCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddLCommand
     * and returns an AddLCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddLCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_USERNAME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultiMap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLCommand.MESSAGE_USAGE));
        }

        if (!arePrefixesPresent(argMultiMap, PREFIX_USERNAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLCommand.MESSAGE_USAGE));
        }

        index = ParserUtil.parseIndex(argMultiMap.getPreamble());
        String username = argMultiMap.getValue(PREFIX_USERNAME).orElse("");

        return new AddLCommand(index, new LinkedIn(username));
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
