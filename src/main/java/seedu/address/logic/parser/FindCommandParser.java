package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FINANCIAL_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicates.CombinedPredicate;
import seedu.address.model.person.predicates.FinancialPlanContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = processRawCommand(args);

        List<String> nameKeywords = getNameList(argMultimap);
        List<String> tagKeywords = getTagList(argMultimap);
        List<String> financialPlanKeywords = getFinancialPlanList(argMultimap);

        CombinedPredicate combinedPredicate = new CombinedPredicate(
                new FinancialPlanContainsKeywordsPredicate(financialPlanKeywords),
                new NameContainsKeywordsPredicate(nameKeywords),
                new TagContainsKeywordsPredicate(tagKeywords)
        );

        return new FindCommand(combinedPredicate);
    }
    private ArgumentMultimap processRawCommand(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME,
                PREFIX_FINANCIAL_PLAN, PREFIX_TAG);
        if (trimmedArgs.isEmpty() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return argMultimap;
    }
    private List<String> getNameList(ArgumentMultimap argumentMultimap) throws ParseException {
        return getFieldList(argumentMultimap, PREFIX_NAME, ParserUtil::validateName);
    }
    private List<String> getFinancialPlanList(ArgumentMultimap argumentMultimap) throws ParseException {
        return getFieldList(argumentMultimap, PREFIX_FINANCIAL_PLAN, ParserUtil::validateFinancialPlan);
    }
    private List<String> getTagList(ArgumentMultimap argumentMultimap) throws ParseException {
        return getFieldList(argumentMultimap, PREFIX_TAG, ParserUtil::validateTag);
    }

    /**
     * Gets the list of values mapped to the given prefix from the given multimap.
     *
     * @param argumentMultimap Multimap to draw values from.
     * @param prefix Key value for the multimap.
     * @param validator Validator for values drawn from multimap.
     * @return List of String values from the multimap.
     * @throws ParseException if any value in the list does not pass the validator's requirements.
     */
    private List<String> getFieldList(ArgumentMultimap argumentMultimap,
                                      Prefix prefix, Validator validator) throws ParseException {
        requireNonNull(argumentMultimap);
        requireNonNull(prefix);
        requireNonNull(validator);
        List<String> list = argumentMultimap.getAllValues(prefix);
        list.replaceAll(String::trim);
        for (String string : list) {
            validator.validate(string);
        }
        return list;
    }
}
