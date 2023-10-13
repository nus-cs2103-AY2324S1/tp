package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.address.logic.commands.LookupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new LookupCommand object
 */
public class LookupCommandParser implements Parser<LookupCommand> {

    private static final Prefix[] prefixList = new Prefix[] {PREFIX_CLASSNUMBER,
        PREFIX_EMAIL, PREFIX_NAME, PREFIX_PHONE,
        PREFIX_STUDENT_NUMBER, PREFIX_TAG};

    /**
     * Parses the given {@code String} of arguments in the context of the LookupCommand
     * and returns a LookupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LookupCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, LookupCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, prefixList);
        argMultimap.verifyNoDuplicatePrefixesFor(prefixList);
        if (!isPrefixPresent(argMultimap, prefixList)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, LookupCommand.MESSAGE_USAGE));
        }
        for (Prefix prefix : prefixList) {
            String value = argMultimap.getValue(prefix).orElse("");
            if (value.split("\\s+").length > 1) {
                throw new ParseException(LookupCommand.MESSAGE_ADDITIONAL_KEYWORDS);
            }
        }

        String classNumber = argMultimap.getValue(PREFIX_CLASSNUMBER)
                .filter(s -> !s.isEmpty()).orElse(null);
        String email = argMultimap.getValue(PREFIX_EMAIL)
                .filter(s -> !s.isEmpty()).orElse(null);
        String name = argMultimap.getValue(PREFIX_NAME)
                .filter(s -> !s.isEmpty()).orElse(null);
        String phone = argMultimap.getValue(PREFIX_PHONE)
                .filter(s -> !s.isEmpty()).orElse(null);
        String studentNumber = argMultimap.getValue(PREFIX_STUDENT_NUMBER)
                .filter(s -> !s.isEmpty()).orElse(null);
        String tag = argMultimap.getValue(PREFIX_TAG)
                .filter(s -> !s.isEmpty()).orElse(null);

        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(classNumber,
                email, name, phone, studentNumber, tag);
        return new LookupCommand(predicate);
    }

    /**
     * Returns true if at least one prefix contains non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes)
                .anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
