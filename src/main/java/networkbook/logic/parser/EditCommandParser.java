package networkbook.logic.parser;

import static java.util.Objects.requireNonNull;

import networkbook.commons.core.index.Index;
import networkbook.logic.Messages;
import networkbook.logic.commands.edit.EditAction;
import networkbook.logic.commands.edit.EditCommand;
import networkbook.logic.parser.exceptions.ParseException;
import networkbook.model.person.Course;
import networkbook.model.person.Email;
import networkbook.model.person.Graduation;
import networkbook.model.person.Link;
import networkbook.model.person.Name;
import networkbook.model.person.Phone;
import networkbook.model.person.Priority;
import networkbook.model.person.Specialisation;
import networkbook.model.tag.Tag;

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
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args,
                        CliSyntax.PREFIX_NAME,
                        CliSyntax.PREFIX_PHONE,
                        CliSyntax.PREFIX_EMAIL,
                        CliSyntax.PREFIX_LINK,
                        CliSyntax.PREFIX_GRADUATION,
                        CliSyntax.PREFIX_COURSE,
                        CliSyntax.PREFIX_SPECIALISATION,
                        CliSyntax.PREFIX_TAG,
                        CliSyntax.PREFIX_PRIORITY,
                        CliSyntax.PREFIX_INDEX
                );

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(
                            Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                            EditCommand.MESSAGE_USAGE
                    ),
                    pe
            );
        }

        Prefix prefix = argMultimap.verifyExactlyOneIsPresent(
                CliSyntax.PREFIX_NAME,
                CliSyntax.PREFIX_PHONE,
                CliSyntax.PREFIX_EMAIL,
                CliSyntax.PREFIX_LINK,
                CliSyntax.PREFIX_GRADUATION,
                CliSyntax.PREFIX_COURSE,
                CliSyntax.PREFIX_SPECIALISATION,
                CliSyntax.PREFIX_TAG,
                CliSyntax.PREFIX_PRIORITY
        );

        argMultimap.verifyIfPresentThen(new Prefix[] {
                CliSyntax.PREFIX_PHONE,
                CliSyntax.PREFIX_EMAIL,
                CliSyntax.PREFIX_LINK,
                CliSyntax.PREFIX_COURSE,
                CliSyntax.PREFIX_SPECIALISATION,
                CliSyntax.PREFIX_TAG
        }, CliSyntax.PREFIX_INDEX);

        EditAction action = generateAction(argMultimap, prefix);

        return new EditCommand(index, action);
    }

    private static EditAction generateAction(ArgumentMultimap argMultimap, Prefix prefix) throws ParseException {
        EditAction result = null;
        if (prefix.equals(CliSyntax.PREFIX_NAME)) {
            Name name = ParserUtil.parseName(argMultimap.getValue(prefix).get());
            result = editPersonDescriptor -> editPersonDescriptor.setName(name);
        } else if (prefix.equals(CliSyntax.PREFIX_PHONE)) {
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(prefix).get());
            Index index = ParserUtil.parseIndex(argMultimap.getValue(CliSyntax.PREFIX_INDEX).get());
            result = editPersonDescriptor -> editPersonDescriptor.setPhone(index, phone);
        } else if (prefix.equals(CliSyntax.PREFIX_EMAIL)) {
            Email email = ParserUtil.parseEmail(argMultimap.getValue(prefix).get());
            Index index = ParserUtil.parseIndex(argMultimap.getValue(CliSyntax.PREFIX_INDEX).get());
            result = editPersonDescriptor -> editPersonDescriptor.setEmail(index, email);
        } else if (prefix.equals(CliSyntax.PREFIX_LINK)) {
            Link link = ParserUtil.parseLink(argMultimap.getValue(prefix).get());
            Index index = ParserUtil.parseIndex(argMultimap.getValue(CliSyntax.PREFIX_INDEX).get());
            result = editPersonDescriptor -> editPersonDescriptor.setLink(index, link);
        } else if (prefix.equals(CliSyntax.PREFIX_GRADUATION)) {
            Graduation graduation = ParserUtil.parseGraduation(argMultimap.getValue(prefix).get());
            result = editPersonDescriptor -> editPersonDescriptor.setGraduation(graduation);
        } else if (prefix.equals(CliSyntax.PREFIX_COURSE)) {
            Course course = ParserUtil.parseCourse(argMultimap.getValue(prefix).get());
            Index index = ParserUtil.parseIndex(argMultimap.getValue(CliSyntax.PREFIX_INDEX).get());
            result = editPersonDescriptor -> editPersonDescriptor.setCourse(index, course);
        } else if (prefix.equals(CliSyntax.PREFIX_SPECIALISATION)) {
            Specialisation specialisation = ParserUtil.parseSpecialisation(argMultimap.getValue(prefix).get());
            Index index = ParserUtil.parseIndex(argMultimap.getValue(CliSyntax.PREFIX_INDEX).get());
            result = editPersonDescriptor -> editPersonDescriptor.setSpecialisation(index, specialisation);
        } else if (prefix.equals(CliSyntax.PREFIX_TAG)) {
            Tag tag = ParserUtil.parseTag(argMultimap.getValue(prefix).get());
            Index index = ParserUtil.parseIndex(argMultimap.getValue(CliSyntax.PREFIX_INDEX).get());
            result = editPersonDescriptor -> editPersonDescriptor.setTag(index, tag);
        } else if (prefix.equals(CliSyntax.PREFIX_PRIORITY)) {
            Priority priority = ParserUtil.parsePriority(argMultimap.getValue(prefix).get());
            result = editPersonDescriptor -> editPersonDescriptor.setPriority(priority);
        }

        requireNonNull(result);
        return result;
    }
}
