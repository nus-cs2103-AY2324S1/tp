package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditScheduleCommand object
 */
public class EditScheduleCommandParser implements Parser<EditScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditScheduleCommand
     * and returns an EditScheduleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_START_TIME, PREFIX_END_TIME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditScheduleCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_START_TIME, PREFIX_END_TIME);

        EditScheduleCommand.EditScheduleDescriptor editScheduleDescriptor =
            new EditScheduleCommand.EditScheduleDescriptor();

        if (argMultimap.getValue(PREFIX_START_TIME).isPresent()) {
            editScheduleDescriptor.setStartTime(ParserUtil
                .parseStartTime(argMultimap.getValue(PREFIX_START_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_END_TIME).isPresent()) {
            editScheduleDescriptor.setEndTime(ParserUtil.parseEndTime(argMultimap.getValue(PREFIX_END_TIME).get()));
        }


        if (!editScheduleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditScheduleCommand.MESSAGE_NOT_EDITED);
        }

        return new EditScheduleCommand(index, editScheduleDescriptor);
    }
}
