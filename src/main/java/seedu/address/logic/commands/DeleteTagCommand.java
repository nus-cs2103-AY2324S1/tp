package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PreferredContact;
import seedu.address.model.person.PreferredMeetingRegion;
import seedu.address.model.policy.Policy;
import seedu.address.model.tag.Tag;

/**
 * Deletes a tag from an existing person in the address book.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "deleteTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the tags of the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX(must be a positive integer)"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "friend";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted tags successfully for person %1$s";

    public static final String MESSAGE_NOT_EDITED = "One tag must be provided.";

    public static final String MESSAGE_INVALID_TAGS_PROVIDED =
            "Tags provided do not exist. Please provide an existing tag.";

    private final Index index;
    private final DeleteTagDescriptor deleteTagDescriptor;

    /**
     * @param index               of the person in the filtered person list to edit
     * @param deleteTagDescriptor details the tags to delete from the person
     */
    public DeleteTagCommand(Index index, DeleteTagDescriptor deleteTagDescriptor) {
        requireNonNull(index);
        requireNonNull(deleteTagDescriptor);
        this.index = index;
        this.deleteTagDescriptor = new DeleteTagDescriptor(deleteTagDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person personWithDeletedTag = createPersonWithDeletedTag(personToEdit, deleteTagDescriptor);
        personWithDeletedTag.deleteTags(personToEdit.getTags());

        if (personWithDeletedTag.getTags().size() == personToEdit.getTags().size()) {
            throw new CommandException(MESSAGE_INVALID_TAGS_PROVIDED);
        }

        model.setPerson(personToEdit, personWithDeletedTag);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS, Messages.format(personWithDeletedTag)));

    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code deleteTagDescriptor}.
     */
    private static Person createPersonWithDeletedTag(Person personToEdit, DeleteTagDescriptor deleteTagDescriptor) {
        assert personToEdit != null;

        Name updatedName = deleteTagDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = deleteTagDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = deleteTagDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = deleteTagDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = deleteTagDescriptor.getTags().orElse(personToEdit.getTags());
        PreferredContact updatePreferredContact = deleteTagDescriptor.getPreferredContact()
                .orElse(personToEdit.getPreferredContact());
        PreferredMeetingRegion updatePreferredMeetingRegion = deleteTagDescriptor.getPreferredMeetingRegion()
                .orElse(personToEdit.getPreferredMeetingRegion());
        Set<Policy> updatedPolicies = deleteTagDescriptor.getPolicies().orElse(personToEdit.getPolicies());
        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                updatePreferredMeetingRegion,
                updatePreferredContact, updatedPolicies);
    }

    /**
     * Compares this with another object.
     * @param other object to compare
     * @return true if the other object is a DeleteTagCommand with the same index
     *         and descriptor
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTagCommand)) {
            return false;
        }

        DeleteTagCommand otherDeleteTagCommand = (DeleteTagCommand) other;
        return index.equals(otherDeleteTagCommand.index)
                && deleteTagDescriptor.equals(otherDeleteTagCommand.deleteTagDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .toString();
    }

    /**
     * Stores the details to delete the tag with. Deleted tag will be deleted from
     * current list of tag.
     */
    public static class DeleteTagDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private PreferredContact preferredContact;
        private PreferredMeetingRegion preferredMeetingRegion;
        private Set<Policy> policies;

        /**
         * Creates an empty {@code DeleteTagDescriptor}.
         */
        public DeleteTagDescriptor() {
        }

        /**
         * Copy constructor.s
         * A defensive copy of {@code tags} is used internally.
         * @param toCopy DeleteTagDescriptor to copy
         */
        public DeleteTagDescriptor(DeleteTagDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setPreferredContact(toCopy.preferredContact);
            setPreferredMeetingRegion(toCopy.preferredMeetingRegion);
            setPolicies(toCopy.policies);
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
         * Returns an unmodifiable tag set, which throws
         * {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code preferredContact} to this object's {@code preferredContact}.
         */
        public void setPreferredContact(PreferredContact preferredContact) {
            this.preferredContact = preferredContact;
        }

        /**
         * Returns an optional preferred contact method of a person
         */
        public Optional<PreferredContact> getPreferredContact() {
            return Optional.ofNullable(preferredContact);
        }

        /**
         * Sets {@code preferredMeetingRegion} to this object's
         * {@code preferredMeetingRegion}.
         */
        public void setPreferredMeetingRegion(PreferredMeetingRegion preferredMeetingRegion) {
            this.preferredMeetingRegion = preferredMeetingRegion;
        }

        /**
         * Returns an optional preferred meeting region of a person
         */
        public Optional<PreferredMeetingRegion> getPreferredMeetingRegion() {
            return Optional.ofNullable(preferredMeetingRegion);
        }

        public void setPolicies(Set<Policy> policies) {
            this.policies = (policies != null) ? new HashSet<>(policies) : null;
        }

        /**
         * Returns an unmodifiable policy set, which throws
         * {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code policies} is null.
         */
        public Optional<Set<Policy>> getPolicies() {
            return (policies != null) ? Optional.of(Collections.unmodifiableSet(policies)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof DeleteTagDescriptor)) {
                return false;
            }

            DeleteTagDescriptor otherDeleteTagDescriptor = (DeleteTagDescriptor) other;
            return Objects.equals(name, otherDeleteTagDescriptor.name)
                    && Objects.equals(phone, otherDeleteTagDescriptor.phone)
                    && Objects.equals(email, otherDeleteTagDescriptor.email)
                    && Objects.equals(address, otherDeleteTagDescriptor.address)
                    && Objects.equals(tags, otherDeleteTagDescriptor.tags)
                    && Objects.equals(preferredContact, otherDeleteTagDescriptor.preferredContact)
                    && Objects.equals(preferredMeetingRegion, otherDeleteTagDescriptor.preferredMeetingRegion)
                    && Objects.equals(policies, otherDeleteTagDescriptor.policies);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("tags", tags)
                    .add("preferredContact", preferredContact)
                    .add("preferredMeetingRegion", preferredMeetingRegion)
                    .toString();
        }
    }
}
