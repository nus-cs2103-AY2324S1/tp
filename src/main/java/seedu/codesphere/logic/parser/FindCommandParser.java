//@@author devanshubisht
package seedu.codesphere.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_PENDING_QUESTION;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.codesphere.logic.commands.FindCommand;
import seedu.codesphere.logic.parser.exceptions.ParseException;
import seedu.codesphere.model.student.predicates.EmailContainsKeywordsPredicate;
import seedu.codesphere.model.student.predicates.NameContainsKeywordsPredicate;
import seedu.codesphere.model.student.predicates.PqContainsKeywordsPredicate;
import seedu.codesphere.model.student.predicates.RemarkContainsKeywordsPredicate;
import seedu.codesphere.model.student.predicates.TagFilterPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * FindCommand.
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TAG,
                PREFIX_PENDING_QUESTION, PREFIX_REMARK, PREFIX_EMAIL);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TAG, PREFIX_PENDING_QUESTION, PREFIX_REMARK,
                PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_TAG, PREFIX_PENDING_QUESTION, PREFIX_REMARK,
                PREFIX_EMAIL);

        Optional<String> namePrefixValue = argMultimap.getValue(PREFIX_NAME);
        Optional<String> tagPrefixValue = argMultimap.getValue(PREFIX_TAG);
        Optional<String> pqPrefixValue = argMultimap.getValue(PREFIX_PENDING_QUESTION);
        Optional<String> remarkPrefixValue = argMultimap.getValue(PREFIX_REMARK);
        Optional<String> emailPrefixValue = argMultimap.getValue(PREFIX_EMAIL);

        if (namePrefixValue.isPresent() && !namePrefixValue.get().isEmpty()) {
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(namePrefixValue.get())));
        } else if (tagPrefixValue.isPresent()) {
            return new FindCommand(new TagFilterPredicate(Arrays.asList(tagPrefixValue.get())));
        } else if (pqPrefixValue.isPresent()) {
            return new FindCommand(new PqContainsKeywordsPredicate(Arrays.asList(pqPrefixValue.get())));
        } else if (remarkPrefixValue.isPresent()) {
            return new FindCommand(new RemarkContainsKeywordsPredicate(Arrays.asList(remarkPrefixValue.get())));
        } else if (emailPrefixValue.isPresent()) {
            return new FindCommand(new EmailContainsKeywordsPredicate(Arrays.asList(emailPrefixValue.get())));
        } else {
            // Handle the case where no prefix is present
            throw new ParseException(FindCommand.MESSAGE_NO_DESCRIPTION);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given.
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        long count = Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
                .count();
        return count == 1;
    }
}
