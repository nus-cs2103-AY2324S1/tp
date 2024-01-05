package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateTaskProgressCommand;
import seedu.address.logic.commands.UpdateTaskProgressCommand.EditProgressDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UpdateTaskProgressCommand object
 */
public class UpdateTaskProgressCommandParser implements Parser<UpdateTaskProgressCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UpdateTaskProgressCommand
     * and returns a UpdateTaskProgressCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateTaskProgressCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TASK_PROGRESS);

        Index index;

        if (!arePrefixesPresent(argMultimap, PREFIX_TASK_PROGRESS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UpdateTaskProgressCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateTaskProgressCommand.MESSAGE_USAGE), pe);
        }

        EditProgressDescriptor descriptor = new EditProgressDescriptor();

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TASK_PROGRESS);

        if (argMultimap.getValue(PREFIX_TASK_PROGRESS).isPresent()) {
            descriptor.setProgress(ParserUtil.parseTaskProgress(argMultimap.getValue(PREFIX_TASK_PROGRESS).get()));
        }

        if (!descriptor.isUpdated()) {
            throw new ParseException(UpdateTaskProgressCommand.MESSAGE_NOT_EDITED);
        }


        return new UpdateTaskProgressCommand(index, descriptor);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
