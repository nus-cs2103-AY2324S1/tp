package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_PERIOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.exceptions.BookingNotFoundException;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_ROOM + "ROOM "
            + PREFIX_BOOKING_PERIOD + "BOOKING PERIOD "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_REMARK + "REMARK "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROOM + "1 "
            + PREFIX_BOOKING_PERIOD + "2023-01-01 08:00 to 2023-01-02 12:00 "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@gmail.com "
            + PREFIX_REMARK + "Requested extra Queen's size bed";

    public static final List<Prefix> PREFIXES = List.of(PREFIX_ROOM, PREFIX_BOOKING_PERIOD, PREFIX_NAME, PREFIX_PHONE,
            PREFIX_EMAIL, PREFIX_REMARK);
    public static final List<String> EXAMPLES = List.of("1", "2023-01-01 08:00 to 2023-01-02 12:00", "John Doe",
            "98765432", "johnd@gmail.com", "Requested extra Queen's sized bed");

    public static final String MESSAGE_SUCCESS = "New booking added: %1$s";
    public static final String MESSAGE_DUPLICATE_BOOKING = "This booking overlaps with existing booking";

    private final Booking toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Booking}.
     *
     * @param booking The booking to be added.
     */
    public AddCommand(Booking booking) {
        if (booking == null) {
            throw new BookingNotFoundException();
        }
        toAdd = booking;
    }

    /**
     * Executes the add command to add a new booking to the address book.
     *
     * @param model The current model.
     * @return A CommandResult indicating the result of the add operation and the success message.
     * @throws CommandException If there is an error in executing the command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null : "Model cannot be null";

        if (model.hasBooking(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOKING);
        }

        model.addBooking(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    /**
     * Checks if this AddCommand is equal to another object. The comparison is based on whether they contain
     * the same booking to add.
     *
     * @param other The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    /**
     * Returns a string representation of this AddCommand. It includes the booking to be added.
     *
     * @return A string representation of this command.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
