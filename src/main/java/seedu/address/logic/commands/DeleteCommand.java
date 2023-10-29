package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

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
public class DeleteCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String COMMAND_WORD_ALIAS = "d";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete the patient identified by the full name or nric of the patient.\n"
            + "Parameters: n/Name or id/Nric (must be valid)\n"
            + "Example: " + COMMAND_WORD + " n/John Doe or " + COMMAND_WORD + " id/S1234567A";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Patient: %1$s";

    public static final String MESSAGE_DELETE_PERSON_FIELD_SUCCESS = "Deleted Patient's field: %1$s";

    public static final String MESSAGE_PERSON_NOT_FOUND =
            "The given combination of Name and NRIC does not match any patient in the patients list.";

    public static final String MESSAGE_UNDO_DELETE_PERSON_SUCCESS = "Undoing the deletion of Patient:  %1$s";

    public static final String MESSAGE_UNDO_DELETE_FIELD_SUCCESS = "Undoing the deletion of a Patient's field:  %1$s";

    /**
     * The original state of the person.
     */
    private Person originalPerson;

    /**
     * The state of the person after a field has been deleted.
     */
    private Person editedPerson;
    private final Name name;
    private final Nric nric;
    private final DeletePersonDescriptor deletePersonDescriptor;

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

        Optional<Person> personOptional = CommandUtil.findPersonByIdentifier(name, nric, lastShownList);

        if (personOptional.isEmpty()) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }

        Person personToDelete = personOptional.get();
        originalPerson = personToDelete;

        if (deletePersonDescriptor.isAllFalse()) {
            model.deletePerson(personToDelete);
            model.addToHistory(this);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
        } else {
            editedPerson = createDeletePerson(personToDelete, deletePersonDescriptor);
            model.setPerson(personToDelete, editedPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            model.addToHistory(this);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_FIELD_SUCCESS, Messages.format(editedPerson)));
        }
    }

    @Override
    public CommandResult undo(Model model) {
        requireNonNull(model);
        if (deletePersonDescriptor.isAllFalse()) {
            model.addPerson(originalPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_UNDO_DELETE_PERSON_SUCCESS,
                    Messages.format(originalPerson)));
        } else {
            Person personToDelete = editedPerson;
            model.setPerson(personToDelete, originalPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_UNDO_DELETE_FIELD_SUCCESS,
                    Messages.format(editedPerson)));
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
    public static Person createDeletePerson(Person personToEdit, DeletePersonDescriptor deletePersonDescriptor) {
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
