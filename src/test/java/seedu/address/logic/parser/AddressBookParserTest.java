package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.APPOINTMENT_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.APPOINTMENT_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.FINANCIAL_PLAN_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FINANCIAL_PLAN_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CompleteByDate;
import seedu.address.logic.commands.CompleteByIndex;
import seedu.address.logic.commands.CompleteCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.GatherCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.person.gatheremail.GatherEmailByFinancialPlan;
import seedu.address.model.person.gatheremail.GatherEmailByTag;
import seedu.address.model.person.predicates.CombinedPredicate;
import seedu.address.model.person.predicates.FinancialPlanContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

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
        System.out.println(descriptor);
        System.out.println(PersonUtil.getEditPersonDescriptorDetails(descriptor));
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
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
                FindCommand.COMMAND_WORD + " "
                        + keywords.stream().map(name -> PREFIX_NAME + name).collect(Collectors.joining(" ")));
        assertEquals(
                new FindCommand(
                        new CombinedPredicate(
                                new FinancialPlanContainsKeywordsPredicate(List.of()),
                                new NameContainsKeywordsPredicate(keywords),
                                new TagContainsKeywordsPredicate(List.of()))),
                command);
    }

    @Test
    public void parseCommand_gather() throws Exception {
        // financial plan
        GatherEmailByFinancialPlan fpPrompt = new GatherEmailByFinancialPlan(VALID_FINANCIAL_PLAN_1);
        GatherCommand fpCommand = (GatherCommand) parser.parseCommand(
                GatherCommand.COMMAND_WORD + FINANCIAL_PLAN_DESC_1);
        assertEquals(new GatherCommand(fpPrompt), fpCommand);

        // tag
        GatherEmailByTag tagPrompt = new GatherEmailByTag(VALID_TAG_HUSBAND);
        GatherCommand tagCommand = (GatherCommand) parser.parseCommand(
                GatherCommand.COMMAND_WORD + TAG_DESC_HUSBAND);
        assertEquals(new GatherCommand(tagPrompt), tagCommand);
    }

    @Test
    public void parseCommand_schedule() throws Exception {
        ScheduleCommand command = (ScheduleCommand) parser.parseCommand(
                ScheduleCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased()
                        + APPOINTMENT_NAME_DESC + APPOINTMENT_DATE_DESC);
        assertEquals(new ScheduleCommand(INDEX_FIRST_PERSON, new Appointment(VALID_APPOINTMENT_NAME,
                LocalDateTime.of(2023, 1, 1, 20, 0))), command);
    }

    @Test
    public void parseCommand_complete() throws Exception {
        CompleteCommand commandIndex = (CompleteCommand) parser.parseCommand(CompleteCommand.COMMAND_WORD
                + " " + INDEX_FIRST_PERSON.getOneBased());

        CompleteCommand commandDate = (CompleteCommand) parser.parseCommand(CompleteCommand.COMMAND_WORD
                + " " + PREFIX_APPOINTMENT_DATE + " " + "01-01-2023");

        assertEquals(new CompleteByIndex(INDEX_FIRST_PERSON), commandIndex);
        assertEquals(new CompleteByDate(LocalDate.of(2023, 1, 1)), commandDate);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " " + "name") instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " " + "appointment") instanceof SortCommand);
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
