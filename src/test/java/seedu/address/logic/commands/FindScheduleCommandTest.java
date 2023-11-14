package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_SCHEDULES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_ALICE_FIRST_JAN;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_ALICE_SECOND_JAN_1;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_ALICE_SECOND_JAN_2;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.schedule.TutorNameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalSchedules;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListTutorCommand.
 */
public class FindScheduleCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalSchedules.getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        TutorNameContainsKeywordsPredicate firstPredicate =
            new TutorNameContainsKeywordsPredicate(Collections.singletonList("first"));
        TutorNameContainsKeywordsPredicate secondPredicate =
            new TutorNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindScheduleCommand findFirstCommand = new FindScheduleCommand(firstPredicate);
        FindScheduleCommand findSecondCommand = new FindScheduleCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindScheduleCommand findFirstCommandCopy = new FindScheduleCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noScheduleFound() {
        String expectedMessage = String.format(MESSAGE_SCHEDULES_LISTED_OVERVIEW, 0);
        TutorNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindScheduleCommand command = new FindScheduleCommand(predicate);
        expectedModel.updateFilteredScheduleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredScheduleList());
    }

    @Test
    public void execute_multipleKeywords_multipleSchedulesFound() {
        String expectedMessage = String.format(MESSAGE_SCHEDULES_LISTED_OVERVIEW, 3);
        TutorNameContainsKeywordsPredicate predicate = preparePredicate("Alice");
        FindScheduleCommand command = new FindScheduleCommand(predicate);
        expectedModel.updateFilteredScheduleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SCHEDULE_ALICE_SECOND_JAN_2, SCHEDULE_ALICE_SECOND_JAN_1, SCHEDULE_ALICE_FIRST_JAN),
            model.getFilteredScheduleList());
    }

    @Test
    public void toStringMethod() {
        TutorNameContainsKeywordsPredicate predicate =
            new TutorNameContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindScheduleCommand findScheduleCommand = new FindScheduleCommand(predicate);
        String expected = FindScheduleCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findScheduleCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code TutorNameContainsKeywordsPredicate}.
     */
    private TutorNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TutorNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
