package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTeams.getTypicalTeamBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.testutil.TypicalPersons;
import seedu.address.testutil.TypicalTeams;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteDeveloperFromTeamCommand}.
 */
public class DeleteDeveloperFromTeamCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTeamBook(), new UserPrefs());

    @Test
    public void execute_validDeveloperAndTeamUnfilteredList_success() throws CommandException {
        Name developerToDelete = TypicalPersons.DANIEL.getName();
        String teamName = TypicalTeams.TEAM1.getTeamName();
        DeleteDeveloperFromTeamCommand deleteDevCommand = new DeleteDeveloperFromTeamCommand(
                                                                teamName, developerToDelete);

        CommandResult commandResult = deleteDevCommand.execute(model);

        assertEquals(String.format(DeleteDeveloperFromTeamCommand.MESSAGE_DELETE_DEVELOPER_FROM_TEAM_SUCCESS,
                                    developerToDelete.toString()), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidTeamNameUnfilteredList_throwsCommandException() {
        Name developerToDelete = TypicalPersons.ALICE.getName();
        String teamName = "Invalid Team";
        DeleteDeveloperFromTeamCommand deleteDevCommand = new DeleteDeveloperFromTeamCommand(
                                                                teamName, developerToDelete);

        assertCommandFailure(deleteDevCommand, model, Messages.MESSAGE_INVALID_TEAM_NAME_DISPLAYED);
    }

    @Test
    public void execute_invalidDeveloperUnfilteredList_throwsCommandException() {
        Name developerToDelete = new Name("Invalid Name");
        String teamName = TypicalTeams.TEAM2.getTeamName();
        DeleteDeveloperFromTeamCommand deleteDevCommand = new DeleteDeveloperFromTeamCommand(
                                                                teamName, developerToDelete);

        assertCommandFailure(deleteDevCommand, model, Messages.MESSAGE_INVALID_PERSON);
    }

    @Test
    public void execute_developerNotInTeamUnfilteredList_throwsCommandException() {
        Name developerToDelete = TypicalPersons.DANIEL.getName();
        String teamName = TypicalTeams.TEAM2.getTeamName(); // Assuming DANIEL is not in TEAM2
        DeleteDeveloperFromTeamCommand deleteDevCommand = new DeleteDeveloperFromTeamCommand(
                                                                teamName, developerToDelete);

        assertCommandFailure(deleteDevCommand, model, Messages.MESSAGE_INVALID_PERSON_IN_TEAM);
    }

    @Test
    public void equals() {
        Name developer1 = TypicalPersons.CARL.getName();
        Name developer2 = TypicalPersons.DANIEL.getName();
        String teamName = TypicalTeams.TEAM1.getTeamName();

        DeleteDeveloperFromTeamCommand deleteDevCommand1 = new DeleteDeveloperFromTeamCommand(teamName, developer1);
        DeleteDeveloperFromTeamCommand deleteDevCommand2 = new DeleteDeveloperFromTeamCommand(teamName, developer2);

        // same object -> returns true
        assertTrue(deleteDevCommand2.equals(deleteDevCommand2));

        // same values -> returns true
        DeleteDeveloperFromTeamCommand deleteDevCommand1Copy = new DeleteDeveloperFromTeamCommand(teamName, developer1);
        assertTrue(deleteDevCommand1.equals(deleteDevCommand1Copy));

        // different types -> returns false
        assertFalse(deleteDevCommand1.equals(1));

        // null -> returns false
        assertFalse(deleteDevCommand1.equals(null));

        // different team name -> returns false
        // Assuming developer2 does not exist in TEAM2
        String differentTeamName = TypicalTeams.TEAM2.getTeamName();
        DeleteDeveloperFromTeamCommand deleteDevFromDiffTeam = new DeleteDeveloperFromTeamCommand(
                                                                    differentTeamName, developer2);
        assertFalse(deleteDevCommand1.equals(deleteDevFromDiffTeam));

        // different developer name -> returns false
        // Assuming developer2 does not exist in TEAM2
        DeleteDeveloperFromTeamCommand deleteDeveloperCommand1DiffDeveloper = new DeleteDeveloperFromTeamCommand(
                                                                                    differentTeamName, developer2);
        assertFalse(deleteDevCommand1.equals(deleteDeveloperCommand1DiffDeveloper));
    }

    @Test
    public void toStringMethod() {
        Name developerToDelete = TypicalPersons.ALICE.getName();
        String teamName = TypicalTeams.TEAM1.getTeamName();
        DeleteDeveloperFromTeamCommand deleteDeveloperCommand = new DeleteDeveloperFromTeamCommand(
                                                                    teamName, developerToDelete);
        String expected = DeleteDeveloperFromTeamCommand.class.getCanonicalName()
                                    + "{developerToDelete=" + developerToDelete + ", teamName=" + teamName + "}";
        assertEquals(expected, deleteDeveloperCommand.toString());
    }
}

