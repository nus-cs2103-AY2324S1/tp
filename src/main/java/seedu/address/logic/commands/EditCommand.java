package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_PERIOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.BookingPeriod;
import seedu.address.model.booking.Remark;
import seedu.address.model.booking.Room;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_ROOM + "ROOM] "
            + "[" + PREFIX_BOOKING_PERIOD + "BOOKING PERIOD] "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_REMARK + "REMARK]"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final List<Prefix> PREFIXES = List.of(PREFIX_ROOM, PREFIX_BOOKING_PERIOD, PREFIX_NAME, PREFIX_PHONE,
            PREFIX_EMAIL, PREFIX_REMARK);
    public static final List<String> EXAMPLES = List.of("1", "date", "name",
            "phone", "email", "remark");
    public static final String MESSAGE_EDIT_BOOKING_SUCCESS = "Edited Booking: %1$s";
    public static final String MESSAGE_NOT_EDITED = "No changes made. At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_BOOKING = "This person already exists in the address book.";
    private final Index index;
    private final EditRoomDescriptor editRoomDescriptor;

    /**
     * Constructs an EditCommand to edit the booking at the specified index with the given editRoomDescriptor.
     *
     * @param index              The index of the booking to be edited.
     * @param editRoomDescriptor The details to edit the booking with.
     */
    public EditCommand(Index index, EditRoomDescriptor editRoomDescriptor) {
        assert index != null;
        assert editRoomDescriptor != null;
        this.index = index;
        this.editRoomDescriptor = new EditRoomDescriptor(editRoomDescriptor);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Booking createEditedBooking(Booking bookingToEdit, EditRoomDescriptor editRoomDescriptor)
            throws CommandException {
        assert bookingToEdit != null;
        Room updatedRoom = editRoomDescriptor.getRoom().orElse(bookingToEdit.getRoom());
        Name updatedName = editRoomDescriptor.getName().orElse(bookingToEdit.getName());
        Phone updatedPhone = editRoomDescriptor.getPhone().orElse(bookingToEdit.getPhone());
        Email updatedEmail = editRoomDescriptor.getEmail().orElse(bookingToEdit.getEmail());
        BookingPeriod updatedBookingPeriod = editRoomDescriptor.getBookingPeriod()
                .orElse(bookingToEdit.getBookingPeriod());
        Remark updatedRemark = editRoomDescriptor.getRemark().orElse(bookingToEdit.getRemark());
        Booking booking = new Booking(updatedRoom, updatedBookingPeriod, updatedName, updatedPhone, updatedEmail,
                updatedRemark);
        return booking;
    }

    /**
     * Executes the edit command by modifying the details of the booking at the specified index.
     *
     * @param model The current model.
     * @return The result of the command execution.
     * @throws CommandException If the index is invalid, or if the booking cannot be edited.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null;
        List<Booking> lastShownList = model.getFilteredBookingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
        }

        Booking bookingToEdit = lastShownList.get(index.getZeroBased());
        Booking editedBooking = createEditedBooking(bookingToEdit, editRoomDescriptor);

        if (!bookingToEdit.isOverlapBooking(editedBooking) && model.hasBooking(editedBooking)) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOKING);
        }

        if (editedBooking.equals(bookingToEdit)) {
            throw new CommandException(MESSAGE_NOT_EDITED);
        }

        model.setBooking(bookingToEdit, editedBooking);
        model.updateFilteredBookingList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_BOOKING_SUCCESS, Messages.format(editedBooking)));
    }

    /**
     * Checks if this edit command is equal to another object.
     *
     * @param other The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editRoomDescriptor.equals(otherEditCommand.editRoomDescriptor);
    }

    /**
     * Returns a string representation of this edit command.
     *
     * @return A string representation of this edit command.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editPersonDescriptor", editRoomDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditRoomDescriptor {
        private Room room;
        private Name name;
        private Phone phone;
        private Email email;
        private BookingPeriod bookingPeriod;
        private Remark remark;

        /**
         * Creates an EditRoomDescriptor object.
         */
        public EditRoomDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditRoomDescriptor(EditRoomDescriptor toCopy) {
            setRoom(toCopy.room);
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setBookingPeriod(toCopy.bookingPeriod);
            setRemark(toCopy.remark);
        }

        /**
         * Returns true if at least one field is edited.
         *
         * @return True if at least one field is edited, false otherwise.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(room, bookingPeriod, name, phone, email, remark);
        }

        /**
         * Returns an optional Room containing the room to edit. If the field is not edited, returns an empty optional.
         *
         * @return An optional containing the room to edit, or an empty optional if the field is not edited.
         */
        public Optional<Room> getRoom() {
            return Optional.ofNullable(room);
        }

        /**
         * Sets the room to edit.
         *
         * @param room The new room value.
         */
        public void setRoom(Room room) {
            this.room = room;
        }

        /**
         * Returns an optional Name containing the name to edit. If the field is not edited, returns an empty optional.
         *
         * @return An optional containing the name to edit, or an empty optional if the field is not edited.
         */
        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        /**
         * Sets the name to edit.
         *
         * @param name The new name value.
         */
        public void setName(Name name) {
            this.name = name;
        }

        /**
         * Returns an optional Phone containing the phone to edit. If the field is not edited, returns an empty
         * optional.
         *
         * @return An optional containing the phone to edit, or an empty optional if the field is not edited.
         */
        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        /**
         * Sets the phone to edit.
         *
         * @param phone The new phone value.
         */
        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        /**
         * Returns an optional Email containing the email to edit. If the field is not edited, returns an empty
         * optional.
         *
         * @return An optional containing the email to edit, or an empty optional if the field is not edited.
         */
        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        /**
         * Sets the email to edit.
         *
         * @param email The new email value.
         */
        public void setEmail(Email email) {
            this.email = email;
        }

        /**
         * Returns an optional BookingPeriod containing the booking period to edit. If the field is not edited, returns
         * an empty optional.
         *
         * @return An optional containing the booking period to edit, or an empty optional if the field is not edited.
         */
        public Optional<BookingPeriod> getBookingPeriod() {
            return Optional.ofNullable(bookingPeriod);
        }

        /**
         * Sets the booking period to edit.
         *
         * @param bookingPeriod The new booking period value.
         */
        public void setBookingPeriod(BookingPeriod bookingPeriod) {
            this.bookingPeriod = bookingPeriod;
        }

        /**
         * Returns an optional Remark containing the remark to edit. If the field is not edited, returns an empty
         * optional.
         *
         * @return An optional containing the remark to edit, or an empty optional if the field is not edited.
         */
        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        /**
         * Sets the remark to edit.
         *
         * @param remark The new remark value.
         */
        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        /**
         * Checks if this edit room descriptor is equal to another object.
         *
         * @param other The object to compare.
         * @return True if the objects are equal, false otherwise.
         */
        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditRoomDescriptor)) {
                return false;
            }

            EditRoomDescriptor otherEditRoomDescriptor = (EditRoomDescriptor) other;
            return Objects.equals(room, otherEditRoomDescriptor.room)
                    && Objects.equals(bookingPeriod, otherEditRoomDescriptor.bookingPeriod)
                    && Objects.equals(name, otherEditRoomDescriptor.name)
                    && Objects.equals(phone, otherEditRoomDescriptor.phone)
                    && Objects.equals(email, otherEditRoomDescriptor.email)
                    && Objects.equals(remark, otherEditRoomDescriptor.remark);
        }

        /**
         * Returns a string representation of this edit room descriptor.
         *
         * @return A string representation of this edit room descriptor.
         */
        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("room", room)
                    .add("booking period", bookingPeriod)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("remark", remark)
                    .toString();
        }
    }
}
