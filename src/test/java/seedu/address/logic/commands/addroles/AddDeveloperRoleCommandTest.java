package seedu.address.logic.commands.addroles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDevelopers.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.deleteroles.DeleteDeveloperRoleCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.Assert;

public class AddDeveloperRoleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullRole_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new AddDeveloperRoleCommand(null));
    }

    @Test
    public void execute_roleAcceptedByModel_addSuccessful() throws CommandException {
        String validRole1 = "Tester";
        AddDeveloperRoleCommand addDeveloperRoleCommand = new AddDeveloperRoleCommand(validRole1);
        String expectedMessage = String.format(AddDeveloperRoleCommand.MESSAGE_SUCCESS, validRole1);

        assertCommandSuccess(addDeveloperRoleCommand, model, expectedMessage, expectedModel);

        DeleteDeveloperRoleCommand deleteDeveloperRoleCommand = new DeleteDeveloperRoleCommand(validRole1);
        deleteDeveloperRoleCommand.execute(model);
    }

    @Test
    public void execute_duplicateRole_throwsCommandException() {
        String duplicateRole = "Developer";
        AddDeveloperRoleCommand addDeveloperRoleCommand = new AddDeveloperRoleCommand(duplicateRole);

        assertCommandFailure(addDeveloperRoleCommand, model, AddDeveloperRoleCommand.MESSAGE_DUPLICATE_ROLE);
    }

    @Test
    public void equals() {
        String firstRole = "Tester";
        String secondRole = "Developer";
        AddDeveloperRoleCommand addFirstCommand = new AddDeveloperRoleCommand(firstRole);
        AddDeveloperRoleCommand addSecondCommand = new AddDeveloperRoleCommand(secondRole);

        // same object -> returns true
        assertTrue(addFirstCommand.equals(addFirstCommand));

        // same values -> returns False because cannot add twice
        AddDeveloperRoleCommand addFirstCommandCopy = new AddDeveloperRoleCommand(firstRole);
        assertFalse(addFirstCommand.equals(addFirstCommandCopy));

        // different types -> returns false
        assertFalse(addFirstCommand.equals(1));

        // null -> returns false
        assertFalse(addFirstCommand.equals(null));

        // different developer role -> returns false
        assertFalse(addFirstCommand.equals(addSecondCommand));
    }

    @Test
    public void toStringTest() {
        String roleName = "Tester";
        AddDeveloperRoleCommand addDeveloperRoleCommand = new AddDeveloperRoleCommand(roleName);
        assertEquals(addDeveloperRoleCommand.toString(), new AddDeveloperRoleCommand(roleName).toString());
    }
}
