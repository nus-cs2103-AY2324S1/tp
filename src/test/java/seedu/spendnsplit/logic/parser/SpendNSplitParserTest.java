package seedu.spendnsplit.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.spendnsplit.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.spendnsplit.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.spendnsplit.logic.parser.CliSyntax.PREFIX_ORIGINAL_COMMAND;
import static seedu.spendnsplit.logic.parser.CliSyntax.PREFIX_SHORTHAND;
import static seedu.spendnsplit.testutil.Assert.assertThrows;
import static seedu.spendnsplit.testutil.PortionUtil.getPortionDescriptorDetails;
import static seedu.spendnsplit.testutil.TransactionUtil.getTransactionDescriptorDetails;
import static seedu.spendnsplit.testutil.TypicalIndexes.INDEX_FIRST_ELEMENT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.spendnsplit.logic.commands.AddPersonCommand;
import seedu.spendnsplit.logic.commands.AddTransactionCommand;
import seedu.spendnsplit.logic.commands.ClearCommand;
import seedu.spendnsplit.logic.commands.DeletePersonCommand;
import seedu.spendnsplit.logic.commands.DeleteTransactionCommand;
import seedu.spendnsplit.logic.commands.EditPersonCommand;
import seedu.spendnsplit.logic.commands.EditTransactionCommand;
import seedu.spendnsplit.logic.commands.ExitCommand;
import seedu.spendnsplit.logic.commands.HelpCommand;
import seedu.spendnsplit.logic.commands.ListPersonCommand;
import seedu.spendnsplit.logic.commands.ListTransactionCommand;
import seedu.spendnsplit.logic.commands.SetShorthandCommand;
import seedu.spendnsplit.logic.commands.SortPersonCommand;
import seedu.spendnsplit.logic.commands.UpdatePortionCommand;
import seedu.spendnsplit.logic.descriptors.PersonDescriptor;
import seedu.spendnsplit.logic.descriptors.PortionDescriptor;
import seedu.spendnsplit.logic.descriptors.TransactionDescriptor;
import seedu.spendnsplit.logic.parser.exceptions.ParseException;
import seedu.spendnsplit.model.person.Name;
import seedu.spendnsplit.model.person.NameContainsKeywordsPredicate;
import seedu.spendnsplit.model.person.Person;
import seedu.spendnsplit.model.transaction.Transaction;
import seedu.spendnsplit.model.transaction.TransactionContainsKeywordsAndPersonNamesPredicate;
import seedu.spendnsplit.model.transaction.portion.Portion;
import seedu.spendnsplit.testutil.PersonBuilder;
import seedu.spendnsplit.testutil.PersonDescriptorBuilder;
import seedu.spendnsplit.testutil.PersonUtil;
import seedu.spendnsplit.testutil.PortionBuilder;
import seedu.spendnsplit.testutil.PortionDescriptorBuilder;
import seedu.spendnsplit.testutil.TransactionBuilder;
import seedu.spendnsplit.testutil.TransactionDescriptorBuilder;

public class SpendNSplitParserTest {

    private final SpendNSplitParser parser = new SpendNSplitParser();

