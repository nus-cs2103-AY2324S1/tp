package seedu.address.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.Person;

public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Schedules an appointment.\n"
            + "Parameters: "
            + PREFIX_APPOINTMENT_PATIENT + "PATIENT "
            + PREFIX_APPOINTMENT_START + "START "
            + PREFIX_APPOINTMENT_END + "END "
            + PREFIX_APPOINTMENT_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_APPOINTMENT_START + "20/10/2023 12:00 "
            + PREFIX_APPOINTMENT_END + "20/10/2023 13:00 "
            + PREFIX_APPOINTMENT_PATIENT + "1 "
            + PREFIX_APPOINTMENT_DESCRIPTION + "Follow up on Chest X-Ray ";

    private final Appointment currAppointment;

    private int patientIndex;
    /**
     * Constructor for the AddAppointmentCommand command to add the specified {@code Appointment}
     */
    public AddAppointmentCommand(Appointment appointment, int patientIndex) {

        // Check that appointment is non-null.
        requireNonNull(appointment);
        // Save the appointment to currAppointment during initialisation.
        this.currAppointment = appointment;
        this.patientIndex = patientIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        List<Person> latestPersonList = model.getFilteredPersonList();
        ObservableList<Appointment> appointmentList = addressBook.getAppointmentList();


        if ((patientIndex) >= latestPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAdd = latestPersonList.get(patientIndex - 1);

        // Add the Person patient to the current appointment
        currAppointment.setPatient(personToAdd);
        model.addAppointment(currAppointment);

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
    }


    @Override
    public boolean equals(Object other) {

        // Check if the given object is the same object.
        if (this == other) {
            return true;
        }

        // if Object other is of type AddAppointmentCommand, cast it to type AddAppointmentCommand and compare the containing
        // currAppointment
        if (other instanceof AddAppointmentCommand) {
            AddAppointmentCommand otherAppointment = (AddAppointmentCommand) other;

            return currAppointment.equals(otherAppointment.currAppointment);
        }
        return false;
    }
}
