package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.BiDirectionalMap;
import seedu.address.model.Model;
import seedu.address.model.ScheduleList;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.person.Person;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Student, schedule and task list has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        model.setScheduleList(new ScheduleList());
        model.setPersonToLessonMap(new BiDirectionalMap<Person, Lesson>());
        model.showPerson(null);
        model.showLesson(null);
        model.showTask(null);
        model.updateFullTaskList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
