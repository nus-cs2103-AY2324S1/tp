package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FINANCIAL_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.NullAppointment;
import seedu.address.model.appointment.ScheduleItem;
import seedu.address.model.financialplan.FinancialPlan;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKinName;
import seedu.address.model.person.NextOfKinPhone;
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
        ArgumentMultimap argMultimap = processRawCommand(args);
        Person person = createPerson(argMultimap);
        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
    /**
     * Processes the given {@code String} into an ArgumentMultimap in the context of an AddCommand.
     *
     * @param args Raw command string.
     * @return ArgumentMultimap containing argument values to create a Person with.
     * @throws ParseException if the string contains invalid arguments or duplicate arguments for Person fields that
     *      require exactly one argument.
     */
    private ArgumentMultimap processRawCommand(String args) throws ParseException {
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_NEXT_OF_KIN_NAME, PREFIX_NEXT_OF_KIN_PHONE, PREFIX_FINANCIAL_PLAN, PREFIX_TAG);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_NEXT_OF_KIN_NAME, PREFIX_NEXT_OF_KIN_PHONE)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argumentMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_NEXT_OF_KIN_NAME, PREFIX_NEXT_OF_KIN_PHONE);

        return argumentMultimap;
    }

    /**
     * Creates a Person from the given ArgumentMultimap. This method ignores fields in the multimap that are not
     * relevant to the Person class and will use the last value in the multimap if any prefixes are mapped to
     * multiple values.
     *
     * @param argumentMultimap Multimap to draw values from.
     * @return Person object.
     * @throws ParseException if the multimap contains invalid values necessary to create a Person.
     */
    private Person createPerson(ArgumentMultimap argumentMultimap) throws ParseException {
        Name name = ParserUtil.parseName(argumentMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argumentMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argumentMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argumentMultimap.getValue(PREFIX_ADDRESS).get());
        NextOfKinName nokName = ParserUtil.parseNextOfKinName(argumentMultimap.getValue(PREFIX_NEXT_OF_KIN_NAME).get());
        NextOfKinPhone nokPhone = ParserUtil
                .parseNextOfKinPhone((argumentMultimap.getValue(PREFIX_NEXT_OF_KIN_PHONE)).get());
        Set<FinancialPlan> financialPlanList = ParserUtil.parseFinancialPlans(
                argumentMultimap.getAllValues(PREFIX_FINANCIAL_PLAN));
        Set<Tag> tagList = ParserUtil.parseTags(argumentMultimap.getAllValues(PREFIX_TAG));
        ScheduleItem appointment = NullAppointment.getNullAppointment();

        Person person = new Person(name, phone, email, address, nokName, nokPhone,
                financialPlanList, tagList, appointment);
        return person;
    }
}
