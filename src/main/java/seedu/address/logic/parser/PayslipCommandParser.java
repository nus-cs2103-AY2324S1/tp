package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PayslipCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new PayslipCommand object
 */
public class PayslipCommandParser implements Parser<PayslipCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the PayslipCommand
     * and returns an PayslipCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PayslipCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, PayslipCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        Index index;

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME);

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            return new PayslipCommand(index);
        } catch (ParseException pe) {
            if (argMultimap.getValue(PREFIX_NAME).isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    PayslipCommand.MESSAGE_USAGE), pe);
            }

            String name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).toString();
            String[] nameKeywords = name.split("\\s+");
            return new PayslipCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
    }
}
