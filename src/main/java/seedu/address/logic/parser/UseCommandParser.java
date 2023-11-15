package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIT;

import java.util.stream.Stream;

import seedu.address.logic.commands.UseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Name;
import seedu.address.model.ingredient.Quantity;
import seedu.address.model.ingredient.Unit;
import seedu.address.model.ingredient.exceptions.UnitFormatException;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class UseCommandParser implements Parser<UseCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UseCommand
     * and returns an UseCommand object for execution.
     * Currently only works for when quantity is specified
     * @throws ParseException if the user input does not conform the expected format
     */
    public UseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_QUANTITY, PREFIX_UNIT);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UseCommand.MESSAGE_USAGE));
        }

        if ((!arePrefixesPresent(argMultimap, PREFIX_QUANTITY) && arePrefixesPresent(argMultimap, PREFIX_UNIT))
                || (!arePrefixesPresent(argMultimap, PREFIX_UNIT)
                && arePrefixesPresent(argMultimap, PREFIX_QUANTITY))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UseCommand.MESSAGE_USAGE));
        }


        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_QUANTITY, PREFIX_UNIT);

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        if (!arePrefixesPresent(argMultimap, PREFIX_QUANTITY, PREFIX_UNIT)) {
            return new UseCommand(name, null);
        }

        Unit unit;
        try {
            unit = ParserUtil.parseUnitOfIngredient(argMultimap.getValue(PREFIX_UNIT).get());
        } catch (UnitFormatException e) {
            throw new ParseException("This is not a valid unit!");
        }

        Quantity quantity = ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get(), unit);

        return new UseCommand(name, quantity);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
