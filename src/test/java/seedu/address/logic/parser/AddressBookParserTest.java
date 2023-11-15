package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalUsers.JOHN;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.AddScheduleCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CommonFreetimeCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RemoveEventCommand;
import seedu.address.logic.commands.RemoveReminderCommand;
import seedu.address.logic.commands.RemoveScheduleCommand;
import seedu.address.logic.commands.SetReminderCommand;
import seedu.address.logic.commands.edit.EditCommand;
import seedu.address.logic.commands.edit.EditPersonDescriptor;
import seedu.address.logic.commands.edit.EditUserCommand;
import seedu.address.logic.commands.edit.EditUserDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.user.User;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditUserDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.UserBuilder;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

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
    public void parseCommand_editUser() throws Exception {
        User user = new UserBuilder(JOHN).build();
        EditUserDescriptor descriptor = new EditUserDescriptorBuilder(user).build();
        EditUserCommand command = (EditUserCommand) parser.parseCommand(EditUserCommand.COMMAND_WORD + " "
                + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditUserCommand(descriptor), command);
    }

    @Test
    public void parseCommand_commonFreetime_validArgs() throws Exception {
        Person person = new PersonBuilder().build();
        CommonFreetimeCommand command = (CommonFreetimeCommand) parser.parseCommand(
                CommonFreetimeCommand.COMMAND_WORD + " " + "1");
        assertEquals(new CommonFreetimeCommand(Index.fromOneBased(1)), command);
    }

    @Test
    public void parseCommand_commonFreetime_all() throws Exception {
        CommonFreetimeCommand command = (CommonFreetimeCommand) parser.parseCommand(
                CommonFreetimeCommand.COMMAND_WORD);
        assertEquals(new CommonFreetimeCommand(), command);
    }

    @Test
    public void parseCommand_setReminder() throws Exception {
        SetReminderCommand command = (SetReminderCommand) parser.parseCommand(
                SetReminderCommand.COMMAND_WORD + " CS2103 Meeting");
        assertEquals(new SetReminderCommand("CS2103 Meeting"), command);
    }

    @Test
    public void parseCommand_removeReminder() throws Exception {
        RemoveReminderCommand command = (RemoveReminderCommand) parser.parseCommand(
                RemoveReminderCommand.COMMAND_WORD + " CS2103 Meeting");
        assertEquals(new RemoveReminderCommand("CS2103 Meeting"), command);
    }

    @Test
    public void parseCommand_addEvent() throws Exception {
        AddEventCommand command = (AddEventCommand) parser.parseCommand(
                AddEventCommand.COMMAND_WORD + " user en/CS2103 Meeting h/2023-10-10 1030 1130 r/y");
        assertEquals(new AddEventCommand("CS2103 MEETING",
                "2023-10-10 1030 1130", "y"), command);
    }

    @Test
    public void parseCommand_addSchedule() throws Exception {
        AddScheduleCommand command = (AddScheduleCommand) parser.parseCommand(
                AddScheduleCommand.COMMAND_WORD + " user type/cca en/Basketball h/Monday 1030 1130");
        assertEquals(new AddScheduleCommand("BASKETBALL", "cca", "Monday 1030 1130"),
                command);
    }

    @Test
    public void parseCommand_removeEvent() throws Exception {
        RemoveEventCommand command = (RemoveEventCommand) parser.parseCommand(
                RemoveEventCommand.COMMAND_WORD + " user en/CS2103 Meeting");
        assertEquals(new RemoveEventCommand("CS2103 MEETING", null), command);
    }

    @Test
    public void parseCommand_removeSchedule() throws Exception {
        RemoveScheduleCommand command = (RemoveScheduleCommand) parser.parseCommand(
                RemoveScheduleCommand.COMMAND_WORD + " user type/cca en/Basketball");
        assertEquals(new RemoveScheduleCommand("BASKETBALL", "cca"), command);
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
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
