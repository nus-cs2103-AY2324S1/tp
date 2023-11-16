package seedu.address.logic.commands.find;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertClientCommandSuccess;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BENSON;
import static seedu.address.testutil.TypicalClients.CARL;
import static seedu.address.testutil.TypicalClients.DANIEL;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.NameClientContainsKeywordsPredicate;
import seedu.address.model.client.OrganisationContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindClientCommand}.
 */
public class FindClientCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameClientContainsKeywordsPredicate firstPredicate =
                new NameClientContainsKeywordsPredicate(Collections.singletonList("first"));
        NameClientContainsKeywordsPredicate secondPredicate =
                new NameClientContainsKeywordsPredicate(Collections.singletonList("second"));

        FindClientCommand findFirstCommand = new FindClientCommand(firstPredicate);
        FindClientCommand findSecondCommand = new FindClientCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindClientCommand findFirstCommandCopy = new FindClientCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different client -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = "There are no clients with matching information.";
        NameClientContainsKeywordsPredicate predicate = prepareNamePredicate("hii");
        FindClientCommand command = new FindClientCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        assertClientCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredClientList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = "These are the 3 clients with matching information.";
        NameClientContainsKeywordsPredicate predicate = prepareNamePredicate("Alice Benson Carl");
        FindClientCommand command = new FindClientCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        assertClientCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL), model.getFilteredClientList());
    }

    @Test
    public void toStringMethod() {
        NameClientContainsKeywordsPredicate predicate =
                new NameClientContainsKeywordsPredicate(List.of("keyword"));
        FindClientCommand findClientCommand = new FindClientCommand(predicate);
        String expected = FindClientCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findClientCommand.toString());
    }

    @Test
    public void execute_caseInsensitiveSearch_clientFound() {
        String expectedMessage = "This is the 1 client with matching information.";
        OrganisationContainsKeywordsPredicate predicate = prepareOrganisationPredicate("Microsoft"); // using mixed case
        FindClientCommand command = new FindClientCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        assertClientCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL), model.getFilteredClientList());
    }

    @Test
    public void execute_partialName_clientFound() {
        String expectedMessage = "This is the 1 client with matching information.";
        NameClientContainsKeywordsPredicate predicate = prepareNamePredicate("rl");
        FindClientCommand command = new FindClientCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        assertClientCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL), model.getFilteredClientList());
    }

    @Test
    public void execute_partialOrganisation_clientFound() {
        String expectedMessage = "This is the 1 client with matching information.";
        OrganisationContainsKeywordsPredicate predicate = prepareOrganisationPredicate("Micro");
        FindClientCommand command = new FindClientCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        assertClientCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL), model.getFilteredClientList());
    }

    @Test
    public void execute_noMatchingClientName_noClientFound() {
        String expectedMessage = "There are no clients with matching information.";
        NameClientContainsKeywordsPredicate predicate = prepareNamePredicate("NonExistentClient");
        FindClientCommand command = new FindClientCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        assertClientCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredClientList());
    }

    @Test
    public void execute_noMatchingClientOrganisation_noClientFound() {
        String expectedMessage = "There are no clients with matching information.";
        OrganisationContainsKeywordsPredicate predicate = prepareOrganisationPredicate("NonExistentClient");
        FindClientCommand command = new FindClientCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        assertClientCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredClientList());
    }

    /**
     * Parses {@code userInput} into a {@code NameClientContainsKeywordsPredicate}.
     */
    private NameClientContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameClientContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code OrganisationClientContainsKeywordsPredicate}.
     */
    private OrganisationContainsKeywordsPredicate prepareOrganisationPredicate(String userInput) {
        return new OrganisationContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
