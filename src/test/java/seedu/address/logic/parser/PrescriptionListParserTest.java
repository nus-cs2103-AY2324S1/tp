package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRESCRIPTION;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListCompletedCommand;
import seedu.address.logic.commands.ListTodayCommand;
import seedu.address.logic.commands.TakeCommand;
import seedu.address.logic.commands.UntakeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.prescription.NameContainsKeywordsPredicate;
import seedu.address.model.prescription.Prescription;
import seedu.address.testutil.PrescriptionBuilder;
import seedu.address.testutil.PrescriptionUtil;

public class PrescriptionListParserTest {

    private final PrescriptionListParser parser = new PrescriptionListParser();

    @Test
    public void parseCommand_add() throws Exception {
        Prescription prescription = new PrescriptionBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(
            PrescriptionUtil.getAddPrescriptionCommand(prescription));
        assertEquals(new AddCommand(prescription), command);
    }

    @Test
    public void parseCommand_take() throws Exception {
        assertTrue(parser.parseCommand(TakeCommand.COMMAND_WORD + " 1" + " d/5") instanceof TakeCommand);
    }

    @Test
    public void parseCommand_untake() throws Exception {
        assertTrue(parser.parseCommand(UntakeCommand.COMMAND_WORD + " 1" + " d/5") instanceof UntakeCommand);
    }

    @Test
    public void parseCommand_listCompleted() throws Exception {
        assertTrue(parser.parseCommand(ListCompletedCommand.COMMAND_WORD) instanceof ListCompletedCommand);
    }
    @Test
    public void parseCommand_listToday() throws Exception {
        assertTrue(parser.parseCommand(ListTodayCommand.COMMAND_WORD) instanceof ListTodayCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
            DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PRESCRIPTION.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PRESCRIPTION), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
            FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
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
}
