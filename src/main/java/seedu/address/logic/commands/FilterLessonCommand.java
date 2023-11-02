package seedu.address.logic.commands;

import seedu.address.logic.MacroPredicate;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lessons.Lesson;
/**
 * Represents a command that filters the list of lessons.
 */
public class FilterLessonCommand extends FilterCommand {
    private MacroPredicate<Lesson> predicate;
    /**
     * Creates a FilterLessonCommand with the specified predicate.
     */
    public FilterLessonCommand(MacroPredicate<Lesson> predicate) {
        this.predicate = predicate;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredScheduleList(predicate);
        return new CommandResult("Filtered schedule list successfully!");
    }
}
