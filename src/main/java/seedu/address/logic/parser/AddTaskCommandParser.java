package seedu.address.logic.parser;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_DATE_TIME;

public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    public AddTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);


        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT_DESCRIPTION, PREFIX_EVENT_END_DATE_TIME);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_EVENT_DESCRIPTION, PREFIX_EVENT_END_DATE_TIME);

        if (argMultimap.getValue(PREFIX_EVENT_DESCRIPTION).isEmpty() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        String description = argMultimap.getValue(PREFIX_EVENT_DESCRIPTION).get();
        LocalDateTime deadline = null;

        if (argMultimap.getValue(PREFIX_EVENT_END_DATE_TIME).isPresent()) {
            deadline = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_EVENT_END_DATE_TIME).get());
        }

        Task toAdd = new Task(description, deadline);
        return new AddTaskCommand(toAdd);
    }
}
