package seedu.address.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.Person;

public class AddAppointment extends Command {

    private final String KEYWORD = "schedule";



    public final String MESSAGE_USAGE = KEYWORD + ": Schedules an appointment.\n"
            + "Parameters: "
            + PREFIX_APPOINTMENT_START + "START "
            + PREFIX_APPOINTMENT_END + "END "
            + PREFIX_APPOINTMENT_PATIENT + "PATIENT "
            + PREFIX_APPOINTMENT_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + KEYWORD + " "
            + PREFIX_APPOINTMENT_START + "2023-10-13 12:00 "
            + PREFIX_APPOINTMENT_END + "2020-10-13 13:00 "
            + PREFIX_APPOINTMENT_PATIENT + "1 "
            + PREFIX_APPOINTMENT_DESCRIPTION + "Follow up on Chest X-Ray "
            + PREFIX_TAG + "Medium Priority";

    private final Appointment currAppointment;
    /**
     * Constructor for the AddAppointment command to add the specified {@code Appointment}
     */
    public AddAppointment(Appointment appointment) {

        // Check that appointment is non-null.
        requireNonNull(appointment);
        // Save the appointment to currAppointment during initialisation.
        this.currAppointment = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        String MESSAGE_INDEX_OUT_OF_RANGE = "Given index is out of range. It exceeds the size of our " +
                "database.";
        try {
            requireNonNull(model);
            ReadOnlyAddressBook addressBook = model.getAddressBook();
            ObservableList<Person> personList = model.getFilteredPersonList();
            ObservableList<Appointment> appointmentList = addressBook.getAppointmentList();

            int index = Integer.parseInt(currAppointment.getPatientName());

            ArrayList<Person> patientArrayList = new ArrayList<>(personList);
            int size = patientArrayList.size();

            // Patient list is empty
            String MESSAGE_INVALID_PATIENT = "Please check the patient name again. Such a patient does not exist" +
                    "in our MediFlowR database.";
            if (size == 0) {
                throw new CommandException((MESSAGE_INVALID_PATIENT));
            }

            // Index is out of range
            if (index > size || index < 0) {
                throw new CommandException((MESSAGE_INDEX_OUT_OF_RANGE));
            }

            // Initialises the patient and their name within the appointment class using the Patient ArrayList and the
            // given index
            currAppointment.parsePatient(patientArrayList, index);

            // Patient not found within MediFlowR database
            if (!model.hasPerson(currAppointment.getPerson())) {
                throw new CommandException((MESSAGE_INVALID_PATIENT));
            }

            // Clash in appointment slot
            final String MESSAGE_APPOINTMENT_CLASH = "Please choose another timing for the appointment. There " +
                    "already exists another appointment in this timing that clashes with the requested appointment.";
            if (!AppointmentTime.isValidTimeSlot(appointmentList, currAppointment)) {
                throw new CommandException(MESSAGE_APPOINTMENT_CLASH);
            }

            // Appointment already exists
            final String MESSAGE_APPOINTMENT_ALREADY_EXISTS = "This appointment has already been" +
                    "created and we have taken note!";
            if (model.hasAppointment(currAppointment)) {
                throw new CommandException(MESSAGE_APPOINTMENT_ALREADY_EXISTS);
            }

            model.addAppointment(currAppointment);
            model.getFilteredAppointmentList();
            String APPOINTMENT_CONFIRMATION = "New appointment scheduled: %1$s.";
            return new CommandResult(String.format(APPOINTMENT_CONFIRMATION, currAppointment),
                    false, false, true);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_INDEX_OUT_OF_RANGE);
        }
    }


    @Override
    public boolean equals(Object other) {

        // Check if the given object is the same object.
        if (this == other) {
            return true;
        }

        // if Object other is of type AddAppointment, cast it to type AddAppointment and compare the containing
        // currAppointment
        if (other instanceof AddAppointment) {
            AddAppointment otherAppointment = (AddAppointment) other;

            return currAppointment.equals(otherAppointment.currAppointment);
        }
        return false;
    }
}
