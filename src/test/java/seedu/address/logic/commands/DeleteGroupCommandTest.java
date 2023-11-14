package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalGroups;

public class DeleteGroupCommandTest {

    @Test
    public void execute_validGroupNumber_success() throws CommandException {
        // Create a model with typical groups and user preferences
        Model model = new ModelManager(TypicalGroups.getTypicalAddressBook(), new UserPrefs());

        // Create a DeleteGroupCommand for a valid group number
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(1);

        // Build the expected success message
        String expectedMessage = String.format(DeleteGroupCommand.MESSAGE_DELETE_GROUP_SUCCESS, 1);

        // Execute the DeleteGroupCommand and get the result
        CommandResult commandResult = deleteGroupCommand.execute(model);

        // Assert that the feedback message matches the expected success message
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidGroupNumber_throwsCommandException() {
        // Create a model with typical groups and user preferences
        Model model = new ModelManager(TypicalGroups.getTypicalAddressBook(), new UserPrefs());

        // Choose a group number that is known to be non-existent (e.g., 5000)
        int nonExistentGroupNumber = 5000;

        // Ensure that the chosen group number is non-existent
        assertTrue(model.getGroupWithNumber(nonExistentGroupNumber).isEmpty(),
                "Precondition: Group with the chosen number should not exist in the model.");

        // Create a DeleteGroupCommand for the non-existent group number
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(nonExistentGroupNumber);

        // Use assertThrows to verify that executing the command throws the expected exception
        assertThrows(CommandException.class, () -> deleteGroupCommand.execute(model),
                DeleteGroupCommand.MESSAGE_DELETE_GROUP_NOT_FOUND);

        // Ensure that the model remains unchanged after the command execution attempt
        assertEquals(TypicalGroups.getTypicalGroups().size(), model.getFilteredGroupList().size());
    }

    @Test
    public void execute_zeroGroupNumber_throwsCommandException() {
        // Create a model with typical groups and user preferences
        Model model = new ModelManager(TypicalGroups.getTypicalAddressBook(), new UserPrefs());

        // Create a DeleteGroupCommand for a group number of zero
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(0);

        // Use assertThrows to verify that executing the command throws the expected exception
        assertThrows(CommandException.class, () -> deleteGroupCommand.execute(model));
    }
}
