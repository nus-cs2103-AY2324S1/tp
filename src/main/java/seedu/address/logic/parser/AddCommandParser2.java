package seedu.address.logic.parser;

import java.util.Set;
import java.util.stream.Stream;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddCommand2;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser2 implements Parser2<AddCommand2> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand2
     * and returns an AddCommand2 object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand2 parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ADDRESS, PREFIX_ANSWER);

        if (!arePrefixesPresent(argMultimap, PREFIX_ADDRESS, PREFIX_ANSWER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ADDRESS, PREFIX_ANSWER);
        Question question = ParserUtil.parseQuestion(argMultimap.getValue(PREFIX_QUESTION).get());
        Answer answer = ParserUtil.parseAnswer(argMultimap.getValue(PREFIX_ANSWER).get());

        Card card = new Card(question, answer);

        return new AddCommand2(card);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
