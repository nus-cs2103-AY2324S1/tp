package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lessons.Lesson;

public class EditLessonCommand extends Command {
    private final Lesson edit;
    private final int index;
    private final boolean parseFromShown;
    public EditLessonCommand(Lesson edit, int index, boolean parseFromShown) {
        this.edit = edit;
        this.index = index;
        this.parseFromShown = parseFromShown;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        Lesson original;
        if (parseFromShown) {
            original = model.getCurrentlyDisplayedLesson();
        } else {
            original = model.getFilteredScheduleList().get(index - 1);
        }
        //model.setLesson(original, edit);
        return new CommandResult("Edited lesson: " + edit);
    }
}
