package wedlog.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static wedlog.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static wedlog.address.logic.parser.CliSyntax.PREFIX_DIETARY;
import static wedlog.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static wedlog.address.logic.parser.CliSyntax.PREFIX_NAME;
import static wedlog.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static wedlog.address.logic.parser.CliSyntax.PREFIX_RSVP;
import static wedlog.address.logic.parser.CliSyntax.PREFIX_TABLE;
import static wedlog.address.logic.parser.CliSyntax.PREFIX_TAG;
import static wedlog.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import wedlog.address.commons.core.index.Index;
import wedlog.address.logic.commands.exceptions.CommandException;
import wedlog.address.model.AddressBook;
import wedlog.address.model.Model;
import wedlog.address.model.person.Guest;
import wedlog.address.model.person.NamePredicate;
import wedlog.address.model.person.Person;
import wedlog.address.model.person.Vendor;
import wedlog.address.testutil.EditGuestDescriptorBuilder;
import wedlog.address.testutil.EditVendorDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    // Names
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_GIA = "Gia Giordano";
    public static final String VALID_NAME_GABE = "Gabe Gaberella";
    public static final String VALID_NAME_VAL = "Val Valencia";
    public static final String VALID_NAME_VICTOR = "Victor Tan";

    // Phone
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_PHONE_GIA = "33333333";
    public static final String VALID_PHONE_GABE = "44444444";
    public static final String VALID_PHONE_VAL = "55555555";

    // Email
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_EMAIL_GIA = "gia@example.com";
    public static final String VALID_EMAIL_GABE = "gabe@example.com";
    public static final String VALID_EMAIL_VAL = "val@example.com";

    // Address
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_ADDRESS_GIA = "Block 456, Gia Street 5";
    public static final String VALID_ADDRESS_GABE = "Block 654, Gabe Street 7";
    public static final String VALID_ADDRESS_VAL = "Block 456, Val Street 7";

    // RSVP
    public static final String VALID_RSVP_STATUS_AMY = "no";
    public static final String VALID_RSVP_STATUS_BOB = "unknown";
    public static final String VALID_RSVP_STATUS_GIA = "yes";
    public static final String VALID_RSVP_STATUS_GABE = "no";

    // Dietary Requirement
    public static final String VALID_DIETARY_REQUIREMENTS_BOB = "no beef";
    public static final String VALID_DIETARY_REQUIREMENTS_GIA = "vegan";
    public static final String VALID_DIETARY_REQUIREMENTS_GABE = "vegetarian";

    // Table Number
    public static final String VALID_TABLE_NUMBER_AMY = "13";
    public static final String VALID_TABLE_NUMBER_BOB = "31";
    public static final String VALID_TABLE_NUMBER_GIA = "57";
    public static final String VALID_TABLE_NUMBER_GABE = "75";

    // Tags
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_TAG_FLORIST = "florist";
    public static final String VALID_TAG_PHOTOGRAPHER = "photographer";

    // With prefixes
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_GIA = " " + PREFIX_NAME + VALID_NAME_GIA;
    public static final String NAME_DESC_GABE = " " + PREFIX_NAME + VALID_NAME_GABE;
    public static final String NAME_DESC_VAL = " " + PREFIX_NAME + VALID_NAME_VAL;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String PHONE_DESC_GIA = " " + PREFIX_PHONE + VALID_PHONE_GIA;
    public static final String PHONE_DESC_GABE = " " + PREFIX_PHONE + VALID_PHONE_GABE;
    public static final String PHONE_DESC_VAL = " " + PREFIX_PHONE + VALID_PHONE_VAL;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String EMAIL_DESC_GIA = " " + PREFIX_EMAIL + VALID_EMAIL_GIA;
    public static final String EMAIL_DESC_GABE = " " + PREFIX_EMAIL + VALID_EMAIL_GABE;
    public static final String EMAIL_DESC_VAL = " " + PREFIX_EMAIL + VALID_EMAIL_VAL;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String ADDRESS_DESC_GIA = " " + PREFIX_ADDRESS + VALID_ADDRESS_GIA;
    public static final String ADDRESS_DESC_GABE = " " + PREFIX_ADDRESS + VALID_ADDRESS_GABE;
    public static final String ADDRESS_DESC_VAL = " " + PREFIX_ADDRESS + VALID_ADDRESS_VAL;
    public static final String RSVP_DESC_GIA = " " + PREFIX_RSVP + VALID_RSVP_STATUS_GIA;
    public static final String RSVP_DESC_GABE = " " + PREFIX_RSVP + VALID_RSVP_STATUS_GABE;
    public static final String DIETARY_DESC_GIA = " " + PREFIX_DIETARY + VALID_DIETARY_REQUIREMENTS_GIA;
    public static final String DIETARY_DESC_GABE = " " + PREFIX_DIETARY + VALID_DIETARY_REQUIREMENTS_GABE;
    public static final String TABLE_DESC_GIA = " " + PREFIX_TABLE + VALID_TABLE_NUMBER_GIA;
    public static final String TABLE_DESC_GABE = " " + PREFIX_TABLE + VALID_TABLE_NUMBER_GABE;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String TAG_DESC_FLORIST = " " + PREFIX_TAG + VALID_TAG_FLORIST;
    public static final String TAG_DESC_PHOTOGRAPHER = " " + PREFIX_TAG + VALID_TAG_PHOTOGRAPHER;
    public static final String INVALID_EMPTY_NAME_DESC = " " + PREFIX_NAME; // empty string not allowed in names
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_RSVP_DESC = " " + PREFIX_RSVP + "abcd"; // not within range of accepted rsvp
    public static final String INVALID_DIETARY_DESC = " " + PREFIX_DIETARY + "#vegan"; // "#" not allowed for dietary
    public static final String INVALID_TABLE_DESC = " " + PREFIX_TABLE + "1a"; // 'a' not allowed in table numbers
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final GuestEditCommand.EditGuestDescriptor DESC_GIA;
    public static final GuestEditCommand.EditGuestDescriptor DESC_GABE;
    public static final VendorEditCommand.EditVendorDescriptor DESC_VAL;
    public static final VendorEditCommand.EditVendorDescriptor DESC_BRYAN;

    static {
        // Guest
        DESC_GIA = new EditGuestDescriptorBuilder().withName(VALID_NAME_GIA).withPhone(VALID_PHONE_GIA)
                .withEmail(VALID_EMAIL_GIA).withAddress(VALID_ADDRESS_GIA).withRsvp(VALID_RSVP_STATUS_GIA)
                .withDietary(VALID_DIETARY_REQUIREMENTS_GIA).withTable(VALID_TABLE_NUMBER_GIA).build();
        DESC_GABE = new EditGuestDescriptorBuilder().withName(VALID_NAME_GABE).withPhone(VALID_PHONE_GABE)
                .withEmail(VALID_EMAIL_GABE).withAddress(VALID_ADDRESS_GABE).withRsvp(VALID_RSVP_STATUS_GABE)
                .withDietary(VALID_DIETARY_REQUIREMENTS_GABE).withTable(VALID_TABLE_NUMBER_GABE)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        // Vendor
        DESC_VAL = new EditVendorDescriptorBuilder().withName(VALID_NAME_VAL)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BRYAN = new EditVendorDescriptorBuilder().withName(VALID_NAME_VICTOR)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_FRIEND).build();
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
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedGuestFilteredList = new ArrayList<>(actualModel.getFilteredGuestList());
        List<Person> expectedVendorFilteredList = new ArrayList<>(actualModel.getFilteredVendorList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedGuestFilteredList, actualModel.getFilteredGuestList());
        assertEquals(expectedVendorFilteredList, actualModel.getFilteredVendorList());
    }

    /**
     * Updates {@code model}'s filtered guest list to show only the guest at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showGuestAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredGuestList().size());

        Guest guest = model.getFilteredGuestList().get(targetIndex.getZeroBased());
        final String fullName = guest.getName().fullName;
        model.updateFilteredGuestList(new NamePredicate(fullName));

        assertEquals(1, model.getFilteredGuestList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showVendorAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredVendorList().size());

        Vendor vendor = model.getFilteredVendorList().get(targetIndex.getZeroBased());
        final String fullName = vendor.getName().fullName;
        model.updateFilteredVendorList(new NamePredicate(fullName));

        assertEquals(1, model.getFilteredVendorList().size());
    }

    /**
     * Inspired by AddressBook Level 4
     * Deletes {@code model}'s first guest from the {@code model}'s address book.
     */
    public static void deleteFirstGuest(Model model) {
        assertTrue(model.getFilteredGuestList().size() > 0);

        Guest firstGuest = model.getFilteredGuestList().get(0);
        model.deleteGuest(firstGuest);
        model.commitAddressBook();
    }
}
