package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.address.Address;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.model.name.Name;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing contact in the JobFestGo.
 */
public class EditContactCommand extends Command {
    public static final String COMMAND_WORD = "edit_contact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the contact identified "
            + "by the index number used in the displayed contact list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG_NAME]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_CONTACT_SUCCESS = "Edited Contact: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CONTACT = "This contact already exists in JobFestGo.";
    public static final String MESSAGE_INVALID_TAG = " is missing in JobFestGo. "
            + "Please use tags already added to JobFestGo.";

    private final Index index;
    private final EditContactDescriptor editContactDescriptor;

    /**
     * @param index of the contact in the filtered contact list to edit
     * @param editContactDescriptor details to edit the contact with
     */
    public EditContactCommand(Index index, EditContactDescriptor editContactDescriptor) {
        requireNonNull(index);
        requireNonNull(editContactDescriptor);

        this.index = index;
        this.editContactDescriptor = new EditContactDescriptor(editContactDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        if (model.isOnEventsScreen() || model.isOnTagsScreen()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_NOT_DISPLAYED);
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToEdit = lastShownList.get(index.getZeroBased());
        Contact editedContact = createEditedContact(contactToEdit, editContactDescriptor);

        if (!model.verifyContact(contactToEdit, editedContact)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONTACT);
        }

        for (Tag tag : editedContact.getTags()) {
            if (!model.hasTag(tag)) {
                throw new CommandException("Tag: " + tag + MESSAGE_INVALID_TAG);
            }
        }

        model.setContact(contactToEdit, editedContact);

        // Update model to depict which screen it is on currently.
        model.switchToContactsScreen(false);
        model.switchToEventsScreen(false);
        model.switchToTagsScreen(false);

        // Update relevant filtered lists to show the correct lists.
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        model.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TASKS);

        return new CommandResult(String.format(MESSAGE_EDIT_CONTACT_SUCCESS, Messages.format(editedContact)));

    }

    /**
     * Creates and returns a {@code Contact} with the details of {@code contactToEdit}
     * edited with {@code editContactDescriptor}.
     */
    private static Contact createEditedContact(Contact contactToEdit, EditContactDescriptor editContactDescriptor) {
        assert contactToEdit != null;

        Name updatedName = editContactDescriptor.getName().orElse(contactToEdit.getName());
        Phone updatedPhone = editContactDescriptor.getPhone().orElse(contactToEdit.getPhone());
        Email updatedEmail = editContactDescriptor.getEmail().orElse(contactToEdit.getEmail());
        Address updatedAddress = editContactDescriptor.getAddress().orElse(contactToEdit.getAddress());
        Set<Tag> updatedTags = editContactDescriptor.getTags().orElse(contactToEdit.getTags());

        return new Contact(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditContactCommand)) {
            return false;
        }

        EditContactCommand otherEditCommand = (EditContactCommand) other;
        return index.equals(otherEditCommand.index)
                && editContactDescriptor.equals(otherEditCommand.editContactDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editContactDescriptor", editContactDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the contact with. Each non-empty field value will replace the
     * corresponding field value of the contact.
     */
    public static class EditContactDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;

        public EditContactDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditContactDescriptor(EditContactDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
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

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditContactDescriptor)) {
                return false;
            }

            EditContactDescriptor otherEditContactDescriptor = (EditContactDescriptor) other;
            return Objects.equals(name, otherEditContactDescriptor.name)
                    && Objects.equals(phone, otherEditContactDescriptor.phone)
                    && Objects.equals(email, otherEditContactDescriptor.email)
                    && Objects.equals(address, otherEditContactDescriptor.address)
                    && Objects.equals(tags, otherEditContactDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("tags", tags)
                    .toString();
        }
    }
}
