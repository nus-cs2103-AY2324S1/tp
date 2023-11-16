package seedu.address.logic.commands.find;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertProjectCommandSuccess;
import static seedu.address.testutil.TypicalProjects.PROJECT_A;
import static seedu.address.testutil.TypicalProjects.PROJECT_A_NO_SPACING;
import static seedu.address.testutil.TypicalProjects.PROJECT_B;
import static seedu.address.testutil.TypicalProjects.PROJECT_C;
import static seedu.address.testutil.TypicalProjects.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.DeadlineContainsKeywordsPredicate;
import seedu.address.model.project.DescriptionContainsKeywordsPredicate;
import seedu.address.model.project.ProjectNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindProjectCommand}.
 */
public class FindProjectCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ProjectNameContainsKeywordsPredicate firstPredicate =
                new ProjectNameContainsKeywordsPredicate(Collections.singletonList("first"));
        ProjectNameContainsKeywordsPredicate secondPredicate =
                new ProjectNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindProjectCommand findFirstCommand = new FindProjectCommand(firstPredicate);
        FindProjectCommand findSecondCommand = new FindProjectCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindProjectCommand findFirstCommandCopy = new FindProjectCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different project -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_zeroKeywords_noProjectFound() {
        String expectedMessage = "There are no projects with matching information.";
        ProjectNameContainsKeywordsPredicate predicate = prepareNamePredicate("hii");
        FindProjectCommand command = new FindProjectCommand(predicate);
        expectedModel.updateFilteredProjectList(predicate);
        assertProjectCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredProjectList());
    }

    @Test
    public void execute_multipleKeywords_multipleProjectsFound() {
        String expectedMessage = "These are the 4 projects with matching information.";
        ProjectNameContainsKeywordsPredicate predicate = prepareNamePredicate("Project A B C");
        FindProjectCommand command = new FindProjectCommand(predicate);
        expectedModel.updateFilteredProjectList(predicate);
        assertProjectCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROJECT_A, PROJECT_B, PROJECT_C, PROJECT_A_NO_SPACING),
                model.getFilteredProjectList());
    }

    @Test
    public void toStringMethod() {
        ProjectNameContainsKeywordsPredicate predicate =
                new ProjectNameContainsKeywordsPredicate(List.of("keyword"));
        FindProjectCommand findProjectCommand = new FindProjectCommand(predicate);
        String expected = FindProjectCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findProjectCommand.toString());
    }

    @Test
    public void execute_caseInsensitiveSearch_projectFound() {
        String expectedMessage = "These are the 2 projects with matching information.";
        ProjectNameContainsKeywordsPredicate predicate = prepareNamePredicate("a"); // using mixed case
        FindProjectCommand command = new FindProjectCommand(predicate);
        expectedModel.updateFilteredProjectList(predicate);
        assertProjectCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROJECT_A, PROJECT_A_NO_SPACING), model.getFilteredProjectList());
    }

    @Test
    public void execute_caseInsensitiveDeadlineSearch_projectFound() {
        String expectedMessage = "These are the 3 projects with matching information.";
        DeadlineContainsKeywordsPredicate predicate = prepareDeadlinePredicate("design");
        FindProjectCommand command = new FindProjectCommand(predicate);
        expectedModel.updateFilteredProjectList(predicate);
        assertProjectCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROJECT_A, PROJECT_B, PROJECT_A_NO_SPACING), model.getFilteredProjectList());
    }

    @Test
    public void execute_caseInsensitiveDescriptionSearch_projectFound() {
        String expectedMessage = "These are the 4 projects with matching information.";
        DescriptionContainsKeywordsPredicate predicate = prepareDescriptionPredicate("this is project");
        FindProjectCommand command = new FindProjectCommand(predicate);
        expectedModel.updateFilteredProjectList(predicate);
        assertProjectCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROJECT_A, PROJECT_B, PROJECT_C, PROJECT_A_NO_SPACING),
                model.getFilteredProjectList());
    }


    @Test
    public void execute_partialName_projectFound() {
        String expectedMessage = "This is the 1 project with matching information.";
        ProjectNameContainsKeywordsPredicate predicate = prepareNamePredicate("jecta");
        FindProjectCommand command = new FindProjectCommand(predicate);
        expectedModel.updateFilteredProjectList(predicate);
        assertProjectCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROJECT_A_NO_SPACING), model.getFilteredProjectList());
    }

    @Test
    public void execute_partialDeadline_projectFound() {
        String expectedMessage = "These are the 4 projects with matching information.";
        DeadlineContainsKeywordsPredicate predicate = prepareDeadlinePredicate("12-2023");
        FindProjectCommand command = new FindProjectCommand(predicate);
        expectedModel.updateFilteredProjectList(predicate);
        assertProjectCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROJECT_A, PROJECT_B, PROJECT_C, PROJECT_A_NO_SPACING),
                model.getFilteredProjectList());
    }

    @Test
    public void execute_partialDescription_projectFound() {
        String expectedMessage = "This is the 1 project with matching information.";
        DescriptionContainsKeywordsPredicate predicate = prepareDescriptionPredicate("b");
        FindProjectCommand command = new FindProjectCommand(predicate);
        expectedModel.updateFilteredProjectList(predicate);
        assertProjectCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROJECT_B), model.getFilteredProjectList());
    }

    @Test
    public void execute_noMatchingProjects_noProjectFound() {
        String expectedMessage = "There are no projects with matching information.";
        ProjectNameContainsKeywordsPredicate predicate = prepareNamePredicate("NonExistentProject");
        FindProjectCommand command = new FindProjectCommand(predicate);
        expectedModel.updateFilteredProjectList(predicate);
        assertProjectCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredProjectList());
    }

    /**
     * Parses {@code userInput} into a {@code ProjectNameContainsKeywordsPredicate}.
     */
    private ProjectNameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new ProjectNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    private DeadlineContainsKeywordsPredicate prepareDeadlinePredicate(String userInput) {
        return new DeadlineContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    private DescriptionContainsKeywordsPredicate prepareDescriptionPredicate(String userInput) {
        return new DescriptionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
