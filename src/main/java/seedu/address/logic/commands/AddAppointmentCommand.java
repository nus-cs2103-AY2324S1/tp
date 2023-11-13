package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_IC;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Patient;

/**
 * Adds an Appointment to the MediLink Contacts address book.
 * Extends the abstract class {@link Command}.
 */
public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "new-appt";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Adds an appointment to the address book for both doctors and patients. "
                    + "Parameters: "
                    + PREFIX_PATIENT_IC + "PATIENT IC "
                    + PREFIX_DOCTOR_IC + "DOCTOR IC "
                    + PREFIX_APPOINTMENT_TIME + "APPOINTMENT TIME";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_INVALID_PATIENT =
            "This patient in the appointment does not exist";
    public static final String MESSAGE_INVALID_DOCTOR =
            "This doctor in the appointment does not exist";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT_PATIENT =
            "This patient already has an appointment at this timing";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT_DOCTOR =
            "This doctor already has an appointment at this timing";
    public static final String MESSAGE_SAME_DOCTOR_AND_PATIENT =
            "You entered the same IC for both doctors and patients!";


    private final Appointment toAdd;

    /**
     * Creates an AddAppointmentCommand to add the specified {@code Appointment}
     */
    public AddAppointmentCommand(Appointment appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (toAdd.getDoctor().equals(toAdd.getPatient())) {
            throw new CommandException(MESSAGE_SAME_DOCTOR_AND_PATIENT);
        }

        Patient chosenPatient = findPatient(model);
        Doctor chosenDoctor = findDoctor(model);
        checkPatientAndDoctor(chosenPatient, chosenDoctor);
        checkValidAppointment(chosenPatient, chosenDoctor, toAdd);
        chosenPatient.addAppointment(toAdd);
        chosenDoctor.addAppointment(toAdd);
        model.addAppointment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    /**
     * Checks if the appointment is valid by ensuring that the patient and doctor do not have
     * conflicting appointments at the same time.
     *
     * @param chosenPatient The chosen patient for the appointment.
     * @param chosenDoctor The chosen doctor for the appointment.
     * @param toAdd The appointment to be added.
     * @throws CommandException If there is a conflict in appointments.
     */
    private void checkValidAppointment(Patient chosenPatient, Doctor chosenDoctor, Appointment toAdd)
            throws CommandException {
        if (chosenPatient.hasAppointmentAt(toAdd.getAppointmentTime())) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT_PATIENT);
        }
        if (chosenDoctor.hasAppointmentAt(toAdd.getAppointmentTime())) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT_DOCTOR);
        }
    }

    /**
     * Checks if the chosen patient and doctor are valid, i.e., they exist in the model.
     *
     * @param chosenPatient The chosen patient for the appointment.
     * @param chosenDoctor The chosen doctor for the appointment.
     * @throws CommandException If the chosen patient or doctor is invalid.
     */
    private void checkPatientAndDoctor(Patient chosenPatient, Doctor chosenDoctor) throws CommandException {
        if (chosenPatient == null) {
            throw new CommandException(MESSAGE_INVALID_PATIENT);
        }
        if (chosenDoctor == null) {
            throw new CommandException(MESSAGE_INVALID_DOCTOR);
        }
    }

    /**
     * Finds a patient in the model based on the patient's IC.
     *
     * @param model The model containing the data.
     * @return The found patient or null if not found.
     */
    private Patient findPatient(Model model) {
        Ic patientIc = toAdd.getPatient();
        List<Patient> patients = model.getFilteredPatientList();
        for (Patient p : patients) {
            if (p.hasIc(patientIc)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Finds a doctor in the model based on the doctor's IC.
     *
     * @param model The model containing the data.
     * @return The found doctor or null if not found.
     */
    private Doctor findDoctor(Model model) {
        Ic doctorIc = toAdd.getDoctor();
        List<Doctor> doctors = model.getFilteredDoctorList();
        for (Doctor d : doctors) {
            if (d.hasIc(doctorIc)) {
                return d;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAppointmentCommand)) {
            return false;
        }

        AddAppointmentCommand otherAddCommand = (AddAppointmentCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
