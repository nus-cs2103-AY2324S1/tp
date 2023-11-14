package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Task;
import seedu.address.model.person.Person;

/**
 * Shows the details of an existing person in the address book.
 */
public class ShowCommand extends Command {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the details of the item identified "
            + "by the index number used in the last item listing.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_SHOW_PERSON_SUCCESS = "Showing Person: %1$s";
    public static final String MESSAGE_SHOW_LESSON_SUCCESS = "Showing Lesson: %1$s";
    public static final String MESSAGE_SHOW_TASK_SUCCESS = "Showing Task: %1$s";

    private final int targetIndex;

    public ShowCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        List<Lesson> lastShownSchedule = model.getFilteredScheduleList();
        List<Task> fullTaskList = model.getFullTaskList();

        // Handle different cases of show command based on app state
        switch (model.getState()) {
        case STUDENT:
            if (targetIndex - 1 >= lastShownList.size() || targetIndex < 1) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            // Show student details
            Person personToShow = lastShownList.get(targetIndex - 1);
            model.showPerson(personToShow);
            return new CommandResult(String.format(MESSAGE_SHOW_PERSON_SUCCESS, Messages.format(personToShow)));
        case SCHEDULE:
            if (targetIndex - 1 >= lastShownSchedule.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
            }
            // Show lesson details
            Lesson lessonToShow = lastShownSchedule.get(targetIndex - 1);
            model.showLesson(lessonToShow);
            return new CommandResult(String.format(MESSAGE_SHOW_LESSON_SUCCESS, Messages.formatLesson(lessonToShow)));
        case TASK:
            if (targetIndex - 1 >= fullTaskList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }
            // Show lesson details
            Task taskToShow = fullTaskList.get(targetIndex - 1);
            model.showTask(taskToShow);
            return new CommandResult(String.format(MESSAGE_SHOW_TASK_SUCCESS, Messages.formatTask(taskToShow)));

        default:
            throw new CommandException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ShowCommand)) {
            return false;
        }

        ShowCommand otherShowCommand = (ShowCommand) other;
        return targetIndex == otherShowCommand.targetIndex;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}


