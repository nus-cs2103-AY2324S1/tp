package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.getTypicalScheduleList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddLessonCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteLessonCommand;
import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.EditLessonCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterLessonCommand;
import seedu.address.logic.commands.FilterPersonCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.LinkCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.NavigateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.state.State;


public class AddressBookParserTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalScheduleList());
    private final AddressBookParser parser = new AddressBookParser(model);

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_addPerson() throws Exception {
        assertTrue(parser.parseCommand(AddPersonCommand.COMMAND_WORD
                + " -name Yiwen") instanceof AddPersonCommand);
        assertTrue(parser.parseCommand(AddPersonCommand.COMMAND_WORD
                + " -name Yiwen -phone 12345678") instanceof AddPersonCommand);
    }

    @Test
    public void parseCommand_addLesson() throws Exception {
        assertTrue(parser.parseCommand(AddLessonCommand.COMMAND_WORD
                        + " -name Yiwen -start 14:30 -end 17:30") instanceof AddLessonCommand);
    }
    @Test
    public void parseCommand_overloadedAdd() throws Exception {
        model.setState(State.STUDENT);
        assertTrue(parser.parseCommand("add -name name") instanceof AddPersonCommand);
        model.setState(State.SCHEDULE);
        assertTrue(parser.parseCommand("add -name name") instanceof AddLessonCommand);
        model.setState(State.TASK);
        assertThrows(ParseException.class, () -> parser.parseCommand("add -name name"));
    }

    @Test
    public void parseCommand_addTask() throws Exception {
        assertTrue(parser.parseCommand(AddTaskCommand.COMMAND_WORD
                        + " 1 description") instanceof AddTaskCommand);
        assertTrue(parser.parseCommand("addtask description") instanceof AddTaskCommand);
        AddTaskCommand addTaskCommand = (AddTaskCommand) parser.parseCommand("addtask 1 description");
        assertEquals(addTaskCommand.getTask().getDescription(), "description");
        assertEquals(addTaskCommand.getIndex(), 1);
        addTaskCommand = (AddTaskCommand) parser.parseCommand("addtask description");
        assertEquals(addTaskCommand.getTask().getDescription(), "description");
        assertNull(addTaskCommand.getIndex());
    }
    @Test
    public void parseCommand_editPerson() throws Exception {
        assertTrue(parser
                .parseCommand(EditPersonCommand.COMMAND_WORD + " 1 -name Yiwen") instanceof EditPersonCommand);
        assertTrue(parser
                .parseCommand(EditPersonCommand.COMMAND_WORD
                        + " -name Yiwen -phone 12345678") instanceof EditPersonCommand);
    }

    @Test
    public void parseCommand_editLesson() throws Exception {
        assertTrue(parser
                .parseCommand(EditLessonCommand.COMMAND_WORD + " 1 -name lesson1") instanceof EditLessonCommand);
        assertTrue(parser
                .parseCommand(EditLessonCommand.COMMAND_WORD
                        + " -name lesson1 -day 20") instanceof EditLessonCommand);
    }

    @Test
    public void parseCommand_overloadedEdit() throws Exception {
        model.setState(State.STUDENT);
        assertTrue(parser.parseCommand("edit 1 -name name") instanceof EditPersonCommand);
        model.setState(State.SCHEDULE);
        assertTrue(parser.parseCommand("edit 1 -name name") instanceof EditLessonCommand);
        model.setState(State.TASK);
        assertThrows(ParseException.class, () -> parser.parseCommand("edit 1 -name name"));
    }

    @Test
    public void parseCommand_deletePerson() throws Exception {
        DeletePersonCommand command = (DeletePersonCommand) parser.parseCommand(
                DeletePersonCommand.COMMAND_WORD + " 1");
        assertEquals(new DeletePersonCommand(1), command);
    }

    @Test
    public void parseCommand_deleteLesson() throws Exception {
        assertTrue(parser.parseCommand(DeleteLessonCommand.COMMAND_WORD
               + " 1") instanceof DeleteLessonCommand);
    }
    @Test
    public void parseCommand_deleteTask() throws Exception {
        assertTrue(parser.parseCommand(DeleteTaskCommand.COMMAND_WORD
                + " 1") instanceof DeleteTaskCommand);
    }
    @Test
    public void parseCommand_overloadedDelete() throws Exception {
        model.setState(State.STUDENT);
        assertTrue(parser.parseCommand("delete 1") instanceof DeletePersonCommand);
        model.setState(State.SCHEDULE);
        assertTrue(parser.parseCommand("delete 1") instanceof DeleteLessonCommand);
        model.setState(State.TASK);
        assertThrows(ParseException.class, () -> parser.parseCommand("delete 1"));
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        String keyword = "foo";
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keyword);
        assertEquals(new FindCommand(keyword), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " STUDENTS") instanceof ListCommand);
    }
    @Test
    public void parseCommand_filter() throws Exception {
        model.setState(State.STUDENT);
        assertTrue(parser.parseCommand("filter -name name") instanceof FilterPersonCommand);
        model.setState(State.SCHEDULE);
        assertTrue(parser.parseCommand("filter -name name") instanceof FilterLessonCommand);
        model.setState(State.TASK);
        assertThrows(ParseException.class, () -> parser.parseCommand("filter -name name"));
    }

    @Test
    public void parseCommand_link_linkTo() throws Exception {
        assertTrue(parser.parseCommand("link -lesson 1 -student 1") instanceof LinkCommand);
        model.setState(State.STUDENT);
        model.showPerson(model.getFilteredPersonList().get(0));
        assertTrue(parser.parseCommand("linkto 1") instanceof LinkCommand);
        model.setState(State.SCHEDULE);
        model.showLesson(model.getFilteredScheduleList().get(0));
        assertTrue(parser.parseCommand("linkto 1") instanceof LinkCommand);
    }

    @Test
    public void parseCommand_nav() throws Exception {
        assertTrue(parser.parseCommand("nav") instanceof NavigateCommand);
        assertTrue(parser.parseCommand("navigate") instanceof NavigateCommand);
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
