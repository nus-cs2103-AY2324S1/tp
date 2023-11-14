package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.PropertyBook;
import seedu.address.model.property.PropNameContainsKeywordsPredicate;
import seedu.address.model.property.Property;
import seedu.address.testutil.EditPropertyDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandPropertyTestUtil {

    public static final String VALID_NAME_AQUAVIEW = "Aquaview";
    public static final String VALID_NAME_SKYVIEW = "Skyview";
    public static final String VALID_PHONE_AQUAVIEW = "91111111";
    public static final String VALID_PHONE_SKYVIEW = "82222222";
    public static final String VALID_ADDRESS_AQUAVIEW = "42 Merlion Avenue, Garden City, Singapore 54321";
    public static final String VALID_ADDRESS_SKYVIEW = "17 Rainbow Crescent, Serene Heights, Singapore 67890";
    public static final String VALID_PRICE_AQUAVIEW = "1000000";
    public static final String VALID_PRICE_SKYVIEW = "5000000";
    public static final String VALID_TAG_BIG = "big";
    public static final String VALID_TAG_SQUARE = "square";

    public static final String NAME_DESC_AQUAVIEW = " " + PREFIX_NAME + VALID_NAME_AQUAVIEW;
    public static final String NAME_DESC_SKYVIEW = " " + PREFIX_NAME + VALID_NAME_SKYVIEW;
    public static final String PHONE_DESC_AQUAVIEW = " " + PREFIX_PHONE + VALID_PHONE_AQUAVIEW;
    public static final String PHONE_DESC_SKYVIEW = " " + PREFIX_PHONE + VALID_PHONE_SKYVIEW;
    public static final String ADDRESS_DESC_AQUAVIEW = " " + PREFIX_ADDRESS + VALID_ADDRESS_AQUAVIEW;
    public static final String ADDRESS_DESC_SKYVIEW = " " + PREFIX_ADDRESS + VALID_ADDRESS_SKYVIEW;
    public static final String PRICE_DESC_AQUAVIEW = " " + PREFIX_PRICE + VALID_PRICE_AQUAVIEW;
    public static final String PRICE_DESC_SKYVIEW = " " + PREFIX_PRICE + VALID_PRICE_SKYVIEW;
    public static final String TAG_DESC_SQUARE = " " + PREFIX_TAG + VALID_TAG_SQUARE;
    public static final String TAG_DESC_BIG = " " + PREFIX_TAG + VALID_TAG_BIG;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + " "; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS + " "; // ! not allowed in address
    public static final String INVALID_PRICE_DESC = " " + PREFIX_PRICE; // empty string not allowed for PRICE
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPropertyCommand.EditPropertyDescriptor DESC_AQUAVIEW;
    public static final EditPropertyCommand.EditPropertyDescriptor DESC_SKYVIEW;

    static {
        DESC_AQUAVIEW = new EditPropertyDescriptorBuilder().withName(VALID_NAME_AQUAVIEW)
                .withPhone(VALID_PHONE_AQUAVIEW).withAddress(VALID_ADDRESS_AQUAVIEW)
                .withPrice(VALID_PRICE_AQUAVIEW)
                .withTags(VALID_TAG_SQUARE).build();
        DESC_SKYVIEW = new EditPropertyDescriptorBuilder().withName(VALID_NAME_SKYVIEW)
                .withPhone(VALID_PHONE_SKYVIEW).withAddress(VALID_ADDRESS_SKYVIEW).withPrice(VALID_PRICE_SKYVIEW)
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
     * - the price book, filtered customer list and selected customer in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        PropertyBook expectedPropertyBook = new PropertyBook(actualModel.getPropertyBook());
        List<Property> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPropertyList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedPropertyBook, actualModel.getPropertyBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPropertyList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the customer at the given {@code targetIndex} in the
     * {@code model}'s price book.
     */
    public static void showPropertyAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPropertyList().size());

        Property property = model.getFilteredPropertyList().get(targetIndex.getZeroBased());
        final String[] splitName = property.getName().fullName.split("\\s+");
        model.updateFilteredPropertyList(new PropNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPropertyList().size());
    }

}
