package seedu.address.logic.parser;

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
        int index;
        try {
            index = TypeParsingUtil.parseNum(TypeParsingUtil
                    .getValueImmediatelyAfterCommandName(EditLessonCommand.COMMAND_WORD, "index", args));
        } catch (ParseException e) {
            throw new ParseException("Missing index " + e.getMessage());
        }
        Lesson lesson = AddLessonCommandParser.parseLesson(args);
        return new EditLessonCommand(index, lesson);
    }
}
