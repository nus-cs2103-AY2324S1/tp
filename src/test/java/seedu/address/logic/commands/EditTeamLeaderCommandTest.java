package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTeams.getTypicalTeamBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TeamBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;

public class EditTeamLeaderCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTeamBook(), new UserPrefs());

    @Test
    public void execute_invalidTeamName_throwsCommandException() {
        String teamName = "TEAM4";
        Name newTeamLeaderName = new Name("Daniel Meier");

        EditTeamLeaderCommand editTeamLeaderCommand = new EditTeamLeaderCommand(teamName, newTeamLeaderName);

        assertCommandFailure(editTeamLeaderCommand, model, EditTeamLeaderCommand.MESSAGE_TEAM_NOT_FOUND);
    }

    @Test
    public void execute_invalidTeamLeaderName_throwsCommandException() {
        String teamName = "TEAM1";
        Name newTeamLeaderName = new Name("Robert Clark");

        EditTeamLeaderCommand editTeamLeaderCommand = new EditTeamLeaderCommand(teamName, newTeamLeaderName);

        assertCommandFailure(editTeamLeaderCommand, model, EditTeamLeaderCommand.MESSAGE_TEAM_LEADER_NOT_FOUND);
    }

    @Test
    public void execute_notChangingTeamLeaderName_throwsCommandException() {
        String teamName = "TEAM1";
        Name newTeamLeaderName = model.getTeamLeaderOfTeam(teamName);

        EditTeamLeaderCommand editTeamLeaderCommand = new EditTeamLeaderCommand(teamName, newTeamLeaderName);

        assertCommandFailure(editTeamLeaderCommand, model, EditTeamLeaderCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void toString_correctStringRepresentation() {
        String teamName = "TEAM1";
        Name newTeamLeaderName = new Name("Alice Pauline");

        EditTeamLeaderCommand editTeamLeaderCommand = new EditTeamLeaderCommand(teamName, newTeamLeaderName);

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

    @Test
    public void execute_validTeamName_success() {
        String teamName = "TEAM1";
        Name originalTeamLeaderName = model.getTeamLeaderOfTeam(teamName);
        Name newTeamLeaderName = new Name("Daniel Meier");

        EditTeamLeaderCommand editTeamLeaderCommand = new EditTeamLeaderCommand(teamName, newTeamLeaderName);

        String expectedMessage = String.format(EditTeamLeaderCommand.MESSAGE_EDIT_TEAM_LEADER_SUCCESS,
                Messages.editTeamLeaderFormat(teamName, originalTeamLeaderName, newTeamLeaderName));

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new TeamBook(model.getTeamBook()), new UserPrefs());

        assertCommandSuccess(editTeamLeaderCommand, model, expectedMessage, expectedModel);



        //Change the teamLeader back to original
        EditTeamLeaderCommand reverseEditTeamLeaderCommand = new EditTeamLeaderCommand(teamName, originalTeamLeaderName);

        String expectedReverseMessage = String.format(EditTeamLeaderCommand.MESSAGE_EDIT_TEAM_LEADER_SUCCESS,
                Messages.editTeamLeaderFormat(teamName, newTeamLeaderName, originalTeamLeaderName));

        Model expectedReverseModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new TeamBook(model.getTeamBook()), new UserPrefs());

        assertCommandSuccess(reverseEditTeamLeaderCommand, model, expectedReverseMessage, expectedReverseModel);
    }
}
