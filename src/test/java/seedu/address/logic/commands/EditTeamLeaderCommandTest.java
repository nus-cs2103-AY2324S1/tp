package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTeams.getTypicalTeamBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;

public class EditTeamLeaderCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTeamBook(), new UserPrefs());

    @Test
    public void execute_invalidTeamName_throwsCommandException() {
        String TeamName = "TEAM4";
        Name newTeamLeaderName = new Name("Daniel Meier");

        EditTeamLeaderCommand editTeamLeaderCommand = new EditTeamLeaderCommand(TeamName, newTeamLeaderName);

        assertCommandFailure(editTeamLeaderCommand, model, EditTeamLeaderCommand.MESSAGE_TEAM_NOT_FOUND);
    }

    @Test
    public void execute_invalidTeamLeaderName_throwsCommandException() {
        String TeamName = "TEAM1";
        Name newTeamLeaderName = new Name("Robert Clark");

        EditTeamLeaderCommand editTeamLeaderCommand = new EditTeamLeaderCommand(TeamName, newTeamLeaderName);

        assertCommandFailure(editTeamLeaderCommand, model, EditTeamLeaderCommand.MESSAGE_TEAM_LEADER_NOT_FOUND);
    }

    @Test
    public void execute_notChangingTeamLeaderName_throwsCommandException() {
        String TeamName = "TEAM1";
        Name newTeamLeaderName = new Name("Alice Pauline");

        EditTeamLeaderCommand editTeamLeaderCommand = new EditTeamLeaderCommand(TeamName, newTeamLeaderName);

        assertCommandFailure(editTeamLeaderCommand, model, EditTeamLeaderCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void toString_correctStringRepresentation() {
        String TeamName = "TEAM1";
        Name newTeamLeaderName = new Name("Alice Pauline");

        EditTeamLeaderCommand editTeamLeaderCommand = new EditTeamLeaderCommand(TeamName, newTeamLeaderName);

        String expectedString = "seedu.address.logic.commands.EditTeamLeaderCommand{"
                + "Team Name=TEAM1, New Team Leader Name=Alice Pauline}";

        assertEquals(expectedString, editTeamLeaderCommand.toString());
    }

    @Test
    public void equals() {
        String TeamName = "TEAM1";
        Name newTeamLeaderName = new Name("Alice Pauline");

        final EditTeamLeaderCommand originalCommand = new EditTeamLeaderCommand(TeamName, newTeamLeaderName);

        EditTeamLeaderCommand duplicateCommand = new EditTeamLeaderCommand(TeamName, newTeamLeaderName);

        // same values -> returns true
        assertEquals(originalCommand, duplicateCommand);

        // same object -> returns true
        assertEquals(originalCommand, originalCommand);

        // null -> returns false
        assertFalse(originalCommand.equals(null));

        // different types -> returns false
        assertFalse(originalCommand.equals(new ClearCommand()));

        // different team name -> returns false
        assertFalse(originalCommand.equals(new EditTeamLeaderCommand("TEAM2", newTeamLeaderName)));
    }
}
