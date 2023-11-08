package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.state.State;

/**
 * Navigates to the linked lessons or students of the currently displayed lesson or student
 */
public class NavigateCommand extends Command {
    public static final String COMMAND_WORD = "navigate";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        State s = model.getState();
        switch (s) {
        case STUDENT:
            Person person = model.getCurrentlyDisplayedPerson();
            if (person == null) {
                throw new CommandException("No student is currently displayed");
            }
            Name[] names = model.getLinkedWith(person);
            if (names.length == 0) {
                throw new CommandException("This student has no linked lessons");
            }
            model.updateFilteredScheduleList(lesson -> {
                for (Name name : names) {
                    if (lesson.getName().equals(name)) {
                        return true;
                    }
                }
                return false;
            });
            model.showLesson(model.getFilteredScheduleList().get(0));
            model.setState(State.SCHEDULE);
            return new CommandResult("Navigated to student's lessons", State.SCHEDULE);

        case SCHEDULE:
            Lesson lesson = model.getCurrentlyDisplayedLesson();
            if (lesson == null) {
                throw new CommandException("No lesson is currently displayed");
            }
            Name[] names2 = model.getLinkedWith(lesson);
            if (names2.length == 0) {
                throw new CommandException("This lesson has no linked students");
            }
            model.updateFilteredPersonList(person1 -> {
                for (Name name : names2) {
                    if (person1.getName().equals(name)) {
                        return true;
                    }
                }
                return false;
            });
            model.showPerson(model.getFilteredPersonList().get(0));
            model.setState(State.STUDENT);
            return new CommandResult("Navigated to lesson's students", State.STUDENT);

        default:
            throw new CommandException("Navigation from task list is not supported");
        }
    }
}
