package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNASSIGN_GROUPS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNASSIGN_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteEventCommand;
import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.commands.EditEventCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindAllCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAllCommand;
import seedu.address.logic.commands.ListEventsCommand;
import seedu.address.logic.commands.ListPersonsCommand;
import seedu.address.logic.commands.RemindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventNameOrGroupContainsKeywordsPredicate;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.Meeting;
import seedu.address.model.group.Group;
import seedu.address.model.person.BirthdayWithinDaysPredicate;
import seedu.address.model.person.EventWithinDaysPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonNameOrGroupContainsKeywordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add_person() throws Exception {
        Person person = new PersonBuilder().build();
        AddPersonCommand command = (AddPersonCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddPersonCommand(person), command);
    }

    @Test
    public void parseCommand_add_event() throws Exception {
        AddEventCommand command = (AddEventCommand) parser.parseCommand(
                AddEventCommand.COMMAND_WORD + " " + PREFIX_EVENT_NAME + "FumbleLog Meeting"
                        + " " + PREFIX_DATE + "2023-12-30" + " " + PREFIX_START_TIME + "1000"
                        + " " + PREFIX_END_TIME + "1200");
        EventName eventName = new EventName("FumbleLog Meeting");
        EventDate date = new EventDate("2023-12-30");
        EventTime startTime = EventTime.of("1000");
        EventTime endTime = EventTime.of("1200");
        Set<Name> nameList = new HashSet<>();
        Set<Group> groupList = new HashSet<>();
        Meeting meeting = new Meeting(eventName, date, Optional.of(startTime),
                Optional.of(endTime), nameList, groupList);
        assertEquals(new AddEventCommand(meeting), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete_person() throws Exception {
        DeletePersonCommand command = (DeletePersonCommand) parser.parseCommand(
                DeletePersonCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeletePersonCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_delete_event() throws Exception {
        DeleteEventCommand command = (DeleteEventCommand) parser.parseCommand(
                DeleteEventCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteEventCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit_person() throws Exception {
        Person person = new PersonBuilder().withGroups("friends").build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditPersonCommand command = (EditPersonCommand) parser.parseCommand(EditPersonCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditPersonCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_edit_event() throws Exception {
        String userInput = PREFIX_EVENT_NAME + " " + VALID_EVENT_NAME + " " + PREFIX_DATE + " "
                + "2021-12-12" + " " + PREFIX_START_TIME + " " + "1400" + " " + PREFIX_END_TIME + " " + "1500" + " "
                + PREFIX_GROUP + " " + "friends" + " " + PREFIX_UNASSIGN_GROUPS + " " + "groups" + " "
                + PREFIX_NAME + " " + "Alice" + " " + PREFIX_UNASSIGN_PERSONS + " " + "Ben";
        EditEventCommand.EditEventDescriptor descriptor = new EditEventCommand.EditEventDescriptor();
        descriptor.setName(new EventName(VALID_EVENT_NAME));
        descriptor.setDate(new EventDate("2021-12-12"));
        descriptor.setStartTime(EventTime.of("1400"));
        descriptor.setEndTime(EventTime.of("1500"));
        descriptor.setGroups(new HashSet<>(Set.of(new Group("friends"))));
        descriptor.setUnassignGroups(new HashSet<>(Set.of(new Group("groups"))));
        descriptor.setPersonNames(new HashSet<>(Set.of(new Name("Alice"))));
        descriptor.setUnassignPersons(new HashSet<>(Set.of(new Name("Ben"))));

        EditEventCommand command = (EditEventCommand) parser.parseCommand(EditEventCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + userInput);
        assertEquals(new EditEventCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindAllCommand command = (FindAllCommand) parser.parseCommand(
                FindAllCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindAllCommand(new PersonNameOrGroupContainsKeywordsPredicate(keywords),
                new EventNameOrGroupContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list_persons() throws Exception {
        assertTrue(parser.parseCommand(ListPersonsCommand.COMMAND_WORD) instanceof ListPersonsCommand);
        assertTrue(parser.parseCommand(ListPersonsCommand.COMMAND_WORD + " 3") instanceof ListPersonsCommand);
    }

    @Test
    public void parseCommand_list_all() throws Exception {
        assertTrue(parser.parseCommand(ListAllCommand.COMMAND_WORD) instanceof ListAllCommand);
        assertTrue(parser.parseCommand(ListAllCommand.COMMAND_WORD + " 3") instanceof ListAllCommand);
    }

    @Test
    public void parseCommmand_list_events() throws Exception {
        assertTrue(parser.parseCommand(ListEventsCommand.COMMAND_WORD) instanceof ListEventsCommand);
        assertTrue(parser.parseCommand(ListEventsCommand.COMMAND_WORD + " 3") instanceof ListEventsCommand);
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

    @Test
    public void parseCommand_remind() throws Exception {
        RemindCommand command = (RemindCommand) parser.parseCommand(
                RemindCommand.COMMAND_WORD + " " + 1);
        BirthdayWithinDaysPredicate birthdayPredicate = new BirthdayWithinDaysPredicate(1);
        EventWithinDaysPredicate eventPredicate = new EventWithinDaysPredicate(1);
        assertEquals(new RemindCommand(birthdayPredicate, eventPredicate, 1), command);

        RemindCommand defaultCommand = (RemindCommand) parser.parseCommand(
                RemindCommand.COMMAND_WORD + " ");
        BirthdayWithinDaysPredicate birthdayPredicateDefault = new BirthdayWithinDaysPredicate(7);
        EventWithinDaysPredicate eventPredicateDefault = new EventWithinDaysPredicate(7);
        assertEquals(new RemindCommand(birthdayPredicateDefault, eventPredicateDefault, 7), defaultCommand);
    }

}
