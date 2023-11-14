package seedu.address.logic.parser.events;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.tab.Tab.FINANCE_PARAMETER;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.DateTimeParser.parseDateTimeInstance;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.tab.Tab;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.TabCommand;
import seedu.address.logic.commands.events.AddEventCommand;
import seedu.address.logic.commands.events.ClearEventsCommand;
import seedu.address.logic.commands.events.DeleteEventCommand;
import seedu.address.logic.commands.events.FilterEventClientCommand;
import seedu.address.logic.commands.events.FilterEventNameCommand;
import seedu.address.logic.commands.events.FilterEventTimeCommand;
import seedu.address.logic.commands.events.ListEventsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.EventsBook;
import seedu.address.model.FinancesBook;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventClientContainsKeywordsPredicate;
import seedu.address.model.event.EventNameContainsKeywordsPredicate;
import seedu.address.model.event.EventTimeBeforePredicate;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.EventUtil;

public class EventParserTest {

    private final EventParser parser = new EventParser();
    private final ModelManager model = new ModelManager(getTypicalPersonsBook(), new EventsBook(), new FinancesBook(),
            new UserPrefs());

    @Test
    public void parseCommand_add() throws Exception {
        Event event = new EventBuilder().withClient(ALICE).build();
        AddEventCommand command = (AddEventCommand) parser.parseCommand(EventUtil.getAddCommand(event));
        ModelManager expectedModel = new ModelManager(getTypicalPersonsBook(), new EventsBook(), new FinancesBook(),
                new UserPrefs());

        assertEquals(new AddEventCommand(event).execute(model), command.execute(expectedModel));
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearEventsCommand.COMMAND_WORD) instanceof ClearEventsCommand);
        assertTrue(parser.parseCommand(ClearEventsCommand.COMMAND_WORD + " 3") instanceof ClearEventsCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteEventCommand command = (DeleteEventCommand) parser.parseCommand(
                DeleteEventCommand.COMMAND_WORD + " " + INDEX_FIRST_EVENT.getOneBased());
        assertEquals(new DeleteEventCommand(INDEX_FIRST_EVENT), command);
    }

    @Test
    public void parseCommand_filterEventTime() throws Exception {
        String args = "01-01-2023";
        String hourMinArgs = args.trim() + " 00:00";
        FilterEventTimeCommand command = (FilterEventTimeCommand) parser.parseCommand(
                FilterEventTimeCommand.COMMAND_WORD + " " + args);

        assertEquals(new FilterEventTimeCommand(new EventTimeBeforePredicate(
                parseDateTimeInstance(hourMinArgs))), command);
    }

    @Test
    public void parseCommand_filterEventName() throws Exception {
        String args = "keyword1 keyword2";
        FilterEventNameCommand command = (FilterEventNameCommand) parser.parseCommand(
                FilterEventNameCommand.COMMAND_WORD + " " + args);

        assertEquals(new FilterEventNameCommand(new EventNameContainsKeywordsPredicate(
                Arrays.asList(args.split("\\s+")))), command);
    }

    @Test
    public void parseCommand_filterEventClient() throws Exception {
        String args = "keyword1 keyword2";
        FilterEventClientCommand command = (FilterEventClientCommand) parser.parseCommand(
                FilterEventClientCommand.COMMAND_WORD + " " + args);

        assertEquals(new FilterEventClientCommand(new EventClientContainsKeywordsPredicate(
                Arrays.asList(args.split("\\s+")))), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListEventsCommand.COMMAND_WORD) instanceof ListEventsCommand);
        assertTrue(parser.parseCommand(ListEventsCommand.COMMAND_WORD + " 3") instanceof ListEventsCommand);
    }

    @Test
    public void parseCommand_tabCommand() throws ParseException {
        TabCommand command = (TabCommand) parser.parseCommand(
                TabCommand.COMMAND_WORD + " " + FINANCE_PARAMETER);
        assertEquals(new TabCommand(Tab.fromParameter(FINANCE_PARAMETER)), command);
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
