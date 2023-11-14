package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.testutil.TypicalGroups;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code CreateCommand}.
 */
public class CreateCommandTest {

    @Test
    public void execute_validTutorialNumber_success() {
        Model model = new ModelManager(TypicalGroups.getTypicalAddressBook(), new UserPrefs());
        CreateCommand createCommand = new CreateCommand(new Tutorial("01"));
        String expectedMessage = String.format(CreateCommand.MESSAGE_SUCCESS,
                createCommand.generateGroupNumber(model));

        CommandResult commandResult = createCommand.execute(model);

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidTutorialNumber_throwsCommandException() {
        Model model = new ModelManager(TypicalGroups.getTypicalAddressBook(), new UserPrefs());
        assertThrows(IllegalArgumentException.class, () -> new CreateCommand(new Tutorial("40")).execute(model));
    }

    @Test
    public void generateGroupNumberTest() {
        Model model = new ModelManager(TypicalGroups.getTypicalAddressBook(), new UserPrefs());
        CreateCommand command = new CreateCommand(new Tutorial("01"));
        assertEquals(command.generateGroupNumber(model), model.getFilteredGroupList().size() + 1);
    }

    @Test
    public void equals() {
        CreateCommand firstCreateCommand = new CreateCommand(new Tutorial("01"));
        CreateCommand secondCreateCommand = new CreateCommand(new Tutorial("02"));

        // same object -> returns true
        assertTrue(firstCreateCommand.equals(firstCreateCommand));

        // same values -> returns true
        assertTrue(firstCreateCommand.equals(new CreateCommand(new Tutorial("01"))));

        // different types -> returns false
        assertFalse(firstCreateCommand.equals(1));

        // null -> returns false
        assertFalse(firstCreateCommand.equals(null));

        // different tutorial number -> returns false
        assertFalse(firstCreateCommand.equals(secondCreateCommand));
    }
}
