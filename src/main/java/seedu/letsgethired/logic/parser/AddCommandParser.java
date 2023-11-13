package seedu.letsgethired.logic.parser;

import static seedu.letsgethired.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.letsgethired.model.application.Deadline.DEFAULT_DATE;

import java.util.stream.Stream;

import seedu.letsgethired.logic.commands.AddCommand;
import seedu.letsgethired.logic.parser.exceptions.ParseException;
import seedu.letsgethired.model.application.Company;
import seedu.letsgethired.model.application.Cycle;
import seedu.letsgethired.model.application.Deadline;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.model.application.Role;
import seedu.letsgethired.model.application.Status;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_COMPANY,
                        PREFIX_ROLE,
                        PREFIX_CYCLE,
                        PREFIX_STATUS,
                        PREFIX_DEADLINE);

        if (!arePrefixesPresent(argMultimap, PREFIX_COMPANY, PREFIX_ROLE, PREFIX_CYCLE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(
                PREFIX_COMPANY,
                PREFIX_ROLE,
                PREFIX_CYCLE,
                PREFIX_STATUS,
                PREFIX_DEADLINE);

        Company company = ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get());
        Role role = ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get());
        Cycle cycle = ParserUtil.parseCycle(argMultimap.getValue(PREFIX_CYCLE).get());
        Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS)
                .orElse("Pending"));
        Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE)
                .orElse(DEFAULT_DATE));

        InternApplication internApplication = new InternApplication(company, role, cycle, status, deadline);

        return new AddCommand(internApplication);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
