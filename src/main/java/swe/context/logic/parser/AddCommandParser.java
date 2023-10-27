package swe.context.logic.parser;

import static swe.context.logic.parser.CliSyntax.PREFIX_EMAIL;
import static swe.context.logic.parser.CliSyntax.PREFIX_NAME;
import static swe.context.logic.parser.CliSyntax.PREFIX_NOTE;
import static swe.context.logic.parser.CliSyntax.PREFIX_PHONE;
import static swe.context.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import swe.context.logic.Messages;
import swe.context.logic.commands.AddCommand;
import swe.context.logic.parser.exceptions.ParseException;
import swe.context.model.contact.Contact;
import swe.context.model.contact.Email;
import swe.context.model.contact.Name;
import swe.context.model.contact.Note;
import swe.context.model.contact.Phone;
import swe.context.model.tag.Tag;



/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {
    /**
     * Returns an {@link AddCommand} from parsing the specified arguments.
     * .
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NOTE, PREFIX_TAG);

        if (
            !arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_NOTE, PREFIX_PHONE, PREFIX_EMAIL)
                    || !argMultimap.getPreamble().isEmpty()
        ) {
            throw new ParseException(
                Messages.commandInvalidFormat(AddCommand.MESSAGE_USAGE)
            );
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NOTE);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Note address = ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Contact contact = new Contact(name, phone, email, address, tagList);

        return new AddCommand(contact);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
