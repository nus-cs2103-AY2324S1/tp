package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROPERTY;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCustomerCommand;
import seedu.address.logic.commands.AddPropertyCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCustomerCommand;
import seedu.address.logic.commands.DeletePropertyCommand;
import seedu.address.logic.commands.EditCustomerCommand;
import seedu.address.logic.commands.EditPropertyCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCustomerCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCustomerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.NameContainsKeywordsPredicate;
import seedu.address.model.property.Property;
import seedu.address.testutil.CustomerBuilder;
import seedu.address.testutil.CustomerUtil;
import seedu.address.testutil.EditCustomerDescriptorBuilder;
import seedu.address.testutil.EditPropertyDescriptorBuilder;
import seedu.address.testutil.PropertyBuilder;
import seedu.address.testutil.PropertyUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addcust() throws Exception {
        Customer customer = new CustomerBuilder().build();
        AddCustomerCommand command = (AddCustomerCommand) parser.parseCommand(CustomerUtil.getAddCommand(customer));
        assertEquals(new AddCustomerCommand(customer), command);
    }

    @Test
    public void parseCommand_addprop() throws Exception {
        Property property = new PropertyBuilder().build();
        AddPropertyCommand command = (AddPropertyCommand) parser.parseCommand(PropertyUtil.getAddCommand(property));
        assertEquals(new AddPropertyCommand(property), command);
    }


    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delcust() throws Exception {
        DeletePropertyCommand command = (DeletePropertyCommand) parser.parseCommand(
                DeletePropertyCommand.COMMAND_WORD + " " + INDEX_FIRST_PROPERTY.getOneBased());
        assertEquals(new DeletePropertyCommand(INDEX_FIRST_PROPERTY), command);
    }

    @Test
    public void parseCommand_delprop() throws Exception {
        DeleteCustomerCommand command = (DeleteCustomerCommand) parser.parseCommand(
                DeleteCustomerCommand.COMMAND_WORD + " " + INDEX_FIRST_PROPERTY.getOneBased());
        assertEquals(new DeleteCustomerCommand(INDEX_FIRST_PROPERTY), command);
    }
    @Test
    public void parseCommand_edit() throws Exception {
        Customer customer = new CustomerBuilder().build();
        EditCustomerCommand.EditCustomerDescriptor descriptor = new EditCustomerDescriptorBuilder(customer).build();
        EditCustomerCommand command = (EditCustomerCommand) parser.parseCommand(EditCustomerCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CUSTOMER.getOneBased() + " " + CustomerUtil.getEditCustomerDescriptorDetails(descriptor));
        assertEquals(new EditCustomerCommand(INDEX_FIRST_CUSTOMER, descriptor), command);
    }
    @Test
    public void parseCommand_editprop() throws Exception {
        Property property = new PropertyBuilder().build();
        EditPropertyCommand.EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder(property).build();
        EditPropertyCommand command = (EditPropertyCommand) parser.parseCommand(EditPropertyCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PROPERTY.getOneBased() + " " + PropertyUtil.getEditPropertyDescriptorDetails(descriptor));
        assertEquals(new EditPropertyCommand(INDEX_FIRST_PROPERTY, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCustomerCommand command = (FindCustomerCommand) parser.parseCommand(
                FindCustomerCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCustomerCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCustomerCommand.COMMAND_WORD) instanceof ListCustomerCommand);
        assertTrue(parser.parseCommand(ListCustomerCommand.COMMAND_WORD + " 3") instanceof ListCustomerCommand);
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
