package seedu.ccacommander.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ccacommander.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ccacommander.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.ccacommander.testutil.Assert.assertThrows;
import static seedu.ccacommander.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.ccacommander.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.logic.commands.ClearCommand;
import seedu.ccacommander.logic.commands.CreateEventCommand;
import seedu.ccacommander.logic.commands.CreateMemberCommand;
import seedu.ccacommander.logic.commands.DeleteEventCommand;
import seedu.ccacommander.logic.commands.DeleteMemberCommand;
import seedu.ccacommander.logic.commands.EditCommand;
import seedu.ccacommander.logic.commands.EditCommand.EditMemberDescriptor;
import seedu.ccacommander.logic.commands.ExitCommand;
import seedu.ccacommander.logic.commands.FindCommand;
import seedu.ccacommander.logic.commands.HelpCommand;
import seedu.ccacommander.logic.commands.ListCommand;
import seedu.ccacommander.logic.parser.exceptions.ParseException;
import seedu.ccacommander.model.event.Event;
import seedu.ccacommander.model.member.Member;
import seedu.ccacommander.model.member.MemberNameContainsKeywordsPredicate;
import seedu.ccacommander.testutil.EditMemberDescriptorBuilder;
import seedu.ccacommander.testutil.EventBuilder;
import seedu.ccacommander.testutil.EventUtil;
import seedu.ccacommander.testutil.MemberBuilder;
import seedu.ccacommander.testutil.MemberUtil;


public class AddressBookParserTest {

    private final CcaCommanderParser parser = new CcaCommanderParser();

    @Test
    public void parseCommand_createMember() throws Exception {
        Member member = new MemberBuilder().build();
        CreateMemberCommand command = (CreateMemberCommand) parser.parseCommand(
                MemberUtil.getCreateMemberCommand(member));
        assertEquals(new CreateMemberCommand(member), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteMember() throws Exception {
        DeleteMemberCommand command = (DeleteMemberCommand) parser.parseCommand(
                DeleteMemberCommand.COMMAND_WORD + " " + INDEX_FIRST_MEMBER.getOneBased());
        assertEquals(new DeleteMemberCommand(INDEX_FIRST_MEMBER), command);
    }

    public void parseCommand_createEvent() throws Exception {
        Event event = new EventBuilder().build();
        CreateEventCommand command = (CreateEventCommand) parser.parseCommand(
                EventUtil.getCreateEventCommand(event));
        assertEquals(new CreateEventCommand(event), command);
    }

    @Test
    public void parseCommand_deleteEvent() throws Exception {
        DeleteEventCommand command = (DeleteEventCommand) parser.parseCommand(
                DeleteEventCommand.COMMAND_WORD + " " + INDEX_FIRST_EVENT.getOneBased());
        assertEquals(new DeleteEventCommand(INDEX_FIRST_EVENT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Member member = new MemberBuilder().build();
        EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder(member).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_MEMBER.getOneBased() + " " + MemberUtil.getEditMemberDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_MEMBER, descriptor), command);
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
        assertEquals(new FindCommand(new MemberNameContainsKeywordsPredicate(keywords)), command);
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
