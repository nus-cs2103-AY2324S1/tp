package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINKEDIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SECONDARY_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Avatar;
import seedu.address.model.person.Balance;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Linkedin;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values must be non empty and will be overwritten by the input values.\n"
            + "At least one field to edit must be provided.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_SECONDARY_EMAIL + "SECONDARYEMAIL] "
            + "[" + PREFIX_LINKEDIN + "LINKEDIN] "
            + "[" + PREFIX_BIRTHDAY + "BIRTHDAY] "
            + "[" + PREFIX_TELEGRAM + "TELEGRAM] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NO_CHANGE = "The input fields of this person: %1$s you are editing"
            + " is the same.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_DUPLICATE_EMAIL = "Both primary and secondary email are the same "
            + "for Person: %1$s";
    public static final String MESSAGE_USE_ADDALT_COMMAND = "Empty alternative contact field(s): %s \n"
            + "Use the addalt command to fill in the alternative contact fields.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor)
            throws CommandException {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Optional<Birthday> updatedBirthday = editPersonDescriptor.getBirthday().or(() -> personToEdit.getBirthday());
        Optional<Linkedin> updatedLinkedin = editPersonDescriptor.getLinkedin().or(() -> personToEdit.getLinkedin());
        Optional<Email> updatedSecondaryEmail = editPersonDescriptor.getSecondaryEmail()
                .or(() -> personToEdit.getSecondaryEmail());
        Optional<Telegram> updatedTelegram = editPersonDescriptor.getTelegram().or(() -> personToEdit.getTelegram());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Optional<Integer> id = personToEdit.getId();
        ObservableList<Note> notes = personToEdit.getNotes();
        Avatar avatar = personToEdit.getAvatar();
        Balance balance = personToEdit.getBalance();

        Person updatedPerson = new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedBirthday,
                updatedLinkedin, updatedSecondaryEmail, updatedTelegram, updatedTags, id, avatar, notes, balance);

        // Records down the alternative contact fields that are initially empty.
        ArrayList<String> emptyAlternativeContactFields = new ArrayList<>();

        if (!personToEdit.hasValidLinkedin() && updatedPerson.hasValidLinkedin()) {
            emptyAlternativeContactFields.add("Linkedin");
        }
        if (!personToEdit.hasValidBirthday() && updatedPerson.hasValidBirthday()) {
            emptyAlternativeContactFields.add("Birthday");
        }
        if (!personToEdit.hasValidSecondaryEmail() && updatedPerson.hasValidSecondaryEmail()) {
            emptyAlternativeContactFields.add("Secondary Email");
        }
        if (!personToEdit.hasValidTelegram() && updatedPerson.hasValidTelegram()) {
            emptyAlternativeContactFields.add("Telegram");
        }

        if (!emptyAlternativeContactFields.isEmpty()) {
            String fields = String.join(", ", emptyAlternativeContactFields);
            throw new CommandException(String.format(MESSAGE_USE_ADDALT_COMMAND, fields));
        } else if (updatedPerson.hasRepeatedEmail()) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_EMAIL, personToEdit.getName()));
        } else if (updatedPerson.equals(personToEdit)) {
            throw new CommandException(String.format(MESSAGE_NO_CHANGE, updatedName));
        }

        return updatedPerson;
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
                && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private Birthday birthday;
        private Linkedin linkedin;
        private Email secondaryEmail;
        private Telegram telegram;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setSecondaryEmail(toCopy.secondaryEmail);
            setTelegram(toCopy.telegram);
            setBirthday(toCopy.birthday);
            setLinkedin(toCopy.linkedin);
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

        public void setLinkedin(Linkedin linkedin) {
            this.linkedin = linkedin;
        }

        public Optional<Linkedin> getLinkedin() {
            return Optional.ofNullable(linkedin);
        }

        public void setBirthday(Birthday birthday) {
            this.birthday = birthday;
        }

        public Optional<Birthday> getBirthday() {
            return Optional.ofNullable(birthday);
        }

        public void setTelegram(Telegram telegram) {
            this.telegram = telegram;
        }

        public Optional<Telegram> getTelegram() {
            return Optional.ofNullable(telegram);
        }

        public void setSecondaryEmail(Email secondaryEmail) {
            this.secondaryEmail = secondaryEmail;
        }

        public Optional<Email> getSecondaryEmail() {
            return Optional.ofNullable(secondaryEmail);
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
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            EditPersonDescriptor otherEditPersonDescriptor = (EditPersonDescriptor) other;
            return Objects.equals(name, otherEditPersonDescriptor.name)
                    && Objects.equals(phone, otherEditPersonDescriptor.phone)
                    && Objects.equals(email, otherEditPersonDescriptor.email)
                    && Objects.equals(address, otherEditPersonDescriptor.address)
                    && Objects.equals(birthday, otherEditPersonDescriptor.birthday)
                    && Objects.equals(linkedin, otherEditPersonDescriptor.linkedin)
                    && Objects.equals(secondaryEmail, otherEditPersonDescriptor.secondaryEmail)
                    && Objects.equals(telegram, otherEditPersonDescriptor.telegram)
                    && Objects.equals(tags, otherEditPersonDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("tags", tags)
                    .add("birthday", birthday)
                    .add("linkedin", linkedin)
                    .add("secondaryEmail", secondaryEmail)
                    .add("telegram", telegram)
                    .toString();
        }
    }
}
