package seedu.letsgethired.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_NOTE_INSERT;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.util.Pair;
import seedu.letsgethired.commons.core.LogsCenter;
import seedu.letsgethired.logic.commands.FindCommand;
import seedu.letsgethired.logic.parser.exceptions.ParseException;
import seedu.letsgethired.model.application.CompanyContainsFieldKeywordsPredicate;
import seedu.letsgethired.model.application.Status;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private static final Logger logger = LogsCenter.getLogger(FindCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_COMPANY, PREFIX_ROLE, PREFIX_CYCLE, PREFIX_STATUS, PREFIX_NOTE_INSERT, PREFIX_DEADLINE);

        argMultimap.verifyNoDuplicatePrefixesFor(
                PREFIX_COMPANY, PREFIX_ROLE, PREFIX_CYCLE, PREFIX_STATUS, PREFIX_NOTE_INSERT, PREFIX_DEADLINE);

        ArrayList<Pair<Prefix, String>> fieldKeywords = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            String keyword = argMultimap.getValue(PREFIX_COMPANY).get();
            if (keyword.isBlank()) {
                throw new ParseException("The company field must not be blank");
            }
            fieldKeywords.add(new Pair<>(PREFIX_COMPANY, argMultimap.getValue(PREFIX_COMPANY).get()));
        }
        if (argMultimap.getValue(PREFIX_NOTE_INSERT).isPresent()) {
            String keyword = argMultimap.getValue(PREFIX_NOTE_INSERT).get();
            if (keyword.isBlank()) {
                throw new ParseException("The note field must not be blank");
            }
            fieldKeywords.add(new Pair<>(PREFIX_NOTE_INSERT, argMultimap.getValue(PREFIX_NOTE_INSERT).get()));
        }
        if (argMultimap.getValue(PREFIX_CYCLE).isPresent()) {
            String keyword = argMultimap.getValue(PREFIX_CYCLE).get();
            if (keyword.isBlank()) {
                throw new ParseException("The cycle field must not be blank");
            }
            fieldKeywords.add(new Pair<>(PREFIX_CYCLE, argMultimap.getValue(PREFIX_CYCLE).get()));
        }
        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            String keyword = argMultimap.getValue(PREFIX_ROLE).get();
            if (keyword.isBlank()) {
                throw new ParseException("The role field must not be blank");
            }
            fieldKeywords.add(new Pair<>(PREFIX_ROLE, argMultimap.getValue(PREFIX_ROLE).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            String keyword = argMultimap.getValue(PREFIX_STATUS).get();
            if (!Status.isValidStatus(keyword)) {
                throw new ParseException(Status.MESSAGE_CONSTRAINTS);
            }
            fieldKeywords.add(new Pair<>(PREFIX_STATUS, keyword));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            String keyword = argMultimap.getValue(PREFIX_DEADLINE).get();
            if (keyword.isBlank()) {
                throw new ParseException("The deadline field must not be blank");
            }
            fieldKeywords.add(new Pair<>(PREFIX_DEADLINE, argMultimap.getValue(PREFIX_DEADLINE).get()));
        }

        if (fieldKeywords.isEmpty()) {
            logger.finer("This argument to the find command caused a Parse Exception " + args);
            throw new ParseException(FindCommand.NO_FIND_SPECIFIED + "\n" + FindCommand.MESSAGE_USAGE);
        }

        return new FindCommand(new CompanyContainsFieldKeywordsPredicate(fieldKeywords));
    }
}
