package seedu.address.logic.parser;

import static seedu.address.logic.parser.TypeParsingUtil.parseIndex;

import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

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
        Integer index = parseIndex(args, true);
        Person person;
        try {
            person = AddPersonCommandParser.parsePerson(args, true);
        } catch (ParseException e) {
            throw new ParseException("Invalid person format. " + e.getMessage() + getUsageInfo());
        }
        return new EditPersonCommand(index, person);
    }
    public static String getUsageInfo() {
        return "\nUsage: edit [INDEX] (at least one of unique -[name|phone|email|address|subject|tag|remark VALUE]). "
                + "\nFor example, edit 1 -name John -phone 91234567"
                + "\nIf you want to edit the currently shown person, you could omit the index. "
                + "\nNote your edited 'name' must not already in the address book.";
    }
}
