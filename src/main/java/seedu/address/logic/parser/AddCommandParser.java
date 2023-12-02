package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_EMPTY_PREFIX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECRUITER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.company.ApplicationStatus;
import seedu.address.model.company.Company;
import seedu.address.model.company.Deadline;
import seedu.address.model.company.Email;
import seedu.address.model.company.Name;
import seedu.address.model.company.Phone;
import seedu.address.model.company.Priority;
import seedu.address.model.company.RecruiterName;
import seedu.address.model.company.Remark;
import seedu.address.model.company.Role;

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
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROLE,
                        PREFIX_DEADLINE, PREFIX_STATUS, PREFIX_RECRUITER_NAME, PREFIX_PRIORITY);

        // Checks for empty text after add word
        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_EMPTY_PREFIX, AddCommand.MESSAGE_USAGE));
        }

        // Checks for missing prefixes after add word
        if (!argMultimap.getPreamble().isEmpty()
                || !arePrefixesPresent(argMultimap, PREFIX_COMPANY_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROLE,
                PREFIX_DEADLINE, PREFIX_STATUS, PREFIX_RECRUITER_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COMPANY_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROLE,
                PREFIX_DEADLINE, PREFIX_STATUS, PREFIX_RECRUITER_NAME, PREFIX_PRIORITY);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_COMPANY_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Role role = ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get());
        Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        ApplicationStatus status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        RecruiterName recruiterName = ParserUtil.parseRecruiterName(argMultimap.getValue(PREFIX_RECRUITER_NAME).get());
        Priority priority = ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).orElse("NONE"));
        Remark remark = ParserUtil.parseRemark("No remarks");

        Company company = new Company(name, phone, email, role, deadline, status, recruiterName, priority, remark);

        return new AddCommand(company);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
