package seedu.spendnsplit.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.spendnsplit.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.spendnsplit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.spendnsplit.testutil.TypicalPersons.ALICE;
import static seedu.spendnsplit.testutil.TypicalPersons.BENSON;
import static seedu.spendnsplit.testutil.TypicalPersons.CARL;
import static seedu.spendnsplit.testutil.TypicalPersons.DANIEL;
import static seedu.spendnsplit.testutil.TypicalPersons.ELLE;
import static seedu.spendnsplit.testutil.TypicalPersons.FIONA;
import static seedu.spendnsplit.testutil.TypicalPersons.GEORGE;
import static seedu.spendnsplit.testutil.TypicalSpendNSplitBook.getTypicalSpendNSplitBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.spendnsplit.model.Model;
import seedu.spendnsplit.model.ModelManager;
import seedu.spendnsplit.model.UserPrefs;
import seedu.spendnsplit.model.person.NameContainsKeywordsPredicate;
import seedu.spendnsplit.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPersonCommand}.
 */
public class ListPersonCommandTest {
    private final Model model = new ModelManager(getTypicalSpendNSplitBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalSpendNSplitBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
            new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
            new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        ListPersonCommand listFirstCommand = new ListPersonCommand(firstPredicate);
        ListPersonCommand listSecondCommand = new ListPersonCommand(secondPredicate);

        // same object -> returns true
        assertEquals(listFirstCommand, listFirstCommand);

        // same values -> returns true
        ListPersonCommand listFirstCommandCopy = new ListPersonCommand(firstPredicate);
        assertEquals(listFirstCommand, listFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, listFirstCommand);

        // null -> returns false
        assertNotEquals(null, listFirstCommand);

        // different person -> returns false
        assertNotEquals(listFirstCommand, listSecondCommand);
    }

    @Test
    public void execute_zeroKeywords_allPersonsFound() {
        List<Person> allPersons = Arrays.asList(ALICE, CARL, DANIEL, ELLE, FIONA, GEORGE, BENSON);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, allPersons.size());
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        ListPersonCommand command = new ListPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(allPersons, model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        ListPersonCommand command = new ListPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(List.of("keyword"));
        ListPersonCommand listPersonCommand = new ListPersonCommand(predicate);
        String expected = ListPersonCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, listPersonCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
