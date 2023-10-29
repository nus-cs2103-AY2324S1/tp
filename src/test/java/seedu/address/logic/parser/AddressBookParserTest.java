package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.ApplicantContainsKeywordsPredicate;
import seedu.address.model.person.MemberContainsKeywordsPredicate;
import seedu.address.testutil.ApplicantBuilder;
import seedu.address.testutil.ApplicantUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    //    @Test
    //    public void parseCommand_add() throws Exception {
    //        Person person = new PersonBuilder().build();
    //        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
    //        assertEquals(new AddCommand(person), command);
    //    }

    // TODO: add parseCommand_addMember
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
    public void parseCommand__deleteApplicant() throws Exception {
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

    // TODO: adapt for editMember and editApplicant
    //    @Test
    //    public void parseCommand_edit() throws Exception {
    //        Person person = new PersonBuilder().build();
    //        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
    //        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
    //                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
    //        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    //    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    // TODO: adapt for findMember and findApplicant
    //    @Test
    //    public void parseCommand_find() throws Exception {
    //        List<String> keywords = Arrays.asList("foo", "bar", "baz");
    //        FindCommand command = (FindCommand) parser.parseCommand(
    //                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
    //        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    //    }

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

    // TODO: adapt for ViewMemberCommand and ViewApplicantCommand
    //    @Test
    //    public void parseCommand_list() throws Exception {
    //        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
    //        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    //    }

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
