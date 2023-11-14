package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import seedu.address.logic.commands.findcommands.FindCommand;
import seedu.address.logic.commands.findcommands.FindCompanyCommand;
import seedu.address.logic.commands.findcommands.FindPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.company.CompanyNameAndTagContainKeywordsPredicate;
import seedu.address.model.person.NameAndTagContainKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    public static final String FIND_COMPANIES_ARG_WORD = "c";
    public static final String FIND_PERSONS_ARG_WORD = "p";
    private static final String ARGUMENT_REGEX_PATTERN =
            "^(" + FIND_COMPANIES_ARG_WORD + "|" + FIND_PERSONS_ARG_WORD + ")$";

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @param args the arguments to be parsed
     * @return a FindCommand object
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        // Used to get the first 3 characters of the argument.
        String trimmedArgs = "";
        try {
            trimmedArgs = args.substring(0, 3).trim();
        } catch (StringIndexOutOfBoundsException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        // Used to check if argument is either c or p.
        if (trimmedArgs.isEmpty() || !trimmedArgs.matches(ARGUMENT_REGEX_PATTERN)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        if (trimmedArgs.equals(FIND_PERSONS_ARG_WORD)) {
            ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args.substring(2),
                    PREFIX_NAME, PREFIX_TAG);
            if (!argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

            Collection<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
            Collection<String> tagKeywords = argMultimap.getAllValues(PREFIX_TAG);

            List<String> nameKeywordsList = new ArrayList<>(nameKeywords);
            List<String> tagKeywordsList = new ArrayList<>(tagKeywords);

            // Go through the list to find if nameKeyword is pure
            // alphabets and tagKeywords are pure alphanumeric without any spaces
            for (String nameKeyword : nameKeywordsList) {
                if (!nameKeyword.matches("^[a-zA-Z]*$") || nameKeyword.isBlank()) {
                    throw new ParseException(
                        //MESSAGE_INVALID_NAME_KEYWORD);
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

                }
            }
            for (String tagKeyword : tagKeywordsList) {
                if (!tagKeyword.matches("^[a-zA-Z0-9]*$") || tagKeyword.isBlank()) {
                    throw new ParseException(
                        //MESSAGE_INVALID_TAG_KEYWORD);
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
                }
            }

            return new FindPersonCommand(new NameAndTagContainKeywordsPredicate(nameKeywordsList, tagKeywordsList));
        } else {
            ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args.substring(2),
                    PREFIX_NAME, PREFIX_TAG);
            if (!argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

            Collection<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
            Collection<String> tagKeywords = argMultimap.getAllValues(PREFIX_TAG);

            List<String> nameKeywordsList = new ArrayList<>(nameKeywords);
            List<String> tagKeywordsList = new ArrayList<>(tagKeywords);

            // Go through the list to find if nameKeyword is pure alphabets and tagKeywords
            // are pure alphanumeric without any spaces
            for (String nameKeyword : nameKeywordsList) {
                if (!nameKeyword.matches("^[a-zA-Z]*$") || nameKeyword.isBlank()) {
                    throw new ParseException(
                        //MESSAGE_INVALID_NAME_KEYWORD);
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

                }
            }
            for (String tagKeyword : tagKeywordsList) {
                if (!tagKeyword.matches("^[a-zA-Z0-9]*$") || tagKeyword.isBlank()) {
                    throw new ParseException(
                        //MESSAGE_INVALID_TAG_KEYWORD);
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
                }
            }

            return new FindCompanyCommand(
                new CompanyNameAndTagContainKeywordsPredicate(nameKeywordsList, tagKeywordsList));
        }

    }
}
