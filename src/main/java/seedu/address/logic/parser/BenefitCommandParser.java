package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REASON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VALUE;

import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.BenefitCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Benefit;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Reason;

/**
 * Parses input arguments and creates a new BenefitCommand object.
 */
public class BenefitCommandParser implements Parser<BenefitCommand> {
    @Override
    public BenefitCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BenefitCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_VALUE, PREFIX_REASON);

        Index index;
        Reason reason;
        Benefit benefit;

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_VALUE, PREFIX_REASON);

        if (argMultimap.getValue(PREFIX_REASON).isPresent()) {
            reason = ParserUtil.parseReason(argMultimap.getValue(PREFIX_REASON).get());
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, Reason.MISSING_ALERT));
        }

        if (argMultimap.getValue(PREFIX_VALUE).isPresent()) {
            benefit = ParserUtil.parseBenefit(argMultimap.getValue(PREFIX_VALUE).get(), reason);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BenefitCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            return new BenefitCommand(index, benefit);
        } catch (ParseException pe) {
            if (argMultimap.getValue(PREFIX_NAME).isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    BenefitCommand.MESSAGE_USAGE), pe);
            }

            String name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).toString();
            String[] nameKeywords = name.split("\\s+");
            return new BenefitCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)), benefit);
        }
    }
}
