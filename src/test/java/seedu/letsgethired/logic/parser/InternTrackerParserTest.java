package seedu.letsgethired.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.letsgethired.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_NOTE_DELETE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_NOTE_INSERT;
import static seedu.letsgethired.logic.parser.SortOrder.DESCENDING;
import static seedu.letsgethired.testutil.Assert.assertThrows;
import static seedu.letsgethired.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import seedu.letsgethired.commons.core.index.Index;
import seedu.letsgethired.logic.commands.AddCommand;
import seedu.letsgethired.logic.commands.ClearCommand;
import seedu.letsgethired.logic.commands.DeleteCommand;
import seedu.letsgethired.logic.commands.EditCommand;
import seedu.letsgethired.logic.commands.EditCommand.EditInternApplicationDescriptor;
import seedu.letsgethired.logic.commands.ExitCommand;
import seedu.letsgethired.logic.commands.FindCommand;
import seedu.letsgethired.logic.commands.HelpCommand;
import seedu.letsgethired.logic.commands.ListCommand;
import seedu.letsgethired.logic.commands.NoteCommand;
import seedu.letsgethired.logic.commands.NoteDeleteCommand;
import seedu.letsgethired.logic.commands.NoteInsertCommand;
import seedu.letsgethired.logic.commands.SortCommand;
import seedu.letsgethired.logic.commands.UndoCommand;
import seedu.letsgethired.logic.commands.ViewCommand;
import seedu.letsgethired.logic.parser.exceptions.ParseException;
import seedu.letsgethired.model.application.CompanyContainsFieldKeywordsPredicate;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.model.application.InternApplicationComparator;
import seedu.letsgethired.model.application.Note;
import seedu.letsgethired.testutil.EditInternApplicationDescriptorBuilder;
import seedu.letsgethired.testutil.InternApplicationBuilder;
import seedu.letsgethired.testutil.InternApplicationUtil;

public class InternTrackerParserTest {

    private final InternTrackerParser parser = new InternTrackerParser();

    @Test
    public void parseCommand_add() throws Exception {
        InternApplication internApplication = new InternApplicationBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(InternApplicationUtil.getAddCommand(internApplication));
        assertEquals(new AddCommand(internApplication), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_APPLICATION.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_APPLICATION), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        InternApplication internApplication = new InternApplicationBuilder().build();
        EditInternApplicationDescriptor descriptor =
                new EditInternApplicationDescriptorBuilder(internApplication).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_APPLICATION.getOneBased() + " "
                + InternApplicationUtil.getEditInternApplicationDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_APPLICATION, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        String searchString = "Jane Street";
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_COMPANY + searchString);
        assertEquals(new FindCommand(new CompanyContainsFieldKeywordsPredicate(
                Arrays.asList(new Pair<>(PREFIX_COMPANY, searchString)))), command);
    }

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
    public void parseCommand_sort() throws Exception {
        String sortStringDescending = "d";
        SortCommand command = (SortCommand) parser.parseCommand(
                SortCommand.COMMAND_WORD + " " + PREFIX_COMPANY + sortStringDescending);
        assertEquals(new SortCommand(InternApplicationComparator.getComparator(PREFIX_COMPANY, DESCENDING)),
                command);
    }

    @Test
    public void parseCommand_insertNote() throws Exception {
        final Note note = new Note("Some note.");
        NoteCommand command = (NoteCommand) parser.parseCommand(NoteCommand.COMMAND_WORD + " "
                + INDEX_FIRST_APPLICATION.getOneBased() + " " + PREFIX_NOTE_INSERT + note);
        assertEquals(new NoteInsertCommand(INDEX_FIRST_APPLICATION, note), command);
    }

    @Test
    public void parseCommand_deleteNote() throws Exception {
        NoteCommand command = (NoteCommand) parser.parseCommand(NoteCommand.COMMAND_WORD + " "
                + INDEX_FIRST_APPLICATION.getOneBased() + " " + PREFIX_NOTE_DELETE + 1);
        assertEquals(new NoteDeleteCommand(INDEX_FIRST_APPLICATION, Index.fromOneBased(1)), command);
    }

    @Test
    public void parseCommand_view() throws Exception {
        assertTrue(parser.parseCommand(ViewCommand.COMMAND_WORD + " "
                + INDEX_FIRST_APPLICATION.getOneBased()) instanceof ViewCommand);
    }

    @Test
    public void parseCommand_undo() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
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
