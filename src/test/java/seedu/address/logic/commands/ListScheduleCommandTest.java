package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_SCHEDULES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_ALICE_FIRST_JAN;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_ALICE_SECOND_JAN;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_BOB_SECOND_JAN;

import java.util.ArrayList;
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
public class ListScheduleCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalSchedules.getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListScheduleCommand(new TutorNameContainsKeywordsPredicate(new ArrayList<>())),
            model, ListScheduleCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        TutorNameContainsKeywordsPredicate firstPredicate =
            new TutorNameContainsKeywordsPredicate(Collections.singletonList("first"));
        TutorNameContainsKeywordsPredicate secondPredicate =
            new TutorNameContainsKeywordsPredicate(Collections.singletonList("second"));

        ListScheduleCommand findFirstCommand = new ListScheduleCommand(firstPredicate);
        ListScheduleCommand findSecondCommand = new ListScheduleCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        ListScheduleCommand findFirstCommandCopy = new ListScheduleCommand(firstPredicate);
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
        TutorNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        ListScheduleCommand command = new ListScheduleCommand(predicate);
        expectedModel.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
        assertCommandSuccess(command, model, ListScheduleCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(Arrays.asList(SCHEDULE_BOB_SECOND_JAN, SCHEDULE_ALICE_SECOND_JAN, SCHEDULE_ALICE_FIRST_JAN),
            model.getFilteredScheduleList());
    }

    @Test
    public void execute_multipleKeywords_multipleSchedulesFound() {
        String expectedMessage = String.format(MESSAGE_SCHEDULES_LISTED_OVERVIEW, 2);
        TutorNameContainsKeywordsPredicate predicate = preparePredicate("Alice");
        ListScheduleCommand command = new ListScheduleCommand(predicate);
        expectedModel.updateFilteredScheduleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SCHEDULE_ALICE_SECOND_JAN, SCHEDULE_ALICE_FIRST_JAN),
            model.getFilteredScheduleList());
    }

    @Test
    public void toStringMethod() {
        TutorNameContainsKeywordsPredicate predicate =
            new TutorNameContainsKeywordsPredicate(Arrays.asList("keyword"));
        ListScheduleCommand listScheduleCommand = new ListScheduleCommand(predicate);
        String expected = ListScheduleCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, listScheduleCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code TutorNameContainsKeywordsPredicate}.
     */
    private TutorNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TutorNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
