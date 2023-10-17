package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANIMAL_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANIMAL_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.AnimalType;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Email;
import seedu.address.model.person.Housing;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_TAG, PREFIX_ANIMAL_NAME,
                        PREFIX_AVAILABILITY, PREFIX_ANIMAL_TYPE, PREFIX_HOUSING);


        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_AVAILABILITY).isPresent()
                && argMultimap.getValue(PREFIX_AVAILABILITY).get().equals("nil")) {
            if (argMultimap.getValue(PREFIX_ANIMAL_NAME).isPresent()
                    && !argMultimap.getValue(PREFIX_ANIMAL_NAME).get().equals("nil")) {
                throw new ParseException("Animal name should be 'nil' when availability is 'nil'.");
            }

            if (argMultimap.getValue(PREFIX_ANIMAL_TYPE).isPresent()
                    && !argMultimap.getValue(PREFIX_ANIMAL_TYPE).get().equals("nil")) {
                throw new ParseException("Animal type should be 'nil' when availability is 'nil'.");
            }
        }

        if (argMultimap.getValue(PREFIX_ANIMAL_NAME).isPresent()
                && !argMultimap.getValue(PREFIX_ANIMAL_NAME).get().equals("nil")) {
            if (argMultimap.getValue(PREFIX_AVAILABILITY).isPresent()
                    && argMultimap.getValue(PREFIX_AVAILABILITY).get().equals("Available")) {
                throw new ParseException("Availability cannot be 'Available' when an animal name "
                        + "is provided; animal name should be 'nil'.");
            }
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        if (!argMultimap.getValue(PREFIX_ANIMAL_NAME).isPresent()) {
            throw new ParseException("Animal name is required. Please indicate as 'nil' if information is "
                    + "not available.");
        }
        Name animalName = ParserUtil.parseName(argMultimap.getValue(PREFIX_ANIMAL_NAME).get());

        if (!argMultimap.getValue(PREFIX_AVAILABILITY).isPresent()) {
            throw new ParseException("Availability is required. Please indicate as 'nil' if information is "
                    + "not available.");
        }
        Availability availability = ParserUtil.parseAvailability(argMultimap
                .getValue(PREFIX_AVAILABILITY).get());

        if (!argMultimap.getValue(PREFIX_ANIMAL_TYPE).isPresent()) {
            throw new ParseException("Animal type is required. Please indicate as 'nil' if information is "
                    + "not available.");
        }
        AnimalType animalType = ParserUtil.parseAnimalType(argMultimap
                .getValue(PREFIX_ANIMAL_TYPE).get(), availability.value);

        if (!argMultimap.getValue(PREFIX_HOUSING).isPresent()) {
            throw new ParseException("Housing is required. Please indicate as 'nil' if information is "
                    + "not available.");
        }

        Housing housing = ParserUtil.parseHousing(argMultimap.getValue(PREFIX_HOUSING).get());

        Person person = new Person(name, phone, email, address,
                housing, availability, animalName, animalType, tagList);

        return new AddCommand(person);
    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
