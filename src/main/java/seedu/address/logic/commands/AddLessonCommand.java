package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.person.Person;

import static java.util.Objects.requireNonNull;

public class AddLessonCommand extends Command {
    public static final String COMMAND_WORD = "addLesson";
    private Lesson lesson;

    public AddLessonCommand(Lesson lesson) {
        requireNonNull(lesson);
        this.lesson = lesson;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasLesson(lesson)) {
            throw new CommandException("Lesson already exists in the specified time slot");
        }

        model.addLesson(lesson);
        return new CommandResult(String.format("New lesson added: %1$s", lesson));
    }

}
