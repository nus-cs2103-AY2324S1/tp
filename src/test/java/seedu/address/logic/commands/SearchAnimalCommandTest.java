package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AnimalCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAnimals.getTypicalCatalog;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AnimalModel;
import seedu.address.model.AnimalModelManager;
import seedu.address.model.AnimalUserPrefs;
import seedu.address.model.animal.KeywordPredicate;

public class SearchAnimalCommandTest {

    private AnimalModel model = new AnimalModelManager(getTypicalCatalog(), new AnimalUserPrefs());
    private AnimalModel expectedModel = new AnimalModelManager(getTypicalCatalog(), new AnimalUserPrefs());

    private KeywordPredicate firstPredicate = new KeywordPredicate(List.of("Tofu"));
    private KeywordPredicate secondPredicate = new KeywordPredicate(List.of("Muffin"));
    private SearchAnimalCommand searchFirstAnimalCommand = new SearchAnimalCommand(firstPredicate);
    private SearchAnimalCommand searchSecondAnimalCommand = new SearchAnimalCommand(secondPredicate);


    @Test
    public void equals_sameCommand_returnsTrue() {
        assertTrue(searchFirstAnimalCommand.equals(searchFirstAnimalCommand));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        SearchAnimalCommand searchFirstAnimalCommandCopy = new SearchAnimalCommand(firstPredicate);
        assertTrue(searchFirstAnimalCommand.equals(searchFirstAnimalCommandCopy));
    }

    @Test
    public void equals_differentCommand_returnsFalse() {
        assertFalse(searchFirstAnimalCommand.equals(searchSecondAnimalCommand));
    }

    @Test
    public void equals_differentTypes_returnsFalse() {
        assertFalse(searchFirstAnimalCommand.equals(1));
    }

    @Test
    public void equals_nullCommand_returnsFalse() {
        assertFalse(searchFirstAnimalCommand.equals(null));
    }

    @Test
    public void equals_nullOtherCommand_returnsFalse() {
        assertThrows(NullPointerException.class, () -> ((SearchAnimalCommand) null).equals(searchFirstAnimalCommand));
    }

    @Test
    public void execute_allKeywords_oneAnimalFound() {
        String expectedMessage = String.format(SearchAnimalCommand.MESSAGE_SUCCESS, 1);
        KeywordPredicate predicate = new KeywordPredicate(List.of("Tofu", "1234", "2019-10-10",
                "2019-11-11", "cat", "female", "british shorthair"));
        SearchAnimalCommand command = new SearchAnimalCommand(predicate);
        expectedModel.updateFilteredAnimalList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredAnimalList().size() == 1);
    }

    @Test
    public void execute_someKeywords_someAnimalsFound() {
        // Only 1 animal found
        String expectedMessage = String.format(SearchAnimalCommand.MESSAGE_SUCCESS, 1);
        KeywordPredicate predicate = new KeywordPredicate(List.of("Tofu", "", "", "", "", "", ""));
        SearchAnimalCommand command = new SearchAnimalCommand(predicate);
        expectedModel.updateFilteredAnimalList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredAnimalList().size() == 1);

        // More than 1 animal found
        expectedMessage = String.format(SearchAnimalCommand.MESSAGE_SUCCESS, 3);
        predicate = new KeywordPredicate(List.of("", "", "", "", "", "male", ""));
        command = new SearchAnimalCommand(predicate);
        expectedModel.updateFilteredAnimalList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredAnimalList().size() == 3);

        // Only animal with matching breed is found
        expectedMessage = String.format(SearchAnimalCommand.MESSAGE_SUCCESS, 1);
        predicate = new KeywordPredicate(List.of("", "", "", "", "", "", "Bear"));
        command = new SearchAnimalCommand(predicate);
        expectedModel.updateFilteredAnimalList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredAnimalList().size() == 1);
    }

}
