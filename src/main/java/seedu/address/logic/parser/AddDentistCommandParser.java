package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALIZATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YOE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddDentistCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.model.person.dentist.Specialization;
import seedu.address.model.person.dentist.Yoe;
import seedu.address.model.tag.Tag;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddDentistCommandParser implements Parser<AddDentistCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddDentistCommand
     * and returns an AddDentistCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDentistCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_NAME,
                        PREFIX_PHONE,
                        PREFIX_EMAIL,
                        PREFIX_ADDRESS,
                        PREFIX_SPECIALIZATION,
                        PREFIX_YOE,
                        PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap,
                PREFIX_NAME,
                PREFIX_PHONE,
                // PREFIX_EMAIL,
                // PREFIX_ADDRESS,
                PREFIX_SPECIALIZATION,
                PREFIX_YOE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddDentistCommand.getCommandUsage()));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(
                PREFIX_NAME,
                PREFIX_PHONE,
                PREFIX_ADDRESS,
                PREFIX_EMAIL,
                PREFIX_SPECIALIZATION,
                PREFIX_YOE);

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL)
                .orElse("NoEmailProvided@ToBeAdded.com"));
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS)
                .orElse("No Address Provided"));
        Specialization specialty = ParserUtil.parseSpecialization(argMultimap.getValue(PREFIX_SPECIALIZATION).get());
        Yoe yoe = ParserUtil.parseYoe(argMultimap.getValue(PREFIX_YOE).get());
        Set<Tag> tags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Dentist dentist = new Dentist(name, phone, email, address, specialty, yoe, tags);

        return new AddDentistCommand(dentist);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
