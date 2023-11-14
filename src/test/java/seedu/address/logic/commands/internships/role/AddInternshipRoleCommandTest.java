package seedu.address.logic.commands.internships.role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.internship.role.AddInternshipRoleCommand;
import seedu.address.model.ModelManager;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.testutil.TypicalInternshipRoles;



public class AddInternshipRoleCommandTest {
    private ModelManager manager = new ModelManager();

    private final InternshipRole role1 = TypicalInternshipRoles.getTypicalInternshipRole2();

    private final InternshipRole role2 = TypicalInternshipRoles.getTypicalInternshipRole1();

    @BeforeEach
    public void init() {
        manager = new ModelManager();
    }

    @Test
    public void constructor_nullInternshipRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddInternshipRoleCommand(null));
    }

    @Test
    public void execute_internshipRoleAcceptedByModel_addSuccessful() throws CommandException {
        CommandResult cr = new AddInternshipRoleCommand(role1).execute(manager);
        assertEquals(String.format(AddInternshipRoleCommand.MESSAGE_SUCCESS, Messages.format(role1)),
                cr.getFeedbackToUser());
        assertEquals(manager.getFilteredInternshipRoleList(), FXCollections.observableArrayList(role1));
    }

    @Test
    public void execute_internshipRoleDuplicate_failure() {
        manager.addInternshipRole(role1);
        assertThrows(CommandException.class, AddInternshipRoleCommand.MESSAGE_DUPLICATE_ROLE, () ->
                new AddInternshipRoleCommand(role1).execute(manager));
    }

    @Test
    public void equals() {
        AddInternshipRoleCommand addRole1Command = new AddInternshipRoleCommand(role1);
        AddInternshipRoleCommand addRole2Command = new AddInternshipRoleCommand(role2);

        // same object -> returns true
        assertTrue(addRole1Command.equals(addRole1Command));

        // same values -> returns true
        AddInternshipRoleCommand addRole1CommandCopy =
                new AddInternshipRoleCommand(TypicalInternshipRoles.getTypicalInternshipRole2());
        assertTrue(addRole1Command.equals(addRole1CommandCopy));

        // different types -> returns false
        assertFalse(addRole1Command.equals(1));

        // null -> returns false
        assertFalse(addRole1Command.equals(null));

        // different person -> returns false
        assertFalse(addRole1Command.equals(addRole2Command));
    }

    @Test
    public void toStringMethod() {
        AddInternshipRoleCommand addCommand = new AddInternshipRoleCommand(role1);
        String expected = AddInternshipRoleCommand.class.getCanonicalName() + "{toAdd=" + role1 + "}";
        assertEquals(expected, addCommand.toString());
    }

}
