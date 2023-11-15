package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEHANDLE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
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

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TELEHANDLE_AMY = "@Amyamy123";
    public static final String VALID_TELEHANDLE_BOB = "@BobaTea123";
    public static final String VALID_TAG_CLOSE_FRIEND = "Close Friend";
    public static final String VALID_TAG_FRIEND = "Friend";
    public static final String VALID_COURSE_CS2103T = "CS2103T";
    public static final String VALID_COURSE_MA1521 = "MA1521";
    public static final String VALID_COURSE_MA2001 = "MA2001";
    public static final String INVALID_COURSE_CS2103X = "CS2103X";
    public static final String VALID_COURSE_ADDITION_CS2103T = "add-" + VALID_COURSE_CS2103T;
    public static final String VALID_COURSE_DELETION_MA2001 = "del-" + VALID_COURSE_MA2001;
    public static final String VALID_COURSE_EDIT_CS1231S_TO_MA1521 = "CS1231S-MA1521";
    public static final String VALID_COURSE_EDIT_MA1521_TO_MA2001 = "MA1521-MA2001";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_JOHN = " " + PREFIX_NAME + "John";
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String PHONE_DESC_JOHN = " " + PREFIX_PHONE + "33333333";
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TELEHANDLE_DESC_AMY = " " + PREFIX_TELEHANDLE + VALID_TELEHANDLE_AMY;
    public static final String TELEHANDLE_DESC_BOB = " " + PREFIX_TELEHANDLE + VALID_TELEHANDLE_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_CLOSE_FRIEND = " " + PREFIX_TAG + VALID_TAG_CLOSE_FRIEND;
    public static final String COURSE_DESC_CS2103T = " " + PREFIX_COURSE + VALID_COURSE_CS2103T;
    public static final String COURSE_DESC_MA1521 = " " + PREFIX_COURSE + VALID_COURSE_MA1521;
    public static final String COURSE_DESC_MA2001 = " " + PREFIX_COURSE + VALID_COURSE_MA2001;
    public static final String COURSE_CHANGE_DESC_ADD = " " + PREFIX_COURSE + VALID_COURSE_ADDITION_CS2103T;
    public static final String COURSE_CHANGE_DESC_DELETE = " " + PREFIX_COURSE + VALID_COURSE_DELETION_MA2001;
    public static final String COURSE_CHANGE_DESC_EDIT = " " + PREFIX_COURSE + VALID_COURSE_EDIT_CS1231S_TO_MA1521;


    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol

    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_COURSE_DESC = " " + PREFIX_COURSE + "MA0!0"; // '!' not allowed in tags
    public static final String INVALID_EXISTING_COURSE_DESC = " " + PREFIX_COURSE + "MA9999"; // Non-existence course
    public static final String INVALID_COURSE_ADDITION_MISSING_COURSE = " " + PREFIX_COURSE + "add-";
    public static final String INVALID_COURSE_ADDITION_INVALID_COURSE = " " + PREFIX_COURSE + "add-"
            + INVALID_COURSE_CS2103X;
    public static final String INVALID_COURSE_DELETION_MISSING_COURSE = " " + PREFIX_COURSE + "del-";
    public static final String INVALID_COURSE_DELETION_INVALID_COURSE = " " + PREFIX_COURSE + "del-"
            + INVALID_COURSE_CS2103X;
    public static final String INVALID_COURSE_EDIT_MISSING_COURSE = " " + PREFIX_COURSE + "-CS2103T";
    public static final String INVALID_COURSE_EDIT_INVALID_ORIGINAL_COURSE = " " + PREFIX_COURSE
            + INVALID_COURSE_CS2103X + "-" + VALID_COURSE_CS2103T;
    public static final String INVALID_COURSE_EDIT_INVALID_NEW_COURSE = " " + PREFIX_COURSE + "CS2103T-"
            + INVALID_COURSE_CS2103X;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTelehandle(VALID_TELEHANDLE_AMY)
                .withTags(VALID_TAG_FRIEND).withCourseChanges(VALID_COURSE_ADDITION_CS2103T).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTelehandle(VALID_TELEHANDLE_BOB)
                .withTags(VALID_TAG_CLOSE_FRIEND, VALID_TAG_FRIEND).withCourseChanges(VALID_COURSE_DELETION_MA2001,
                VALID_COURSE_EDIT_CS1231S_TO_MA1521).build();
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

}
