package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALIZATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YOE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DENTISTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.model.person.dentist.Specialization;
import seedu.address.model.person.dentist.Yoe;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditDentistCommand extends Command {

    public static final String COMMAND_WORD = "edit-dentist";

    public static final String MESSAGE_USAGE =
        COMMAND_WORD + ": Edits the details of the dentist identified "
            + "by the index number used in the displayed dentist list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: DENTIST_ID (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_SPECIALIZATION + "SPECIALIZATION] "
            + "[" + PREFIX_YOE + "YEARS OF EXPERIENCE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com "
            + PREFIX_SPECIALIZATION + "Orthodontics";

    public static final String MESSAGE_EDIT_DENTIST_SUCCESS = "Edited Dentist: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_DENTIST = "This dentist already exists in the address book.";

    private final long dentistId;
    private final EditDentistDescriptor editDentistDescriptor;

    /**
     * @param dentistId             of the dentist in the filtered dentist list to edit
     * @param editDentistDescriptor details to edit the dentist with
     */
    public EditDentistCommand(long dentistId, EditDentistDescriptor editDentistDescriptor) {
        requireNonNull(dentistId);
        requireNonNull(editDentistDescriptor);

        this.dentistId = dentistId;
        this.editDentistDescriptor = new EditDentistDescriptor(editDentistDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            Dentist dentistToEdit = model.getDentistById(dentistId);

            if (dentistToEdit == null) {
                throw new CommandException(Messages.MESSAGE_INVALID_DENTIST_DISPLAYED_INDEX);
            }
            Dentist editedDentist = createEditedDentist(dentistToEdit, editDentistDescriptor);

            if (!dentistToEdit.isSameDentist(editedDentist) && model.hasDentist(editedDentist)) {
                throw new CommandException(MESSAGE_DUPLICATE_DENTIST);
            }

            model.setDentist(dentistToEdit, editedDentist);
            model.updateFilteredDentistList(PREDICATE_SHOW_ALL_DENTISTS);
            return new CommandResult(
                String.format(MESSAGE_EDIT_DENTIST_SUCCESS, Messages.format(editedDentist)));

        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }

    }

    /**
     * Creates and returns a {@code Dentist} with the details of {@code dentistToEdit}
     * edited with {@code editDentistDescriptor}.
     */
    private static Dentist createEditedDentist(Dentist dentistToEdit,
        EditDentistDescriptor editDentistDescriptor) {
        assert dentistToEdit != null;

        Name updatedName = editDentistDescriptor.getName().orElse(dentistToEdit.getName());
        Phone updatedPhone = editDentistDescriptor.getPhone().orElse(dentistToEdit.getPhone());
        Email updatedEmail = editDentistDescriptor.getEmail().orElse(dentistToEdit.getEmail());
        Address updatedAddress = editDentistDescriptor.getAddress()
            .orElse(dentistToEdit.getAddress());
        Specialization updatedSpecialization = editDentistDescriptor.getSpecialization()
            .orElse(dentistToEdit.getSpecialization());
        Yoe updatedYoe = editDentistDescriptor.getYoe().orElse(dentistToEdit.getYoe());
        long dentistIdRemains = dentistToEdit.getId(); //ID Must not change!
        Set<Tag> updatedTags = editDentistDescriptor.getTags().orElse(dentistToEdit.getTags());

        return new Dentist(updatedName, updatedPhone, updatedEmail, updatedAddress,
            updatedSpecialization,
            updatedYoe, dentistIdRemains, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditDentistCommand)) {
            return false;
        }

        EditDentistCommand otherEditCommand = (EditDentistCommand) other;
        return dentistId == (otherEditCommand.dentistId)
            && editDentistDescriptor.equals(otherEditCommand.editDentistDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("dentistId", dentistId)
            .add("editDentistDescriptor", editDentistDescriptor)
            .toString();
    }

    /**
     * Stores the details to edit the dentist with. Each non-empty field value will replace the
     * corresponding field value of the dentist.
     */
    public static class EditDentistDescriptor {

        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Specialization specialization;
        private Yoe yoe;
        private long dentistId;
        private Set<Tag> tags;

        public EditDentistDescriptor() {
        }

        /**
         * Copy constructor. A defensive copy of {@code tags} is used internally.
         */
        public EditDentistDescriptor(EditDentistDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setSpecialization(toCopy.specialization);
            setYoe(toCopy.yoe);
            setDentistId(toCopy.dentistId);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, specialization, yoe, tags);
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

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setSpecialization(Specialization specialization) {
            this.specialization = specialization;
        }

        public Optional<Specialization> getSpecialization() {
            return Optional.ofNullable(specialization);
        }

        public void setYoe(Yoe yoe) {
            this.yoe = yoe;
        }

        public Optional<Yoe> getYoe() {
            return Optional.ofNullable(yoe);
        }

        public void setDentistId(long id) {
            this.dentistId = id;
        }

        public Optional<Long> getDentistId() {
            return Optional.ofNullable(dentistId);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}. A defensive copy of {@code tags} is used
         * internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException} if
         * modification is attempted. Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags))
                : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditDentistDescriptor)) {
                return false;
            }

            EditDentistDescriptor otherEditDentistDescriptor = (EditDentistDescriptor) other;
            return Objects.equals(name, otherEditDentistDescriptor.getName().get())
                && Objects.equals(phone, otherEditDentistDescriptor.getPhone().get())
                && Objects.equals(email, otherEditDentistDescriptor.getEmail().get())
                && Objects.equals(address, otherEditDentistDescriptor.getAddress().get())
                && Objects.equals(specialization,
                otherEditDentistDescriptor.getSpecialization().get())
                && Objects.equals(yoe, otherEditDentistDescriptor.getYoe().get())
                && Objects.equals(dentistId, otherEditDentistDescriptor.getDentistId().get())
                && Objects.equals(tags, otherEditDentistDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("specialization", specialization)
                .add("yoe", yoe)
                .add("tags", tags)
                .toString();
        }
    }
}
