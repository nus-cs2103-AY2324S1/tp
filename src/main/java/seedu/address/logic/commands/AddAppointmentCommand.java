package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DENTIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TREATMENT;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.appointments.Appointment;
import seedu.address.model.appointments.AppointmentTime;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.model.person.patients.Patient;
import seedu.address.model.treatment.Treatment;
import seedu.address.ui.CalendarWindow;


/**
 * Adds an appointment to ToothTracker.
 */
public class AddAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "add-appointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to ToothTracker. \n"
            + "Parameters: "
            + PREFIX_DENTIST + "DENTIST "
            + PREFIX_PATIENT + "PATIENT "
            + PREFIX_START + "START_TIME "
            + PREFIX_TREATMENT + "TREATMENT \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DENTIST + "1 "
            + PREFIX_PATIENT + "1 "
            + PREFIX_START + "2023-10-12 16:00 "
            + PREFIX_TREATMENT + "Braces";

    public static final String MESSAGE_SUCCESS = "New Appointment added: %1$s";

    public static final String MESSAGE_CLASHING_DENTIST = "Dentist already has an"
            + " existing appointment in this time slot.";

    public static final String MESSAGE_CLASHING_PATIENT = "Patient already has an"
            + " existing appointment in this time slot.";

    private final Appointment toAdd;
    private final long dentistId;
    private final long patientId;
    private final String treatmentName;
    private final String start;

    private CalendarWindow calendarWindow;


    /**
     * Constructs an AddAppointmentCommand with the specified appointment.
     *
     * @param appointment The appointment to be added.
     */
    public AddAppointmentCommand(Appointment appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
        dentistId = appointment.getDentistId();
        patientId = appointment.getPatientId();
        treatmentName = appointment.getTreatment();
        start = appointment.getStart();
    }

    /**
     * Setter for injecting a mock or stub CalendarWindow for testing purposes.
     * @param calendarWindow The CalendarWindow to be injected.
     */
    public void setCalendarWindow(CalendarWindow calendarWindow) {
        this.calendarWindow = calendarWindow;
    }


    /**
     * Executes the command to add the appointment to the model.
     *
     * @param model The model in which the appointment is to be added.
     * @return The result of the command execution.
     * @throws CommandException If the appointment clashes with an existing one.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        if (toAdd.getDentistId() < 0) {
            throw new CommandException("Invalid input. Please enter a valid integer value for dentist ID.");
        } else if (toAdd.getPatientId() < 0) {
            throw new CommandException("Invalid input. Please enter a valid integer value for patient ID.");
        }

        Predicate<Treatment> treatmentPredicate = treatment -> treatment.getName().toString()
                .equalsIgnoreCase(treatmentName);
        model.updateFilteredTreatmentList(treatmentPredicate);

        if (model.getFilteredTreatmentList().isEmpty()) {
            throw new CommandException("Treatment is not provided in this clinic");
        }
        String duration = model.getFilteredTreatmentList().get(0).getTime().toString();
        LocalDateTime startParsed;
        try {
            startParsed = LocalDateTime.parse(start);
        } catch (DateTimeException e) {
            throw new CommandException("Invalid inputs for appointment start time.\nDate must be valid. \n"
                    + "Format must be in yyyy-MM-dd HH:mm.\nE.g. 2023-01-01 09:05");
        }

        if (startParsed.getYear() < 2000) {
            throw new CommandException("Invalid year. Year must be 2000 or later.");
        }
        AppointmentTime appointmentTime;
        try {
            appointmentTime = ParserUtil.parseAppointmentTime(start, duration);
        } catch (ParseException e) {
            throw new CommandException("Appointment start time in wrong format.", e);
        }

        toAdd.setAppointmentTime(appointmentTime);

        String cost = model.getFilteredTreatmentList().get(0).getCost().toString();
        toAdd.setCost(cost);

        checkValidPersons(model);


        checkClash(model);

        model.addAppointment(toAdd);
        if (calendarWindow == null) {
            calendarWindow = CalendarWindow.getInstance();
        }
        calendarWindow.addAppointment(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    /**
     * Checks the validity of dentist and patient IDs in the given model and
     * updates the corresponding names in the Appointment to be added.
     *
     * @param model The model containing dentist and patient information.
     * @throws CommandException If an invalid dentist or patient ID is encountered.
     */
    public void checkValidPersons(Model model) throws CommandException {
        if (dentistId >= 0) {

            Predicate<Dentist> dentistPredicate = dentist -> true;
            model.updateFilteredDentistList(dentistPredicate);
            Dentist dentist = model.getDentistById(dentistId);

            if (dentist == null) {
                throw new CommandException("No dentist with ID " + dentistId + ".");
            }
            toAdd.setDentistName(dentist.getName().fullName);
        } else {
            throw new CommandException("Dentist ID must be a valid input: ID must be positive.");
        }

        if (patientId >= 0) {

            Predicate<Patient> patientPredicate = patient -> true;
            model.updateFilteredPatientList(patientPredicate);
            Patient patient = model.getPatientById(patientId);
            if (patient == null) {
                throw new CommandException("No patient with ID " + patientId);
            }
            toAdd.setPatientName(patient.getName().fullName);
        } else {
            throw new CommandException("Patient ID must be valid: ID must be positive.");
        }
    }

    /**
     * Checks for appointment clashes in the given model and throws a CommandException if a clash is detected.
     * The method uses the provided 'toAdd' appointment and compares its time with existing appointments in the model.
     *
     * @param model The model containing the list of appointments to check for clashes.
     * @throws CommandException If a clash with another appointment is detected:
     *                         - If the dentist ID clashes with another appointment's dentist ID,
     *                              throws MESSAGE_CLASHING_DOCTORS.
     *                         - If the patient ID clashes with another appointment's patient ID,
     *                              throws MESSAGE_CLASHING_PATIENTS.
     */
    public void checkClash(Model model) throws CommandException {
        if (model.hasAppointment(toAdd)) {
            Predicate<Appointment> appointmentPredicate = toAdd::isSameAppointment;
            model.updateFilteredAppointmentList(appointmentPredicate);

            if (!model.getFilteredAppointmentList().isEmpty()) {
                for (int i = 0; i < model.getFilteredAppointmentList().size(); i++) {
                    if (model.getFilteredAppointmentList().get(i).getDentistId() == dentistId) {
                        throw new CommandException(MESSAGE_CLASHING_DENTIST);
                    }
                }
            }

            if (!model.getFilteredAppointmentList().isEmpty()) {
                for (int i = 0; i < model.getFilteredAppointmentList().size(); i++) {
                    if (model.getFilteredAppointmentList().get(i).getPatientId() == patientId) {
                        throw new CommandException(MESSAGE_CLASHING_PATIENT);
                    }
                }
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof AddAppointmentCommand)) {
            return false;
        }

        AddAppointmentCommand otherCommand = (AddAppointmentCommand) other;

        return dentistId == otherCommand.dentistId
                && patientId == otherCommand.patientId
                && treatmentName.equals(otherCommand.treatmentName)
                && start.equals(otherCommand.start);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
