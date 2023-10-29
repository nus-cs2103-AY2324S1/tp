package seedu.address.logic.parser;

import static seedu.address.logic.parser.TypeParsingUtil.parseField;

import seedu.address.logic.commands.AddLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lessons.Day;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Time;
import seedu.address.model.person.Name;
import seedu.address.model.person.Subject;



/**
 * Parses input arguments and creates a new AddLessonCommand object
 */
public class AddLessonCommandParser implements Parser<AddLessonCommand> {

    @Override
    public AddLessonCommand parse(String args) throws ParseException {
        return new AddLessonCommand(parseLesson(args));
    }

    /**
     * Parses the given {@code String} of arguments in to a lesson object
     * @param args the user input string
     * @return a lesson object
     * @throws ParseException if the user input is of wrong format or the lesson clashes with existing lessons
     */
    public static Lesson parseLesson(String args) throws ParseException {
        Lesson lesson = Lesson.getDefaultLesson();
        lesson.setNameIfNotDefault(parseField("name", args, Name::of));
        lesson.setSubjectIfNotDefault(parseField("subject", args, Subject::of));
        lesson.setDayIfNotDefault(parseField("day", args, Day::of));
        lesson.setStartIfNotDefault(parseField("start", args, Time::of));
        lesson.setEndIfNotDefault(parseField("end", args, Time::of));
        return lesson;
        //lesson.setTaskListIfNotDefault(parseField("task", args, TaskList::of));
    }
}
