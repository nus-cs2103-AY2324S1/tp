package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.state.State;

/**
 * Adds a lesson to the schedule.
 */
public class AddLessonCommand extends Command {
    public static final String COMMAND_WORD = "addlesson";
    private Lesson lesson;
    /**
     * Creates an AddLessonCommand to add the specified {@code Lesson}
     */
    public AddLessonCommand(Lesson lesson) {
        requireNonNull(lesson);
        this.lesson = lesson;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasLessonClashWith(lesson)) {
            Lesson clashingLesson = model.getLessonClashWith(lesson);
            if (clashingLesson.getName().equals(lesson.getName())) {
                throw new CommandException("Lesson with this name already exists in the schedule");
            }
            throw new CommandException("Exist lesson clashes with this lesson: "
                    + clashingLesson.toString());
        }
        model.addLesson(lesson);
        model.setState(State.SCHEDULE);
        model.showLesson(lesson);
        return new CommandResult(String.format("New lesson added: " + lesson.toString()));
    }
    public Lesson getLesson() {
        return lesson;
    }
}
