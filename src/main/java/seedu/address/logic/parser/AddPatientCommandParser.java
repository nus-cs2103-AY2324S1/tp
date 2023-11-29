package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TREATMENT;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthdate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.patients.Patient;
import seedu.address.model.tag.Tag;
import seedu.address.model.treatment.TreatmentName;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddPatientCommandParser implements Parser<AddPatientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand and returns an
     * AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPatientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args,
                PREFIX_NAME,
                PREFIX_PHONE,
                PREFIX_BIRTHDATE,
                PREFIX_GENDER,
                PREFIX_REMARK,
                PREFIX_TREATMENT,
                PREFIX_ADDRESS,
                PREFIX_EMAIL,
                PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap,
            PREFIX_NAME,
            PREFIX_PHONE,
            PREFIX_BIRTHDATE,
            PREFIX_GENDER
        )
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPatientCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(
            PREFIX_NAME,
            PREFIX_PHONE,
            PREFIX_BIRTHDATE,
            PREFIX_GENDER,
            PREFIX_REMARK,
            PREFIX_TREATMENT,
            PREFIX_ADDRESS,
            PREFIX_EMAIL
        );

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());

        Birthdate birthdate = ParserUtil.parseBirthdate(
            argMultimap.getValue(PREFIX_BIRTHDATE).get());

        Gender gender = ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get());

        Remark remark = ParserUtil.parseRemark(
            argMultimap.getValue(PREFIX_REMARK).orElse("NIL"));

        TreatmentName treatmentName = ParserUtil.parseTreatmentName(
            argMultimap.getValue(PREFIX_TREATMENT).orElse("NIL"));

        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL)
            .orElse("NoEmailProvided@ToBeAdded.com"));

        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS)
            .orElse("No Address Provided."));

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Patient patient = new Patient(name, phone, birthdate, gender, remark, treatmentName,
            address, email, tagList);

        return new AddPatientCommand(patient);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap,
        Prefix... prefixes) {
        return Stream.of(prefixes)
            .allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
