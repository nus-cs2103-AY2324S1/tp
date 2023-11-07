package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HINT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Difficulty;
import seedu.address.model.card.Hint;
import seedu.address.model.card.PracticeDate;
import seedu.address.model.card.Question;
import seedu.address.model.card.SolveCount;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        assert args != null : "Command is empty";

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_QUESTION, PREFIX_ANSWER,
                PREFIX_TAG, PREFIX_HINT);

        // Compulsory fields: Question, Answer
        // No extra input in between Command word and Prefixes
        if (!arePrefixesPresent(argMultimap, PREFIX_QUESTION, PREFIX_ANSWER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        // Only one Question and Answer allowed for each Card
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_QUESTION, PREFIX_ANSWER);
        Question question = ParserUtil.parseQuestion(argMultimap.getValue(PREFIX_QUESTION).get());
        Answer answer = ParserUtil.parseAnswer(argMultimap.getValue(PREFIX_ANSWER).get());

        List<Tag> tags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Hint hint;
        if (argMultimap.getValue(PREFIX_HINT).isPresent()) {
            hint = ParserUtil.parseHint(argMultimap.getValue(PREFIX_HINT).get());
        } else {
            // If the prefix doesn't exist, create an empty hint
            hint = new Hint.EmptyHint();
        }

        // Default values
        Difficulty difficulty = Difficulty.NEW;
        SolveCount solveCount = new SolveCount();
        PracticeDate practiceDate = new PracticeDate(LocalDateTime.now());

        Card card = new Card(question, answer, difficulty, tags, practiceDate, null, solveCount, hint);

        return new AddCommand(card);
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
