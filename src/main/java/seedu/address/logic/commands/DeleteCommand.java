package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Email;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index targetIndex;

    private final DeletePersonDescriptor deletePersonDescriptor;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.deletePersonDescriptor = null;
    }

    public DeleteCommand(DeletePersonDescriptor deletePersonDescriptor) {
        this.targetIndex = null;
        this.deletePersonDescriptor = deletePersonDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
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
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }

    /**
     * Stores the fields that is to be deleted from a person in the address book.
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

        public void setPhone(Phone phone) {
            this.phone = true;
        }

        public void setEmail(Email email) {
            this.email = true;
        }

        public void setAddress(Address address) {
            this.address = true;
        }

        public void setMedicalHistory(MedicalHistory medicalHistory) {
            this.medicalHistory = true;
        }

        public void setAppointment(Appointment appointment) {
            this.appointment = true;
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = true;
        }

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
