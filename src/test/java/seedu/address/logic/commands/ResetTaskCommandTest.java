package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AnimalCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.AnimalCommandTestUtil.showAnimalAtIndex;
import static seedu.address.testutil.TypicalAnimals.TOFU;
import static seedu.address.testutil.TypicalAnimals.getTypicalCatalog;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalTasks.FED;
import static seedu.address.testutil.TypicalTasks.WALKED;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AnimalModel;
import seedu.address.model.AnimalModelManager;
import seedu.address.model.AnimalUserPrefs;
import seedu.address.model.animal.Animal;
import seedu.address.testutil.AnimalBuilder;



public class ResetTaskCommandTest {

    private AnimalModel model;
    private AnimalModel expectedModel;

    @BeforeEach
    public void setUp() {
        model = new AnimalModelManager(getTypicalCatalog(), new AnimalUserPrefs());
        expectedModel = new AnimalModelManager(model.getAnimalCatalog(), new AnimalUserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        Animal markedAnimal = new AnimalBuilder(TOFU).withTaskList(List.of(FED, WALKED)).build();
        model.setAnimal(TOFU, markedAnimal);

        assertCommandSuccess(new ResetTaskCommand(), model, ResetTaskCommand.MESSAGE_SUCCESS, expectedModel);
        assertTrue(model.getFilteredAnimalList().equals(expectedModel.getFilteredAnimalList()));
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        Animal markedAnimal = new AnimalBuilder(TOFU).withTaskList(List.of(FED, WALKED)).build();
        model.setAnimal(TOFU, markedAnimal);

        showAnimalAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ResetTaskCommand(), model, ResetTaskCommand.MESSAGE_SUCCESS, expectedModel);
        assertTrue(model.getFilteredAnimalList().equals(expectedModel.getFilteredAnimalList()));
    }
}
