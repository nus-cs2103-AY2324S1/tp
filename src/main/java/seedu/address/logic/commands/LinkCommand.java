package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

import java.util.Set;

public class LinkCommand extends Command {
    public static final String COMMAND_WORD = "link";
    public static final String STATEFUL_COMMAND_WORD = "linkTo";
    private Name LessonName;
    private Name StudentName;
    public LinkCommand(Name LessonName, Name StudentName) {
        this.LessonName = LessonName;
        this.StudentName = StudentName;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        Set<Person> personSet = model.getPersonsFulfill(person -> person.getName().equals(StudentName));
        Set<Lesson> lessonSet = model.getLessonsFulfill(lesson -> lesson.getName().equals(LessonName));
        if (personSet.isEmpty()) {
            throw new CommandException("No such student with name " + StudentName.toString() + " found");
        } else if (personSet.size() > 1) {
            throw new CommandException("Multiple students with the same name");
        } else if (lessonSet.isEmpty()) {
            throw new CommandException("No such lesson");
        } else if (lessonSet.size() > 1) {
            throw new CommandException("Multiple lessons with the same name");
        } else {
            Person person = personSet.iterator().next();
            Lesson lesson = lessonSet.iterator().next();
            model.link(person, lesson);
            return new CommandResult("Linked " + person.getName() + " to " + lesson.getName());
        }
    }
}
