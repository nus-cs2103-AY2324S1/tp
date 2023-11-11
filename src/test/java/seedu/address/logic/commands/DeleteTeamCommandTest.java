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
import seedu.address.testutil.TypicalTeams;

public class DeleteTeamCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTeamBook(), new UserPrefs());

    @Test
    public void execute_validTeamNameUnfilteredList_success() throws CommandException {
        String teamNameToDelete = TypicalTeams.TEAM1.getTeamName();
        DeleteTeamCommand deleteTeamCommand = new DeleteTeamCommand(teamNameToDelete);

        CommandResult commandResult = deleteTeamCommand.execute(model);

        assertEquals(String.format(DeleteTeamCommand.MESSAGE_DELETE_TEAM_SUCCESS, teamNameToDelete),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidTeamNameUnfilteredList_throwsCommandException() {
        String teamNameToDelete = "Invalid Team";
        DeleteTeamCommand deleteTeamCommand = new DeleteTeamCommand(teamNameToDelete);

        assertCommandFailure(deleteTeamCommand, model, Messages.MESSAGE_INVALID_TEAM_NAME_DISPLAYED);
    }

    @Test
    public void equals() {
        DeleteTeamCommand deleteTeamCommand1 = new DeleteTeamCommand(TypicalTeams.TEAM1.getTeamName());
        DeleteTeamCommand deleteTeamCommand2 = new DeleteTeamCommand(TypicalTeams.TEAM2.getTeamName());

        // same object -> returns true
        assertTrue(deleteTeamCommand1.equals(deleteTeamCommand1));

        // same values -> returns true
        DeleteTeamCommand deleteTeamCommand1Copy = new DeleteTeamCommand(TypicalTeams.TEAM1.getTeamName());
        assertTrue(deleteTeamCommand1.equals(deleteTeamCommand1Copy));

        // different types -> returns false
        assertFalse(deleteTeamCommand1.equals(1));

        // null -> returns false
        assertFalse(deleteTeamCommand1.equals(null));

        // different team name -> returns false
        assertFalse(deleteTeamCommand1.equals(deleteTeamCommand2));
    }

    @Test
    public void toStringMethod() {
        String teamNameToDelete = TypicalTeams.TEAM1.getTeamName();
        DeleteTeamCommand deleteTeamCommand = new DeleteTeamCommand(teamNameToDelete);
        String expected = DeleteTeamCommand.class.getCanonicalName() + "{teamToDelete=" + teamNameToDelete + "}";
        assertEquals(expected, deleteTeamCommand.toString());
    }
}
