package seedu.address.logic.parser;

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

        int index = TypeParsingUtil.parseNum(TypeParsingUtil
                .getValueImmediatelyAfterCommandName("edit", "index", args));
        Person person = AddPersonCommandParser.parsePerson(args);
        return new EditPersonCommand(index, person);

    }
}
