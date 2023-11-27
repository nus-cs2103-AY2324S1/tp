package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_PERIOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditRoomDescriptor;
import seedu.address.model.booking.Booking;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Booking booking) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(booking);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Booking booking) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_ROOM + booking.getRoom().toString() + " ");
        sb.append(PREFIX_NAME + booking.getName().fullName + " ");
        sb.append(PREFIX_PHONE + booking.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + booking.getEmail().value + " ");
        sb.append(PREFIX_BOOKING_PERIOD + booking.getBookingPeriod().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditRoomDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getRoom().ifPresent(room -> sb.append(PREFIX_ROOM).append(room.toString()).append(" "));
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getBookingPeriod().ifPresent(address -> sb.append(PREFIX_BOOKING_PERIOD)
                .append(address.value).append(" "));
        descriptor.getRemark().ifPresent(remark -> sb.append(PREFIX_REMARK).append(remark.value).append(" "));
        return sb.toString();
    }
}
