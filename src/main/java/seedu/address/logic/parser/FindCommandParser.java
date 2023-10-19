package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.musician.Musician;
import seedu.address.model.musician.NameContainsKeywordsPredicate;
import seedu.address.model.tag.GenreMatchesPredicate;
import seedu.address.model.tag.InstrumentMatchesPredicate;
import seedu.address.model.tag.TagMatchesPredicate;

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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TAG, PREFIX_INSTRUMENT, PREFIX_GENRE);

        if (!argMultimap.getPreamble().isEmpty() || args.trim().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<String> names = argMultimap.getAllValues(PREFIX_NAME);
        List<String> tags = argMultimap.getAllValues(PREFIX_TAG);
        List<String> instruments = argMultimap.getAllValues(PREFIX_INSTRUMENT);
        List<String> genres = argMultimap.getAllValues(PREFIX_GENRE);

        HashSet<Predicate<Musician>> predicates = new HashSet<>();
        if (!names.isEmpty()) {
            predicates.add(new NameContainsKeywordsPredicate(names));
        }
        if (!tags.isEmpty()) {
            predicates.add(new TagMatchesPredicate(tags));
        }
        if (!instruments.isEmpty()) {
            predicates.add(new InstrumentMatchesPredicate(instruments));
        }
        if (!genres.isEmpty()) {
            predicates.add(new GenreMatchesPredicate(genres));
        }

        return new FindCommand(predicates);
    }

}
