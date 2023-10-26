package seedu.application.logic.parser;

import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_JOB_TYPE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import seedu.application.logic.Messages;
import seedu.application.logic.commands.FindCommand;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.job.CombinedPredicate;
import seedu.application.model.job.FieldContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROLE, PREFIX_COMPANY,
                        PREFIX_DEADLINE, PREFIX_STATUS, PREFIX_JOB_TYPE, PREFIX_INDUSTRY);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ROLE, PREFIX_COMPANY,
                PREFIX_DEADLINE, PREFIX_STATUS, PREFIX_JOB_TYPE, PREFIX_INDUSTRY);



        ArrayList<FieldContainsKeywordsPredicate> predicateList = new ArrayList<>();

        for (Map.Entry<Prefix, List<String>> entry : argMultimap.getArgMultimap().entrySet()) {
            if (entry.getKey().equals(new Prefix(""))) {
                continue;
            }

            if (!FieldContainsKeywordsPredicate.isValidPrefix(entry.getKey())) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, Messages.MESSAGE_INVALID_PREFIX));
            }

            String keywords = entry.getValue().get(0);
            if (keywords.equals("")) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_EMPTY_KEYWORDS));
            }

            String[] splitKeywords = keywords.split("\\s+");
            List<String> keywordList = new ArrayList<>();
            for (String keyword : splitKeywords) {
                keywordList.add(keyword);
            }

            FieldContainsKeywordsPredicate predicate = new FieldContainsKeywordsPredicate(entry.getKey(), keywordList);
            predicateList.add(predicate);
        }

        if (argMultimap.size() < 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(new CombinedPredicate(predicateList));
    }

}
