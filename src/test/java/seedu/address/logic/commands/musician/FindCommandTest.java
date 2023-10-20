package seedu.address.logic.commands.musician;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_MUSICIANS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMusicians.BENSON;
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
import seedu.address.model.tag.TagMatchesPredicate;

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

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        HashSet<Predicate<Musician>> firstPredicatesCopy = new HashSet<>(Arrays.asList(firstPredicateCopy));
        FindCommand findFirstCommandDeepCopy = new FindCommand(firstPredicatesCopy);
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicates);
        assertTrue(findFirstCommand.equals(findFirstCommandDeepCopy));
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
        NameContainsKeywordsPredicate predicate = prepareNameKeywordsPredicate(" ");
        HashSet<Predicate<Musician>> predicates = new HashSet<>(Arrays.asList(predicate));
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredMusicianList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredMusicianList());
    }

    @Test
    public void execute_multipleNameKeywords_multipleMusiciansFound() {
        String expectedMessage = String.format(MESSAGE_MUSICIANS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareNameKeywordsPredicate("Kurz Elle Kunz");

        HashSet<Predicate<Musician>> predicates = new HashSet<>(Arrays.asList(predicate));
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredMusicianList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredMusicianList());
    }

    @Test
    public void execute_mixedPredicates_singleMusicianFound() {
        String expectedMessage = String.format(MESSAGE_MUSICIANS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate namePredicate = prepareNameKeywordsPredicate("Benson");
        TagMatchesPredicate tagPredicate = prepareTagMatchesPredicate("friends");

        HashSet<Predicate<Musician>> predicates = new HashSet<>(Arrays.asList(namePredicate, tagPredicate));
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredMusicianList(namePredicate.and(tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredMusicianList());
    }

    @Test
    public void execute_mixedPredicates_noMusicianFound() {
        String expectedMessage = String.format(MESSAGE_MUSICIANS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate namePredicate = prepareNameKeywordsPredicate("Kunz");
        TagMatchesPredicate tagPredicate = prepareTagMatchesPredicate("friends");

        HashSet<Predicate<Musician>> predicates = new HashSet<>(Arrays.asList(namePredicate, tagPredicate));
        FindCommand command = new FindCommand(predicates);
        expectedModel.updateFilteredMusicianList(namePredicate.and(tagPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredMusicianList());
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
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}. This parsing method is used
     * for the convenience of testing.
     */
    private NameContainsKeywordsPredicate prepareNameKeywordsPredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code TagMatchesPredicate}. This parsing method is used for the
     * convenience of testing.
     */
    private TagMatchesPredicate prepareTagMatchesPredicate(String userInput) {
        return new TagMatchesPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
