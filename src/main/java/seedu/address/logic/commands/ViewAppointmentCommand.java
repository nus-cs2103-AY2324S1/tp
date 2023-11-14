package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Opens the Appointments window.
 */
public class ViewAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "viewappointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Opens Appointments window.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_APPOINTMENTS_MESSAGE = "Opened appointments window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_APPOINTMENTS_MESSAGE, false, true, false);
    }
}
