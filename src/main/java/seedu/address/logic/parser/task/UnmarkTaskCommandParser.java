package seedu.address.logic.parser.task;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import java.util.stream.Stream;

import seedu.address.logic.commands.task.UnmarkTaskCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.name.Name;
import seedu.address.model.task.TaskDescription;

/**
 * Parses input arguments and creates a new UnmarkCommand object
 */
public class UnmarkTaskCommandParser implements Parser<UnmarkTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkCommand
     * and returns an UnmarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TASK_DESCRIPTION, PREFIX_EVENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_TASK_DESCRIPTION, PREFIX_EVENT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkTaskCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TASK_DESCRIPTION, PREFIX_EVENT);
        TaskDescription taskDescription = ParserUtil.parseTaskDescription(argMultimap.getValue(
                PREFIX_TASK_DESCRIPTION).get());
        Name associatedEventName = ParserUtil.parseName(argMultimap.getValue(PREFIX_EVENT).get());

        return new UnmarkTaskCommand(taskDescription, associatedEventName);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
