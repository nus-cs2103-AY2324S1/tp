package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Email;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the patient identified by the name or nric of the patient.\n"
            + "Parameters: n/Name or id/Nric (must be valid)\n"
            + "Example: " + COMMAND_WORD + " n/John Doe or " + COMMAND_WORD + " id/S1234567A";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    public static final String MESSAGE_DELETE_PERSON_FIELD_SUCCESS = "Deleted Person's field: %1$s";

    private final DeletePersonDescriptor deletePersonDescriptor;

    private final Nric nric;

    private final Name name;

    /**
     * @param nric of the person in the filtered person list to edit
     * @param name of the person in the filtered person list to edit
     * @param deletePersonDescriptor details to delete the person with
     */
    public DeleteCommand(Nric nric, Name name, DeletePersonDescriptor deletePersonDescriptor) {
        this.nric = nric;
        this.name = name;
        this.deletePersonDescriptor = deletePersonDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        Optional<Person> findPersonToDelete = null;

        if (name != null) {
            Stream<Person> personbyName = lastShownList.stream()
                    .filter(person -> person.getName().equals(name));
            findPersonToDelete = findPersonToDeleteName(personbyName);
        } else if (nric != null) {
            findPersonToDelete = findPersonToDeleteIc(lastShownList);
        }

        Person personToDelete = findPersonToDelete.get();

        if (deletePersonDescriptor.isAllFalse()) {
            model.deletePerson(personToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
        } else {
            Person editedPerson = createEditedPerson(personToDelete, deletePersonDescriptor);
            model.setPerson(personToDelete, editedPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_FIELD_SUCCESS, Messages.format(editedPerson)));
        }
    }

    /**
     * Finds the person to delete by NRIC.
     *
     * @param persons list of persons in the address book.
     * @return the person to delete.
     * @throws CommandException if the person is not found.
     */
    public Optional<Person> findPersonToDeleteIc(List<Person> persons)
            throws CommandException {
        Optional<Person> personbyNric = persons.stream()
                .filter(person -> person.getNric().equals(nric))
                .findFirst();
        if (personbyNric.isPresent()) {
            return personbyNric;
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_NRIC);
        }
    }

    /**
     * Finds the person to delete by name.
     *
     * @param persons list of persons in the address book.
     * @return the person to delete.
     * @throws CommandException if the person is not found.
     */
    public Optional<Person> findPersonToDeleteName(Stream<Person> persons)
            throws CommandException {
        Optional<Person> personbyName = persons.findFirst();
        if (personbyName.isPresent()) {
            return personbyName;
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_NAME);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;

        return Objects.equals(nric, otherDeleteCommand.nric)
                && Objects.equals(name, otherDeleteCommand.name)
                && Objects.equals(deletePersonDescriptor, otherDeleteCommand.deletePersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("nric", nric)
                .add("name", name)
                .add("deletePersonDescriptor", deletePersonDescriptor)
                .toString();
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code deletePersonDescriptor}.
     */
    public static Person createEditedPerson(Person personToEdit, DeletePersonDescriptor deletePersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Nric updatedNric = personToEdit.getNric();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Set<Tag> updatedTags = personToEdit.getTags();
        Set<MedicalHistory> updatedMedicalHistories = personToEdit.getMedicalHistories();
        Appointment updatedAppointment = personToEdit.getAppointment().isPresent() ? personToEdit.getAppointment().get()
                : null;

        if (deletePersonDescriptor.getAppointment()) {
            updatedAppointment = null;
        }

        return new Person(updatedName, updatedNric, updatedPhone, updatedEmail, updatedAddress, updatedAppointment,
                updatedMedicalHistories, updatedTags);
    }

    /**
     * Stores the boolean value of the fields that is to be deleted from a person in
     * the address book.
     */
    public static class DeletePersonDescriptor {
        private boolean phone;
        private boolean medicalHistory;
        private boolean appointment;
        private boolean email;
        private boolean address;
        private boolean tags;

        public DeletePersonDescriptor() {
        }

        public void setPhone() {
            this.phone = true;
        }

        public boolean getPhone() {
            return this.phone;
        }

        public void setEmail() {
            this.email = true;
        }

        public boolean getEmail() {
            return this.email;
        }

        public void setAddress() {
            this.address = true;
        }

        public boolean getAddress() {
            return this.address;
        }

        public void setMedicalHistory() {
            this.medicalHistory = true;
        }

        public boolean getMedicalHistory() {
            return this.medicalHistory;
        }

        public void setAppointment() {
            this.appointment = true;
        }

        public boolean getAppointment() {
            return this.appointment;
        }

        public void setTags() {
            this.tags = true;
        }

        public boolean getTags() {
            return this.tags;
        }

        /**
         * Returns true if all fields are false.
         */
        public boolean isAllFalse() {
            return !(phone || email || address || tags || medicalHistory || appointment);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof DeletePersonDescriptor)) {
                return false;
            }

            DeletePersonDescriptor otherDeletePersonDescriptor = (DeletePersonDescriptor) other;
            return Objects.equals(phone, otherDeletePersonDescriptor.phone)
                    && Objects.equals(email, otherDeletePersonDescriptor.email)
                    && Objects.equals(address, otherDeletePersonDescriptor.address)
                    && Objects.equals(tags, otherDeletePersonDescriptor.tags)
                    && Objects.equals(medicalHistory, otherDeletePersonDescriptor.medicalHistory)
                    && Objects.equals(appointment, otherDeletePersonDescriptor.appointment);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("tags", tags)
                    .add("medicalHistory", medicalHistory)
                    .add("appointment", appointment)
                    .toString();
        }
    }
}
