package seedu.codesphere.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.codesphere.logic.stagemanager.StageManager;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.course.Course;

/**
 * Clears the student list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears and removes all students in the student list\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Student list has been cleared.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        StageManager stageManager = StageManager.getInstance();
        Course course = stageManager.getSelectedCourse();

        course.clearStudentList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
