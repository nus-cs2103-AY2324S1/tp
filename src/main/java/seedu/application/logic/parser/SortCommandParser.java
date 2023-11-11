package seedu.application.logic.parser;

import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.parser.CliSyntax.*;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INDUSTRY;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import seedu.application.logic.commands.SortCommand;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.job.FieldComparator;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public SortCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROLE, PREFIX_COMPANY,
                        PREFIX_DEADLINE, PREFIX_STATUS, PREFIX_JOB_TYPE, PREFIX_INDUSTRY);

        if (!isValidArgMultimap(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        return new SortCommand(new FieldComparator(getPrefix(argMultimap)));
    }

    private boolean isValidArgMultimap(ArgumentMultimap argMultimap) {
        if (argMultimap.size() != 2) {
            return false;
        }

        for (Map.Entry<Prefix, List<String>> e: argMultimap.getArgMultimap().entrySet()) {
            if (!e.getValue().equals(Arrays.asList(""))) {
                return false;
            }
        }
        return true;
    }

    private Prefix getPrefix(ArgumentMultimap argMultimap) {
        for (Prefix p : argMultimap.getArgMultimap().keySet()) {
            if (!p.equals(new Prefix(""))) {
                return p;
            }
        }
        return null;
    }
}
