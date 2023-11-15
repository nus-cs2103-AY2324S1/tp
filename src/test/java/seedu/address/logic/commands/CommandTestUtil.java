package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANIMAL_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANIMAL_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAILABILITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.index.Indices;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String ANIMAL_NAME_DESC_NIL = "nil";
    public static final String AVAILABILITY_DESC_NIL = "nil";
    public static final String ANIMAL_TYPE_DESC_NIL = "nil";
    public static final String HOUSING_DESC_NIL = "nil";

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_ANIMAL_NAME_BOB = "WOOF";
    public static final String VALID_AVAILABILITY_BOB = "NotAvailable";
    public static final String VALID_ANIMAL_TYPE_BOB = "current.Dog";
    public static final String VALID_HOUSING_BOB = "HDB";
    public static final String VALID_ANIMAL_NAME_AMY = "nil";
    public static final String VALID_AVAILABILITY_AMY = "nil";
    public static final String VALID_ANIMAL_TYPE_AMY = "nil";
    public static final String VALID_HOUSING_AMY = "nil";
    public static final String VALID_AVAILABILITY_AVAILABLE = "Available";

    public static final String ANIMAL_NAME_NIL_DESC_BOB = " " + PREFIX_ANIMAL_NAME + ANIMAL_NAME_DESC_NIL;
    public static final String AVAILABILITY_NIL_DESC_BOB = " " + PREFIX_AVAILABILITY + AVAILABILITY_DESC_NIL;
    public static final String ANIMAL_TYPE_NIL_DESC_BOB = " " + PREFIX_ANIMAL_TYPE + ANIMAL_TYPE_DESC_NIL;
    public static final String HOUSING_NIL_DESC_BOB = " " + PREFIX_HOUSING + HOUSING_DESC_NIL;
    public static final String AVAILABILITY_DESC_AVAILABLE = " " + PREFIX_AVAILABILITY + VALID_AVAILABILITY_AVAILABLE;
    public static final String AVAILABILITY_DESC_NOT_AVAILABLE = " " + PREFIX_AVAILABILITY + VALID_AVAILABILITY_BOB;

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ANIMAL_NAME_DESC_BOB = " " + PREFIX_ANIMAL_NAME + VALID_ANIMAL_NAME_BOB;
    public static final String AVAILABILITY_DESC_BOB = " " + PREFIX_AVAILABILITY + VALID_AVAILABILITY_BOB;
    public static final String ANIMAL_TYPE_DESC_BOB = " " + PREFIX_ANIMAL_TYPE + VALID_ANIMAL_TYPE_BOB;
    public static final String HOUSING_DESC_BOB = " " + PREFIX_HOUSING + VALID_HOUSING_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String ANIMAL_NAME_DESC_AMY = " " + PREFIX_ANIMAL_NAME + VALID_ANIMAL_NAME_AMY;
    public static final String AVAILABILITY_DESC_AMY = " " + PREFIX_AVAILABILITY + VALID_AVAILABILITY_AMY;
    public static final String ANIMAL_TYPE_DESC_AMY = " " + PREFIX_ANIMAL_TYPE + VALID_ANIMAL_TYPE_AMY;
    public static final String HOUSING_DESC_AMY = " " + PREFIX_HOUSING + VALID_HOUSING_AMY;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_ANIMAL_NAME_DESC = " " + PREFIX_ANIMAL_NAME + "WOOF&";
    public static final String INVALID_ANIMAL_TYPE_DESC = " " + PREFIX_ANIMAL_TYPE + "cat";
    public static final String INVALID_AVAILABILITY_DESC = " " + PREFIX_AVAILABILITY + "available";
    public static final String INVALID_HOUSING_DESC = " " + PREFIX_HOUSING + "hdb";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder()
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withHousing(VALID_HOUSING_AMY)
                .withAvailability(VALID_AVAILABILITY_AMY)
                .withAnimalName(VALID_ANIMAL_NAME_AMY)
                .withAnimalType(VALID_ANIMAL_TYPE_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder()
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withHousing(VALID_HOUSING_BOB)
                .withAvailability(VALID_AVAILABILITY_BOB)
                .withAnimalName(VALID_ANIMAL_NAME_BOB)
                .withAnimalType(VALID_ANIMAL_TYPE_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
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
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Returns the Persons at the specified indices.
     */
    public static Person[] getPeople(List<Person> list, Indices indices) {
        assertTrue(indices.getSize() <= list.size());
        assertTrue(indices.getZeroBasedMax() < list.size());

        int number = indices.getSize();
        Person[] people = new Person[number];
        int[] zeroBasedIndices = indices.getZeroBased();

        for (int i = 0; i < number; i++) {
            people[i] = list.get(zeroBasedIndices[i]);
        }
        return people;
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the persons at the given {@code targetIndices} in the
     * {@code model}'s address book.
     */
    public static void showPeopleAtIndices(Model model, Indices targetIndices) {

        Person[] people = getPeople(model.getFilteredPersonList(), targetIndices);
        ArrayList<String> keywords = new ArrayList<>();
        for (Person person : people) {
            final String[] splitName = person.getName().fullName.split("\\s+");
            keywords.add(splitName[0]);
        }
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(keywords));
        assertEquals(targetIndices.getSize(), model.getFilteredPersonList().size());
    }
}
