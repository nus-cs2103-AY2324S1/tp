package seedu.lovebook.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_HOROSCOPE;
import static seedu.lovebook.logic.parser.CliSyntax.PREFIX_INCOME;

import seedu.lovebook.logic.commands.SetPrefCommand;
import seedu.lovebook.logic.commands.SetPrefCommand.SetPreferenceDescriptor;
import seedu.lovebook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SetPrefCommand object
 */
public class SetPrefCommandParser implements Parser<SetPrefCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetPrefCommand
     * and returns an SetPrefCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetPrefCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_AGE, PREFIX_GENDER, PREFIX_HEIGHT, PREFIX_INCOME, PREFIX_HOROSCOPE);
        argMultimap.verifyNoDuplicatePrefixesFor(
                PREFIX_AGE, PREFIX_GENDER, PREFIX_HEIGHT, PREFIX_INCOME, PREFIX_HOROSCOPE);

        SetPreferenceDescriptor setPrefDescriptor = new SetPreferenceDescriptor();

        if (argMultimap.getValue(PREFIX_AGE).isPresent()) {
            setPrefDescriptor.setAge(ParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get()));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            setPrefDescriptor.setGender(ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get()));
        }
        if (argMultimap.getValue(PREFIX_HEIGHT).isPresent()) {
            setPrefDescriptor.setHeight(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_HEIGHT).get()));
        }
        if (argMultimap.getValue(PREFIX_INCOME).isPresent()) {
            setPrefDescriptor.setIncome(ParserUtil.parseIncome(argMultimap.getValue(PREFIX_INCOME).get()));
        }
        if (argMultimap.getValue(PREFIX_HOROSCOPE).isPresent()) {
            setPrefDescriptor.setHoroscope(ParserUtil.parseHoroscope(argMultimap.getValue(PREFIX_HOROSCOPE).get()));
        }

        if (!setPrefDescriptor.isAnyFieldEdited()) {
            throw new ParseException(SetPrefCommand.MESSAGE_NOT_EDITED);
        }

        return new SetPrefCommand(setPrefDescriptor);
    }
}
