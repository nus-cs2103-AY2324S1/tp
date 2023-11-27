package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_PERIOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.exceptions.command.parse.exceptions.AddCommandParseException;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.BookingPeriod;
import seedu.address.model.booking.Remark;
import seedu.address.model.booking.Room;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROOM, PREFIX_BOOKING_PERIOD, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_REMARK);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROOM, PREFIX_BOOKING_PERIOD, PREFIX_NAME,
                PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new AddCommandParseException();
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ROOM, PREFIX_BOOKING_PERIOD, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL);
        Room room = ParserUtil.parseRoom(argMultimap.getValue(PREFIX_ROOM).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        BookingPeriod bookingPeriod = ParserUtil.parseBookingPeriod(argMultimap.getValue(PREFIX_BOOKING_PERIOD).get());
        Remark remark;
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            String remarkInput = argMultimap.getValue(PREFIX_REMARK).get();
            if (remarkInput.trim().isEmpty()) {
                remark = new Remark("N/A");
            } else {
                remark = ParserUtil.parseRemark(remarkInput);
            }
        } else {
            remark = new Remark("N/A");
        }
        Booking booking = new Booking(room, bookingPeriod, name, phone, email, remark);

        return new AddCommand(booking);
    }

}
