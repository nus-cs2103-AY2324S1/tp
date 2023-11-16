package seedu.address.logic.commands.deleteroles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDevelopers.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.addroles.AddDeveloperRoleCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.developer.DeveloperRoles;
import seedu.address.testutil.Assert;
import seedu.address.testutil.DeveloperBuilder;

public class DeleteDeveloperRoleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullRole_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new DeleteDeveloperRoleCommand(null));
    }

    @Test
    public void execute_roleAcceptedByModel_deleteSuccessful() throws CommandException {

        String validRole = "NewRole";
        AddDeveloperRoleCommand addDeveloperRoleCommand = new AddDeveloperRoleCommand(validRole);
        addDeveloperRoleCommand.execute(model);

        DeleteDeveloperRoleCommand deleteDeveloperRoleCommand = new DeleteDeveloperRoleCommand(validRole);

        String expectedMessage = String.format(DeleteDeveloperRoleCommand.MESSAGE_SUCCESS, validRole);

        assertCommandSuccess(deleteDeveloperRoleCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_roleSomeoneUsing_throwsCommandException() throws CommandException {
        // Assign a developer with the specified role
        String roleWithDevelopers = "NewRole";
        AddDeveloperRoleCommand addDeveloperRoleCommand = new AddDeveloperRoleCommand(roleWithDevelopers);
        addDeveloperRoleCommand.execute(model);
        DeveloperBuilder developerBuilder = new DeveloperBuilder().withRole(roleWithDevelopers);
        model.addDeveloper(developerBuilder.build());

        // Attempt to delete the role
        DeleteDeveloperRoleCommand deleteDeveloperRoleCommand = new DeleteDeveloperRoleCommand(roleWithDevelopers);
        String expectedResult = String.format(DeleteDeveloperRoleCommand.MESSAGE_CANNOT_DELETE_REPEAT,
                Messages.format(roleWithDevelopers));

        // Assert that the command throws an exception
        assertCommandFailure(deleteDeveloperRoleCommand, model, expectedResult);

        model.deleteDeveloper(developerBuilder.build());
        deleteDeveloperRoleCommand.execute(model);
    }


    @Test
    public void execute_roleNotInList_throwsCommandException() {
        String nonExistingRole = "NonExistingRole";
        DeleteDeveloperRoleCommand deleteDeveloperRoleCommand = new DeleteDeveloperRoleCommand(nonExistingRole);

        assertCommandFailure(deleteDeveloperRoleCommand, model,
                DeleteDeveloperRoleCommand.MESSAGE_CANNOT_DELETE_NONEXISTING
                        + DeleteDeveloperRoleCommand.MESSAGE_EXISTING_DEVELOPERS_ROLES
                        + DeveloperRoles.printRoles());
    }

    @Test
    public void execute_roleIsPredefined_throwsCommandException() {
        String roleWithDevelopers = "Developer";
        DeleteDeveloperRoleCommand deleteDeveloperRoleCommand = new DeleteDeveloperRoleCommand(roleWithDevelopers);
        assertThrows(CommandException.class, () -> deleteDeveloperRoleCommand.execute(model));
    }

    @Test
    public void equals() {
        String firstRole = "Tester";
        String secondRole = "Developer";
        DeleteDeveloperRoleCommand deleteFirstCommand = new DeleteDeveloperRoleCommand(firstRole);
        DeleteDeveloperRoleCommand deleteSecondCommand = new DeleteDeveloperRoleCommand(secondRole);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // role will no longer exists-> returns false
        DeleteDeveloperRoleCommand deleteFirstCommandCopy = new DeleteDeveloperRoleCommand(firstRole);
        assertFalse(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different developer role -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringTest() {
        String roleName = "Tester";
        DeleteDeveloperRoleCommand deleteDeveloperRoleCommand = new DeleteDeveloperRoleCommand(roleName);
        assertEquals(deleteDeveloperRoleCommand.toString(), new DeleteDeveloperRoleCommand(roleName).toString());
    }
}
