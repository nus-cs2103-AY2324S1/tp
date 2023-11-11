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
        return new AddLessonCommand(parseLesson(args, false));
    }

    /**
     * Parses the given {@code String} of arguments in to a lesson object
     * @param args the user input string
     * @return a lesson object
     * @throws ParseException if the user input is of wrong format or the lesson clashes with existing lessons
     */
    public static Lesson parseLesson(String args, boolean nameIsOptional) throws ParseException {
        try {
            Lesson lesson = Lesson.getDefaultLesson();
            lesson.setNameIfNotDefault(parseField("name", args, Name::of, nameIsOptional));
            lesson.setSubjectIfNotDefault(parseField("subject", args, Subject::of));
            lesson.setDayIfNotDefault(parseField("day", args, Day::of));
            Time start = parseField("start", args, Time::of);
            if (start == null) {
                start = Time.DEFAULT_TIME;
            }
            Time end = parseField("end", args, Time::of);
            if (end == null) {
                end = Time.DEFAULT_TIME;
            }
            lesson.updateStartAndEnd(start, end);
            return lesson;
        } catch (ParseException e) {
            throw new ParseException("Invalid lesson input: " + e.getMessage() + ". "
                    + getUsageInfo());
        }
    }
    private static String getUsageInfo() {
        return "\nUsage: addLesson -name NAME (any number of unique [-subject|day|start|end VALUE]). "
                + "\n For example, addLesson -name John -subject English -day 23 -start 14:30 -end 16:30"
                + "\n If you are currently displaying schedule list, you could use 'add' inplace of 'addLesson'. "
                + "\n Note you must provide a 'name' not already in the schedule and 'start' must be before 'end'.";
    }
}
