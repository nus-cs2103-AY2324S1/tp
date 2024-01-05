package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.ViewAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new ViewAttendanceCommand object.
 */
public class ViewAttendanceCommandParser implements Parser<ViewAttendanceCommand> {
    /**
     * Parses the given `args` and creates a new `ViewAttendanceCommand` object.
     *
     * @param args The input arguments provided by the user.
     * @return A `ViewAttendanceCommand` object.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public ViewAttendanceCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        Set<Name> names = ParserUtil.parseNames(argMultimap.getAllValues(PREFIX_NAME));

        return new ViewAttendanceCommand(names);
    }

    /**
     * Checks if the specified prefixes are present in the given ArgumentMultimap.
     *
     * @param argumentMultimap The ArgumentMultimap to check for prefix presence.
     * @param prefixes The prefixes to be checked.
     * @return `true` if all specified prefixes are present, `false` otherwise.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
