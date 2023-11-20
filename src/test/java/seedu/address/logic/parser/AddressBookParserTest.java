package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_TIME_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TIME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.AMY;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalLeads.ELLE;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddClientCommand;
import seedu.address.logic.commands.AddLeadCommand;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ConvertClientToLeadCommand;
import seedu.address.logic.commands.ConvertLeadToClientCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteMeetingCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditLeadDescriptor;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditLeadCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListClientCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListLeadCommand;
import seedu.address.logic.commands.SortMeetingTimeCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Client;
import seedu.address.model.person.Lead;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.ClientUtil;
import seedu.address.testutil.EditLeadDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.LeadUtil;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;


public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addClient() throws Exception {
        Client client = new PersonBuilder(AMY).buildClient();
        AddClientCommand command = (AddClientCommand) parser.parseCommand(ClientUtil.getAddClientCommand(client));
        assertEquals(new AddClientCommand(client), command);
    }

    @Test
    public void parseCommand_addLead() throws Exception {
        Lead lead = new PersonBuilder(ELLE).withKeyMilestone("01/12/2023").buildLead();
        AddLeadCommand command = (AddLeadCommand) parser.parseCommand(LeadUtil.getAddLeadCommand(lead));
        assertEquals(new AddLeadCommand(lead), command);
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
    //todo: test failed maybe because meeting time is null, should be fixed after merging master

    @Test
    public void parseCommand_deleteMeeting() throws Exception {
        DeleteMeetingCommand command = (DeleteMeetingCommand) parser.parseCommand(
                DeleteMeetingCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteMeetingCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit_withClient() throws Exception {
        Client client = new PersonBuilder().buildClient();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(client).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }
    @Test
    public void parseCommand_edit_withLead() throws Exception {
        Lead lead = new PersonBuilder().buildLead();
        EditLeadDescriptor descriptor = new EditLeadDescriptorBuilder(lead).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditLeadCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
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

    @Test
    public void parseCommand_view() throws Exception {
        ViewCommand command = (ViewCommand) parser.parseCommand(
                ViewCommand.COMMAND_WORD + " " + 3);
        assertEquals(new ViewCommand(Index.fromOneBased(3)), command);
    }
    @Test
    public void parseCommand_listClient() throws Exception {
        assertTrue(parser.parseCommand(ListClientCommand.COMMAND_WORD) instanceof ListClientCommand);
    }
    @Test
    public void parseCommand_listLead() throws Exception {
        assertTrue(parser.parseCommand(ListLeadCommand.COMMAND_WORD) instanceof ListLeadCommand);
    }

    @Test
    public void parseCommand_sortMeetingTime() throws Exception {
        assertTrue(parser.parseCommand(SortMeetingTimeCommand.COMMAND_WORD) instanceof SortMeetingTimeCommand);
    }

    @Test
    public void parseCommand_convertLeadToClient() throws Exception {
        ConvertLeadToClientCommand command = (ConvertLeadToClientCommand) parser.parseCommand(
                ConvertLeadToClientCommand.COMMAND_WORD + " " + 3);
        assertEquals(new ConvertLeadToClientCommand(Index.fromOneBased(3)), command);
    }

    @Test
    public void parseCommand_convertClientToLead() throws Exception {
        ConvertClientToLeadCommand command = (ConvertClientToLeadCommand) parser.parseCommand(
                ConvertClientToLeadCommand.COMMAND_WORD + " " + 3);
        assertEquals(new ConvertClientToLeadCommand(Index.fromOneBased(3)), command);
    }

    @Test
    public void parseCommand_addMeetingTime() throws Exception {
        AddMeetingCommand command = (AddMeetingCommand) parser.parseCommand(
                AddMeetingCommand.COMMAND_WORD + " 4 " + PREFIX_MEETING_TIME + VALID_MEETING_TIME_AMY);
        assertEquals(
                new AddMeetingCommand(Index.fromOneBased(4), new MeetingTime(VALID_MEETING_TIME_AMY)),
                command);
    }
}
