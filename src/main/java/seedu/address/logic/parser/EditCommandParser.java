package seedu.address.logic.parser;

import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Subject;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {

        int index = TypeParsingUtil.parseNum(TypeParsingUtil
                .getValueImmediatelyAfterCommandName("edit", "index", args));
        Name name = TypeParsingUtil.parseName("name", args, true);
        Phone phone = TypeParsingUtil.parsePhone("phone", args, true);
        Email email = TypeParsingUtil.parseEmail("email", args, true);
        Address address = TypeParsingUtil.parseAddress("address", args, true);
        Set<Subject> subjects = TypeParsingUtil.parseSubjects("subject", args, true);
        Set<Tag> tags = TypeParsingUtil.parseTags("tag", args, true);
        return new EditCommand(index, name, phone, email, address, subjects, tags);

    }
}
