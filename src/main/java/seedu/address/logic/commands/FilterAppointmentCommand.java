package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointments.Appointment;

/**
 * Filters the appointment list by dentistID.
 */
public class FilterAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "filter-appointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters appointments by dentist or patient ID. \n"
            + "Parameters: " + "ID_TYPE[dentist/patient] " + "DENTIST_ID/PATIENT_ID \n"
            + "Examples: \n"
            + "For filtering by dentist ID: " + COMMAND_WORD + " dentist" + " 1 \n"
            + "For filtering by patient ID: " + COMMAND_WORD + " patient" + " 1";

    public static final String INVALID_INPUTS = "Invalid inputs. Please key in correct format: \n"
            + "For filtering by dentist ID: dentist DENTIST_ID \n"
            + "For filtering by patient ID: patient PATIENT_ID";

    private String idType;
    private long id;

    /**
     * Constructs a FilterAppointmentCommand to filter appointments based on the provided dentist or patient ID.
     *
     * @param idType Determines whether the given ID is a patient or dentist ID.
     * @param id The dentist or patient ID.
     */
    public FilterAppointmentCommand(String idType, long id) {
        this.idType = idType;
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Predicate<Appointment> appointmentPredicate;
        String success;
        String failure;
        if (idType.equalsIgnoreCase("patient")) {
            appointmentPredicate = appointment -> appointment.getPatientId() == id;
            success = "Appointments with patient whose patient ID is " + id + " listed.";
            failure = "No appointments with patient whose patient ID is " + id + " found.";
        } else if (idType.equalsIgnoreCase("dentist")) {
            appointmentPredicate = appointment -> appointment.getDentistId() == id;
            success = "Appointments with dentist whose dentist ID is " + id + " listed.";
            failure = "No appointments with dentist whose dentist ID is " + id + " found.";
        } else {
            throw new CommandException(INVALID_INPUTS);
        }

        if (id > 0) {
            model.updateFilteredAppointmentList(appointmentPredicate);

            if (model.getFilteredAppointmentList().isEmpty()) {
                return new CommandResult(failure);
            } else {
                return new CommandResult(success);
            }
        } else {
            throw new CommandException("Invalid ID. ID must be a positive number.");
        }

    }

    /**
     * Checks if this FilterAppointmentCommand is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterAppointmentCommand)) {
            return false;
        }

        FilterAppointmentCommand otherFilterAppointmentCommand = (FilterAppointmentCommand) other;
        return Objects.equals(this.idType, otherFilterAppointmentCommand.idType)
                && this.id == otherFilterAppointmentCommand.id;
    }
}
