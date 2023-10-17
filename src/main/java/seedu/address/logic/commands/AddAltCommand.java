package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINKEDIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SECONDARY_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
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
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Linkedin;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Changes the remark of an existing person in the address book.
 */
public class AddAltCommand extends Command {

    public static final String COMMAND_WORD = "addalt";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds alternative contact of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing alternative contact will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_BIRTHDAY + "[BIRTHDAY] "
            + PREFIX_TELEGRAM + "[TELEGRAM] "
            + PREFIX_SECONDARY_EMAIL + "[SECONDARY_EMAIL] "
            + PREFIX_LINKEDIN + "[LINKEDIN]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TELEGRAM + "johndoe_telegram"
            + PREFIX_SECONDARY_EMAIL + "johndoe2@example.com";
    public static final String MESSAGE_ADDALT_SUCCESS = "Added alternative contact to Person: %1$s";
    public static final String MESSAGE_ADDALT_DUPLICATE_EMAIL = "Secondary email is same as primary email "
            + "for Person: %1$s";
    public static final String MESSAGE_ADDALT_FAILURE = "There is existing alternative contact to Person: %1$s. "
            + "To change the corresponding field, use the edit command.";
    private final Index index;
    private final AddAltPersonDescriptor addAltPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param addAltPersonDescriptor details of the person to be updated to
     */
    public AddAltCommand(Index index, AddAltPersonDescriptor addAltPersonDescriptor) {
        requireAllNonNull(index, addAltPersonDescriptor);

        this.index = index;
        this.addAltPersonDescriptor = addAltPersonDescriptor;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createAddAltPerson(personToEdit, addAltPersonDescriptor);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_ADDALT_SUCCESS, Messages.format(editedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     * @throws CommandException if the alternative contact information added to the person is not empty or invalid.
     */
    private static Person createAddAltPerson(Person personToEdit, AddAltPersonDescriptor addAltPersonDescriptor)
            throws CommandException {
        assert personToEdit != null;

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        Set<Tag> tags = personToEdit.getTags();
        Birthday birthday = personToEdit.getBirthday().orElse(addAltPersonDescriptor.getBirthday());
        Linkedin updatedLinkedin = personToEdit.getLinkedin().orElse(addAltPersonDescriptor.getLinkedin());
        Email updatedSecondaryEmail = personToEdit.getSecondaryEmail()
                .orElse(addAltPersonDescriptor.getSecondaryEmail());
        Telegram updatedTelegram = personToEdit.getTelegram().orElse(addAltPersonDescriptor.getTelegram());
        Optional<Integer> id = personToEdit.getId();

        Person updatedPerson = new Person(name, phone, email, address, Optional.ofNullable(birthday),
                Optional.ofNullable(updatedLinkedin), Optional.ofNullable(updatedSecondaryEmail),
                Optional.ofNullable(updatedTelegram), tags, id);

        if ((personToEdit.hasValidLinkedin() && addAltPersonDescriptor.hasValidLinkedin())
                || (personToEdit.hasValidBirthday() && addAltPersonDescriptor.hasValidBirthday())
                || (personToEdit.hasValidSecondaryEmail() && addAltPersonDescriptor.hasValidSecondaryEmail())
                || (personToEdit.hasValidTelegram() && addAltPersonDescriptor.hasValidTelegram())) {
            throw new CommandException(String.format(MESSAGE_ADDALT_FAILURE, name));
        } else if (updatedPerson.hasSameEmail(updatedSecondaryEmail)) {
            throw new CommandException(String.format(MESSAGE_ADDALT_DUPLICATE_EMAIL, name));
        } else {
            return updatedPerson;
        }
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAltCommand)) {
            return false;
        }

        AddAltCommand otherAddAltCommand = (AddAltCommand) other;
        return index.equals(otherAddAltCommand.index)
                && addAltPersonDescriptor.equals(otherAddAltCommand.addAltPersonDescriptor);
    }

    /**
     * Stores the alternative contact details to add the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class AddAltPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private Birthday birthday;

        private Linkedin linkedin;
        private Email secondaryEmail;
        private Telegram telegram;

        public AddAltPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public AddAltPersonDescriptor(AddAltPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setBirthday(toCopy.birthday);
            setLinkedin(toCopy.linkedin);
            setSecondaryEmail(toCopy.secondaryEmail);
            setTelegram(toCopy.telegram);
            setTags(toCopy.tags);
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

        public void setBirthday(Birthday birthday) {
            this.birthday = birthday;
        }

        public Birthday getBirthday() {
            return birthday;
        }

        public boolean hasValidBirthday() {
            return birthday != null;
        }

        public void setLinkedin(Linkedin linkedin) {
            this.linkedin = linkedin;
        }

        public Linkedin getLinkedin() {
            return linkedin;
        }
        public boolean hasValidLinkedin() {
            return linkedin != null;
        }

        public void setSecondaryEmail(Email email) {
            this.secondaryEmail = email;
        }

        public Email getSecondaryEmail() {
            return secondaryEmail;
        }

        public boolean hasValidSecondaryEmail() {
            return secondaryEmail != null;
        }

        public void setTelegram(Telegram telegram) {
            this.telegram = telegram;
        }

        public Telegram getTelegram() {
            return telegram;
        }

        public boolean hasValidTelegram() {
            return telegram != null;
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
            if (!(other instanceof AddAltPersonDescriptor)) {
                return false;
            }

            AddAltPersonDescriptor otherAddAltPersonDescriptor = (AddAltPersonDescriptor) other;
            return Objects.equals(name, otherAddAltPersonDescriptor.name)
                    && Objects.equals(phone, otherAddAltPersonDescriptor.phone)
                    && Objects.equals(email, otherAddAltPersonDescriptor.email)
                    && Objects.equals(address, otherAddAltPersonDescriptor.address)
                    && Objects.equals(birthday, otherAddAltPersonDescriptor.birthday)
                    && Objects.equals(tags, otherAddAltPersonDescriptor.tags)
                    && Objects.equals(linkedin, otherAddAltPersonDescriptor.linkedin)
                    && Objects.equals(telegram, otherAddAltPersonDescriptor.telegram)
                    && Objects.equals(secondaryEmail, otherAddAltPersonDescriptor.secondaryEmail);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("birthday", birthday)
                    .add("tags", tags)
                    .add("linkedin", linkedin)
                    .add("secondaryEmail", secondaryEmail)
                    .add("telegram", telegram)
                    .toString();
        }
    }
}
