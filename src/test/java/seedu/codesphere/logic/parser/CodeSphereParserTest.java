package seedu.codesphere.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codesphere.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.codesphere.testutil.Assert.assertThrows;
import static seedu.codesphere.testutil.TypicalCourses.CS1101S;
import static seedu.codesphere.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.codesphere.logic.commands.AddCourseCommand;
import seedu.codesphere.logic.commands.ClearCourseCommand;
import seedu.codesphere.logic.commands.DeleteCourseCommand;
import seedu.codesphere.logic.commands.EditCommand;
import seedu.codesphere.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.codesphere.logic.commands.EditCourseCommand;
import seedu.codesphere.logic.commands.EditCourseCommand.EditCourseDescriptor;
import seedu.codesphere.logic.commands.ExitCommand;
import seedu.codesphere.logic.commands.FindCommand;
import seedu.codesphere.logic.commands.FindCourseCommand;
import seedu.codesphere.logic.commands.HelpCommand;
import seedu.codesphere.logic.commands.HomeCommand;
import seedu.codesphere.logic.commands.RemarkCommand;
import seedu.codesphere.logic.commands.ResetCommand;
import seedu.codesphere.logic.commands.ResetCourseCommand;
import seedu.codesphere.logic.parser.exceptions.ParseException;
import seedu.codesphere.logic.stagemanager.StageManager;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.course.CourseNameContainsKeywordsPredicate;
import seedu.codesphere.model.student.Remark;
import seedu.codesphere.model.student.Student;
import seedu.codesphere.model.student.predicates.NameContainsKeywordsPredicate;
import seedu.codesphere.testutil.CourseBuilder;
import seedu.codesphere.testutil.CourseUtil;
import seedu.codesphere.testutil.EditCourseDescriptorBuilder;
import seedu.codesphere.testutil.EditStudentDescriptorBuilder;
import seedu.codesphere.testutil.StudentBuilder;
import seedu.codesphere.testutil.StudentUtil;

public class CodeSphereParserTest {

    private final CodeSphereParser parser = new CodeSphereParser();

    @Test
    public void parseCommand_addCourse() throws Exception {
        // set HOME stage
        StageManager.getInstance().setHomeStage();

        Course course = new CourseBuilder().build();
        AddCourseCommand command = (AddCourseCommand) parser.parseCommand(CourseUtil.getAddCourseCommand(course));
        assertEquals(new AddCourseCommand(course), command);
    }

    @Test
    public void parseCommand_clearCourse() throws Exception {
        // set HOME stage
        StageManager.getInstance().setHomeStage();
        assertTrue(parser.parseCommand(ClearCourseCommand.COMMAND_WORD) instanceof ClearCourseCommand);
    }

    @Test
    public void parseCommand_deleteCourse() throws Exception {
        // set HOME stage
        StageManager.getInstance().setHomeStage();
        DeleteCourseCommand command = (DeleteCourseCommand) parser.parseCommand(
                DeleteCourseCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new DeleteCourseCommand(INDEX_FIRST_STUDENT), command);
    }

    @Test
    public void parseCommand_editCourse() throws Exception {
        // set HOME stage
        StageManager.getInstance().setHomeStage();
        Course course = new CourseBuilder().build();
        EditCourseDescriptor descriptor = new EditCourseDescriptorBuilder(course).build();
        EditCourseCommand command = (EditCourseCommand) parser.parseCommand(
                EditCourseCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased() + " "
                        + CourseUtil.getEditCourseDescriptorDetails(descriptor));
        assertEquals(new EditCourseCommand(INDEX_FIRST_STUDENT, descriptor), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        // set COURSE stage
        StageManager.getInstance().setCourseStage(CS1101S);
        Student student = new StudentBuilder().build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_STUDENT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        // set HOME stage
        StageManager.getInstance().setHomeStage();
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);

        // set COURSE stage
        StageManager.getInstance().setCourseStage(CS1101S);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findCourse() throws Exception {
        // set HOME stage
        StageManager.getInstance().setHomeStage();
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCourseCommand command = (FindCourseCommand) parser.parseCommand(
                FindCourseCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCourseCommand(new CourseNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_find() throws Exception {
        // set COURSE stage
        StageManager.getInstance().setCourseStage(CS1101S);
        List<String> keywords = Arrays.asList("foo");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " n/" + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        // set HOME stage
        StageManager.getInstance().setHomeStage();
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);

        // set COURSE stage
        StageManager.getInstance().setCourseStage(CS1101S);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_resetCourses() throws Exception {
        // set HOME stage
        StageManager.getInstance().setHomeStage();
        assertTrue(parser.parseCommand(ResetCourseCommand.COMMAND_WORD) instanceof ResetCourseCommand);
    }

    @Test
    public void parseCommand_reset() throws Exception {
        // set COURSE stage
        StageManager.getInstance().setCourseStage(CS1101S);
        assertTrue(parser.parseCommand(ResetCommand.COMMAND_WORD) instanceof ResetCommand);
    }

    @Test
    public void parseCommand_home() throws Exception {
        // set HOME stage
        StageManager.getInstance().setHomeStage();
        assertTrue(parser.parseCommand(HomeCommand.COMMAND_WORD) instanceof HomeCommand);

        // set COURSE stage
        StageManager.getInstance().setCourseStage(CS1101S);
        assertTrue(parser.parseCommand(ResetCommand.COMMAND_WORD) instanceof ResetCommand);
    }

    @Test
    public void parseCommand_remark() throws Exception {
        // set COURSE stage
        StageManager.getInstance().setCourseStage(CS1101S);
        final Remark remark = new Remark("Some remark.");
        RemarkCommand command = (RemarkCommand) parser.parseCommand(RemarkCommand.COMMAND_WORD + " "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + PREFIX_REMARK + remark.value);
        assertEquals(new RemarkCommand(INDEX_FIRST_STUDENT, remark), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        // set HOME stage
        StageManager.getInstance().setHomeStage();
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));

        // set COURSE stage
        StageManager.getInstance().setCourseStage(CS1101S);
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));

    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        // set HOME stage
        StageManager.getInstance().setHomeStage();
        assertThrows(ParseException.class,
                MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));

        // set COURSE stage
        StageManager.getInstance().setCourseStage(CS1101S);
        assertThrows(ParseException.class,
                MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));

    }
}
