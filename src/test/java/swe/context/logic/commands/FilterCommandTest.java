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
import swe.context.model.contact.ContainsTagPredicate;
import swe.context.testutil.TestData;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(TestData.Valid.Contact.getTypicalContacts(), new Settings());
    private Model expectedModel = new ModelManager(TestData.Valid.Contact.getTypicalContacts(), new Settings());

    @Test
    public void equals() {
        ContainsTagPredicate firstPredicate =
                new ContainsTagPredicate("first");
        ContainsTagPredicate secondPredicate =
                new ContainsTagPredicate("second");

        FilterCommand findFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand findSecondCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FilterCommand findFirstCommandCopy = new FilterCommand(firstPredicate);
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
        ContainsTagPredicate predicate = preparePredicate(" ");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.setContactsFilter(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredContactList());
    }

    @Test
    public void execute_multipleKeywords_multipleContactsFound() {
        String expectedMessage = Messages.contactsListedOverview(3);
        ContainsTagPredicate predicate = preparePredicate("Friends");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.setContactsFilter(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(
                Arrays.asList(TestData.Valid.Contact.ALICE, TestData.Valid.Contact.BENSON,
                        TestData.Valid.Contact.DANIEL),
                model.getFilteredContactList()
        );
    }

    @Test
    public void toStringMethod() {
        ContainsTagPredicate predicate = new ContainsTagPredicate("keyword");
        FilterCommand findCommand = new FilterCommand(predicate);
        String expected = FilterCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    @Test
    public void execute_partialKeyword_noContactFound() {
        // Test the requirement for full matches by using a substring of a known tag
        String expectedMessage = Messages.contactsListedOverview(0);
        ContainsTagPredicate predicate = preparePredicate("frien"); // 'frien' is a substring of 'Friends'
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.setContactsFilter(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredContactList());
    }

    @Test
    public void execute_mixedCaseKeyword_multipleContactsFound() {
        // Test case insensitivity by mixing case in the keyword
        String expectedMessage = Messages.contactsListedOverview(3);
        ContainsTagPredicate predicate = preparePredicate("fRIeNdS"); // 'Friends' with mixed case
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.setContactsFilter(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TestData.Valid.Contact.ALICE,
                TestData.Valid.Contact.BENSON, TestData.Valid.Contact.DANIEL),
                model.getFilteredContactList());
    }

    @Test
    public void execute_nonExistentKeyword_noContactFound() {
        // Test the behavior when using a tag that does not exist
        String expectedMessage = Messages.contactsListedOverview(0);
        ContainsTagPredicate predicate = preparePredicate("NonExistentTag");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.setContactsFilter(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredContactList());
    }

    /**
     * Parses {@code userInput} into a {@code ContainsTagPredicate}.
     */
    private ContainsTagPredicate preparePredicate(String userInput) {
        return new ContainsTagPredicate(userInput);
    }
}
