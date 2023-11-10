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
 * Adds an Appointment to the address book.
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
     * Creates an AddPatientCommand to add the specified {@code Patient}
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

    private void checkValidAppointment(Patient chosenPatient, Doctor chosenDoctor, Appointment toAdd)
            throws CommandException {
        if (chosenPatient.hasAppointmentAt(toAdd.getAppointmentTime())) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT_PATIENT);
        }
        if (chosenDoctor.hasAppointmentAt(toAdd.getAppointmentTime())) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT_DOCTOR);
        }
    }

    private void checkPatientAndDoctor(Patient chosenPatient, Doctor chosenDoctor) throws CommandException {
        if (chosenPatient == null) {
            throw new CommandException(MESSAGE_INVALID_PATIENT);
        }
        if (chosenDoctor == null) {
            throw new CommandException(MESSAGE_INVALID_DOCTOR);
        }
    }

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