    @Test
    public void parseCommand_addPerson() throws Exception {
        Person person = new PersonBuilder().build();
        AddPersonCommand command = (AddPersonCommand) parser
            .parseCommand(PersonUtil.getAddPersonCommand(person), new CommandAliasMap());
        assertEquals(new AddPersonCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, new CommandAliasMap()) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3", new CommandAliasMap())
            instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deletePerson() throws Exception {
        DeletePersonCommand command = (DeletePersonCommand) parser.parseCommand(
            DeletePersonCommand.COMMAND_WORD + " " + INDEX_FIRST_ELEMENT.getOneBased(), new CommandAliasMap());
        assertEquals(new DeletePersonCommand(INDEX_FIRST_ELEMENT), command);
    }

    @Test
    public void parseCommand_editPerson() throws Exception {
        Person person = new PersonBuilder().build();
        PersonDescriptor descriptor = new PersonDescriptorBuilder(person).build();
        EditPersonCommand command = (EditPersonCommand) parser.parseCommand(EditPersonCommand.COMMAND_WORD + " "
            + INDEX_FIRST_ELEMENT.getOneBased() + " "
            + PersonUtil.getPersonDescriptorDetails(descriptor), new CommandAliasMap());
        assertEquals(new EditPersonCommand(INDEX_FIRST_ELEMENT, descriptor), command);
    }

    @Test
    public void parseCommand_sortPerson() throws Exception {
        assertTrue(parser.parseCommand(SortPersonCommand.COMMAND_WORD
            + " +", new CommandAliasMap()) instanceof SortPersonCommand);
        assertTrue(parser.parseCommand(SortPersonCommand.COMMAND_WORD
            + " -", new CommandAliasMap()) instanceof SortPersonCommand);
    }

    @Test
    public void parseCommand_listPerson() throws Exception {
        assertTrue(parser.parseCommand(ListPersonCommand.COMMAND_WORD, new CommandAliasMap())
            instanceof ListPersonCommand);
        assertTrue(parser.parseCommand(ListPersonCommand.COMMAND_WORD + " Alice", new CommandAliasMap())
            instanceof ListPersonCommand);
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        ListPersonCommand command = (ListPersonCommand) parser.parseCommand(
            ListPersonCommand.COMMAND_WORD + " " + keywords.stream()
                .collect(Collectors.joining(" ")), new CommandAliasMap());
        assertEquals(new ListPersonCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, new CommandAliasMap()) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", new CommandAliasMap()) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, new CommandAliasMap()) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", new CommandAliasMap()) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listTransaction() throws Exception {
        ListTransactionCommand command = (ListTransactionCommand) parser.parseCommand(
            ListTransactionCommand.COMMAND_WORD + " " + NAME_DESC_AMY + " " + NAME_DESC_BOB, new CommandAliasMap());
        TransactionContainsKeywordsAndPersonNamesPredicate predicate =
                new TransactionContainsKeywordsAndPersonNamesPredicate(List.of(),
                List.of(new Name(VALID_NAME_AMY), new Name(VALID_NAME_BOB)));
        assertEquals(new ListTransactionCommand(predicate), command);
    }

    @Test
    public void parseCommand_addTransaction() throws Exception {
        assertTrue(parser.parseCommand(AddTransactionCommand.COMMAND_WORD
            + " d=bread n=Bob c=20.00 n=self w=1", new CommandAliasMap()) instanceof AddTransactionCommand);
        assertTrue(parser.parseCommand(AddTransactionCommand.COMMAND_WORD
            + " d=bread n=Bob c=20.00 ts=10/10/2020 12:00 n=self w=1", new CommandAliasMap())
            instanceof AddTransactionCommand);
    }

    @Test
    public void parseCommand_editTransaction() throws Exception {
        Transaction transaction = new TransactionBuilder().build();
        TransactionDescriptor descriptor = new TransactionDescriptorBuilder(transaction)
            .withoutTimestamp().build();
        EditTransactionCommand command = (EditTransactionCommand) parser.parseCommand(
            EditTransactionCommand.COMMAND_WORD + " " + INDEX_FIRST_ELEMENT.getOneBased() + " "
                + getTransactionDescriptorDetails(descriptor), new CommandAliasMap());
        assertEquals(new EditTransactionCommand(INDEX_FIRST_ELEMENT, descriptor), command);
    }

    @Test
    public void parseCommand_deleteTransaction() throws Exception {
        DeleteTransactionCommand command = (DeleteTransactionCommand) parser.parseCommand(
            DeleteTransactionCommand.COMMAND_WORD + " " + INDEX_FIRST_ELEMENT.getOneBased(), new CommandAliasMap());
        assertEquals(new DeleteTransactionCommand(INDEX_FIRST_ELEMENT), command);
    }

    @Test
    public void parseCommand_setShorthandCommand() throws Exception {
        SetShorthandCommand command = (SetShorthandCommand) parser.parseCommand(
            SetShorthandCommand.COMMAND_WORD + " "
                + PREFIX_ORIGINAL_COMMAND + " " + DeleteTransactionCommand.COMMAND_WORD + " "
                + PREFIX_SHORTHAND + " " + "dt", new CommandAliasMap());
        assertEquals(new SetShorthandCommand(DeleteTransactionCommand.COMMAND_WORD, "dt"), command);
    }

    @Test
    public void parseCommand_updatePortion() throws Exception {
        Portion portion = new PortionBuilder().build();
        PortionDescriptor descriptor = new PortionDescriptorBuilder(portion).build();
        UpdatePortionCommand command = (UpdatePortionCommand) parser.parseCommand(
            UpdatePortionCommand.COMMAND_WORD + " " + INDEX_FIRST_ELEMENT.getOneBased() + " "
                + getPortionDescriptorDetails(descriptor), new CommandAliasMap());
        assertEquals(new UpdatePortionCommand(INDEX_FIRST_ELEMENT, descriptor), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand("", new CommandAliasMap()));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> parser.parseCommand("unknownCommand", new CommandAliasMap()));
    }
}
