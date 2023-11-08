package seedu.address.logic.commands;

import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.state.State;

/**
 * Links a student to a lesson
 */
public class LinkCommand extends Command {
    /**
     * The COMMAND_WORD where a -lesson and -student is required
     */
    public static final String COMMAND_WORD = "link";
    /**
     * The COMMAND_WORD where only the name of the linked lesson or student is required
     */
    public static final String STATEFUL_COMMAND_WORD = "linkto";
    private Name lessonName;
    private Name studentName;

    /**
     * Creates a LinkCommand to link the specified {@code Person} to the specified {@code Lesson}
     */
    public LinkCommand(Name lessonName, Name studentName) {
        this.lessonName = lessonName;
        this.studentName = studentName;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        Set<Person> personSet = model.getPersonsFulfill(person -> person.getName().equals(studentName));
        Set<Lesson> lessonSet = model.getLessonsFulfill(lesson -> lesson.getName().equals(lessonName));
        if (personSet.isEmpty()) {
            throw new CommandException("No such student with name " + studentName.toString() + " found");
        } else if (lessonSet.isEmpty()) {
            throw new CommandException("No such lesson");
        } else {
            Person person = personSet.iterator().next();
            Lesson lesson = lessonSet.iterator().next();
            if (Set.of(model.getLinkedWith(person)).contains(lessonName)) {
                throw new CommandException("The student is already linked to this lesson");
            }
            model.linkWith(person, lesson);
            // parser make sure the state is either STUDENT or SCHEDULE
            State state = model.getState();
            if (state == State.STUDENT && person.equals(model.getCurrentlyDisplayedPerson())) {
                model.showPerson(person);
            } else if (state == State.SCHEDULE && lesson.equals(model.getCurrentlyDisplayedLesson())) {
                model.showLesson(lesson);
            }
            return new CommandResult("Linked " + person.getName() + " to " + lesson.getName());
        }
    }
}
