package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ModifyCommand;
import seedu.address.logic.commands.RecipeViewCommand;
import seedu.address.logic.commands.SearchCommand;
import seedu.address.logic.commands.StockCommand;
import seedu.address.logic.commands.UseCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class InventoryAppParserTest {

    private final InventoryAppParser parser = new InventoryAppParser();
    /*
    @Test
    public void parseCommand_add() throws Exception {
        Ingredient ingredient = new IngredientBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(IngredientUtil.getAddCommand(ingredient));
        assertEquals(new AddCommand(ingredient), command);
    }*/

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }
    /*
    @Test
    public void parseCommand_edit() throws Exception {
        Ingredient ingredient = new IngredientBuilder().build();
        EditIngredientDescriptor descriptor = new EditIngredientDescriptorBuilder(ingredient).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_INGREDIENT.getOneBased() + " " +
                IngredientUtil.getEditIngredientDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_INGREDIENT, descriptor), command);
    }*/

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }
    /*
    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }*/

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_stock() throws Exception {
        assertTrue(parser.parseCommand(StockCommand.COMMAND_WORD) instanceof StockCommand);
        assertTrue(parser.parseCommand(StockCommand.COMMAND_WORD + " flour") instanceof StockCommand);
    }

    @Test
    public void parseCommand_use() throws Exception {
        assertTrue(parser.parseCommand(UseCommand.COMMAND_WORD + " n/milk") instanceof UseCommand);
        assertTrue(parser.parseCommand(UseCommand.COMMAND_WORD + " n/milk q/600 u/g ") instanceof UseCommand);
    }

    @Test
    public void parseCommand_add() throws Exception {
        assertTrue(parser.parseCommand(AddCommand.COMMAND_WORD + " n/milk q/600 u/g") instanceof AddCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        assertTrue(parser.parseCommand(DeleteCommand.COMMAND_WORD + " 1") instanceof DeleteCommand);
    }

    @Test
    public void parseCommand_modify() throws Exception {
        assertTrue(parser.parseCommand(ModifyCommand.COMMAND_WORD + " i/1 n/milk q/600 u/g")
                instanceof ModifyCommand);
    }

    @Test
    public void parseCommand_view() throws Exception {
        assertTrue(parser.parseCommand(RecipeViewCommand.COMMAND_WORD + " 1")
                instanceof RecipeViewCommand);
    }

    @Test
    public void parseCommand_search() throws Exception {
        assertTrue(parser.parseCommand(SearchCommand.COMMAND_WORD + " flour")
                instanceof SearchCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseCommand("unknownCommand"));
    }
}
