package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LICENCE_PLATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_ISSUE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.LicenceContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NricContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.PolicyExpiryContainsKeywordsPredicate;
import seedu.address.model.person.PolicyIssueContainsKeywordsPredicate;
import seedu.address.model.person.PolicyNumberContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG,
                        PREFIX_NRIC, PREFIX_LICENCE_PLATE, PREFIX_POLICY_NUMBER, PREFIX_POLICY_ISSUE_DATE,
                        PREFIX_POLICY_EXPIRY_DATE);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG,
                PREFIX_NRIC, PREFIX_LICENCE_PLATE, PREFIX_POLICY_NUMBER, PREFIX_POLICY_ISSUE_DATE,
                PREFIX_POLICY_EXPIRY_DATE);

        String nameKeyword = "";
        String licenceKeyword = "";
        String nricKeyword = "";
        String phoneKeyword = "";
        String policyNumberKeyword = "";
        String tagKeyword = "";
        String policyExpiryKeyword = "";
        String emailKeyword = "";
        String policyIssueKeyword = "";

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            nameKeyword = argMultimap.getValue(PREFIX_NAME).get();
        }
        if (argMultimap.getValue(PREFIX_LICENCE_PLATE).isPresent()) {
            licenceKeyword = argMultimap.getValue(PREFIX_LICENCE_PLATE).get();
        }
        if (argMultimap.getValue(PREFIX_NRIC).isPresent()) {
            nricKeyword = argMultimap.getValue(PREFIX_NRIC).get();
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            phoneKeyword = argMultimap.getValue(PREFIX_PHONE).get();
        }
        if (argMultimap.getValue(PREFIX_POLICY_NUMBER).isPresent()) {
            policyNumberKeyword = argMultimap.getValue(PREFIX_POLICY_NUMBER).get();
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            tagKeyword = argMultimap.getValue(PREFIX_TAG).get();
        }
        if (argMultimap.getValue(PREFIX_POLICY_EXPIRY_DATE).isPresent()) {
            policyExpiryKeyword = argMultimap.getValue(PREFIX_POLICY_EXPIRY_DATE).get();
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            emailKeyword = argMultimap.getValue(PREFIX_EMAIL).get();
        }
        if (argMultimap.getValue(PREFIX_POLICY_ISSUE_DATE).isPresent()) {
            policyIssueKeyword = argMultimap.getValue(PREFIX_POLICY_ISSUE_DATE).get();
        }

        return new FindCommand(new NameContainsKeywordsPredicate(nameKeyword),
                new LicenceContainsKeywordsPredicate(licenceKeyword),
                new NricContainsKeywordsPredicate(nricKeyword),
                new PhoneContainsKeywordsPredicate(phoneKeyword),
                new PolicyNumberContainsKeywordsPredicate(policyNumberKeyword),
                new TagContainsKeywordsPredicate(tagKeyword),
                new PolicyExpiryContainsKeywordsPredicate(policyExpiryKeyword),
                new EmailContainsKeywordsPredicate(emailKeyword),
                new PolicyIssueContainsKeywordsPredicate(policyIssueKeyword));
    }

}
