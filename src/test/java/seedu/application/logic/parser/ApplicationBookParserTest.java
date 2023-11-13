package seedu.application.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.application.logic.parser.CliSyntax.*;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.application.logic.commands.*;
import seedu.application.logic.commands.EditCommand.EditJobDescriptor;
import seedu.application.logic.commands.SortCommand;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.job.CombinedPredicate;
import seedu.application.model.job.FieldContainsKeywordsPredicate;
import seedu.application.model.job.Job;
import seedu.application.testutil.EditJobDescriptorBuilder;
import seedu.application.testutil.JobBuilder;
import seedu.application.testutil.JobUtil;

public class ApplicationBookParserTest {

    private final ApplicationBookParser parser = new ApplicationBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Job job = new JobBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(JobUtil.getAddCommand(job));
        assertEquals(new AddCommand(job), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
            DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_interview() throws Exception {
        assertTrue(parser.parseCommand("interview delete 1 from/2") instanceof InterviewCommand);
    }
    @Test
    public void parseCommand_edit() throws Exception {
        Job job = new JobBuilder().build();
        EditJobDescriptor descriptor = new EditJobDescriptorBuilder(job).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
            + INDEX_FIRST.getOneBased() + " " + JobUtil.getEditJobDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
            FindCommand.COMMAND_WORD + " " + PREFIX_ROLE
                + " " + keywords.stream().collect(Collectors.joining(" ")));
        FieldContainsKeywordsPredicate fieldPredicate = new FieldContainsKeywordsPredicate(PREFIX_ROLE, keywords);
        CombinedPredicate combinedPredicate = new CombinedPredicate(List.of(fieldPredicate));
        assertEquals(new FindCommand(combinedPredicate), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }
    @Test
    public void parseCommand_sort() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " "
                + PREFIX_COMPANY) instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " "
                + PREFIX_ROLE) instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " "
                + PREFIX_STATUS) instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " "
                + PREFIX_INDUSTRY) instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " "
                + PREFIX_DEADLINE) instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " "
                + PREFIX_JOB_TYPE) instanceof SortCommand);
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
