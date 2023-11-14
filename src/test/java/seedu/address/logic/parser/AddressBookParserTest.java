package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SCHEDULE;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddScheduleCommand;
import seedu.address.logic.commands.AddTutorCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteScheduleCommand;
import seedu.address.logic.commands.DeleteTutorCommand;
import seedu.address.logic.commands.EditScheduleCommand;
import seedu.address.logic.commands.EditScheduleCommand.EditScheduleDescriptor;
import seedu.address.logic.commands.EditTutorCommand;
import seedu.address.logic.commands.EditTutorCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindScheduleCommand;
import seedu.address.logic.commands.FindTutorCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListScheduleCommand;
import seedu.address.logic.commands.ListTutorCommand;
import seedu.address.logic.commands.MarkScheduleCommand;
import seedu.address.logic.commands.ShowCalendarCommand;
import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.commands.UnmarkScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Date;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.Status;
import seedu.address.model.schedule.TutorNameContainsKeywordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditScheduleDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.ScheduleBuilder;
import seedu.address.testutil.ScheduleUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddTutorCommand command = (AddTutorCommand) parser.parseCommand(PersonUtil.getAddTutorCommand(person));
        assertEquals(new AddTutorCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteTutorCommand command = (DeleteTutorCommand) parser.parseCommand(
                DeleteTutorCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteTutorCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditTutorCommand command = (EditTutorCommand) parser.parseCommand(
                EditTutorCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
                        + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditTutorCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindTutorCommand command = (FindTutorCommand) parser.parseCommand(
                FindTutorCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindTutorCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_theme() throws Exception {
        String theme = "dark";
        String themePath = "/view/DarkTheme.css";
        ThemeCommand command = (ThemeCommand) parser.parseCommand(ThemeCommand.COMMAND_WORD + " " + theme);
        assertEquals(new ThemeCommand(themePath, theme), command);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListTutorCommand.COMMAND_WORD) instanceof ListTutorCommand);
        assertTrue(parser.parseCommand(ListTutorCommand.COMMAND_WORD + " 3") instanceof ListTutorCommand);
    }

    @Test
    public void parseCommand_addSchedule() throws Exception {
        Schedule schedule = new ScheduleBuilder().build();
        AddScheduleCommand command =
                (AddScheduleCommand) parser.parseCommand(ScheduleUtil.getAddScheduleCommand(schedule));
        assertEquals(new AddScheduleCommand(INDEX_FIRST_PERSON, schedule.getStartTime(), schedule.getEndTime()),
                command);
    }

    @Test
    public void parseCommand_editSchedule() throws Exception {
        Schedule schedule = new ScheduleBuilder().build();
        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder(schedule).build();
        EditScheduleCommand expectedCommand = (EditScheduleCommand) parser.parseCommand(EditScheduleCommand.COMMAND_WORD
            + " " + INDEX_FIRST_SCHEDULE.getOneBased() + " "
            + ScheduleUtil.getEditScheduleDescriptorDetails(descriptor));
        assertEquals(new EditScheduleCommand(INDEX_FIRST_SCHEDULE, descriptor), expectedCommand);
    }

    @Test
    public void parseCommand_markSchedule() throws Exception {
        Schedule schedule = new ScheduleBuilder().withStatus(Status.MISSED).build();
        MarkScheduleCommand command = (MarkScheduleCommand) parser.parseCommand(
                MarkScheduleCommand.COMMAND_WORD + " " + INDEX_FIRST_SCHEDULE.getOneBased() + " "
                        + PREFIX_STATUS + Status.parseStatusToString(schedule.getStatus()));
        assertEquals(new MarkScheduleCommand(INDEX_FIRST_SCHEDULE, schedule.getStatus()), command);
    }

    @Test
    public void parseCommand_unmarkSchedule() throws Exception {
        UnmarkScheduleCommand command = (UnmarkScheduleCommand) parser.parseCommand(
            UnmarkScheduleCommand.COMMAND_WORD + " " + INDEX_FIRST_SCHEDULE.getOneBased());
        assertEquals(new UnmarkScheduleCommand(INDEX_FIRST_SCHEDULE), command);
    }

    @Test
    public void parseCommand_listSchedule() throws Exception {
        assertTrue(parser.parseCommand(ListScheduleCommand.COMMAND_WORD) instanceof ListScheduleCommand);
        assertTrue(parser.parseCommand(ListScheduleCommand.COMMAND_WORD + " 1") instanceof ListScheduleCommand);
    }

    @Test
    public void parseCommand_findSchedule() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindScheduleCommand command = (FindScheduleCommand) parser.parseCommand(
            FindScheduleCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindScheduleCommand(new TutorNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_deleteSchedule() throws Exception {
        DeleteScheduleCommand command = (DeleteScheduleCommand) parser.parseCommand(
            DeleteScheduleCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteScheduleCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_showCalendar() throws Exception {
        ShowCalendarCommand command = (ShowCalendarCommand) parser.parseCommand(
            ShowCalendarCommand.COMMAND_WORD + " 2023-09-15");
        assertEquals(new ShowCalendarCommand(new Date(LocalDate.of(2023, 9, 15))), command);
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
