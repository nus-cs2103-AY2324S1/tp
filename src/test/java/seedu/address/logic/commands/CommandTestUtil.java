package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.displayable.buyer.Buyer;
import seedu.address.model.displayable.seller.Seller;
import seedu.address.testutil.EditBuyerDescriptorBuilder;
import seedu.address.testutil.EditSellerDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_HOUSE_INFO_AMY = "Amy's lovely house";
    public static final String VALID_HOUSE_INFO_BOB = "Bob's lovely house";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_PRIORITY_AMY = "high";
    public static final String VALID_PRIORITY_BOB = "low";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditBuyerCommand.EditBuyerDescriptor DESC_AMY;
    public static final EditBuyerCommand.EditBuyerDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditBuyerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withHouseInfo(VALID_HOUSE_INFO_AMY)
                .withTags(VALID_TAG_FRIEND).withPriority(VALID_PRIORITY_AMY).build();
        DESC_BOB = new EditBuyerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withHouseInfo(VALID_HOUSE_INFO_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withPriority(VALID_PRIORITY_BOB).build();
    }

    public static final EditSellerCommand.EditSellerDescriptor DESC_SAMY;
    public static final EditSellerCommand.EditSellerDescriptor DESC_SBOB;

    static {
        DESC_SAMY = new EditSellerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withSellingAddress(VALID_ADDRESS_AMY).withHouseInfo(VALID_HOUSE_INFO_AMY)
                .withTags(VALID_TAG_FRIEND).withPriority(VALID_PRIORITY_AMY).build();
        DESC_SBOB = new EditSellerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withSellingAddress(VALID_ADDRESS_BOB).withHouseInfo(VALID_HOUSE_INFO_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withPriority(VALID_PRIORITY_BOB).build();
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
     * - the address book, filtered displayable list and selected displayable in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Buyer> expectedFilteredBuyerList = new ArrayList<>(actualModel.getFilteredBuyerList());
        List<Seller> expectedFilteredSellerList = new ArrayList<>(actualModel.getFilteredSellerList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredBuyerList, actualModel.getFilteredBuyerList());
        assertEquals(expectedFilteredSellerList, actualModel.getFilteredSellerList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the buyer at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showBuyerAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredBuyerList().size());

        Buyer buyer = model.getFilteredBuyerList().get(targetIndex.getZeroBased());
        final String[] splitName = buyer.getName().fullName.split("\\s+");
        model.updateFilteredBuyerList(buyer1 ->
                StringUtil.containsWordIgnoreCase(buyer1.getName().fullName, splitName[0]));

        assertEquals(1, model.getFilteredBuyerList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the seller at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showSellerAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredSellerList().size());

        Seller seller = model.getFilteredSellerList().get(targetIndex.getZeroBased());
        final String[] splitName = seller.getName().fullName.split("\\s+");
        model.updateFilteredSellerList(seller1 ->
                StringUtil.containsWordIgnoreCase(seller1.getName().fullName, splitName[0]));

        assertEquals(1, model.getFilteredSellerList().size());
    }

    //@@author peasantbird-reused
    //Reused from Address Book (Level4) with minor modifications
    /**
     * Deletes the first buyer in {@code model}'s filtered list from {@code model}'s address book.
     *
     * @param model The model to delete the buyer from.
     */
    public static void deleteFirstBuyer(Model model) {
        Buyer firstBuyer = model.getFilteredBuyerList().get(0);
        model.deleteBuyer(firstBuyer);
        model.commitAddressBook();
    }
    //@@author
}
