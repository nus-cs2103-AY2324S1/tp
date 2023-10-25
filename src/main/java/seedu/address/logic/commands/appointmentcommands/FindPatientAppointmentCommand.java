package seedu.address.logic.commands.appointmentcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.appointmentfilters.FindPatientFilter;

import seedu.address.logic.Messages;

import static java.util.Objects.requireNonNull;


public class FindPatientAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "a-find patient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all appointments belonging to the patients" +
            " whose names are provided. The keywords are case-insensitive and may just contain part of the patient's" +
            "name to retrieve their appointments. Multiple names are allowed. \n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " JOHN david leo";

    private final FindPatientFilter PREDICATE;

    public FindPatientAppointmentCommand(FindPatientFilter predicate) {
        this.PREDICATE = predicate;
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
        model.updateFilteredAppointmentList(this.PREDICATE);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW,
                        model.getFilteredAppointmentList().size()));
    }
}
