package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESCRIPTION;
import static seedu.address.logic.parser.ParserUtil.DATE_TIME_STRING_FORMATTER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddContactEventCommand;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ClearEventsCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteContactEventCommand;
import seedu.address.logic.commands.DeleteEventCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditContactEventCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortTasksCommand;
import seedu.address.logic.commands.SwitchListCommand;
import seedu.address.logic.commands.ViewContactEventsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventPeriod;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.EventPeriodBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class UniMateParserTest {

    private final UniMateParser parser = new UniMateParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_editContactEvent() throws Exception {
        Person person = new PersonBuilder().withCalendar().build();
        Event event = person.getCalendar().getEventList().get(INDEX_FIRST_EVENT.getOneBased());
        EditContactEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder(event).build();
        EditContactEventCommand command = (EditContactEventCommand)
                parser.parseCommand(EditContactEventCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_EVENT.getOneBased() + " "
                        + PersonUtil.getEditPersonEventDetails(descriptor));
        ArrayList<Index> expectedIndexArray = new ArrayList<>();
        expectedIndexArray.add(INDEX_FIRST_PERSON);
        expectedIndexArray.add(INDEX_FIRST_EVENT);
        assertEquals(new EditContactEventCommand(expectedIndexArray, descriptor), command);
    }

    @Test
    public void parseCommand_editContactEvent2() throws Exception {
        EditContactEventCommand command = (EditContactEventCommand)
                parser.parseCommand(EditContactEventCommand.COMMAND_WORD + " "
                        + INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_EVENT.getOneBased() + " "
                        + PREFIX_EVENT_DESCRIPTION + " sleep");
        assertTrue(command instanceof EditContactEventCommand);
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
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
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
    public void parseCommand_addEvent() throws Exception {
        String description = "sleep";
        String startDateTime = "2023-10-10 10:00";
        String endDateTime = "2023-10-10 12:00";
        String validArg = "d/" + description
                + " ts/" + startDateTime
                + " te/" + endDateTime;
        validArg = AddEventCommand.COMMAND_WORD + " " + validArg;
        EventBuilder eventBuilder = new EventBuilder();
        eventBuilder.withDescription(description);
        eventBuilder.withStartEndDate(startDateTime, endDateTime);

        AddEventCommand addEventCommand = (AddEventCommand) parser.parseCommand(validArg);
        assertEquals(addEventCommand, new AddEventCommand(eventBuilder.build()));
    }

    @Test
    public void parseCommand_sort() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " "
                + SortCommand.SORTBY_KEYWORD1) instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " "
                + SortCommand.SORTBY_KEYWORD2) instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " "
                + SortCommand.SORTBY_KEYWORD3 + " " + SortCommand.REVERSE_KEYWORD) instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " "
                + SortCommand.SORTBY_KEYWORD4 + " " + SortCommand.REVERSE_KEYWORD) instanceof SortCommand);
    }

    @Test
    public void parserCommand_addContactEvent() throws Exception {
        String description = "sleep";
        String startDateTime = "2023-10-10 10:00";
        String endDateTime = "2023-10-10 12:00";
        String validArg = "d/" + description
                + " ts/" + startDateTime
                + " te/" + endDateTime;
        EventBuilder eventBuilder = new EventBuilder();
        eventBuilder.withDescription(description);
        eventBuilder.withStartEndDate(startDateTime, endDateTime);
        AddContactEventCommand command =
                (AddContactEventCommand) parser.parseCommand(AddContactEventCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + validArg);

        assertEquals(command, new AddContactEventCommand(INDEX_FIRST_PERSON, eventBuilder.build()));
    }

    @Test
    public void parserCommand_deleteContactEvent() throws Exception {
        String targetDateTime = "2023-10-10 10:00";
        LocalDateTime localDateTime = LocalDateTime.parse(targetDateTime, DATE_TIME_STRING_FORMATTER);
        String validArg = "ts/" + targetDateTime;
        DeleteContactEventCommand command =
                (DeleteContactEventCommand) parser.parseCommand(DeleteContactEventCommand.COMMAND_WORD + " "
                        + INDEX_FIRST_PERSON.getOneBased() + " " + validArg);

        assertEquals(command, new DeleteContactEventCommand(INDEX_FIRST_PERSON, localDateTime));
    }

    @Test
    public void parserCommand_deleteEvent() throws Exception {
        String targetDateTime = "2023-10-10 10:00";
        String validArg = " " + targetDateTime;
        DeleteEventCommand deleteEventCommand =
                new DeleteEventCommand(LocalDateTime.parse("2023-10-10 10:00", DATE_TIME_STRING_FORMATTER));

        assertEquals(deleteEventCommand,
                (DeleteEventCommand) parser.parseCommand(DeleteEventCommand.COMMAND_WORD + validArg));
    }

    @Test
    public void parserCommand_clearEvents() throws Exception {
        String startDateTime = "2023-10-10 10:00";
        String endDateTime = "2023-10-10 12:00";
        String validArg = " ts/" + startDateTime
                + " te/" + endDateTime
                + " c/CONFIRMED";

        EventPeriod period = new EventPeriodBuilder().changeStartAndEnd(startDateTime, endDateTime).build();

        ClearEventsCommand clearEventsCommand =
                new ClearEventsCommand(period, true);

        assertEquals(clearEventsCommand,
                (ClearEventsCommand) parser.parseCommand(ClearEventsCommand.COMMAND_WORD + validArg));
    }

    @Test
    public void parserCommand_addTask() throws Exception {
        String description = "A";
        String validArg = " d/" + description;

        AddTaskCommand addTaskCommand = new AddTaskCommand(new Task(description, null));

        assertEquals(addTaskCommand,
                (AddTaskCommand) parser.parseCommand(AddTaskCommand.COMMAND_WORD + validArg));
    }

    @Test
    public void parserCommand_switchList() throws Exception {
        String validArg = " 1231231";

        SwitchListCommand switchListCommand = new SwitchListCommand();

        assertTrue(parser.parseCommand(SwitchListCommand.COMMAND_WORD + validArg)
                instanceof SwitchListCommand);
    }

    @Test
    public void parserCommand_sortTasks() throws Exception {
        String comparatorType = "Description";
        String validArg = " " + comparatorType;

        SortTasksCommand sortTasksCommand = new SortTasksCommand(comparatorType);

        assertEquals(sortTasksCommand,
                (SortTasksCommand) parser.parseCommand(SortTasksCommand.COMMAND_WORD + validArg));
    }

    @Test
    public void parseCommand_deleteTask() throws Exception {
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(
                DeleteTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteTaskCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_viewContactEvents() throws Exception {
        ViewContactEventsCommand command = (ViewContactEventsCommand) parser.parseCommand(
                ViewContactEventsCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new ViewContactEventsCommand(INDEX_FIRST_PERSON), command);
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
