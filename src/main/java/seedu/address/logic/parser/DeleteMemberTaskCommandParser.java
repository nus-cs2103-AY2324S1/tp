package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TODO;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteMemberTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DeleteMemberTaskCommandParser implements Parser<DeleteMemberTaskCommand> {

    @Override
    public DeleteMemberTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK);

        Index memberIndex;
        Index taskIndex;

        try {
            memberIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            // Parse the taskIndex from the PREFIX_TASK
            taskIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TASK).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMemberTaskCommand.MESSAGE_USAGE), pe);
        }

        return new DeleteMemberTaskCommand(memberIndex, taskIndex);
    }
}