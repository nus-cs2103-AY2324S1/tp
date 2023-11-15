package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INGREDIENTS;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.StockCommand;
import seedu.address.model.ingredient.NameContainsKeywordsPredicate;

public class StockCommandParserTest {

    private StockCommandParser parser = new StockCommandParser();


    @Test
    public void parse_validArgs_returnsStockCommand() {
        // no leading and trailing whitespaces
        StockCommand expectedStockCommand =
                new StockCommand(new NameContainsKeywordsPredicate(Arrays.asList("Flour", "Egg")));
        assertParseSuccess(parser, "Flour Egg", expectedStockCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Flour \n \t Egg  \t", expectedStockCommand);
    }

    @Test
    public void parse_noArgs_returnsStockCommand() {
        StockCommand expectedStockCommand = new StockCommand(PREDICATE_SHOW_ALL_INGREDIENTS);
        assertParseSuccess(parser, " ", expectedStockCommand);
    }

}
