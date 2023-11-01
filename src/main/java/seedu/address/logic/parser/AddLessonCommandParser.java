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
        try {
            Lesson lesson = Lesson.getDefaultLesson();
            lesson.setNameIfNotDefault(parseField("name", args, Name::of, false));
            lesson.setSubjectIfNotDefault(parseField("subject", args, Subject::of));
            lesson.setDayIfNotDefault(parseField("day", args, Day::of));
            lesson.setStartIfNotDefault(parseField("start", args, Time::of));
            lesson.setEndIfNotDefault(parseField("end", args, Time::of));
            return lesson;
        } catch (ParseException e) {
            throw new ParseException("Invalid lesson format: " + e.getMessage() + ". "
                    + getUsageInfo());
        }
    }
    private static String getUsageInfo() {
        return "\nUsage: addLesson -name [NAME] (any number of -[subject|day|start|end|remark] [value]). "
                + "\n For example, addLesson -name John -subject Math -day Monday -start 14:30 -end 16:30"
                + "\n If you are currently displaying lesson list, you could use 'add' inplace of 'addLesson'. "
                + "\n Note you must provide a 'name' not already in the schedule and 'start' must be before 'end'.";
    }
}
