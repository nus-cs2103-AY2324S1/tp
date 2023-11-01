package seedu.classmanager.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.classmanager.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.classmanager.commons.core.index.Index;
import seedu.classmanager.logic.commands.MarkPresentAllCommand;
import seedu.classmanager.logic.parser.exceptions.ParseException;
import seedu.classmanager.model.student.ClassDetails;

/**
 * Parses input arguments and creates a new MarkPresentAllCommand object
 */
public class MarkPresentAllCommandParser implements Parser<MarkPresentAllCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkPresentAllCommand
     * and returns a MarkPresentAllCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkPresentAllCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkPresentAllCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException e) {
            throw new ParseException(ClassDetails.getMessageInvalidTutorialIndex());
        }

        return new MarkPresentAllCommand(index);
    }
}
