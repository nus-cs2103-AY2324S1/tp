package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import java.util.NoSuchElementException;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditFreeTimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.availability.TimeInterval;

/**
 * Parses input arguments and creates a new EditFreeTimeCommand object
 */
public class EditFreeTimeCommandParser implements Parser<EditFreeTimeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditFreeTimeCommand
     * and returns an EditFreeTimeCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditFreeTimeCommand parse(String args) throws ParseException, NumberFormatException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DAY, PREFIX_FROM, PREFIX_TO);
        Index index;
        Integer dayOfWeek;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            dayOfWeek = Integer.parseInt(argMultimap.getValue(PREFIX_DAY).get());
            if (dayOfWeek < 1 || dayOfWeek > 5) {
                throw new ParseException(Messages.MESSAGE_INVALID_DAY);
            }
            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_FROM, PREFIX_TO, PREFIX_DAY);
            EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();

            TimeInterval updatedTimeInterval = ParserUtil.parseTimeInterval(argMultimap.getValue(PREFIX_FROM).get(),
                    argMultimap.getValue(PREFIX_TO).get());
            editPersonDescriptor.setTimeInterval(updatedTimeInterval);

            if (editPersonDescriptor.isNoFieldEdited()) {
                throw new ParseException(EditFreeTimeCommand.MESSAGE_NOT_EDITED);
            }
            return new EditFreeTimeCommand(index, dayOfWeek, editPersonDescriptor);
        } catch (NumberFormatException | NoSuchElementException exception) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditFreeTimeCommand.MESSAGE_USAGE), exception);
        }
    }
}
