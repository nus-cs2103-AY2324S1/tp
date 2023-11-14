package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTeams.TEAM1;
import static seedu.address.testutil.TypicalTeams.TEAM2;
import static seedu.address.testutil.TypicalTeams.getTypicalTeamBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.team.TeamContainsKeywordsPredicate;

public class FindTeamCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTeamBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTeamBook(), new UserPrefs());

    @Test
    public void equals() {
        TeamContainsKeywordsPredicate firstPredicate =
                new TeamContainsKeywordsPredicate(Collections.singletonList("first"));
        TeamContainsKeywordsPredicate secondPredicate =
                new TeamContainsKeywordsPredicate(Collections.singletonList("second"));

        FindTeamCommand findFirstCommand = new FindTeamCommand(firstPredicate);
        FindTeamCommand findSecondCommand = new FindTeamCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindTeamCommand findFirstCommandCopy = new FindTeamCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different team -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTeamFound() {
        String expectedMessage = "0 team listed!";
        TeamContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindTeamCommand command = new FindTeamCommand(predicate);
        expectedModel.updateFilteredTeamList(predicate);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage,
                false, false, false, false, false, false, true, false);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTeamList());
    }

    @Test
    public void execute_multipleKeywords_oneTeamsFound() {
        String expectedMessage = "1 team listed!";
        TeamContainsKeywordsPredicate predicate = preparePredicate("TEAM1");
        FindTeamCommand command = new FindTeamCommand(predicate);
        expectedModel.updateFilteredTeamList(predicate);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage,
                false, false, false, false, false, false, true, false);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(TEAM1), model.getFilteredTeamList());
    }
    @Test
    public void execute_multipleKeywords_multipleTeamsFound() {
        String expectedMessage = "2 teams listed!";
        TeamContainsKeywordsPredicate predicate = preparePredicate("TEAM1 TEAM2");
        FindTeamCommand command = new FindTeamCommand(predicate);
        expectedModel.updateFilteredTeamList(predicate);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage,
                false, false, false, false, false, false, true, false);

        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(TEAM1, TEAM2), model.getFilteredTeamList());
    }

    @Test
    public void toStringMethod() {
        TeamContainsKeywordsPredicate predicate = new TeamContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindTeamCommand findTeamCommand = new FindTeamCommand(predicate);
        String expected = FindTeamCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findTeamCommand.toString());
    }

    private TeamContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TeamContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
