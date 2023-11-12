package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.ArrayList;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddApplicantCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.fields.InterviewTime;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;

/**
 * Parses input arguments and creates a new AddApplicantCommand object
 */
public class AddApplicantCommandParser implements Parser<AddApplicantCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddApplicantCommand
     * and returns an AddApplicantCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddApplicantCommand parse(String args) throws ParseException {

        ArrayList<String> missingPrefixes = new ArrayList<>();

        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_INTERVIEW);

        if (argMultimap.getValue(PREFIX_NAME).isEmpty()) {
            missingPrefixes.add(Name.TYPE);
        }

        if (argMultimap.getValue(PREFIX_PHONE).isEmpty()) {
            missingPrefixes.add(Phone.TYPE);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    formatMissingPrefixesErrorMessage(missingPrefixes) + AddApplicantCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_INTERVIEW);

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        InterviewTime interviewTime = new InterviewTime("cancel");

        if (argMultimap.getValue(PREFIX_INTERVIEW).isPresent()) {
            interviewTime = ParserUtil.parseInterviewTime(argMultimap.getValue(PREFIX_INTERVIEW).get());
        }

        Applicant applicant = new Applicant(name, phone, interviewTime);

        return new AddApplicantCommand(applicant);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Formats the error message for missing prefixes to be displayed to the user.
     */
    private String formatMissingPrefixesErrorMessage(ArrayList<String> missingPrefixes) {
        StringBuilder errorMessage = new StringBuilder("Missing fields: ");
        int len = missingPrefixes.size();
        for (int i = 0; i < len; i++) {
            errorMessage.append(missingPrefixes.get(i));
            if (i >= len - 1) {
                break;
            }
            errorMessage.append(", ");
        }
        errorMessage.append(" is missing!\n");
        return errorMessage.toString();
    }
}
