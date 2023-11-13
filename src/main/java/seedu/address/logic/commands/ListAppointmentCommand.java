package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPTS;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "list_appt";

    public static final String MESSAGE_SUCCESS = "Listed all appointments";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
