package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ContactsManager;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.NameContainsKeywordsPredicate;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {


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
     * - the address book, filtered contact list and selected contact in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ContactsManager expectedContactsManager = new ContactsManager(actualModel.getContactList());
        List<Contact> expectedFilteredList = new ArrayList<>(actualModel.getFilteredContactList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedContactsManager, actualModel.getContactList());
        assertEquals(expectedFilteredList, actualModel.getFilteredContactList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the contact at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showContactAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredContactList().size());

        Contact contact = model.getFilteredContactList().get(targetIndex.getZeroBased());
        final String[] splitName = contact.getName().fullName.split("\\s+");
        model.updateFilteredContactList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredContactList().size());
    }

}
