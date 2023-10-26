package seedu.address.logic.parser;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.card.Card;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CARDS;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_QUESTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_QUESTION)
                || !argMultimap.getPreamble().isEmpty()) {
            return new ListCommand(PREDICATE_SHOW_ALL_CARDS);
        }

        if (arePrefixesPresent(argMultimap, PREFIX_QUESTION)
                && argMultimap.getValue(PREFIX_QUESTION).get().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_QUESTION);
        String prefix = argMultimap.getValue(PREFIX_QUESTION).get();
        Predicate<Card> predicate = card -> card.getQuestion().startsWith(prefix);

        return new ListCommand(predicate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
