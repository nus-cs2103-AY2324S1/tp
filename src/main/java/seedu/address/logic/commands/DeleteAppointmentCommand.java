package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.doctor.Doctor;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.timeslots.Timeslot;

/**
 * Deletes the appointment of an existing person in the address book.
 */
public class DeleteAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "delete_appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the appointment identified "
            + "by the index number used in the displayed appointments list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted Appointment %1$s of %2$s";
    private static Logger logger = Logger.getLogger("delete_appt");
    private final Index appointmentIndex;

    /**
     * @param appointmentIndex Index of the appointment
     */
    public DeleteAppointmentCommand(Index appointmentIndex) {
        requireAllNonNull(appointmentIndex);

        this.appointmentIndex = appointmentIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownAppointmentList = model.getFilteredAppointmentList();
        List<Doctor> doctorList = model.getFilteredDoctorList();
        int zeroBasedAppointmentIndex = appointmentIndex.getZeroBased();

        if (zeroBasedAppointmentIndex >= lastShownAppointmentList.size()) {
            logger.log(Level.WARNING, "wrong index input");
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        // Delete appointment from model and patient
        Appointment appointmentToDelete = lastShownAppointmentList.get(zeroBasedAppointmentIndex);
        Person patient = appointmentToDelete.getPerson();
        deletePatientAppointment(patient, appointmentToDelete);
        model.deleteAppointment(appointmentToDelete);

        //Delete appointment from doctor
        Doctor targetDoctor = getDoctor(doctorList, new Name(appointmentToDelete.getName()));
        deleteDoctorAppointment(targetDoctor, appointmentToDelete);

        //adding available timeslot back to list
        updateModelTimeslotList(model, appointmentToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_APPOINTMENT_SUCCESS,
                Messages.formatAppointment(appointmentToDelete), Messages.format(patient)));
    }

    private void updateModelTimeslotList(Model model, Appointment appointmentToDelete) {
        if (model.getAvailableTimeSlotList().size() > 0) {
            LocalDate apptDate = appointmentToDelete.getDateTime().toLocalDate();
            LocalDate currDate = model.getAvailableTimeSlotList().get(0).getDate();
            if (apptDate.equals(currDate)) {
                Timeslot timeslotToAdd = new Timeslot(apptDate, appointmentToDelete.getDateTime().getHour());
                model.addAvailableTimeSlot(timeslotToAdd);
            }
        }
    }

    /**
     * Deletes the specified appointment inside patient.
     */
    public void deletePatientAppointment(Person patient, Appointment appointment) {
        int appointmentIndexInPatient = patient.getAppointments().indexOf(appointment);
        assert appointmentIndexInPatient > -1 : "index of patient should be positive integer";
        patient.deleteAppointment(appointmentIndexInPatient);

    }

    /**
     * Deletes appointments that is inside the Doctor object.
     */
    public void deleteDoctorAppointment(Doctor doctor, Appointment appointment) {
        int appointmentIndexInDoctor = doctor.getAppointments().indexOf(appointment);
        assert appointmentIndexInDoctor > -1 : "index of doctor should be positive integer";
        doctor.deleteAppointment(appointmentIndexInDoctor);
    }

    public Doctor getDoctor(List<Doctor> doctorList, Name doctorName) {
        Doctor targetDoctor = null;
        for (Doctor doctor : doctorList) {
            if (doctor.getName().equals(doctorName)) {
                targetDoctor = doctor;
                break;
            }
        }
        if (targetDoctor == null) {
            throw new RuntimeException();
        }
        return targetDoctor;
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

        DeleteAppointmentCommand otherDeleteApptCommand = (DeleteAppointmentCommand) other;
        return appointmentIndex.equals(otherDeleteApptCommand.appointmentIndex);
    }
}

