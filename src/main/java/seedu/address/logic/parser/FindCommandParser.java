package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.LicenceContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG,
                        PREFIX_NRIC, PREFIX_LICENCE_PLATE, PREFIX_POLICY_NUMBER, PREFIX_POLICY_ISSUE_DATE,
                        PREFIX_POLICY_EXPIRY_DATE);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_TAG, PREFIX_NRIC, PREFIX_LICENCE_PLATE, PREFIX_POLICY_NUMBER, PREFIX_POLICY_ISSUE_DATE,
                PREFIX_POLICY_EXPIRY_DATE);

        String[] nameKeyword = {""};
        String[] licenceKeyword = {""};

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            nameKeyword[0] = argMultimap.getValue(PREFIX_NAME).get();
        }

        if (argMultimap.getValue(PREFIX_LICENCE_PLATE).isPresent()) {
            licenceKeyword[0] = argMultimap.getValue(PREFIX_LICENCE_PLATE).get();
        }

        return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeyword)),
                new LicenceContainsKeywordsPredicate(Arrays.asList(licenceKeyword)));
    }

}
