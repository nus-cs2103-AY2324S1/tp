package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PayrollCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new PayrollCommand object
 */
public class PayrollCommandParser implements Parser<PayrollCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PayrollCommand
     * and returns a PayrollCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public PayrollCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PayrollCommand.MESSAGE_USAGE));
        }

        try {
            Index index = ParserUtil.parseIndex(args);
            return new PayrollCommand(index);
        } catch (ParseException pe) {
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);
            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME);

            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                String name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).toString();
                String[] nameKeywords = name.split("\\s+");
                return new PayrollCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
            } else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, PayrollCommand.MESSAGE_USAGE));
            }
        }
    }
}
