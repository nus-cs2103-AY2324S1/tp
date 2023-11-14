package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.ManageHr;
import seedu.address.model.Model;

/**
 * Clears all employees from the ManageHR app.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "ManageHR app has been cleared!";
    public static final String MESSAGE_EXAMPLE = COMMAND_WORD;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears ManageHR Storage.\n"
            + "\n"
            + "Example: \n" + MESSAGE_EXAMPLE;


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setManageHr(new ManageHr());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
