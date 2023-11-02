package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AddTaskCommand.MESSAGE_INVALID_DESCRIPTION;
import static seedu.address.logic.commands.AddTaskCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_DATE_TIME;
import static seedu.address.model.task.Deadline.MESSAGE_CONSTRAINTS;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;

/**
 * Parses input arguments and creates a new addTaskCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns an AddTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);


        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT_DESCRIPTION, PREFIX_EVENT_END_DATE_TIME);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_EVENT_DESCRIPTION, PREFIX_EVENT_END_DATE_TIME);

        if (argMultimap.getValue(PREFIX_EVENT_DESCRIPTION).isEmpty() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        String description = argMultimap.getValue(PREFIX_EVENT_DESCRIPTION).get();
        if (description.equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_DESCRIPTION, MESSAGE_USAGE));
        }
        LocalDateTime deadline = null;

        if (argMultimap.getValue(PREFIX_EVENT_END_DATE_TIME).isPresent()) {
            try {
                deadline = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_EVENT_END_DATE_TIME).get());
            } catch (DateTimeParseException e) {
                throw new ParseException(MESSAGE_CONSTRAINTS);
            }
        }

        Task toAdd = new Task(description, deadline);
        return new AddTaskCommand(toAdd);
    }
}
