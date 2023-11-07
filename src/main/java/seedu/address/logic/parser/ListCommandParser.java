package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CARDS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.card.Card;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {


    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns an ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        assert args != null : "Command is empty";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_QUESTION, PREFIX_TAG);

        List<Predicate<Card>> predicates = new ArrayList<>(Collections.singleton(PREDICATE_SHOW_ALL_CARDS));

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_QUESTION);
        if (argMultimap.getValue(PREFIX_QUESTION).isPresent()) {
            if (argMultimap.getValue(PREFIX_QUESTION).get().isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
            }

            predicates.add(parseQuestionPrefix(argMultimap.getValue(PREFIX_QUESTION).get()));
        }

        if (!argMultimap.getAllValues(PREFIX_TAG).isEmpty()) {
            predicates.add(parseTagPrefix(argMultimap.getAllValues(PREFIX_TAG)));
        }

        return new ListCommand(predicates);
    }

    /**
     * Parses {@code <String> prefix} into a {@code Predicate<Card>} if {@code prefix} is non-empty.
     */
    private static Predicate<Card> parseQuestionPrefix(String prefix) {
        assert(!prefix.isEmpty());

        return (card -> card.getQuestion().startsWith(prefix));
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Predicate<Card>} if {@code tags} is non-empty.
     */
    private static Predicate<Card> parseTagPrefix(Collection<String> tags) throws ParseException {
        assert(!tags.isEmpty());

        List<Tag> tagSet = ParserUtil.parseTags(tags);

        return (card -> new HashSet<>(card.getTags()).containsAll(tagSet));
    }
}
