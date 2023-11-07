package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddApplicantCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CopyApplicantCommand;
import seedu.address.logic.commands.CopyMemberCommand;
import seedu.address.logic.commands.DeleteApplicantCommand;
import seedu.address.logic.commands.DeleteMemberCommand;
import seedu.address.logic.commands.EditApplicantCommand;
import seedu.address.logic.commands.EditApplicantCommand.EditApplicantDescriptor;
import seedu.address.logic.commands.EditMemberCommand;
import seedu.address.logic.commands.EditMemberCommand.EditMemberDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindApplicantCommand;
import seedu.address.logic.commands.FindMemberCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ViewMembersCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.ApplicantContainsKeywordsPredicate;
import seedu.address.model.person.Member;
import seedu.address.model.person.MemberContainsKeywordsPredicate;
import seedu.address.testutil.ApplicantBuilder;
import seedu.address.testutil.ApplicantUtil;
import seedu.address.testutil.EditApplicantDescriptorBuilder;
import seedu.address.testutil.EditMemberDescriptorBuilder;
import seedu.address.testutil.MemberBuilder;
import seedu.address.testutil.MemberUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addApplicant() throws Exception {
        Applicant applicant = new ApplicantBuilder().build();
        AddApplicantCommand command =
                (AddApplicantCommand) parser.parseCommand(ApplicantUtil.getAddApplicantCommand(applicant));
        assertEquals(new AddApplicantCommand(applicant), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteApplicant() throws Exception {
        DeleteApplicantCommand commandWord = (DeleteApplicantCommand) parser.parseCommand(
                DeleteApplicantCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        DeleteApplicantCommand commandAlias = (DeleteApplicantCommand) parser.parseCommand(
                DeleteApplicantCommand.COMMAND_ALIAS + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteApplicantCommand(INDEX_FIRST_PERSON), commandWord);
        assertEquals(new DeleteApplicantCommand(INDEX_FIRST_PERSON), commandAlias);
    }

    @Test
    public void parseCommand_commandWord_deleteMember() throws Exception {
        DeleteMemberCommand commandWord = (DeleteMemberCommand) parser.parseCommand(
                DeleteMemberCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        DeleteMemberCommand commandAlias = (DeleteMemberCommand) parser.parseCommand(
                DeleteMemberCommand.COMMAND_ALIAS + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteMemberCommand(INDEX_FIRST_PERSON), commandWord);
        assertEquals(new DeleteMemberCommand(INDEX_FIRST_PERSON), commandAlias);
    }

    @Test
    public void parseCommand_editMember() throws Exception {
        Member member = new MemberBuilder().build();
        EditMemberDescriptor descriptor = new EditMemberDescriptorBuilder(member).build();
        EditMemberCommand command = (EditMemberCommand) parser.parseCommand(EditMemberCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + MemberUtil.getEditMemberDescriptorDetails(descriptor));
        assertEquals(new EditMemberCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_editApplicant() throws Exception {
        Applicant applicant = new ApplicantBuilder().build();
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(applicant).build();
        EditApplicantCommand command = (EditApplicantCommand) parser.parseCommand(
                EditApplicantCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + ApplicantUtil.getEditApplicantDescriptorDetails(descriptor));
        assertEquals(new EditApplicantCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findApplicant() throws Exception {
        List<String> keywords = Arrays.asList("hello", "world", "wassup");
        FindApplicantCommand commandWord = (FindApplicantCommand) parser.parseCommand(
                FindApplicantCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        FindApplicantCommand commandAlias = (FindApplicantCommand) parser.parseCommand(
                FindApplicantCommand.COMMAND_ALIAS + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindApplicantCommand(new ApplicantContainsKeywordsPredicate(keywords)), commandWord);
        assertEquals(new FindApplicantCommand(new ApplicantContainsKeywordsPredicate(keywords)), commandAlias);
    }

    @Test
    public void parseCommand_findMember() throws Exception {
        List<String> keywords = Arrays.asList("hello", "world", "wassup");
        FindMemberCommand commandWord = (FindMemberCommand) parser.parseCommand(
                FindMemberCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        FindMemberCommand commandAlias = (FindMemberCommand) parser.parseCommand(
                FindMemberCommand.COMMAND_ALIAS + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindMemberCommand(new MemberContainsKeywordsPredicate(keywords)), commandWord);
        assertEquals(new FindMemberCommand(new MemberContainsKeywordsPredicate(keywords)), commandAlias);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_viewMember() throws Exception {
        assertTrue(parser.parseCommand(ViewMembersCommand.COMMAND_WORD) instanceof ViewMembersCommand);
        assertTrue(parser.parseCommand(ViewMembersCommand.COMMAND_WORD + " 3") instanceof ViewMembersCommand);
    }

    @Test
    public void parseCommand_viewApplicant() throws Exception {
        assertTrue(parser.parseCommand(ViewMembersCommand.COMMAND_WORD) instanceof ViewMembersCommand);
        assertTrue(parser.parseCommand(ViewMembersCommand.COMMAND_WORD + " 3") instanceof ViewMembersCommand);
    }

    @Test
    public void parseCommand_copyMember() throws Exception {
        CopyMemberCommand command = (CopyMemberCommand) parser.parseCommand(
                CopyMemberCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new CopyMemberCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_copyApplicant() throws Exception {
        CopyApplicantCommand command = (CopyApplicantCommand) parser.parseCommand(
                CopyApplicantCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new CopyApplicantCommand(INDEX_FIRST_PERSON), command);
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
