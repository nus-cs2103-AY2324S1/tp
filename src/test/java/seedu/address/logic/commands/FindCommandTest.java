package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_MUSICIANS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMusicians.CARL;
import static seedu.address.testutil.TypicalMusicians.ELLE;
import static seedu.address.testutil.TypicalMusicians.FIONA;
import static seedu.address.testutil.TypicalMusicians.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.musician.Musician;
import seedu.address.model.musician.NameContainsKeywordsPredicate;

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
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        HashSet<Predicate<Musician>> firstPredicates = new HashSet<>(Arrays.asList(firstPredicate));
        HashSet<Predicate<Musician>> secondPredicates = new HashSet<>(Arrays.asList(secondPredicate));

        FindCommand findFirstCommand = new FindCommand(firstPredicates);
        FindCommand findSecondCommand = new FindCommand(secondPredicates);

        NameContainsKeywordsPredicate firstPredicateCopy =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        HashSet<Predicate<Musician>> firstPredicatesCopy = new HashSet<>(Arrays.asList(firstPredicateCopy));

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicatesCopy);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different musician -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noMusicianFound() {
        String expectedMessage = String.format(MESSAGE_MUSICIANS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        HashSet<Predicate<Musician>> predicates = new HashSet<>(Arrays.asList(predicate));
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredMusicianList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredMusicianList());
    }

    @Test
    public void execute_multipleKeywords_multipleMusiciansFound() {
        String expectedMessage = String.format(MESSAGE_MUSICIANS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        HashSet<Predicate<Musician>> predicates = new HashSet<>(Arrays.asList(predicate));
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredMusicianList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredMusicianList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        HashSet<Predicate<Musician>> predicates = new HashSet<>(Arrays.asList(predicate));
        FindCommand findCommand = new FindCommand(predicates);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicates + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
