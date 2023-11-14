package seedu.lovebook.logic.parser;

import static seedu.lovebook.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HOROSCOPE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.lovebook.logic.commands.AddCommand;
import seedu.lovebook.logic.parser.exceptions.ParseException;
import seedu.lovebook.model.date.Age;
import seedu.lovebook.model.date.Date;
import seedu.lovebook.model.date.Gender;
import seedu.lovebook.model.date.Height;
import seedu.lovebook.model.date.Income;
import seedu.lovebook.model.date.Name;
import seedu.lovebook.model.date.horoscope.Horoscope;


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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_AGE, PREFIX_GENDER, PREFIX_HEIGHT, PREFIX_INCOME,
                        PREFIX_HOROSCOPE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_HEIGHT, PREFIX_AGE, PREFIX_GENDER, PREFIX_INCOME,
                PREFIX_HOROSCOPE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_AGE, PREFIX_GENDER, PREFIX_HEIGHT, PREFIX_INCOME,
                PREFIX_HOROSCOPE);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Age age = ParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get());
        Gender gender = ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get());
        Height height = ParserUtil.parseHeight(argMultimap.getValue(PREFIX_HEIGHT).get());
        Income income = ParserUtil.parseIncome(argMultimap.getValue(PREFIX_INCOME).get());
        Horoscope horoscope = ParserUtil.parseHoroscope(argMultimap.getValue(PREFIX_HOROSCOPE).get());

        Date date = new Date(name, age, gender, height, income, horoscope);

        return new AddCommand(date);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
