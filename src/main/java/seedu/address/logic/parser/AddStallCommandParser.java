package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddStallCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.stall.Location;
import seedu.address.model.stall.Name;
import seedu.address.model.stall.Stall;

/**
 * Parses input arguments and creates a new AddStallCommand object
 */
public class AddStallCommandParser implements Parser<AddStallCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddStallCommand
     * and returns an AddStallCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStallCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_LOCATION);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_LOCATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStallCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_LOCATION);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get());

        Stall stall = new Stall(name, location);

        return new AddStallCommand(stall);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
