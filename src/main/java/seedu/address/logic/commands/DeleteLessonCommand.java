package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lessons.Lesson;

/**
 * Deletes a lesson identified using it's displayed index from the schedule.
 */
public class DeleteLessonCommand extends Command {
    public static final String COMMAND_WORD = "deleteLesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the lesson identified by the index number used in the displayed lesson list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_LESSON_SUCCESS = "Deleted Lesson: %1$s";

    private final int targetIndex;

    public DeleteLessonCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Lesson> lastShownList = model.getFilteredScheduleList();

        if (targetIndex < 1 || targetIndex > lastShownList.size()) {
            String errMessage = lastShownList.isEmpty()
                    ? "The index provided is invalid as the lesson list is empty."
                    : "Index out of bounds, expected 1 to " + lastShownList.size()
                    + " but got " + targetIndex + ".";
            throw new CommandException(errMessage);
        }

        Lesson lessonToDelete = lastShownList.get(targetIndex - 1);
        model.deleteLesson(lessonToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete.toString()));
    }
}
