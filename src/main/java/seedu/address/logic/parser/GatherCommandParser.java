//@@author AlyssaPng
package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FINANCIAL_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.validateFinancialPlan;
import static seedu.address.logic.parser.ParserUtil.validateTag;
import static seedu.address.model.financialplan.FinancialPlan.isValidFinancialPlanName;
import static seedu.address.model.tag.Tag.isValidTagName;

import seedu.address.logic.commands.GatherCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.gatheremail.GatherEmailByFinancialPlan;
import seedu.address.model.person.gatheremail.GatherEmailByTag;

/**
 * Parses input arguments and creates a new GatherCommand object
 */
public class GatherCommandParser implements Parser<GatherCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the GatherCommand.
     * and returns a GatherCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public GatherCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FINANCIAL_PLAN, PREFIX_TAG);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_FINANCIAL_PLAN, PREFIX_TAG);

        if (argMultimap.getValue(PREFIX_FINANCIAL_PLAN).isPresent() && argMultimap.getValue(PREFIX_TAG).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GatherCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_FINANCIAL_PLAN).isPresent()) {
            String financialPlanArgs = removePrefix(trimmedArgs, PREFIX_FINANCIAL_PLAN);
            validateFinancialPlan(financialPlanArgs);
            assert isValidFinancialPlanName(financialPlanArgs) : "Prompt has to meets valid FP requirements";
            return new GatherCommand(new GatherEmailByFinancialPlan(financialPlanArgs));
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            // Parse Tag
            String tagArgs = removePrefix(trimmedArgs, PREFIX_TAG);
            validateTag(tagArgs);
            assert isValidTagName(tagArgs) : "Prompt has to meets valid Tag requirements";
            return new GatherCommand(new GatherEmailByTag(tagArgs));
        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GatherCommand.MESSAGE_USAGE));
    }

    /**
     * Removes the {@code prefix} from the {@code currArg}.
     */
    public String removePrefix(String currArg, Prefix prefix) {
        String noPrefix = currArg.replace(prefix.getPrefix(), "");
        String trimmedArg = noPrefix.trim();
        return trimmedArg;
    }
}
