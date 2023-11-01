package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.BiDirectionalMap;
import seedu.address.model.Model;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.state.State;

import java.util.Set;

public class NavigateCommand extends Command {
    public static final String COMMAND_WORD = "navigate";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        State s = model.getState();
        // fix SWE principle violation later
        BiDirectionalMap<Person,Lesson> map = model.getPersonLessonMap();
        switch (s) {
            case STUDENT:
                Person person = model.getCurrentlyDisplayedPerson();
                assert person != null;
                Name[] names = map.get(person);
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
                assert lesson != null;
                Name[] names2 = map.getReversed(lesson);
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
                throw new CommandException("Unknown state");
        }
    }
}
