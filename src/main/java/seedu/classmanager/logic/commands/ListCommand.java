package seedu.classmanager.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.classmanager.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import seedu.classmanager.logic.CommandHistory;
import seedu.classmanager.model.Model;

/**
 * Lists all students in Class Manager to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all students";

    /**
     * Lists all students in the Student List.
     * @param model {@code Model} which the command should operate on.
     * @param commandHistory The command history to record this command.
     * @return A {@code CommandResult} with the feedback message of the operation result.
     */
    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
