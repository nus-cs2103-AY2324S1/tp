package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.GeneralPersonPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PersonTagContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsPredicate;
import seedu.address.model.person.StatusContainsPredicate;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_STATUS, PREFIX_TAG);
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_STATUS, PREFIX_TAG);

        String[] nameKeyWords = argMultimap.getValue(PREFIX_NAME).orElse("").split("\\s+");
        String[] phoneValues = argMultimap.getValue(PREFIX_PHONE).orElse("").split("\\s+");
        String[] emailKeyWords = argMultimap.getValue(PREFIX_EMAIL).orElse("").split("\\s+");
        String[] statusKeyWords = argMultimap.getValue(PREFIX_STATUS).orElse("").split("\\s+");
        String[] tagKeyWords = argMultimap.getValue(PREFIX_TAG).orElse("").split("\\s+");

        GeneralPersonPredicate generalPersonPredicate = new GeneralPersonPredicate(
                new NameContainsKeywordsPredicate(Arrays.asList(nameKeyWords)),
                new PhoneContainsPredicate(Arrays.asList(phoneValues)),
                new EmailContainsKeywordsPredicate(Arrays.asList(emailKeyWords)),
                new StatusContainsPredicate(Arrays.asList(statusKeyWords)),
                new PersonTagContainsKeywordsPredicate(Arrays.asList(tagKeyWords)));

        return new FindCommand(generalPersonPredicate);
    }

}
