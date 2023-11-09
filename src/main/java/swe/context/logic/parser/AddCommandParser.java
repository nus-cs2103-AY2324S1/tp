package swe.context.logic.parser;

import static swe.context.logic.parser.CliSyntax.PREFIX_ALTERNATE;
import static swe.context.logic.parser.CliSyntax.PREFIX_EMAIL;
import static swe.context.logic.parser.CliSyntax.PREFIX_NAME;
import static swe.context.logic.parser.CliSyntax.PREFIX_NOTE;
import static swe.context.logic.parser.CliSyntax.PREFIX_PHONE;
import static swe.context.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import swe.context.logic.Messages;
import swe.context.logic.commands.AddCommand;
import swe.context.logic.parser.exceptions.ParseException;
import swe.context.model.alternate.AlternateContact;
import swe.context.model.contact.Contact;
import swe.context.model.contact.Email;
import swe.context.model.contact.Name;
import swe.context.model.contact.Note;
import swe.context.model.contact.Phone;
import swe.context.model.tag.Tag;



/**
 * Parses input arguments and creates a new {@link AddCommand} object.
 */
public class AddCommandParser implements Parser<AddCommand> {
    /**
     * Returns true if none of the prefixes contains empty {@link Optional} values in the given
     * {@link ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        // Tokenize arguments based on the expected prefixes
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args,
                        PREFIX_NAME,
                        PREFIX_PHONE,
                        PREFIX_EMAIL,
                        PREFIX_NOTE,
                        PREFIX_TAG,
                        PREFIX_ALTERNATE
                );

        // Check for mandatory prefixes and empty preamble
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(Messages.commandInvalidFormat(AddCommand.MESSAGE_USAGE));
        }

        // Verify that there are no duplicate entries for the specified prefixes
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NOTE);

        // Parse the mandatory fields
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());

        // Parse the optional tags and alternates
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Set<AlternateContact> alternateContactList =
                ParserUtil.parseAlternates(argMultimap.getAllValues(PREFIX_ALTERNATE));

        // Parse note, defaulting to an empty string if not provided
        Optional<String> noteOptional = argMultimap.getValue(PREFIX_NOTE);
        Note note = ParserUtil.parseNote(noteOptional.orElse(""));

        // Create the new contact
        Contact contact = new Contact(name, phone, email, note, tagList, alternateContactList);

        // Return the constructed AddCommand
        return new AddCommand(contact);
    }
}
