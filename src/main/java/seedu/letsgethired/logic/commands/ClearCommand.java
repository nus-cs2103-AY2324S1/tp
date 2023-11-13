package seedu.letsgethired.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.letsgethired.model.Model;

/**
 * Clears the intern tracker.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Intern tracker has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearInternshipApplications();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
