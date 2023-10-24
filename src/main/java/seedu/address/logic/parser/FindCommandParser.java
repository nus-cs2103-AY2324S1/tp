package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FINANCIAL_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.validateFinancialPlans;
import static seedu.address.logic.parser.ParserUtil.validateNames;
import static seedu.address.logic.parser.ParserUtil.validateTags;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
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
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME,
                PREFIX_FINANCIAL_PLAN, PREFIX_TAG);

        List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
        validateNames(nameKeywords);

        List<String> tagKeywords = argMultimap.getAllValues(PREFIX_TAG);
        validateTags(tagKeywords);

        List<String> financialPlanKeywords = argMultimap.getAllValues(PREFIX_FINANCIAL_PLAN);
        validateFinancialPlans(financialPlanKeywords);

        Predicate<Person> combinedPredicate = new NameContainsKeywordsPredicate(nameKeywords)
                .or(new TagContainsKeywordsPredicate(tagKeywords))
                .or(new FinancialPlanContainsKeywordsPredicate(financialPlanKeywords));

        return new FindCommand(combinedPredicate);
    }

}
