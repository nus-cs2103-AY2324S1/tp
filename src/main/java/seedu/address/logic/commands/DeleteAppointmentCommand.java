package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "delete-appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by specified index in the Appointments list.\n"
            + "Parameters: valid integer as shown in Appointments list \n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted Appointment: %1$s";
    private static final Logger logger = LogsCenter.getLogger(DeleteAppointmentCommand.class);

    private final int targetIndex;

    public DeleteAppointmentCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = new ArrayList<>();
        lastShownList.addAll(model.getFilteredAppointmentList());
        try {
            Appointment target = lastShownList.get(targetIndex - 1);
            model.deleteAppointment(target);
            List<Appointment> updatedList = new ArrayList<>();
            updatedList.addAll(model.getFilteredAppointmentList());
            assert updatedList.size() < lastShownList.size();
            logger.info("Successfully deleted appointment");
            return new CommandResult(String.format(MESSAGE_DELETE_APPOINTMENT_SUCCESS, Messages.format(target)));
        } catch (IndexOutOfBoundsException e) {
            logger.warning("Appointment does not exist");
            throw new CommandException(Messages.MESSAGE_APPOINTMENT_NOT_FOUND);
        }
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
        return targetIndex == otherDeleteAppointmentCommand.targetIndex;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIc", targetIndex)
                .toString();
    }
}

