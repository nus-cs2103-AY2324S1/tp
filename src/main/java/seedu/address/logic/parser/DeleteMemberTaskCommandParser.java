package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteMemberTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteMemberTaskCommand object
 */
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
            taskIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TASK).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteMemberTaskCommand.MESSAGE_USAGE), pe);
        }

        return new DeleteMemberTaskCommand(memberIndex, taskIndex);
    }
}
