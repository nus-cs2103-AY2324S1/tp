package swe.context.logic.commands;

import static java.util.Objects.requireNonNull;
import static swe.context.logic.parser.CliSyntax.PREFIX_ALTERNATE;
import static swe.context.logic.parser.CliSyntax.PREFIX_EMAIL;
import static swe.context.logic.parser.CliSyntax.PREFIX_NAME;
import static swe.context.logic.parser.CliSyntax.PREFIX_NOTE;
import static swe.context.logic.parser.CliSyntax.PREFIX_PHONE;
import static swe.context.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import swe.context.commons.core.index.Index;
import swe.context.commons.util.CollectionUtil;
import swe.context.commons.util.ToStringBuilder;
import swe.context.logic.Messages;
import swe.context.logic.commands.exceptions.CommandException;
import swe.context.model.Model;
import swe.context.model.ModelManager;
import swe.context.model.alternate.AlternateContact;
import swe.context.model.contact.Contact;
import swe.context.model.contact.Email;
import swe.context.model.contact.Name;
import swe.context.model.contact.Note;
import swe.context.model.contact.Phone;
import swe.context.model.tag.Tag;



/**
 * Edits an existing {@link Contact}.
 */
public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = String.format(
        "%s: Edits a contact. At least one optional parameter required."
                + "%nParameters: INDEX [%sNAME] [%sPHONE_NUMBER] [%sEMAIL]"
                + " [%sNOTE] [%sTAG]... [%sALTERNATE_CONTACT]..."
                + "%nExample: %s 3 %sMember of NUS S/U %s",
        EditCommand.COMMAND_WORD,
        PREFIX_NAME,
        PREFIX_PHONE,
        PREFIX_EMAIL,
        PREFIX_NOTE,
        PREFIX_TAG,
        PREFIX_ALTERNATE,
        EditCommand.COMMAND_WORD,
        PREFIX_NOTE,
        PREFIX_TAG
    );

    private final Index index;
    private final EditContactDescriptor editContactDescriptor;

    /**
     * @param index of the contact in the filtered contact list to edit
     * @param editContactDescriptor details to edit the contact with
     */
    public EditCommand(Index index, EditContactDescriptor editContactDescriptor) {
        requireNonNull(index);
        requireNonNull(editContactDescriptor);

        this.index = index;
        this.editContactDescriptor = new EditContactDescriptor(editContactDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        validateIndex(lastShownList, index);

        Contact contactToEdit = lastShownList.get(index.getZeroBased());
        Contact editedContact = createEditedContact(contactToEdit, editContactDescriptor);

        ensureUniqueContact(model, contactToEdit, editedContact);

        model.updateContact(contactToEdit, editedContact);
        model.setContactsFilter(ModelManager.FILTER_NONE);

        return new CommandResult(Messages.editCommandSuccess(Contact.format(editedContact)));
    }

    private void validateIndex(List<Contact> contactList, Index index) throws CommandException {
        if (index.getZeroBased() >= contactList.size()) {
            throw new CommandException(Messages.INVALID_EDIT_INDEX);
        }
    }

    private void ensureUniqueContact(Model model, Contact original, Contact edited) throws CommandException {
        if (!original.isSameContact(edited) && model.containsContact(edited)) {
            throw new CommandException(Messages.COMMAND_DUPLICATE_CONTACT);
        }
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
        Note updatedAddress = editContactDescriptor.getNote().orElse(contactToEdit.getNote());
        Set<Tag> updatedTags = editContactDescriptor.getTags().orElse(contactToEdit.getTags());
        Set<AlternateContact> updatedAlternateContacts =
                editContactDescriptor.getAlternateContacts().orElse(contactToEdit.getAlternates());

        return new Contact(
                updatedName,
                updatedPhone,
                updatedEmail,
                updatedAddress,
                updatedTags,
                updatedAlternateContacts
        );
    }

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
        private Note note;
        private Set<Tag> tags;
        private Set<AlternateContact> alternateContacts;

        public EditContactDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditContactDescriptor(EditContactDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setNote(toCopy.note);
            setTags(toCopy.tags);
            setAlternateContacts(toCopy.alternateContacts);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, note, tags, alternateContacts);
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

        public void setNote(Note _note) {
            this.note = _note;
        }

        public Optional<Note> getNote() {
            return Optional.ofNullable(this.note);
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

        public void setAlternateContacts(Set<AlternateContact> alternateContacts) {
            this.alternateContacts = (alternateContacts != null) ? new HashSet<>(alternateContacts) : null;
        }

        public Optional<Set<AlternateContact>> getAlternateContacts() {
            return (alternateContacts != null)
                    ? Optional.of(Collections.unmodifiableSet(alternateContacts))
                    : Optional.empty();
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
            return Objects.equals(this.name, otherEditContactDescriptor.name)
                    && Objects.equals(this.phone, otherEditContactDescriptor.phone)
                    && Objects.equals(this.email, otherEditContactDescriptor.email)
                    && Objects.equals(this.note, otherEditContactDescriptor.note)
                    && Objects.equals(this.tags, otherEditContactDescriptor.tags)
                    && Objects.equals(this.alternateContacts, otherEditContactDescriptor.alternateContacts);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", this.name)
                    .add("phone", this.phone)
                    .add("email", this.email)
                    .add("note", this.note)
                    .add("tags", this.tags)
                    .add("alternate contacts", this.alternateContacts)
                    .toString();
        }
    }
}
