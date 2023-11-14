package seedu.address.logic.commands.findcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameAndTagContainKeywordsPredicate;

public class FindPersonCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameAndTagContainKeywordsPredicate firstPredicate =
            new NameAndTagContainKeywordsPredicate(Arrays.asList("Alice"), Arrays.asList("friend"));
        NameAndTagContainKeywordsPredicate secondPredicate =
            new NameAndTagContainKeywordsPredicate(Arrays.asList("Bob"), Arrays.asList("colleague"));
        NameAndTagContainKeywordsPredicate firstPredicateCopy =
            new NameAndTagContainKeywordsPredicate(Arrays.asList("Alice"), Arrays.asList("friend"));

        FindPersonCommand findFirstCommand = new FindPersonCommand(firstPredicate);
        FindPersonCommand findSecondCommand = new FindPersonCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindPersonCommand findFirstCommandCopy = new FindPersonCommand(firstPredicate);
        FindPersonCommand findFirstCommandCopyCoupled = new FindPersonCommand(firstPredicateCopy);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
        assertTrue(findFirstCommand.equals(findFirstCommandCopyCoupled));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameAndTagContainKeywordsPredicate predicate = preparePredicate(" ", " ");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        CommandTestUtil.assertRegularCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(0, model.getFilteredPersonList().size());
    }

    @Test
    public void execute_multipleNameKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2); // Alice and Benson match
        NameAndTagContainKeywordsPredicate predicate = preparePredicate("Alice Benson", " ");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        CommandTestUtil.assertRegularCommandSuccess(command, model, expectedMessage, expectedModel);

        // Check that the actual persons returned match Alice and Benson
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleTagKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        // Alice, Benson, and Daniel have "friends" tag
        NameAndTagContainKeywordsPredicate predicate = preparePredicate(" ", "friends");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        CommandTestUtil.assertRegularCommandSuccess(command, model, expectedMessage, expectedModel);

        // Check that the actual persons returned have the "friends" tag
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameAndTagKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        // Only Benson matches both name and tag criteria
        NameAndTagContainKeywordsPredicate predicate = preparePredicate("Benson", "owesMoney");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        CommandTestUtil.assertRegularCommandSuccess(command, model, expectedMessage, expectedModel);

        // Check that only Benson is returned, as he matches both name and tag criteria
        assertEquals(Collections.singletonList(BENSON), model.getFilteredPersonList());
    }

    /**
     * Parses {@code nameInput} and {@code tagInput} into a {@code NameAndTagContainKeywordsPredicate}.
     */
    private NameAndTagContainKeywordsPredicate preparePredicate(String nameInput, String tagInput) {
        return new NameAndTagContainKeywordsPredicate(
            Arrays.asList(nameInput.split("\\s+")),
            Arrays.asList(tagInput.split("\\s+"))
        );
    }
}
