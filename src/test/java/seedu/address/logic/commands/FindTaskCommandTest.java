package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.AGENDA;
import static seedu.address.testutil.TypicalTasks.BUDGET;
import static seedu.address.testutil.TypicalTasks.CATERING;
import static seedu.address.testutil.TypicalTasks.DRAFT;
import static seedu.address.testutil.TypicalTasks.ENTERTAINMENT;
import static seedu.address.testutil.TypicalTasks.FUNDING;
import static seedu.address.testutil.TypicalTasks.GUESTLIST;
import static seedu.address.testutil.TypicalTasks.HOSPITALITY;
import static seedu.address.testutil.TypicalTasks.INVITATION;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TaskContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTaskCommand}.
 */
public class FindTaskCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TaskContainsKeywordsPredicate firstPredicate =
                new TaskContainsKeywordsPredicate(Collections.singletonList("first"));
        TaskContainsKeywordsPredicate secondPredicate =
                new TaskContainsKeywordsPredicate(Collections.singletonList("second"));

        FindTaskCommand findFirstCommand = new FindTaskCommand(firstPredicate);
        FindTaskCommand findSecondCommand = new FindTaskCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindTaskCommand findFirstCommandCopy = new FindTaskCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);
        assertNotEquals(findFirstCommand, 1);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different values -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_zeroKeywords_noTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindTaskCommand command = new FindTaskCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleKeywords_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        TaskContainsKeywordsPredicate predicate = preparePredicate("Send and");
        FindTaskCommand command = new FindTaskCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DRAFT, INVITATION), model.getFilteredTaskList());
    }

    @Test
    public void execute_oneKeyword_searchesInBothTitleAndNote() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        TaskContainsKeywordsPredicate predicate = preparePredicate("Book");
        FindTaskCommand command = new FindTaskCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(AGENDA, CATERING, ENTERTAINMENT), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleKeywords_searchesInBothTitleAndNote() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 6);
        TaskContainsKeywordsPredicate predicate = preparePredicate("Prepare CS2103T");
        FindTaskCommand command = new FindTaskCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(
                Arrays.asList(AGENDA, BUDGET, FUNDING, GUESTLIST, HOSPITALITY, INVITATION),
                model.getFilteredTaskList());
    }

    @Test
    public void toStringMethod() {
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate(List.of("keyword"));
        FindTaskCommand findTaskCommand = new FindTaskCommand(predicate);
        String expected = FindTaskCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findTaskCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code TaskContainsKeywordsPredicate}.
     */
    private TaskContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TaskContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
