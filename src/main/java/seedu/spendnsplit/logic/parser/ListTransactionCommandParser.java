package seedu.spendnsplit.logic.parser;

import static seedu.spendnsplit.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import seedu.spendnsplit.logic.commands.ListTransactionCommand;
import seedu.spendnsplit.logic.parser.exceptions.ParseException;
import seedu.spendnsplit.model.person.Name;
import seedu.spendnsplit.model.transaction.TransactionContainsKeywordsAndPersonNamesPredicate;

/**
 * Parses input arguments and creates a new ListTransactionCommand object
 */
public class ListTransactionCommandParser implements Parser<ListTransactionCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListTransactionCommand
     * and returns a ListTransactionCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListTransactionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);
        Set<Name> nameList = ParserUtil.parseNames(argMultimap.getAllValues(PREFIX_NAME));
        List<String> keywords = argMultimap.getPreamble().trim().isEmpty() ? List.of()
                : Arrays.asList(argMultimap.getPreamble().trim().split("\\s+"));
        for (String keyword : keywords) {
            if (keyword.contains("=")) {
                throw new ParseException(ListTransactionCommand.MESSAGE_USAGE);
            }
        }
        return new ListTransactionCommand(new TransactionContainsKeywordsAndPersonNamesPredicate(
                keywords, new ArrayList<>(nameList)));
    }
}
