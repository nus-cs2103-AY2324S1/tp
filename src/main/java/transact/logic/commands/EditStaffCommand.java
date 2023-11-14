package transact.logic.commands;

import static java.util.Objects.requireNonNull;
import static transact.commons.util.CollectionUtil.requireAllNonNull;
import static transact.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static transact.logic.parser.CliSyntax.PREFIX_EMAIL;
import static transact.logic.parser.CliSyntax.PREFIX_NAME;
import static transact.logic.parser.CliSyntax.PREFIX_PHONE;
import static transact.logic.parser.CliSyntax.PREFIX_TAG;
import static transact.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javafx.collections.ObservableMap;
import transact.commons.util.CollectionUtil;
import transact.commons.util.ToStringBuilder;
import transact.logic.Messages;
import transact.logic.commands.exceptions.CommandException;
import transact.model.Model;
import transact.model.person.Address;
import transact.model.person.Email;
import transact.model.person.Name;
import transact.model.person.Person;
import transact.model.person.Phone;
import transact.model.tag.Tag;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionId;
import transact.ui.MainWindow.TabWindow;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditStaffCommand extends Command {

    public static final String COMMAND_WORD = "editstaff";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by their unqiue id displayed in the person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: ID (must be a non-negative integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_PERSON_ID_NOT_FOUND = "Cannot find person with id: %d";

    private final Integer personId;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param personId
     *            of the person to edit
     * @param editPersonDescriptor
     *            details to edit the person with
     */
    public EditStaffCommand(Integer personId, EditPersonDescriptor editPersonDescriptor) {
        requireAllNonNull(personId, editPersonDescriptor);

        this.personId = personId;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToEdit = model.getPerson(personId);

        if (personToEdit.equals(Person.NULL_PERSON)) {
            throw new CommandException(String.format(MESSAGE_PERSON_ID_NOT_FOUND, personId));
        }

        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        model.setPerson(personToEdit.getPersonId(), editedPerson);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // This is to trigger a refresh for the affected rows only
        ArrayList<Transaction> toBeRefreshed = new ArrayList<>();
        ObservableMap<TransactionId, Transaction> transactionMap = model.getTransactionMap();
        transactionMap.values().stream().filter(t -> t.getPersonId().equals(personToEdit.getPersonId().getValue()))
                .forEach(toBeRefreshed::add);
        toBeRefreshed.forEach(t -> {
            Transaction toAdd = new Transaction(t.getTransactionId(), t.getTransactionType(), new Description("tmp"),
                    t.getAmount(), t.getDate(), t.getPersonId());
            model.setTransaction(t.getTransactionId(), toAdd);
            model.setTransaction(t.getTransactionId(), t);
        });

        return new CommandResult(
                String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)),
                TabWindow.ADDRESSBOOK);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(personToEdit.getPersonId(), updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditStaffCommand)) {
            return false;
        }

        EditStaffCommand otherEditStaffCommand = (EditStaffCommand) other;
        return personId.equals(otherEditStaffCommand.personId)
                && editPersonDescriptor.equals(otherEditStaffCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("personId", personId)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will
     * replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
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
         * Returns an unmodifiable tag set, which throws
         * {@code UnsupportedOperationException}
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
                    .toString();
        }
    }
}
