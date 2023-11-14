package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENDOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENDOR_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENDOR_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENDOR_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_VENDORS;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.vendor.Email;
import seedu.address.model.vendor.Name;
import seedu.address.model.vendor.Phone;
import seedu.address.model.vendor.Vendor;

/**
 * Edits the details of an existing vendor in EventWise.
 */
public class EditVendorCommand extends Command {

    public static final String COMMAND_WORD = "editVendor";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the vendor identified "
            + "by the index number used in the displayed vendor list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + PREFIX_VENDOR + "INDEX (must be a positive integer) "
            + "[" + PREFIX_VENDOR_NAME + "NAME] "
            + "[" + PREFIX_VENDOR_PHONE + "PHONE] "
            + "[" + PREFIX_VENDOR_EMAIL + "EMAIL]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_VENDOR + "1 "
            + PREFIX_VENDOR_NAME + "SUN Catering "
            + PREFIX_VENDOR_PHONE + "64646565";

    public static final String MESSAGE_EDIT_VENDOR_SUCCESS = "Edited Vendor: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_VENDOR = "This vendor already exists in EventWise.";

    private final Index index;
    private final EditVendorDescriptor editVendorDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editVendorDescriptor details to edit the person with
     */
    public EditVendorCommand(Index index, EditVendorDescriptor editVendorDescriptor) {
        requireNonNull(index);
        requireNonNull(editVendorDescriptor);

        this.index = index;
        this.editVendorDescriptor = new EditVendorDescriptor(editVendorDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Vendor> lastShownList = model.getFilteredVendorList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_VENDOR_INDEX);
        }

        Vendor vendorToEdit = lastShownList.get(index.getZeroBased());
        Vendor editedVendor = createEditedVendor(vendorToEdit, editVendorDescriptor);

        if (!vendorToEdit.isSameVendor(editedVendor) && model.hasVendor(editedVendor)) {
            throw new CommandException(MESSAGE_DUPLICATE_VENDOR);
        }

        model.setVendor(vendorToEdit, editedVendor);

        // Check if event contains vendorToDelete, if true, update vendor from the event's vendor list
        for (Event event : model.getAddressBook().getEventList()) {
            if (event.getVendors().contains(vendorToEdit)) {
                List<Vendor> editedVendorList = new ArrayList<>(event.getVendors());
                editedVendorList.set(editedVendorList.indexOf(vendorToEdit), editedVendor);

                Event updatedEvent = new Event(event.getName(), event.getDescription(),
                        event.getFromDate(), event.getToDate(), event.getNote(), event.getPersons(),
                        editedVendorList, event.getVenue());
                model.setEvent(event, updatedEvent);

                // Check if the current event that is being shown in the event details is affected
                Event eventToView = model.getEventToView();
                if (eventToView != null && eventToView.getVendors().contains(vendorToEdit)) {
                    model.setEventToView(updatedEvent);
                }
            }
        }

        model.updateFilteredVendorList(PREDICATE_SHOW_ALL_VENDORS);
        return new CommandResult(String.format(MESSAGE_EDIT_VENDOR_SUCCESS, Messages.format(editedVendor)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Vendor createEditedVendor(Vendor vendorToEdit, EditVendorDescriptor editVendorDescriptor) {
        assert vendorToEdit != null;

        Name updatedName = editVendorDescriptor.getName().orElse(vendorToEdit.getName());
        Phone updatedPhone = editVendorDescriptor.getPhone().orElse(vendorToEdit.getPhone());
        Email updatedEmail = editVendorDescriptor.getEmail().orElse(vendorToEdit.getEmail());

        return new Vendor(updatedName, updatedPhone, updatedEmail);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditVendorCommand)) {
            return false;
        }

        EditVendorCommand otherEditVendorCommand = (EditVendorCommand) other;
        return index.equals(otherEditVendorCommand.index)
                && editVendorDescriptor.equals(otherEditVendorCommand.editVendorDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editVendorDescriptor", editVendorDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditVendorDescriptor {
        private Name name;
        private Phone phone;
        private Email email;

        public EditVendorDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditVendorDescriptor(EditVendorDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditVendorDescriptor)) {
                return false;
            }

            EditVendorDescriptor otherEditVendorDescriptor = (EditVendorDescriptor) other;
            return Objects.equals(name, otherEditVendorDescriptor.name)
                    && Objects.equals(phone, otherEditVendorDescriptor.phone)
                    && Objects.equals(email, otherEditVendorDescriptor.email);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .toString();
        }
    }
}
