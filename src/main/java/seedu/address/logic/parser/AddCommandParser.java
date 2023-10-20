package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_MISSING_FIELDS_FOR_ADD_COMMAND;
import static seedu.address.logic.Messages.MESSAGE_MISSING_FIELDS_POLICY_FOR_ADD_COMMAND;
import static seedu.address.logic.Messages.MESSAGE_PREAMBLE_DETECTED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LICENCE_PLATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_ISSUE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.LicencePlate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyDate;
import seedu.address.model.policy.PolicyNumber;
import seedu.address.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NRIC,
                        PREFIX_LICENCE_PLATE, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_POLICY_NUMBER,
                        PREFIX_POLICY_ISSUE_DATE, PREFIX_POLICY_EXPIRY_DATE);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_PREAMBLE_DETECTED));
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NRIC,
                PREFIX_LICENCE_PLATE, PREFIX_ADDRESS)) {
            String errorMessage = MESSAGE_MISSING_FIELDS_FOR_ADD_COMMAND;
            if (argMultimap.getValue(PREFIX_NAME).isEmpty()) {
                errorMessage += "- Name("  + PREFIX_NAME + ") ";
            }
            if (argMultimap.getValue(PREFIX_PHONE).isEmpty()) {
                errorMessage += "- Phone(" + PREFIX_PHONE + ") ";
            }
            if (argMultimap.getValue(PREFIX_EMAIL).isEmpty()) {
                errorMessage += "- Email(" + PREFIX_EMAIL" + ") ";
            }
            if (argMultimap.getValue(PREFIX_NRIC).isEmpty()) {
                errorMessage += "- NRIC(i/) ";
            }
            if (argMultimap.getValue(PREFIX_LICENCE_PLATE).isEmpty()) {
                errorMessage += "- License Plate(" + PREFIX_LICENCE_PLATE + ") ";
            }
            if (argMultimap.getValue(PREFIX_ADDRESS).isEmpty()) {
                errorMessage += "- Address(" + PREFIX_ADDRESS + ") ";
            }
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, errorMessage));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NRIC,
                PREFIX_LICENCE_PLATE, PREFIX_ADDRESS);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        LicencePlate licencePlate = ParserUtil.parseLicencePlate(argMultimap.getValue(PREFIX_LICENCE_PLATE).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        if (!arePrefixesAbsent(argMultimap, PREFIX_POLICY_NUMBER, PREFIX_POLICY_ISSUE_DATE,
                PREFIX_POLICY_EXPIRY_DATE)) {
            if (!arePrefixesPresent(argMultimap, PREFIX_POLICY_NUMBER, PREFIX_POLICY_ISSUE_DATE,
                    PREFIX_POLICY_EXPIRY_DATE)) {
                String errorMessage = MESSAGE_MISSING_FIELDS_POLICY_FOR_ADD_COMMAND;
                if (argMultimap.getValue(PREFIX_POLICY_NUMBER).isEmpty()) {
                    errorMessage += "- Policy Number(pn/) ";
                }
                if (argMultimap.getValue(PREFIX_POLICY_ISSUE_DATE).isEmpty()) {
                    errorMessage += "- Policy Issue Date(pi/) ";
                }
                if (argMultimap.getValue(PREFIX_POLICY_EXPIRY_DATE).isEmpty()) {
                    errorMessage += "- Policy Expiry Date(pe/) ";
                }
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, errorMessage));
            }
        }

        // temporary variables for when no policy paramers were inputed
        PolicyNumber policyNumber = new PolicyNumber(PolicyNumber.DEFAULT_VALUE);
        PolicyDate policyIssueDate = new PolicyDate(PolicyDate.DEFAULT_VALUE);
        PolicyDate policyExpiryDate = new PolicyDate(PolicyDate.DEFAULT_VALUE);

        // if all details about policy exists
        if (arePrefixesPresent(argMultimap, PREFIX_POLICY_NUMBER, PREFIX_POLICY_ISSUE_DATE,
                PREFIX_POLICY_EXPIRY_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            policyNumber = ParserUtil.parsePolicyNumber(argMultimap.getValue(PREFIX_POLICY_NUMBER).get());
            policyIssueDate = ParserUtil.parsePolicyIssueDate(argMultimap.getValue(PREFIX_POLICY_ISSUE_DATE).get());
            policyExpiryDate = ParserUtil.parsePolicyExpiryDate(argMultimap.getValue(PREFIX_POLICY_EXPIRY_DATE).get());
        }

        Policy policy = new Policy(policyNumber, policyIssueDate, policyExpiryDate);

        Person person = new Person(name, phone, email, address, tagList, nric, licencePlate, policy);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if all of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesAbsent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isEmpty());
    }

}
