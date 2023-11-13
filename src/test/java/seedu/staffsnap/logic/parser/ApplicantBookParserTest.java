package seedu.staffsnap.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.staffsnap.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.staffsnap.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.staffsnap.testutil.Assert.assertThrows;
import static seedu.staffsnap.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.logic.commands.AddCommand;
import seedu.staffsnap.logic.commands.AddInterviewCommand;
import seedu.staffsnap.logic.commands.DeleteCommand;
import seedu.staffsnap.logic.commands.EditCommand;
import seedu.staffsnap.logic.commands.EditCommand.EditApplicantDescriptor;
import seedu.staffsnap.logic.commands.ExitCommand;
import seedu.staffsnap.logic.commands.FilterCommand;
import seedu.staffsnap.logic.commands.FindCommand;
import seedu.staffsnap.logic.commands.HelpCommand;
import seedu.staffsnap.logic.commands.ListCommand;
import seedu.staffsnap.logic.commands.SortCommand;
import seedu.staffsnap.logic.parser.exceptions.ParseException;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.NameContainsKeywordsPredicate;
import seedu.staffsnap.testutil.ApplicantBuilder;
import seedu.staffsnap.testutil.ApplicantUtil;
import seedu.staffsnap.testutil.EditApplicantDescriptorBuilder;

public class ApplicantBookParserTest {

    private final ApplicantBookParser parser = new ApplicantBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Applicant applicant = new ApplicantBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ApplicantUtil.getAddCommand(applicant));
        assertEquals(new AddCommand(applicant), command);
    }
    /*
    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    */

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command =
                (DeleteCommand) parser.parseCommand(DeleteCommand.COMMAND_WORD + " "
                        + INDEX_FIRST_APPLICANT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_APPLICANT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Applicant applicant = new ApplicantBuilder().build();
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(applicant).build();
        EditCommand command =
                (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                        + INDEX_FIRST_APPLICANT.getOneBased() + " "
                        + ApplicantUtil.getEditApplicantDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_APPLICANT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command =
                (FindCommand) parser.parseCommand(FindCommand.COMMAND_WORD + " " + keywords.stream()
                        .collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
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
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " " + "d/ name") instanceof SortCommand);
    }

    @Test
    public void parseCommand_filter() throws Exception {
        assertTrue(parser.parseCommand(FilterCommand.COMMAND_WORD + " " + "n/ name") instanceof FilterCommand);
    }

    @Test
    public void parseCommand_addi() throws Exception {
        assertTrue(parser.parseCommand(AddInterviewCommand.COMMAND_WORD + " 1 " + "t/ technical")
                instanceof AddInterviewCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
