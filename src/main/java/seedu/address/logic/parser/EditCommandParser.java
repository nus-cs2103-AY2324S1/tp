package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_PERIOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.exceptions.command.parse.exceptions.EditCommandParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROOM, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_BOOKING_PERIOD, PREFIX_REMARK);

        Index index;

        try {
            index = ParserUtil.parseEditIndex(argMultimap.getPreamble());
        } catch (EditCommandParseException ee) {
            throw new EditCommandParseException();
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ROOM, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_BOOKING_PERIOD, PREFIX_REMARK);

        EditCommand.EditRoomDescriptor editRoomDescriptor = new EditCommand.EditRoomDescriptor();

        if (argMultimap.getValue(PREFIX_ROOM).isPresent()) {
            editRoomDescriptor.setRoom(ParserUtil.parseRoom(argMultimap.getValue(PREFIX_ROOM).get()));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editRoomDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editRoomDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editRoomDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        if (argMultimap.getValue(PREFIX_BOOKING_PERIOD).isPresent()) {
            editRoomDescriptor.setBookingPeriod(ParserUtil.parseBookingPeriod(argMultimap
                    .getValue(PREFIX_BOOKING_PERIOD).get()));
        }

        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editRoomDescriptor.setRemark(ParserUtil.parseRemark(argMultimap
                    .getValue(PREFIX_REMARK).get()));
        }

        if (!editRoomDescriptor.isAnyFieldEdited()) {
            throw new EditCommandParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editRoomDescriptor);
    }

}
