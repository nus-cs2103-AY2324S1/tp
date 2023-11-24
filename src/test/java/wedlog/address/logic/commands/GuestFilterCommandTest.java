package wedlog.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static wedlog.address.logic.Messages.MESSAGE_GUESTS_LISTED_OVERVIEW;
import static wedlog.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static wedlog.address.testutil.TypicalGuests.GINA;
import static wedlog.address.testutil.TypicalGuests.getTypicalAddressBook;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import wedlog.address.model.Model;
import wedlog.address.model.ModelManager;
import wedlog.address.model.UserPrefs;
import wedlog.address.model.person.Guest;
import wedlog.address.model.person.NamePredicate;
import wedlog.address.testutil.Assert;

class GuestFilterCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        List<Predicate<? super Guest>> firstPredicate =
                Collections.singletonList(new NamePredicate("keyword1"));
        List<Predicate<? super Guest>> secondPredicate =
                Collections.singletonList(new NamePredicate("keyword2"));

        GuestFilterCommand filterFirstCommand = new GuestFilterCommand(firstPredicate);
        GuestFilterCommand filterSecondCommand = new GuestFilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        List<Predicate<? super Guest>> firstPredicateCopy =
                Collections.singletonList(new NamePredicate("keyword1"));
        GuestFilterCommand filterFirstCommandCopy = new GuestFilterCommand(firstPredicateCopy);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void testAssertionPersonNonNull() {
        NamePredicate predicate = prepareNamePredicate("Alice");
        List<Predicate<? super Guest>> predicates = Collections.singletonList(predicate);

        // Non null scenario
        assertTrue(new GuestFilterCommand(predicates) instanceof GuestFilterCommand);

        // Heuristic: No more than 1 invalid input in a test case
        // Null scenario
        List<Predicate<? super Guest>> nullPredicates = null;
        String expectedErrMsg = "Predicates passed to GuestFilterCommand should not be null!";
        Assert.assertThrows(AssertionError.class,
                expectedErrMsg, () -> new GuestFilterCommand(nullPredicates));
    }

    @Test
    public void execute_noKeywords_noGuestFound() {
        String expectedMessage = String.format(MESSAGE_GUESTS_LISTED_OVERVIEW, 0);
        NamePredicate predicate = prepareNamePredicate(" ");
        GuestFilterCommand command = new GuestFilterCommand(Collections.singletonList(predicate));
        expectedModel.updateFilteredGuestList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredGuestList());
    }

    @Test
    public void execute_singleKeyword_singleGuestFound() {
        String expectedMessage = String.format(MESSAGE_GUESTS_LISTED_OVERVIEW, 1);
        NamePredicate predicate = prepareNamePredicate("gina");
        GuestFilterCommand command = new GuestFilterCommand(Collections.singletonList(predicate));
        expectedModel.updateFilteredGuestList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(GINA), model.getFilteredGuestList());
    }

    @Test
    public void toStringMethod() {
        List<Predicate<? super Guest>> predicates = Collections.singletonList(new NamePredicate("keyword1"));
        GuestFilterCommand filterCommand = new GuestFilterCommand(predicates);
        String expected = GuestFilterCommand.class.getCanonicalName() + "{predicates=" + predicates + "}";
        assertEquals(expected, filterCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NamePredicate}.
     */
    private NamePredicate prepareNamePredicate(String userInput) {
        return new NamePredicate(userInput.trim());
    }
}
