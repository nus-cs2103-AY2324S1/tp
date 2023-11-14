package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BUDGET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCustomerCommand.EditCustomerDescriptor;
import seedu.address.logic.commands.FilterCustomerCommand.FilterCustomerDescriptor;
import seedu.address.logic.commands.FilterPropertyCommand.FilterPropertyDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditCustomerDescriptorBuilder;
import seedu.address.testutil.FilterCustomerDescriptorBuilder;
import seedu.address.testutil.FilterPropertyDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    // Customers
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "91111111";
    public static final String VALID_PHONE_BOB = "62222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_BUDGET_AMY = "1000000";
    public static final String VALID_BUDGET_BOB = "5000000";
    public static final String VALID_TAG_BIG = "big";
    public static final String VALID_TAG_SQUARE = "square";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String BUDGET_DESC_AMY = " " + PREFIX_BUDGET + VALID_BUDGET_AMY;
    public static final String BUDGET_DESC_BOB = " " + PREFIX_BUDGET + VALID_BUDGET_BOB;
    public static final String TAG_DESC_SQUARE = " " + PREFIX_TAG + VALID_TAG_SQUARE;
    public static final String TAG_DESC_BIG = " " + PREFIX_TAG + VALID_TAG_BIG;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_BUDGET_DESC = " " + PREFIX_BUDGET; // empty string not allowed for budget
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCustomerDescriptor DESC_AMY;
    public static final EditCustomerDescriptor DESC_BOB;
    public static final FilterCustomerDescriptor FILTER_CUSTOMER_DESCRIPTOR_AMY;
    public static final FilterCustomerDescriptor FILTER_CUSTOMER_DESCRIPTOR_BOB;

    static {
        DESC_AMY = new EditCustomerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withBudget(VALID_BUDGET_AMY)
                .withTags(VALID_TAG_SQUARE).build();
        DESC_BOB = new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withBudget(VALID_BUDGET_BOB)
                .withTags(VALID_TAG_BIG, VALID_TAG_SQUARE).build();
        FILTER_CUSTOMER_DESCRIPTOR_AMY = new FilterCustomerDescriptorBuilder().withBudget(VALID_BUDGET_AMY)
                .withTags(VALID_TAG_SQUARE).build();
        FILTER_CUSTOMER_DESCRIPTOR_BOB = new FilterCustomerDescriptorBuilder().withBudget(VALID_BUDGET_BOB)
                .withTags(VALID_TAG_BIG, VALID_TAG_SQUARE).build();
    }

    // Properties
    public static final String VALID_NAME_LIGHT = "Light House";
    public static final String VALID_NAME_HELIX = "Helix House";
    public static final String VALID_PHONE_LIGHT = "93333333";
    public static final String VALID_PHONE_HELIX = "84444444";
    public static final String VALID_ADDRESS_LIGHT = "25 Prince George's Park, Singapore 118424";
    public static final String VALID_ADDRESS_HELIX = "37 Prince George's Park, Singapore 118430";
    public static final String VALID_PRICE_LIGHT = "1000000";
    public static final String VALID_PRICE_HELIX = "5000000";

    public static final String NAME_DESC_LIGHT = " " + PREFIX_NAME + VALID_NAME_LIGHT;
    public static final String NAME_DESC_HELIX = " " + PREFIX_NAME + VALID_NAME_HELIX;
    public static final String PHONE_DESC_LIGHT = " " + PREFIX_PHONE + VALID_PHONE_LIGHT;
    public static final String PHONE_DESC_HELIX = " " + PREFIX_PHONE + VALID_PHONE_HELIX;
    public static final String ADDRESS_DESC_LIGHT = " " + PREFIX_EMAIL + VALID_ADDRESS_LIGHT;
    public static final String ADDRESS_DESC_HELIX = " " + PREFIX_EMAIL + VALID_ADDRESS_HELIX;
    public static final String PRICE_DESC_LIGHT = " " + PREFIX_BUDGET + VALID_PRICE_LIGHT;
    public static final String PRICE_DESC_HELIX = " " + PREFIX_BUDGET + VALID_PRICE_HELIX;

    public static final String INVALID_PRICE_DESC = " " + PREFIX_PRICE; // empty string not allowed for Price
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for Address

    public static final FilterPropertyDescriptor FILTER_PROPERTY_DESCRIPTOR_LIGHT;
    public static final FilterPropertyDescriptor FILTER_PROPERTY_DESCRIPTOR_HELIX;

    static {
        FILTER_PROPERTY_DESCRIPTOR_LIGHT = new FilterPropertyDescriptorBuilder().withPrice(VALID_PRICE_LIGHT)
                .withTags(VALID_TAG_SQUARE).build();
        FILTER_PROPERTY_DESCRIPTOR_HELIX = new FilterPropertyDescriptorBuilder().withPrice(VALID_PRICE_HELIX)
                .withTags(VALID_TAG_BIG, VALID_TAG_SQUARE).build();
    }


    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the budget book, filtered customer list and selected customer in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Customer> expectedFilteredList = new ArrayList<>(actualModel.getFilteredCustomerList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredCustomerList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the customer at the given {@code targetIndex} in the
     * {@code model}'s budget book.
     */
    public static void showCustomerAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredCustomerList().size());

        Customer customer = model.getFilteredCustomerList().get(targetIndex.getZeroBased());
        final String[] splitName = customer.getName().fullName.split("\\s+");
        model.updateFilteredCustomerList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredCustomerList().size());
    }

}
