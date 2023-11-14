package seedu.codesphere.logic.commands;
import static seedu.codesphere.commons.util.CollectionUtil.requireAllNonNull;

import seedu.codesphere.commons.core.index.Index;
import seedu.codesphere.logic.Messages;
import seedu.codesphere.logic.commands.exceptions.CommandException;
import seedu.codesphere.logic.stagemanager.StageManager;
import seedu.codesphere.logic.stagemanager.Stages;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.course.Course;

//Solution below adapted from https://github.com/AY2223S1-CS2103T-W11-4/tp
/**
 * Moves to viewing of the selected course.
 */
public class SelectCommand extends Command {
    public static final String COMMAND_WORD = "select";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": selects a course to view into "
            + "by the index number used in the displayed course listing.\n"
            + "Parameters: INDEX (must exist in the course list)\n"
            + "Example: " + COMMAND_WORD + " 1 ";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";
    public static final String MESSAGE_SELECT_SUCCESS = "Selected course: %1$s";
    private final Index index;

    /**
     * @param index of the selected course
     */
    public SelectCommand(Index index) {
        requireAllNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        StageManager currStageManager = StageManager.getInstance();
        Course selectedCourse;

        // Get selected course
        try {
            selectedCourse = model.getFilteredCourseList().get(this.index.getZeroBased());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_COURSE_DISPLAYED_INDEX);
        }
        // Update to Course Stage
        try {
            currStageManager.setCourseStage(selectedCourse);
        } catch (NullPointerException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_COURSE_DISPLAYED_INDEX);
        }

        new ResetCourseCommand().execute(model);
        return new CommandResult(String.format(MESSAGE_SELECT_SUCCESS, selectedCourse.getCourseName()),
                Stages.SELECTED_COURSE);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles null
        if (!(other instanceof SelectCommand)) {
            return false;
        }

        SelectCommand e = (SelectCommand) other;
        return index.equals(e.index);
    }
}
