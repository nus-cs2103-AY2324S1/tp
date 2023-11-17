package seedu.address.logic.commands;

import static seedu.address.logic.commands.AnimalCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.AnimalCommandTestUtil.showAnimalAtIndex;
import static seedu.address.testutil.TypicalAnimals.getTypicalCatalog;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AnimalModel;
import seedu.address.model.AnimalModelManager;
import seedu.address.model.AnimalUserPrefs;


/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListAnimalCommandTest {

    private AnimalModel model;
    private AnimalModel expectedModel;

    @BeforeEach
    public void setUp() {
        model = new AnimalModelManager(getTypicalCatalog(), new AnimalUserPrefs());
        expectedModel = new AnimalModelManager(model.getAnimalCatalog(), new AnimalUserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListAnimalCommand(), model, ListAnimalCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showAnimalAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListAnimalCommand(), model, ListAnimalCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
