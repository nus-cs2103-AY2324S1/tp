//@@author tiongjjyi
package seedu.codesphere.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_PENDING_QUESTION;

import seedu.codesphere.commons.core.index.Index;
import seedu.codesphere.logic.commands.PendingQuestionCommand;
import seedu.codesphere.logic.parser.exceptions.ParseException;
import seedu.codesphere.model.student.PendingQuestion;

/**
 * Parses input arguments and creates a new {@code PendingQuestionCommand} object
 */
public class PendingQuestionCommandParser implements Parser<PendingQuestionCommand> {

    public static final String MESSAGE_CONSTRAINTS =
            "Pending question should not be blank." + "\nExample: pq 1 pq/What is SUT?";
    /**
     * Parses the given {@code String} of arguments in the context of the {@code PendingQuestionCommand}
     * and returns a {@code PendingQuestionCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PendingQuestionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PENDING_QUESTION);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            PendingQuestionCommand.MESSAGE_USAGE), pe);
        }

        String pendingQuestion = argMultimap.getAllValuesAsString(PREFIX_PENDING_QUESTION).orElse("");

        if (pendingQuestion.isEmpty()) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }

        return new PendingQuestionCommand(index, new PendingQuestion(pendingQuestion));
    }

}
