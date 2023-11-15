package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPriorities.HIGH_PRIORITY;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddBuyerCommand;
import seedu.address.logic.commands.AddSellerCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteBuyerCommand;
import seedu.address.logic.commands.DeleteSellerCommand;
import seedu.address.logic.commands.EditBuyerCommand;
import seedu.address.logic.commands.EditSellerCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListBuyerCommand;
import seedu.address.logic.commands.ListSellerCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.SetBuyerPriorityCommand;
import seedu.address.logic.commands.SetSellerPriorityCommand;
import seedu.address.logic.commands.SortBuyerCommand;
import seedu.address.logic.commands.SortSellerCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.displayable.NameContainsKeywordsPredicate;
import seedu.address.model.displayable.buyer.Buyer;
import seedu.address.model.displayable.seller.Seller;
import seedu.address.testutil.BuyerBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.SellerBuilder;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addBuyer() throws Exception {
        Buyer buyer = new BuyerBuilder().build();
        AddBuyerCommand command = (AddBuyerCommand) parser.parseCommand(PersonUtil.getAddCommand(buyer));
        assertEquals(new AddBuyerCommand(buyer), command);
    }

    @Test
    public void parseCommand_addSeller() throws Exception {
        Seller seller = new SellerBuilder().build();
        AddSellerCommand command = (AddSellerCommand) parser.parseCommand(PersonUtil.getAddCommand(seller));
        assertEquals(new AddSellerCommand(seller), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteBuyer() throws Exception {
        DeleteBuyerCommand command = (DeleteBuyerCommand) parser.parseCommand(
                DeleteBuyerCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteBuyerCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_deleteSeller() throws Exception {
        DeleteSellerCommand command = (DeleteSellerCommand) parser.parseCommand(
                DeleteSellerCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteSellerCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_filter() throws Exception {
        assertTrue(parser.parseCommand(FilterCommand.COMMAND_WORD + " ABCD") instanceof FilterCommand);
        FilterCommand command = (FilterCommand) parser.parseCommand(FilterCommand.COMMAND_WORD + " ABCD");
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("ABCD");
        assertEquals(new FilterCommand(new NameContainsKeywordsPredicate(arguments)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listBuyer() throws Exception {
        assertTrue(parser.parseCommand(ListBuyerCommand.COMMAND_WORD + " 3") instanceof ListBuyerCommand);
    }

    @Test
    public void parseCommand_listSellers() throws Exception {
        assertTrue(parser.parseCommand(ListSellerCommand.COMMAND_WORD + " 3") instanceof ListSellerCommand);
    }

    @Test
    public void parseCommand_setBuyerPriority() throws Exception {
        SetBuyerPriorityCommand command = (SetBuyerPriorityCommand) parser.parseCommand(
                SetBuyerPriorityCommand.COMMAND_WORD + " "
                        + INDEX_FIRST_PERSON.getOneBased() + " "
                        + HIGH_PRIORITY);
        assertEquals(new SetBuyerPriorityCommand(INDEX_FIRST_PERSON, HIGH_PRIORITY), command);
    }

    @Test
    public void parseCommand_setSellerPriority() throws Exception {
        SetSellerPriorityCommand command = (SetSellerPriorityCommand) parser.parseCommand(
                SetSellerPriorityCommand.COMMAND_WORD + " "
                        + INDEX_FIRST_PERSON.getOneBased() + " "
                        + HIGH_PRIORITY);
        assertEquals(new SetSellerPriorityCommand(INDEX_FIRST_PERSON, HIGH_PRIORITY), command);
    }
    @Test
    public void parseCommand_editBuyerCommand() throws Exception {
        assertTrue(parser
                .parseCommand(EditBuyerCommand.COMMAND_WORD + " 1 n/test") instanceof EditBuyerCommand);
    }
    @Test
    public void parseCommand_editSellerCommand() throws Exception {
        assertTrue(parser
                .parseCommand(EditSellerCommand.COMMAND_WORD + " 1 n/test") instanceof EditSellerCommand);
    }
    @Test
    public void parseCommand_sortBuyerCommand() throws Exception {
        assertTrue(parser.parseCommand(SortBuyerCommand.COMMAND_WORD + " n/a") instanceof SortBuyerCommand);
    }
    @Test
    public void parseCommand_sortSellerCommand() throws Exception {
        assertTrue(parser.parseCommand(SortSellerCommand.COMMAND_WORD + " n/a") instanceof SortSellerCommand);
    }
    @Test
    public void parseCommand_undoCommand() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
    }
    @Test
    public void parseCommand_redoCommand() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
    }
    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_checkCaseInsensitivity() throws Exception {
        assertTrue(parser.parseCommand(ListBuyerCommand.COMMAND_WORD.toLowerCase() + " 3")
                instanceof ListBuyerCommand);
        assertTrue(parser.parseCommand(ListSellerCommand.COMMAND_WORD.toUpperCase() + " 3")
                instanceof ListSellerCommand);
    }

}
