//@@author tiongjjyi
package seedu.codesphere.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.codesphere.model.Model.PREDICATE_SHOW_ALL_COURSES;

import seedu.codesphere.model.Model;

/**
 * Resets the current listed courses to the original state.
 */
public class ResetCourseCommand extends Command {

    public static final String COMMAND_WORD = "reset";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Resets the course list to its original state\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Reset course list to original state.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCourseList(PREDICATE_SHOW_ALL_COURSES);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof ResetCourseCommand;
    }
}
