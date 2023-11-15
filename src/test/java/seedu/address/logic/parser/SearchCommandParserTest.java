package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SearchCommand;
import seedu.address.model.recipe.RecipeIngredientNameMatchesPredicate;

public class SearchCommandParserTest {
    private SearchCommandParser parser = new SearchCommandParser();


    @Test
    public void parse_validArgs_returnsStockCommand() {
        // no leading and trailing whitespaces
        SearchCommand expectedSearchCommand =
                new SearchCommand(new RecipeIngredientNameMatchesPredicate("Flour"));
        assertParseSuccess(parser, "Flour", expectedSearchCommand);

        // multiple whitespaces
        assertParseSuccess(parser, " \n Flour \t", expectedSearchCommand);
    }

    @Test
    public void parse_noArgs_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
    }
}
