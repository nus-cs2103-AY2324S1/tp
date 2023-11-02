package seedu.address.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.appointmentfilters.FindPatientFilter;


/**
 * The FindAppointmentCommand is used to find patient appointments by their names.
 * This command allows listing all appointments belonging to patients whose names
 * match the provided keywords. The keywords are case-insensitive and may contain
 * only part of the patient's name to retrieve their appointments. Multiple names
 * are allowed.
 **/
public class FindAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "find-appointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all appointments belonging to the patients"
            + " whose names are provided.\nThe keywords are case-insensitive and may just contain part of the patient's"
            + "name to retrieve their appointments. Multiple names are allowed. \n"
            + "Parameters: PATIENT\n"
            + "Example: " + COMMAND_WORD + " alex david";

    private final FindPatientFilter predicate;

    public FindAppointmentCommand(FindPatientFilter predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredAppointmentList(this.predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW,
                        model.getFilteredAppointmentList().size()));
    }
}
