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

import java.util.Arrays;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_TAG, PREFIX_NRIC, PREFIX_LICENCE_PLATE, PREFIX_COMPANY, PREFIX_POLICY_NUMBER,
                PREFIX_POLICY_ISSUE_DATE, PREFIX_POLICY_EXPIRY_DATE);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_PREAMBLE_DETECTED));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG, PREFIX_COMPANY,
                PREFIX_NRIC, PREFIX_LICENCE_PLATE, PREFIX_POLICY_NUMBER, PREFIX_POLICY_ISSUE_DATE,
                PREFIX_POLICY_EXPIRY_DATE);

        String[] keywords = getKeywords(argMultimap, PREFIX_NAME, PREFIX_LICENCE_PLATE, PREFIX_NRIC, PREFIX_PHONE,
                PREFIX_POLICY_NUMBER, PREFIX_TAG, PREFIX_POLICY_EXPIRY_DATE, PREFIX_EMAIL,
                PREFIX_POLICY_ISSUE_DATE, PREFIX_COMPANY);

        return new FindCommand(
                new NameContainsKeywordsPredicate(keywords[0]),
                new LicenceContainsKeywordsPredicate(keywords[1]),
                new NricContainsKeywordsPredicate(keywords[2]),
                new PhoneContainsKeywordsPredicate(keywords[3]),
                new PolicyNumberContainsKeywordsPredicate(keywords[4]),
                new TagContainsKeywordsPredicate(keywords[5]),
                new PolicyExpiryContainsKeywordsPredicate(keywords[6]),
                new EmailContainsKeywordsPredicate(keywords[7]),
                new PolicyIssueContainsKeywordsPredicate(keywords[8]),
                new CompanyContainsKeywordsPredicate(keywords[9])
        );
    }

    private String[] getKeywords(ArgumentMultimap argMultimap, Prefix... prefixes) throws ParseException {
        String[] keywords = new String[prefixes.length];
        Arrays.fill(keywords, "");
        StringBuilder emptyPrefixes = new StringBuilder();
        boolean atLeastOneFieldPresent = false;

        for (int i = 0; i < prefixes.length; i++) {
            if (argMultimap.getValue(prefixes[i]).isPresent()) {
                keywords[i] = argMultimap.getValue(prefixes[i]).get();
                if (keywords[i].isEmpty()) {
                    emptyPrefixes.append(prefixes[i]).append(" ");
                }
                atLeastOneFieldPresent = true;
            }
        }

        if (!atLeastOneFieldPresent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (emptyPrefixes.length() > 0) {
            throw new ParseException(String.format(MESSAGE_EMPTY_FIELDS, emptyPrefixes.toString().trim()));
        }

        return keywords;
    }

}
