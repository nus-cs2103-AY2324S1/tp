package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddMemberTaskCommand;
import seedu.address.logic.commands.AddMemberTaskCommand.AddMemberTaskDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;

/**
 * Parses input arguments and creates a new AddMemberTaskCommand object
 */
public class AddMemberTaskCommandParser implements Parser<AddMemberTaskCommand> {
    @Override
    public AddMemberTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK);

        if (argMultimap.getValue(PREFIX_TASK).isEmpty() || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMemberTaskCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());

        AddMemberTaskDescriptor addMemberTaskDescriptor = new AddMemberTaskDescriptor();

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TASK);

        Task task = ParserUtil.parseTask(argMultimap.getValue(PREFIX_TASK).get());
        addMemberTaskDescriptor.setTasks(new ArrayList<>(List.of(task)));

        return new AddMemberTaskCommand(index, addMemberTaskDescriptor);
    }
}
