package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PayrollCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import java.util.Arrays;
import java.util.List;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class PayrollCommandParser implements Parser<PayrollCommand>{

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
                return new PayrollCommand(new NameContainsKeywordsPredicate(List.of(name)));
            } else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, PayrollCommand.MESSAGE_USAGE));
            }
        }
    }
}
