package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TODO;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddMemberTaskCommand;
import seedu.address.logic.commands.AddMemberTaskCommand.AddMemberTaskDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;

public class AddMemberToDoCommandParser implements Parser<AddMemberTaskCommand> {
    @Override
    public AddMemberTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TODO);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddMemberTaskCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TODO);

        AddMemberTaskDescriptor addMemberTaskDescriptor = new AddMemberTaskDescriptor();

        if (argMultimap.getValue(PREFIX_TODO).isPresent()) {
            parseTasksForEdit(argMultimap.getAllValues(PREFIX_TODO)).ifPresent(addMemberTaskDescriptor::setTasks);
        }

        return new AddMemberTaskCommand(index, addMemberTaskDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<List<Task>> parseTasksForEdit(Collection<String> tasks) throws ParseException {
        assert tasks != null;

        if (tasks.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> taskSet = tasks.size() == 1 && tasks.contains("") ? Collections.emptyList() : tasks;
        return Optional.of(ParserUtil.parseTasks(taskSet));
    }
}
