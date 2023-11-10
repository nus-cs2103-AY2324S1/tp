package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_TUTEES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NameSubjectPredicate;
import seedu.address.model.person.SubjectContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        SubjectContainsKeywordsPredicate secondPredicate =
                new SubjectContainsKeywordsPredicate(Collections.singletonList("second"));
        NameContainsKeywordsPredicate thirdPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("third"));
        SubjectContainsKeywordsPredicate fourthPredicate =
                new SubjectContainsKeywordsPredicate(Collections.singletonList("fourth"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate, secondPredicate);
        FindCommand findSecondCommand = new FindCommand(thirdPredicate, fourthPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate, secondPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_TUTEES_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        SubjectContainsKeywordsPredicate subject = prepareSubject("Biology Maths English");
        FindCommand command = new FindCommand(predicate, subject);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        SubjectContainsKeywordsPredicate subject = new SubjectContainsKeywordsPredicate(Arrays.asList("subject"));
        FindCommand findCommand = new FindCommand(predicate, subject);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private SubjectContainsKeywordsPredicate prepareSubject(String userInput) {
        return new SubjectContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    private NameSubjectPredicate prepareNameSubject(String nameInput, String subjectInput) {
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Arrays.asList(nameInput.split("\\s+")));

        SubjectContainsKeywordsPredicate subject =
                new SubjectContainsKeywordsPredicate(Arrays.asList(subjectInput.split("\\s+")));
        return new NameSubjectPredicate(predicate, subject);
    }
}
