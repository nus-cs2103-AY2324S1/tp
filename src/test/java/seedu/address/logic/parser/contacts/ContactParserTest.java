package seedu.address.logic.parser.contacts;

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

import seedu.address.commons.core.tab.Tab;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.TabCommand;
import seedu.address.logic.commands.contacts.AddContactCommand;
import seedu.address.logic.commands.contacts.ClearContactsCommand;
import seedu.address.logic.commands.contacts.DeleteContactCommand;
import seedu.address.logic.commands.contacts.EditContactCommand;
import seedu.address.logic.commands.contacts.EditContactCommand.EditPersonDescriptor;
import seedu.address.logic.commands.contacts.FilterContactCompanyCommand;
import seedu.address.logic.commands.contacts.FilterContactNameCommand;
import seedu.address.logic.commands.contacts.ListContactsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CompanyContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class ContactParserTest {

    private final ContactParser parser = new ContactParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddContactCommand command = (AddContactCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddContactCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearContactsCommand.COMMAND_WORD) instanceof ClearContactsCommand);
        assertTrue(parser.parseCommand(ClearContactsCommand.COMMAND_WORD + " 3") instanceof ClearContactsCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteContactCommand command = (DeleteContactCommand) parser.parseCommand(
                DeleteContactCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteContactCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditContactCommand command = (EditContactCommand) parser.parseCommand(EditContactCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditContactCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_filterByName() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FilterContactNameCommand command = (FilterContactNameCommand) parser.parseCommand(
                FilterContactNameCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FilterContactNameCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_filterByCompany() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FilterContactCompanyCommand command = (FilterContactCompanyCommand) parser.parseCommand(
                FilterContactCompanyCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FilterContactCompanyCommand(new CompanyContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListContactsCommand.COMMAND_WORD) instanceof ListContactsCommand);
        assertTrue(parser.parseCommand(ListContactsCommand.COMMAND_WORD + " 3") instanceof ListContactsCommand);
    }

    @Test
    public void parseCommand_tabCommand() throws ParseException {
        TabCommand command = (TabCommand) parser.parseCommand(
                TabCommand.COMMAND_WORD + " finance");
        assertEquals(new TabCommand(Tab.fromParameter("finance")), command);
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
