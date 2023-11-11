package seedu.address.logic.parser;

import static seedu.address.logic.parser.TypeParsingUtil.parseIndex;

import seedu.address.logic.commands.EditLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lessons.Lesson;


/**
 * Parses input arguments and creates a new EditLessonCommand object
 */
public class EditLessonCommandParser implements Parser<EditLessonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditLessonCommand parse(String args) throws ParseException {
        Integer index = parseIndex(args, true);
        Lesson lesson;
        try {
            lesson = AddLessonCommandParser.parseLesson(args, true);
        } catch (ParseException e) {
            throw new ParseException("Invalid lesson format. " + e.getMessage() + getUsageInfo());
        }
        return new EditLessonCommand(index, lesson);
    }

    public static String getUsageInfo() {
        return "\nUsage: edit [INDEX] (at least one of unique -[name|subject|day|start|end VALUE]). "
                + "\nFor example, edit 1 -name lesson2 -subject English -day 23/12 -start 14:30 -end 16:30"
                + "\nIf you want to edit the currently shown lesson, you could omit the index. "
                + "\nNote your edited 'name' must not already in the schedule and 'start' must be before 'end'.";
    }
}
