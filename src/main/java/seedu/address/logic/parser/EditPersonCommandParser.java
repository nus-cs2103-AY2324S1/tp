package seedu.address.logic.parser;

import seedu.address.logic.commands.EditLessonCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.person.Person;

import static seedu.address.logic.parser.TypeParsingUtil.getValueImmediatelyAfterCommandName;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditPersonCommandParser implements Parser<EditPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditPersonCommand parse(String args) throws ParseException {
        String indexStr = getValueImmediatelyAfterCommandName(EditPersonCommand.COMMAND_WORD, "index", args, true);
        Person person;
        try {
            person = AddPersonCommandParser.parsePerson(args);
        } catch (ParseException e) {
            throw new ParseException("Invalid person format. " + e.getMessage() + getUsageInfo());
        }
        if (indexStr != null) {
            try {
                int index = TypeParsingUtil.parseNum(indexStr);
                return new EditPersonCommand(index, person);
            } catch (ParseException e) {
                throw new ParseException("Invalid index input: " + indexStr + ". Please input a valid number.");
            }
        } else {
            return new EditPersonCommand(person);
        }
    }
    public static String getUsageInfo() {
        return "\nUsage: edit <Index> (at least one of -[name|phone|email|address|subject|tag|remark] [value]). "
                + "\nFor example, edit 1 -name John -phone 91234567"
                + "\nIf you want to edit the currently shown person, you could omit the index. "
                + "\nNote your edited 'name' must not already in the address book.";
    }
}
