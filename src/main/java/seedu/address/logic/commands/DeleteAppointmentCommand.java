package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.UniqueAppointmentList;
import seedu.address.model.person.Person;
import seedu.address.model.shared.Nric;

/**
 * Deletes an appointment identified using it's displayed index from the address
 * book.
 */
public class DeleteAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "deleteappointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the appointment identified by the index number used in the displayed appointment list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted Appointment: %1$s";

    public static final String MESSAGE_INVALID_NRIC = "The Patient with the corresponding NRIC no longer exists";

    private final Index targetIndex;

    public DeleteAppointmentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> appointmentList = model.getAppointmentList();
        List<Person> personList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= appointmentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToDelete = appointmentList.get(targetIndex.getZeroBased());
        Nric patientNric = appointmentToDelete.getNric();
        Person personWithAppointment = personList.stream()
                .filter(person -> person.getNric().equals(patientNric))
                .findFirst()
                .orElse(null);
        if (personWithAppointment == null) {
            throw new CommandException(MESSAGE_INVALID_NRIC);
        }
        UniqueAppointmentList newList = new UniqueAppointmentList();
        newList.setAppointments(personWithAppointment.getAppointments());
        newList.remove(appointmentToDelete);

        Person newPatient = new Person(personWithAppointment.getName(), personWithAppointment.getNric(),
                personWithAppointment.getEmail(),
                personWithAppointment.getPhone(), personWithAppointment.getGender(), personWithAppointment.getAge(),
                personWithAppointment.getBloodType(),
                personWithAppointment.getAllergies(), personWithAppointment.getRecords(), newList,
                personWithAppointment.isPinned());

        model.setPerson(personWithAppointment, newPatient);
        model.resetAppointmentList();
        return new CommandResult(String.format(MESSAGE_DELETE_APPOINTMENT_SUCCESS,
                Messages.format(appointmentToDelete, personWithAppointment)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteAppointmentCommand)) {
            return false;
        }

        DeleteAppointmentCommand otherDeleteAppointmentCommand = (DeleteAppointmentCommand) other;
        return targetIndex.equals(otherDeleteAppointmentCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
