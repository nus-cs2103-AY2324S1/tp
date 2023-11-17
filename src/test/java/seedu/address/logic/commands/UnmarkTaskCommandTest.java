package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AnimalCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.AnimalCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAnimals.TOFU;
import static seedu.address.testutil.TypicalAnimals.getTypicalCatalog;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_SIXTH;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.address.testutil.TypicalTasks.FED;
import static seedu.address.testutil.TypicalTasks.WALKED;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AnimalCatalog;
import seedu.address.model.AnimalModel;
import seedu.address.model.AnimalModelManager;
import seedu.address.model.AnimalUserPrefs;
import seedu.address.model.animal.Animal;
import seedu.address.testutil.AnimalBuilder;


public class UnmarkTaskCommandTest {

    private AnimalModel model = new AnimalModelManager(getTypicalCatalog(), new AnimalUserPrefs());
    private UnmarkTaskCommand unmarkAllTaskCommand = new UnmarkTaskCommand(INDEX_FIRST, INDEX_FIRST, INDEX_SECOND);
    private UnmarkTaskCommand unmarkOneTaskCommand = new UnmarkTaskCommand(INDEX_FIRST, INDEX_FIRST);

    @Test
    public void equals_sameCommand_returnsTrue() {
        assertTrue(unmarkAllTaskCommand.equals(unmarkAllTaskCommand));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        UnmarkTaskCommand unmarkTaskCommandCopy = new UnmarkTaskCommand(INDEX_FIRST, INDEX_FIRST, INDEX_SECOND);
        assertTrue(unmarkAllTaskCommand.equals(unmarkTaskCommandCopy));
    }

    @Test
    public void equals_differentCommand_returnsFalse() {
        assertFalse(unmarkAllTaskCommand.equals(unmarkOneTaskCommand));
    }

    @Test
    public void equals_differentTypes_returnsFalse() {
        assertFalse(unmarkAllTaskCommand.equals(1));
    }

    @Test
    public void equals_nullCommand_returnsFalse() {
        assertFalse(unmarkAllTaskCommand.equals(null));
    }

    @Test
    public void equals_nullOtherCommand_returnsFalse() {
        assertThrows(NullPointerException.class, () -> ((UnmarkTaskCommand) null).equals(unmarkAllTaskCommand));
    }

    @Test
    public void execute_validAnimalTaskIndex_success() {
        Animal markedAnimal = new AnimalBuilder(TOFU).withTaskList(List.of(FED, WALKED)).build();
        model.setAnimal(TOFU, markedAnimal);

        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(INDEX_FIRST, INDEX_FIRST, INDEX_SECOND);

        String expectedMessage = UnmarkTaskCommand.MESSAGE_SUCCESS;

        AnimalModel expectedModel = new AnimalModelManager(
                new AnimalCatalog(model.getAnimalCatalog()), new AnimalUserPrefs());

        assertCommandSuccess(unmarkTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidAnimalIndex_failure() {
        // Animal index exceeds number of animals in the list
        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(INDEX_SIXTH, INDEX_FIRST, INDEX_SECOND);

        String expectedMessage = UnmarkTaskCommand.MESSAGE_EXCESS_ANIMAL_INDEX;

        assertCommandFailure(unmarkTaskCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidTaskIndex_failure() {
        // Task index exceeds number of tasks in the animal
        UnmarkTaskCommand unmarkTaskCommand =
                new UnmarkTaskCommand(INDEX_FIRST, INDEX_FIRST, INDEX_SECOND, INDEX_THIRD);

        String expectedMessage = UnmarkTaskCommand.MESSAGE_EXCESS_TASK_INDEX;

        Animal markedAnimal = new AnimalBuilder(TOFU).withTaskList(List.of(FED, WALKED)).build();

        assertCommandFailure(unmarkTaskCommand, model, expectedMessage);
        // Check that tasks are not marked as uncompleted
        assertTrue(model.getFilteredAnimalList()
                .get(INDEX_FIRST.getZeroBased())
                .getTaskList()
                .equals(markedAnimal.getTaskList()));
    }
}
