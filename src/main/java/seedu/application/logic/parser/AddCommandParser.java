package seedu.application.logic.parser;

import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.parser.CliSyntax.*;

import java.util.stream.Stream;

import seedu.application.logic.commands.AddCommand;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.job.*;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_ROLE, PREFIX_COMPANY, PREFIX_DEADLINE,
                    PREFIX_STATUS, PREFIX_INDUSTRY);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROLE, PREFIX_COMPANY)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ROLE, PREFIX_COMPANY, PREFIX_DEADLINE,
                PREFIX_STATUS, PREFIX_INDUSTRY);
        Role role = ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get());
        Company company = ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get());
        Deadline deadline = Deadline.EMPTY_DEADLINE; // Deadline is an optional field for add command
        Status status = Status.DEFAULT_STATUS; // Status is an optional field for add command
        Industry industry = Industry.DEFAULT_INDUSTRY; // Industry is an optional field for add command

        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        }

        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        }

        if (argMultimap.getValue(PREFIX_INDUSTRY).isPresent()) {
            industry = ParserUtil.parseIndustry(argMultimap.getValue(PREFIX_INDUSTRY).get());
        }

        Job job = new Job(role, company, deadline, status, industry);

        return new AddCommand(job);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
