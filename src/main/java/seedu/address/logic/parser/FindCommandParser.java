package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_EMPTY_FIELDS;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_PREAMBLE_DETECTED;
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

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicates.CompanyContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.LicenceContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NricContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PolicyExpiryContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PolicyIssueContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PolicyNumberContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG,
                        PREFIX_NRIC, PREFIX_LICENCE_PLATE, PREFIX_COMPANY,
                        PREFIX_POLICY_NUMBER, PREFIX_POLICY_ISSUE_DATE,
                        PREFIX_POLICY_EXPIRY_DATE);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_PREAMBLE_DETECTED));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG, PREFIX_COMPANY,
                PREFIX_NRIC, PREFIX_LICENCE_PLATE, PREFIX_POLICY_NUMBER, PREFIX_POLICY_ISSUE_DATE,
                PREFIX_POLICY_EXPIRY_DATE);

        // Get keywords to find by
        String nameKeyword = "";
        String licenceKeyword = "";
        String nricKeyword = "";
        String phoneKeyword = "";
        String policyNumberKeyword = "";
        String tagKeyword = "";
        String policyExpiryKeyword = "";
        String emailKeyword = "";
        String policyIssueKeyword = "";
        String companyKeyword = "";

        String emptyPrefixes = "";
        boolean atLeastOneFieldPresent = false;

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            nameKeyword = argMultimap.getValue(PREFIX_NAME).get();
            if (nameKeyword.isEmpty()) {
                emptyPrefixes += PREFIX_NAME + " ";
            }
            atLeastOneFieldPresent = true;
        }
        if (argMultimap.getValue(PREFIX_LICENCE_PLATE).isPresent()) {
            licenceKeyword = argMultimap.getValue(PREFIX_LICENCE_PLATE).get();
            if (licenceKeyword.isEmpty()) {
                emptyPrefixes += PREFIX_LICENCE_PLATE + " ";
            }
            atLeastOneFieldPresent = true;
        }
        if (argMultimap.getValue(PREFIX_NRIC).isPresent()) {
            nricKeyword = argMultimap.getValue(PREFIX_NRIC).get();
            if (nricKeyword.isEmpty()) {
                emptyPrefixes += PREFIX_NRIC + " ";
            }
            atLeastOneFieldPresent = true;
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            phoneKeyword = argMultimap.getValue(PREFIX_PHONE).get();
            if (phoneKeyword.isEmpty()) {
                emptyPrefixes += PREFIX_PHONE + " ";
            }
            atLeastOneFieldPresent = true;
        }
        if (argMultimap.getValue(PREFIX_POLICY_NUMBER).isPresent()) {
            policyNumberKeyword = argMultimap.getValue(PREFIX_POLICY_NUMBER).get();
            if (policyNumberKeyword.isEmpty()) {
                emptyPrefixes += PREFIX_POLICY_NUMBER + " ";
            }
            atLeastOneFieldPresent = true;
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            tagKeyword = argMultimap.getValue(PREFIX_TAG).get();
            if (tagKeyword.isEmpty()) {
                emptyPrefixes += PREFIX_TAG + " ";
            }
            atLeastOneFieldPresent = true;
        }
        if (argMultimap.getValue(PREFIX_POLICY_EXPIRY_DATE).isPresent()) {
            policyExpiryKeyword = argMultimap.getValue(PREFIX_POLICY_EXPIRY_DATE).get();
            if (policyExpiryKeyword.isEmpty()) {
                emptyPrefixes += PREFIX_POLICY_EXPIRY_DATE + " ";
            }
            atLeastOneFieldPresent = true;
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            emailKeyword = argMultimap.getValue(PREFIX_EMAIL).get();
            if (emailKeyword.isEmpty()) {
                emptyPrefixes += PREFIX_EMAIL + " ";
            }
            atLeastOneFieldPresent = true;
        }
        if (argMultimap.getValue(PREFIX_POLICY_ISSUE_DATE).isPresent()) {
            policyIssueKeyword = argMultimap.getValue(PREFIX_POLICY_ISSUE_DATE).get();
            if (policyIssueKeyword.isEmpty()) {
                emptyPrefixes += PREFIX_POLICY_ISSUE_DATE + " ";
            }
            atLeastOneFieldPresent = true;
        }
        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            companyKeyword = argMultimap.getValue(PREFIX_COMPANY).get();
            if (companyKeyword.isEmpty()) {
                emptyPrefixes += PREFIX_COMPANY + " ";
            }
            atLeastOneFieldPresent = true;
        }

        if (!atLeastOneFieldPresent) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (!emptyPrefixes.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_EMPTY_FIELDS, emptyPrefixes.trim()));
        }

        return new FindCommand(new NameContainsKeywordsPredicate(nameKeyword),
                new LicenceContainsKeywordsPredicate(licenceKeyword),
                new NricContainsKeywordsPredicate(nricKeyword),
                new PhoneContainsKeywordsPredicate(phoneKeyword),
                new PolicyNumberContainsKeywordsPredicate(policyNumberKeyword),
                new TagContainsKeywordsPredicate(tagKeyword),
                new PolicyExpiryContainsKeywordsPredicate(policyExpiryKeyword),
                new EmailContainsKeywordsPredicate(emailKeyword),
                new PolicyIssueContainsKeywordsPredicate(policyIssueKeyword),
                new CompanyContainsKeywordsPredicate(companyKeyword));
    }

}
