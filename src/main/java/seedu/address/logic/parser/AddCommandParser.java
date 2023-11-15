package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_DATES_NOT_COMPATIBLE;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_MISSING_FIELDS_FOR_ADD_COMMAND;
import static seedu.address.logic.Messages.MESSAGE_MISSING_FIELDS_POLICY_FOR_ADD_COMMAND;
import static seedu.address.logic.Messages.MESSAGE_PREAMBLE_DETECTED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
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
import seedu.address.model.person.Remark;
import seedu.address.model.policy.Company;
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
                        PREFIX_LICENCE_PLATE, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_COMPANY,
                        PREFIX_POLICY_NUMBER, PREFIX_POLICY_ISSUE_DATE, PREFIX_POLICY_EXPIRY_DATE);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_PREAMBLE_DETECTED));
        }
        if (!hasAllCompulsoryPrefixes(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    generateMissingCompulsoryFieldsErrorMessages(argMultimap)));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NRIC,
                PREFIX_LICENCE_PLATE, PREFIX_ADDRESS);

        Policy policy;
        if (hasCompletePolicyInputsAndNoDuplicate(argMultimap)) {
            policy = createPolicy(argMultimap);
        } else {
            policy = Policy.createNewDefaultPolicy();
        }

        Person person = createPerson(argMultimap, policy);

        return new AddCommand(person);
    }

    /**
     * Returns true if all compulsory prefixes exist in the given {@code ArgumentMultimap}.
     */
    private boolean hasAllCompulsoryPrefixes(ArgumentMultimap argMultimap) {
        if (arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NRIC,
                PREFIX_LICENCE_PLATE, PREFIX_ADDRESS)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns error message indicates which compulsory fields are missing in the given
     * {@code ArgumentMultimap}.
     */
    private String generateMissingCompulsoryFieldsErrorMessages(ArgumentMultimap argMultimap) {
        String errorMessage = MESSAGE_MISSING_FIELDS_FOR_ADD_COMMAND;

        if (argMultimap.getValue(PREFIX_NAME).isEmpty()) {
            errorMessage += "- Name(" + PREFIX_NAME + ") ";
        }
        if (argMultimap.getValue(PREFIX_PHONE).isEmpty()) {
            errorMessage += "- Phone(" + PREFIX_PHONE + ") ";
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isEmpty()) {
            errorMessage += "- Email(" + PREFIX_EMAIL + ") ";
        }
        if (argMultimap.getValue(PREFIX_NRIC).isEmpty()) {
            errorMessage += "- NRIC(" + PREFIX_NRIC + ") ";
        }
        if (argMultimap.getValue(PREFIX_LICENCE_PLATE).isEmpty()) {
            errorMessage += "- Licence Plate(" + PREFIX_LICENCE_PLATE + ") ";
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isEmpty()) {
            errorMessage += "- Address(" + PREFIX_ADDRESS + ") ";
        }

        return errorMessage;
    }

    /**
     * Returns true if all prefixes about policy exist and contain values in the given {@code ArgumentMultimap}.
     * @throws ParseException if it contains incomplete information about policy
     */
    private boolean hasCompletePolicyInputsAndNoDuplicate(ArgumentMultimap argMultimap) throws ParseException {
        if (arePrefixesAbsent(argMultimap, PREFIX_COMPANY, PREFIX_POLICY_NUMBER, PREFIX_POLICY_ISSUE_DATE,
                PREFIX_POLICY_EXPIRY_DATE)) {
            return false;
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_COMPANY, PREFIX_POLICY_NUMBER, PREFIX_POLICY_ISSUE_DATE,
                PREFIX_POLICY_EXPIRY_DATE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    generateMissingPolicyFieldsErrorMessages(argMultimap)));
        } else {
            // all prefixes related to policy present
            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COMPANY, PREFIX_POLICY_NUMBER,
                    PREFIX_POLICY_ISSUE_DATE, PREFIX_POLICY_EXPIRY_DATE);
            return true;
        }
    }

    /**
     * Returns error message indicates which policy fields are missing in the given
     * {@code ArgumentMultimap}.
     */
    private String generateMissingPolicyFieldsErrorMessages(ArgumentMultimap argMultimap) {
        String errorMessage = MESSAGE_MISSING_FIELDS_POLICY_FOR_ADD_COMMAND;
        if (argMultimap.getValue(PREFIX_COMPANY).isEmpty()) {
            errorMessage += "- Company(" + PREFIX_COMPANY + ") ";
        }
        if (argMultimap.getValue(PREFIX_POLICY_NUMBER).isEmpty()) {
            errorMessage += "- Policy Number(" + PREFIX_POLICY_NUMBER + ") ";
        }
        if (argMultimap.getValue(PREFIX_POLICY_ISSUE_DATE).isEmpty()) {
            errorMessage += "- Policy Issue Date(" + PREFIX_POLICY_ISSUE_DATE + ") ";
        }
        if (argMultimap.getValue(PREFIX_POLICY_EXPIRY_DATE).isEmpty()) {
            errorMessage += "- Policy Expiry Date(" + PREFIX_POLICY_EXPIRY_DATE + ") ";
        }

        return errorMessage;
    }

    /**
     * Returns a policy based on the given {@code ArgumentMultimap}.
     * @throws ParseException if the value does not conform the expected format or
     *     policy expiry date is not after policy issue date
     */
    private Policy createPolicy(ArgumentMultimap argMultimap) throws ParseException {
        Company company = ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get());
        PolicyNumber policyNumber = ParserUtil.parsePolicyNumber(argMultimap.getValue(PREFIX_POLICY_NUMBER).get());
        PolicyDate policyIssueDate = ParserUtil.parsePolicyIssueDate(argMultimap
                .getValue(PREFIX_POLICY_ISSUE_DATE).get());
        PolicyDate policyExpiryDate = ParserUtil.parsePolicyExpiryDate(argMultimap
                .getValue(PREFIX_POLICY_EXPIRY_DATE).get());

        if (policyIssueDate.compareTo(policyExpiryDate) > 0) {
            throw new ParseException(MESSAGE_DATES_NOT_COMPATIBLE);
        }

        return new Policy(company, policyNumber, policyIssueDate, policyExpiryDate);
    }

    /**
     * Returns a person based on the given {@code ArgumentMultimap} and {@code Policy}.
     * @throws ParseException if the value does not conform the expected format
     */
    private Person createPerson(ArgumentMultimap argMultimap, Policy policy) throws ParseException {
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        LicencePlate licencePlate = ParserUtil.parseLicencePlate(argMultimap.getValue(PREFIX_LICENCE_PLATE).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Remark remark = new Remark("");

        return new Person(name, phone, email, address, tagList, nric, licencePlate, remark, policy);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if all prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesAbsent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isEmpty());
    }

}
