package swe.context.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swe.context.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import swe.context.logic.Messages;
import swe.context.model.Model;
import swe.context.model.ModelManager;
import swe.context.model.Settings;
import swe.context.model.contact.NameContainsKeywordsPredicate;
import swe.context.testutil.TestData;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(TestData.Valid.Contact.getTypicalContacts(), new Settings());
    private Model expectedModel = new ModelManager(TestData.Valid.Contact.getTypicalContacts(), new Settings());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different contact -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noContactFound() {
        String expectedMessage = Messages.contactsListedOverview(0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.setContactsFilter(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredContactList());
    }

    @Test
    public void execute_multipleKeywords_multipleContactsFound() {
        String expectedMessage = Messages.contactsListedOverview(3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.setContactsFilter(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(
                Arrays.asList(TestData.Valid.Contact.CARL, TestData.Valid.Contact.ELLE, TestData.Valid.Contact.FIONA),
                model.getFilteredContactList()
        );
    }

    @Test
    public void execute_caseInsensitiveKeyword_contactFound() {
        String expectedMessage = Messages.contactsListedOverview(1);
        NameContainsKeywordsPredicate predicate = preparePredicate("aLiCe");
        FindCommand command = new FindCommand(predicate);
        expectedModel.setContactsFilter(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TestData.Valid.Contact.ALICE), model.getFilteredContactList());
    }

    @Test
    public void execute_partialWordMatch_noContactFound() {
        String expectedMessage = Messages.contactsListedOverview(0);
        NameContainsKeywordsPredicate predicate = preparePredicate("Ali");
        FindCommand command = new FindCommand(predicate);
        expectedModel.setContactsFilter(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredContactList());
    }

    @Test
    public void execute_specialCharactersInKeywords_noContactFound() {
        String expectedMessage = Messages.contactsListedOverview(0);
        NameContainsKeywordsPredicate predicate = preparePredicate("@lice! #Bob");
        FindCommand command = new FindCommand(predicate);
        expectedModel.setContactsFilter(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredContactList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
