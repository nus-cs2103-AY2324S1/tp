package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNASSIGN_GROUPS;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditPersonCommand extends Command {

    public static final String COMMAND_WORD = "edit_person";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_BIRTHDAY + "BIRTHDAY] "
            + "[" + PREFIX_REMARK + "REMARK] "
            + "[" + PREFIX_GROUP + "GROUP]... "
            + "[" + PREFIX_UNASSIGN_GROUPS + "GROUP]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    private final Logger logger = LogsCenter.getLogger(EditPersonCommand.class);

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditPersonCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
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

        // This check must happen first to check duplicate persons
        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            System.out.println("duplicate detected 2");
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);

        // Update assigned persons in the event list
        model.updateAssignedPersons(personToEdit, editedPerson);

        /* Remove empty groups from event when un-assigning groups from persons
            and that person is the last member of the group
        */
        Set<Group> emptyGroups = model.getEmptyGroups(personToEdit);
        if (!emptyGroups.isEmpty()) {
            for (Group group : emptyGroups) {
                logger.info(String.format("Removing empty group: %s", group));
            }
            model.removeEmptyGroups(emptyGroups);
        } else {
            model.updateGroups();
        }
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit,
                                             EditPersonDescriptor editPersonDescriptor) throws CommandException {

        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Birthday updatedBirthday = editPersonDescriptor.getBirthday().orElse(personToEdit.getBirthday());
        Remark updatedRemark = editPersonDescriptor.getRemark().orElse(personToEdit.getRemark());

        Set<Group> updatedGroups = new HashSet<>();

        LocalDate bd = updatedBirthday.getValue();
        if (bd != null && bd.isAfter(LocalDate.now())) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_BIRTHDAY,
                    updatedBirthday.getStringValue()));
        }

        if (editPersonDescriptor.getGroups().isPresent()) {
            updatedGroups.addAll(personToEdit.getGroups());
            updatedGroups.addAll(editPersonDescriptor.getGroups().get());
        } else {
            updatedGroups.addAll(personToEdit.getGroups());
        }

        if (editPersonDescriptor.getUnassignGroups().isPresent()) {
            if (personToEdit.getGroups().containsAll(editPersonDescriptor.getUnassignGroups().get())) {
                updatedGroups.removeAll(editPersonDescriptor.getUnassignGroups().get());
            } else {
                Set<Group> invalidGroups =
                        getInvalidGroups(personToEdit, editPersonDescriptor.getUnassignGroups().get());

                throw new CommandException(String.format(Messages.MESSAGE_INVALID_UNASSIGN_GROUP,
                        listInvalidGroups(invalidGroups)));
            }
            updatedGroups.removeAll(editPersonDescriptor.getUnassignGroups().get());
        }


        Optional<Phone> phone = Optional.ofNullable(updatedPhone);
        Optional<Email> email = Optional.ofNullable(updatedEmail);
        Optional<Address> address = Optional.ofNullable(updatedAddress);
        Optional<Birthday> birthday = Optional.ofNullable(updatedBirthday);
        Optional<Remark> remark = Optional.ofNullable(updatedRemark);
        return new Person(updatedName, phone, email, address, birthday, remark, updatedGroups);
    }

    private static Set<Group> getInvalidGroups(Person personToEdit, Set<Group> groups) {
        Set<Group> invalidGroups = new HashSet<>();
        for (Group group : groups) {
            if (!personToEdit.getGroups().contains(group)) {
                invalidGroups.add(group);
            }
        }

        return invalidGroups;
    }

    private static String listInvalidGroups(Set<Group> invalidGroups) {
        StringBuilder builder = new StringBuilder();
        for (Group group : invalidGroups) {
            builder.append(group.toString());
            builder.append(", ");
        }

        builder.delete(builder.length() - 2, builder.length()); //removes the last comma
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPersonCommand)) {
            return false;
        }

        EditPersonCommand otherEditPersonCommand = (EditPersonCommand) other;
        return index.equals(otherEditPersonCommand.index)
                && editPersonDescriptor.equals(otherEditPersonCommand.editPersonDescriptor);
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
        private Birthday birthday;
        private Remark remark;
        private Set<Group> groups;

        private Set<Group> unassignGroups;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code groups} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setBirthday(toCopy.birthday);
            setRemark(toCopy.remark);
            setGroups(toCopy.groups);
            setUnassignGroups(toCopy.unassignGroups);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, birthday, remark, groups, unassignGroups);
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

        public Optional<Birthday> getBirthday() {
            return Optional.ofNullable(birthday);
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        /**
         * Sets {@code groups} to this object's {@code groups}.
         * A defensive copy of {@code groups} is used internally.
         */
        public void setGroups(Set<Group> groups) {
            this.groups = (groups != null) ? new HashSet<>(groups) : null;
        }

        /**
         * Sets {@code groups} to this object's {@code unassignGroups}.
         */
        public void setUnassignGroups(Set<Group> groups) {
            this.unassignGroups = groups != null ? new HashSet<>(groups) : null;
        }

        /**
         * Returns an unmodifiable group set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code groups} is null.
         */
        public Optional<Set<Group>> getGroups() {
            return (groups != null) ? Optional.of(Collections.unmodifiableSet(groups)) : Optional.empty();
        }

        /**
         * Returns an unmodifiable group set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code groups} is null.
         */
        public Optional<Set<Group>> getUnassignGroups() {
            return (unassignGroups != null) ? Optional.of(unassignGroups)
                    : Optional.empty();
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
                    && Objects.equals(remark, otherEditPersonDescriptor.remark)
                    && Objects.equals(groups, otherEditPersonDescriptor.groups);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("birthday", birthday)
                    .add("remark", remark)
                    .add("groups", groups)
                    .toString();
        }


    }
}
