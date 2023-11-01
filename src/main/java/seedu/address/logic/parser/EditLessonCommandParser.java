package seedu.address.logic.parser;

import seedu.address.logic.commands.EditLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lessons.Lesson;

import static seedu.address.logic.parser.TypeParsingUtil.getValueImmediatelyAfterCommandName;

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
        String indexStr = getValueImmediatelyAfterCommandName(EditLessonCommand.COMMAND_WORD, "index", args, true);
        Lesson lesson;
        try {
            lesson = AddLessonCommandParser.parseLesson(args);
        } catch (ParseException e) {
            throw new ParseException("Invalid lesson format. " + e.getMessage() + getUsageInfo());
        }
        if (indexStr != null) {
            try {
                int index = TypeParsingUtil.parseNum(indexStr);
                return new EditLessonCommand(index, lesson);
            } catch (ParseException e) {
                throw new ParseException("Invalid index input: " + indexStr + ". Please input a valid number.");
            }
        } else {
            return new EditLessonCommand(lesson);
        }
    }

    public static String getUsageInfo() {
        return "\nUsage: edit <Index> (at least one of -[name|subject|day|start|end|remark] [value]). "
                + "\nFor example, edit 1 -name lesson2 -subject Math -day Monday -start 14:30 -end 16:30"
                + "\nIf you want to edit the currently shown lesson, you could omit the index. "
                + "\nNote your edited 'name' must not already in the schedule and 'start' must be before 'end'.";
    }
}
