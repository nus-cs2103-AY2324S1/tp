package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNCLEAR_COMMAND;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PLAN;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddPlanCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CompletePlanCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeletePlanCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindPlanCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListPlanCommand;
import seedu.address.logic.commands.UncompletePlanCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.plan.Plan;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.PlanBuilder;
import seedu.address.testutil.PlanUtil;

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
    public void parseCommand_unclearInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNCLEAR_COMMAND, () -> parser.parseCommand("delete 1"));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
                -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_completePlan() throws Exception {
        CompletePlanCommand command = (CompletePlanCommand) parser.parseCommand(
                CompletePlanCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new CompletePlanCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_unCompletePlan() throws Exception {
        UncompletePlanCommand command = (UncompletePlanCommand) parser.parseCommand(
                UncompletePlanCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new UncompletePlanCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_addPlan() throws Exception {
        Plan plan = new PlanBuilder().build();
        AddPlanCommand command = (AddPlanCommand) parser.parseCommand(PlanUtil.getAddPlanCommand(plan));
        assertEquals(new AddPlanCommand(plan.getPlanName(), plan.getPlanDateTime(), plan.getPlanFriend().getName()),
                command);
    }

    @Test
    public void parseCommand_listPlan() throws Exception {
        assertTrue(parser.parseCommand(ListPlanCommand.COMMAND_WORD) instanceof ListPlanCommand);
        assertTrue(parser.parseCommand(ListPlanCommand.COMMAND_WORD + " 3") instanceof ListPlanCommand);
    }

    @Test
    public void parseCommand_deletePlan() throws Exception {
        DeletePlanCommand command = (DeletePlanCommand) parser.parseCommand(
                DeletePlanCommand.COMMAND_WORD + " " + INDEX_FIRST_PLAN.getOneBased());
        assertEquals(new DeletePlanCommand(INDEX_FIRST_PLAN), command);
    }

    @Test
    public void parseCommand_findPlan() throws Exception {
        String friendName = "Alice Pauline";
        FindPlanCommand command = (FindPlanCommand) parser.parseCommand(
                FindPlanCommand.COMMAND_WORD + " " + friendName);
        assertEquals(new FindPlanCommand(new Name(friendName)), command);
    }
}
