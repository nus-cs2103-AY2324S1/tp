package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_BEGIN_AFTER_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BEGIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYRATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Begin;
import seedu.address.model.person.Day;
import seedu.address.model.person.Email;
import seedu.address.model.person.End;
import seedu.address.model.person.Lesson;
import seedu.address.model.person.Name;
import seedu.address.model.person.PayRate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Subject;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_SUBJECT + "SUBJECT] "
            + "[" + PREFIX_DAY + "DAY] "
            + "[" + PREFIX_BEGIN + "BEGIN] "
            + "[" + PREFIX_END + "END] "
            + "[" + PREFIX_PAID + "PAID]...\n"
            + "[" + PREFIX_PAYRATE + "PAYRATE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This tutee already exists";
    public static final String MESSAGE_DUPLICATE_DATE = "This date clashes with an existing schedule";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;
    private final Logger logger = LogsCenter.getLogger(getClass());


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
            throw new CommandException(Messages.MESSAGE_INVALID_TUTEE_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!Lesson.isValid(editedPerson.getBegin(), editedPerson.getEnd())) {
            throw new CommandException(String.format(MESSAGE_BEGIN_AFTER_END, AddCommand.MESSAGE_USAGE));
        }

        if (editedPerson.getBegin() == null) {
            if (!Lesson.isValid(personToEdit.getBegin(), editedPerson.getEnd())) {
                throw new CommandException(String.format(MESSAGE_BEGIN_AFTER_END, AddCommand.MESSAGE_USAGE));
            }
        }

        if (editedPerson.getEnd() == null) {
            if (!Lesson.isValid(editedPerson.getBegin(), personToEdit.getEnd())) {
                throw new CommandException(String.format(MESSAGE_BEGIN_AFTER_END, AddCommand.MESSAGE_USAGE));
            }
        }

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            logger.info("[EditCommand.execute()]: Editing would result in duplicate persons");
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        } else if (editPersonDescriptor.getEditSchedule() && model.hasDate(editedPerson)) {
            logger.info("[EditCommand.execute()]: Editing would result in clashing schedules");
            throw new CommandException(MESSAGE_DUPLICATE_DATE);
        }

        model.setPerson(personToEdit, editedPerson, editPersonDescriptor.getEditSchedule());
        model.purgeAddressBook();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.commitAddressBook();

        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
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
        Subject updatedSubject = editPersonDescriptor.getSubject().orElse(personToEdit.getSubject());
        Day updatedDay = editPersonDescriptor.getDay().orElse(personToEdit.getDay());
        Begin updatedBegin = editPersonDescriptor.getBegin().orElse(personToEdit.getBegin());
        End updatedEnd = editPersonDescriptor.getEnd().orElse(personToEdit.getEnd());
        Boolean updatedPaid = editPersonDescriptor.getPaid().orElse(personToEdit.getPaid());
        PayRate updatedPayRate = editPersonDescriptor.getPayRate().orElse(personToEdit.getPayRate());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedSubject, updatedDay,
                updatedBegin, updatedEnd, updatedPaid, updatedPayRate);
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
        private Subject subject;
        private Day day;
        private Begin begin;
        private End end;
        private Boolean paid;
        private PayRate payRate;
        private Boolean editSchedule = false;

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
            setSubject(toCopy.subject);
            setDay(toCopy.day);
            setBegin(toCopy.begin);
            setEnd(toCopy.end);
            setPaid(toCopy.paid);
            setPayRate(toCopy.payRate);
            setEditSchedule(toCopy.editSchedule);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, subject, day, begin, end, paid, payRate);
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

        public void setSubject(Subject subject) {
            this.subject = subject;
        }

        public Optional<Subject> getSubject() {
            return Optional.ofNullable(subject);
        }

        public void setDay(Day day) {
            this.day = day;
        }

        public Optional<Day> getDay() {
            return Optional.ofNullable(day);
        }

        public void setBegin(Begin begin) {
            this.begin = begin;
        }

        public Optional<Begin> getBegin() {
            return Optional.ofNullable(begin);
        }

        public void setEnd(End end) {
            this.end = end;
        }

        public Optional<End> getEnd() {
            return Optional.ofNullable(end);
        }

        public void setPaid(Boolean paid) {
            this.paid = paid;
        }

        public Optional<Boolean> getPaid() {
            return Optional.ofNullable(paid);
        }

        public void setPayRate(PayRate payRate) {
            this.payRate = payRate;
        }

        public Optional<PayRate> getPayRate() {
            return Optional.ofNullable(payRate);
        }
        public void setEditSchedule(Boolean setTrue) {
            this.editSchedule = setTrue;
        }

        public Boolean getEditSchedule() {
            return this.editSchedule;
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
                    && Objects.equals(subject, otherEditPersonDescriptor.subject)
                    && Objects.equals(day, otherEditPersonDescriptor.day)
                    && Objects.equals(begin, otherEditPersonDescriptor.begin)
                    && Objects.equals(end, otherEditPersonDescriptor.end)
                    && Objects.equals(paid, otherEditPersonDescriptor.paid)
                    && Objects.equals(payRate, otherEditPersonDescriptor.payRate);

        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("day", day)
                    .add("begin", begin)
                    .add("end", end)
                    .add("paid", paid)
                    .add("payrate", payRate)
                    .add("editSchedule", editSchedule)
                    .toString();
        }
    }
}
