package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINKEDIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SECONDARY_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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
 * Adds alternative contact information to the specified person.
 */
public class AddAltCommand extends Command {

    public static final String COMMAND_WORD = "addalt";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds alternative contact of the person identified "
            + "by the index number used in the last person listing.\n"
            + "At least one field must be present.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_BIRTHDAY + "[BIRTHDAY] "
            + PREFIX_TELEGRAM + "[TELEGRAM] "
            + PREFIX_SECONDARY_EMAIL + "[SECONDARY_EMAIL] "
            + PREFIX_LINKEDIN + "[LINKEDIN]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TELEGRAM + "johndoe_telegram"
            + PREFIX_SECONDARY_EMAIL + "johndoe2@example.com";
    public static final String MESSAGE_ADDALT_SUCCESS = "Added alternative contact information to Person: %1$s";
    public static final String MESSAGE_ADDALT_DUPLICATE_EMAIL = "Secondary email is same as primary email "
            + "for Person: %1$s";
    public static final String MESSAGE_ADDALT_FAILURE = "There is existing %1$sto Person: %2$s. "
            + "\nTo change the corresponding fields, use the edit command.";
    private final Index index;
    private final AddAltPersonDescriptor addAltPersonDescriptor;

    /**
     * Constructs a {@code AddAltCommand}.
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
     * updated with {@code addAltPersonDescriptor}.
     * @throws CommandException if the alternative contact information added to the person is not empty
     *      or when updated {@code personToEdit} has same primary and secondary email.
     */
    private static Person createAddAltPerson(Person personToEdit, AddAltPersonDescriptor addAltPersonDescriptor)
            throws CommandException {
        assert personToEdit != null;

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        Set<Tag> tags = personToEdit.getTags();
        Optional<Birthday> updatedBirthday = personToEdit.getBirthday().or(() -> addAltPersonDescriptor.getBirthday());
        Optional<Linkedin> updatedLinkedin = personToEdit.getLinkedin().or(() -> addAltPersonDescriptor.getLinkedin());
        Optional<Email> updatedSecondaryEmail = personToEdit.getSecondaryEmail()
                .or(() -> addAltPersonDescriptor.getSecondaryEmail());
        Optional<Telegram> updatedTelegram = personToEdit.getTelegram().or(() -> addAltPersonDescriptor.getTelegram());
        Optional<Integer> id = personToEdit.getId();
        ObservableList<Note> notes = personToEdit.getNotes();
        Balance balance = personToEdit.getBalance();

        Person updatedPerson = new Person(name, phone, email, address, updatedBirthday, updatedLinkedin,
                updatedSecondaryEmail, updatedTelegram, tags, id, notes, balance);

        // Records down the alternative contact fields that are not initially empty.
        String existingAlternativeContactField = "";

        if (personToEdit.hasValidLinkedin() && addAltPersonDescriptor.hasValidLinkedin()) {
            existingAlternativeContactField += "Linkedin ";
        }
        if (personToEdit.hasValidBirthday() && addAltPersonDescriptor.hasValidBirthday()) {
            existingAlternativeContactField += "Birthday ";
        }
        if (personToEdit.hasValidSecondaryEmail() && addAltPersonDescriptor.hasValidSecondaryEmail()) {
            existingAlternativeContactField += "Secondary Email ";
        }
        if (personToEdit.hasValidTelegram() && addAltPersonDescriptor.hasValidTelegram()) {
            existingAlternativeContactField += "Telegram ";
        }

        if (!existingAlternativeContactField.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_ADDALT_FAILURE, existingAlternativeContactField, name));
        } else if (updatedPerson.hasRepeatedEmail()) {
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("addAltPersonDescriptor", addAltPersonDescriptor)
                .toString();
    }

    /**
     * Stores the alternative contact details to add the specified person with. Each empty alternative contact field
     * will be replaced by the corresponding field value specified by the user.
     */
    public static class AddAltPersonDescriptor {
        private Birthday birthday;
        private Linkedin linkedin;
        private Email secondaryEmail;
        private Telegram telegram;

        public AddAltPersonDescriptor() {}

        public void setBirthday(Birthday birthday) {
            this.birthday = birthday;
        }

        public Optional<Birthday> getBirthday() {
            return Optional.ofNullable(birthday);
        }

        public boolean hasValidBirthday() {
            return birthday != null;
        }

        public void setLinkedin(Linkedin linkedin) {
            this.linkedin = linkedin;
        }

        public Optional<Linkedin> getLinkedin() {
            return Optional.ofNullable(linkedin);
        }
        public boolean hasValidLinkedin() {
            return linkedin != null;
        }

        public void setSecondaryEmail(Email email) {
            this.secondaryEmail = email;
        }

        public Optional<Email> getSecondaryEmail() {
            return Optional.ofNullable(secondaryEmail);
        }

        public boolean hasValidSecondaryEmail() {
            return secondaryEmail != null;
        }

        public void setTelegram(Telegram telegram) {
            this.telegram = telegram;
        }

        public Optional<Telegram> getTelegram() {
            return Optional.ofNullable(telegram);
        }

        public boolean hasValidTelegram() {
            return telegram != null;
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
            return Objects.equals(birthday, otherAddAltPersonDescriptor.birthday)
                    && Objects.equals(linkedin, otherAddAltPersonDescriptor.linkedin)
                    && Objects.equals(telegram, otherAddAltPersonDescriptor.telegram)
                    && Objects.equals(secondaryEmail, otherAddAltPersonDescriptor.secondaryEmail);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("birthday", birthday)
                    .add("linkedin", linkedin)
                    .add("secondaryEmail", secondaryEmail)
                    .add("telegram", telegram)
                    .toString();
        }
    }
}
