package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FINANCIAL_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.GatherCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.GatherEmailsByFinancialPlan;
import seedu.address.model.person.GatherEmailsByTag;

/**
 * Parses input arguments and creates a new GatherCommand object
 */
public class GatherCommandParser implements Parser<GatherCommand> {
    public static final String FINANCIAL_PLAN_REGEX = "^[a-zA-Z0-9\\s]+";
    public static final String TAG_REGEX = "\\p{Alnum}+";

    public static final String FINANCIAL_PLAN_CONSTRAINTS = "PROMPT should be alphanumeric or space characters";
    public static final String TAG_CONSTRAINTS = "Tags names should be alphanumeric";

    /**
     * Parses the given {@code String} of arguments in the context of the GatherCommand
     * and returns a GatherCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GatherCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String argDescription = trimmedArgs;

        if (trimmedArgs.contains(PREFIX_FINANCIAL_PLAN.getPrefix())) {
            // Parse Financial Plan
            argDescription = trimmedArgs.replace(PREFIX_FINANCIAL_PLAN.getPrefix(), "").trim();
            validateInput(argDescription, FINANCIAL_PLAN_REGEX, FINANCIAL_PLAN_CONSTRAINTS);
            return new GatherCommand(new GatherEmailsByFinancialPlan(argDescription));
        } else if (trimmedArgs.contains(PREFIX_TAG.getPrefix())) {
            // Parse Tag
            argDescription = trimmedArgs.replace(PREFIX_TAG.getPrefix(), "").trim();
            validateInput(argDescription, TAG_REGEX, TAG_CONSTRAINTS);
            return new GatherCommand(new GatherEmailsByTag(argDescription));
        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GatherCommand.MESSAGE_USAGE));
    }

    private void validateInput(String input, String regexPattern, String errorMessage) throws ParseException {
        if (input.isEmpty() || !input.matches(regexPattern)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, errorMessage));
        }
    }

}
