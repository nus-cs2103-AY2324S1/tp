package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_FIELDS;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListMeetingCommand;
import seedu.address.logic.commands.MarkMeetingCommand;
import seedu.address.logic.commands.RemoveMeetingContactCommand;
import seedu.address.logic.commands.ViewContactCommand;
import seedu.address.logic.commands.ViewMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.AttendeeContainsKeywordsPredicate;
import seedu.address.model.meeting.GeneralMeetingPredicate;
import seedu.address.model.meeting.LocationContainsKeywordsPredicate;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingTagContainsKeywordsPredicate;
import seedu.address.model.meeting.MeetingTimeContainsPredicate;
import seedu.address.model.meeting.TitleContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.GeneralPersonPredicate;
import seedu.address.model.person.LastContactTimeContainsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonTagContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsPredicate;
import seedu.address.model.person.StatusContainsKeywordsPredicate;
import seedu.address.testutil.EditMeetingDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.MeetingUtil;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addm() throws Exception {
        Meeting meeting = new MeetingBuilder().build();
        AddMeetingCommand command = (AddMeetingCommand) parser.parseCommand(MeetingUtil.getAddMeetingCommand(meeting));
        assertEquals(new AddMeetingCommand(meeting), command);
    }

    @Test
    public void parseCommand_addc() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);

        assertThrows(ParseException.class, MESSAGE_INVALID_FIELDS, (
                ) -> parser.parseCommand(ClearCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_deletec() throws Exception {
        DeleteCommand command = (DeleteCommand) parser
                .parseCommand(DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_editc() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        String str = EditCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
                + PersonUtil.getEditPersonDescriptorDetails(descriptor);
        EditCommand command = (EditCommand) parser.parseCommand(str);
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_editm() throws Exception {
        Meeting meeting = new MeetingBuilder().build();
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(meeting).build();
        String str = EditMeetingCommand.COMMAND_WORD + " " + INDEX_FIRST_MEETING.getOneBased() + " "
                + MeetingUtil.getEditMeetingDescriptorDetails(descriptor);
        EditMeetingCommand command = (EditMeetingCommand) parser.parseCommand(str);
        assertEquals(new EditMeetingCommand(INDEX_FIRST_MEETING, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);

        assertThrows(ParseException.class, MESSAGE_INVALID_FIELDS, (
                ) -> parser.parseCommand(ExitCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_findc() throws Exception {
        LocalDateTime lastContacted = DateTimeUtil.parse("20.09.2023 1000");
        FindCommand command = (FindCommand) parser
                .parseCommand(FindCommand.COMMAND_WORD + " n/Alice p/913 e/gmail lc/20.09.2023 1000 s/Active t/friend");
        assertEquals(new FindCommand(
                preparePersonPredicate(new String[] { "Alice", "913", "gmail", "Active", "friend" }, lastContacted)),
                command);
    }

    @Test
    public void parseCommand_findm() throws Exception {
        LocalDateTime start = DateTimeUtil.parse("20.09.2023 1000");
        LocalDateTime end = DateTimeUtil.parse("20.09.2023 1200");
        FindMeetingCommand command = (FindMeetingCommand) parser.parseCommand(FindMeetingCommand.COMMAND_WORD
                + " m/CS2103T a/Zoom s/20.09.2023 1000 e/20.09.2023 1200 n/Alice Bob t/friend");
        assertEquals(
                new FindMeetingCommand(
                        prepareMeetingPredicate(new String[] { "CS2103T", "Zoom", "Alice Bob", "friend" }, start, end)),
                command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);

        assertThrows(ParseException.class, MESSAGE_INVALID_FIELDS, (
                ) -> parser.parseCommand(HelpCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_listc() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);

        assertThrows(ParseException.class, MESSAGE_INVALID_FIELDS, (
                ) -> parser.parseCommand(ListCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_listm() throws Exception {
        assertTrue(parser.parseCommand(ListMeetingCommand.COMMAND_WORD) instanceof ListMeetingCommand);

        assertThrows(ParseException.class, MESSAGE_INVALID_FIELDS, (
                ) -> parser.parseCommand(ListMeetingCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_viewc() throws Exception {
        assertTrue(parser.parseCommand(ViewContactCommand.COMMAND_WORD + " 3") instanceof ViewContactCommand);
    }

    @Test
    public void parseCommand_viewm() throws Exception {
        assertTrue(parser.parseCommand(ViewMeetingCommand.COMMAND_WORD + " 3") instanceof ViewMeetingCommand);
    }

    @Test
    public void parseCommand_rmmc() throws Exception {
        assertTrue(parser.parseCommand(
                RemoveMeetingContactCommand.COMMAND_WORD + " 1 1") instanceof RemoveMeetingContactCommand);
    }

    @Test
    public void parseCommand_mark() throws Exception {
        assertTrue(parser.parseCommand(MarkMeetingCommand.COMMAND_WORD + " 1") instanceof MarkMeetingCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_COMMAND_FORMAT + "\n" + HelpCommand.MESSAGE_USAGE, (
                ) -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    /**
     * Parses {@code userInput} into a {@code GeneralMeetingPredicate}.
     */
    private GeneralMeetingPredicate prepareMeetingPredicate(String[] userInput, LocalDateTime start,
            LocalDateTime end) {
        return new GeneralMeetingPredicate(new TitleContainsKeywordsPredicate(List.of(userInput[0].split("\\s+"))),
                new LocationContainsKeywordsPredicate(List.of(userInput[1].split("\\s+"))),
                new MeetingTimeContainsPredicate(start, end),
                new AttendeeContainsKeywordsPredicate(List.of(userInput[2].split("\\s+"))),
                new MeetingTagContainsKeywordsPredicate(List.of(userInput[3].split("\\s+"))));
    }

    /**
     * Parses {@code userInput} into a {@code GeneralPersonPredicate}.
     */
    private GeneralPersonPredicate preparePersonPredicate(String[] userInput, LocalDateTime lastContacted) {
        return new GeneralPersonPredicate(new NameContainsKeywordsPredicate(List.of(userInput[0].split("\\s+"))),
                new PhoneContainsPredicate(List.of(userInput[1].split("\\s+"))),
                new EmailContainsKeywordsPredicate(List.of(userInput[2].split("\\s+"))),
                new LastContactTimeContainsPredicate(lastContacted),
                new StatusContainsKeywordsPredicate(List.of(userInput[3].split("\\s+"))),
                new PersonTagContainsKeywordsPredicate(List.of(userInput[4].split("\\s+"))));
    }
}
