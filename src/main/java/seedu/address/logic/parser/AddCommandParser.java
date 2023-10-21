package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.InvalidInputException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Schedule;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Subject;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        Person person = new Person(TypeParsingUtil.parseName("name", args));
        person.setPhoneIfNotNull(TypeParsingUtil.parsePhone("phone", args, true));
        person.setEmailIfNotNull(TypeParsingUtil.parseEmail("email", args, true));
        person.setAddressIfNotNull(TypeParsingUtil.parseAddress("address", args, true));
        person.setSubjectsIfNotNull(TypeParsingUtil.parseSubjects("subject", args, true));
        person.setTagsIfNotNull(TypeParsingUtil.parseTags("tag", args, true));
        //person.setLessons(TypeParsingUtil.parseLessons("lesson", args, true));

        return new AddCommand(person);
    }
}
