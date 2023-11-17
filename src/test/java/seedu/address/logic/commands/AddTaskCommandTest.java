package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_TASK_DESCRIPTION;
import static seedu.address.logic.commands.AnimalCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.AnimalCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAnimals.TOFU;
import static seedu.address.testutil.TypicalAnimals.getTypicalCatalog;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_SIXTH;
import static seedu.address.testutil.TypicalTasks.FEED;
import static seedu.address.testutil.TypicalTasks.WALK;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.AnimalMessages;
import seedu.address.model.AnimalModel;
import seedu.address.model.AnimalModelManager;
import seedu.address.model.AnimalUserPrefs;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Task;
import seedu.address.testutil.AnimalBuilder;

public class AddTaskCommandTest {
    private AnimalModel model = new AnimalModelManager(getTypicalCatalog(), new AnimalUserPrefs());
    private AddTaskCommand addTaskIndex1Command = new AddTaskCommand(INDEX_FIRST, new Task(VALID_TASK_DESCRIPTION));
    private AddTaskCommand addTaskIndex2Command = new AddTaskCommand(INDEX_SECOND, new Task(VALID_TASK_DESCRIPTION));
    @Test
    public void equals_sameCommand_returnsTrue() {
        assertTrue(addTaskIndex1Command.equals(addTaskIndex1Command));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        AddTaskCommand addTaskCommandCopy = new AddTaskCommand(INDEX_FIRST, new Task(VALID_TASK_DESCRIPTION));
        assertTrue(addTaskIndex1Command.equals(addTaskCommandCopy));
    }

    @Test
    public void equals_differentCommand_returnsFalse() {
        assertFalse(addTaskIndex1Command.equals(addTaskIndex2Command));
    }

    @Test
    public void equals_differentTypes_returnsFalse() {
        assertFalse(addTaskIndex1Command.equals(1));
    }

    @Test
    public void equals_nullCommand_returnsFalse() {
        assertFalse(addTaskIndex1Command.equals(null));
    }

    @Test
    public void execute_validAnimalTaskIndexAndDescription_success() {
        Task newTask = new Task(VALID_TASK_DESCRIPTION);
        AddTaskCommand addTaskCommand = new AddTaskCommand(INDEX_FIRST, newTask);

        String expectedMessage = String.format(AddTaskCommand.MESSAGE_SUCCESS, TOFU.getName(), VALID_TASK_DESCRIPTION);

        AnimalModel expectedModel = new AnimalModelManager(model.getAnimalCatalog(), new AnimalUserPrefs());
        Animal expectedAnimal = new AnimalBuilder(TOFU).withTaskList(List.of(FEED, WALK, newTask)).build();
        expectedModel.setAnimal(TOFU, expectedAnimal);

        // Execute the command
        assertCommandSuccess(addTaskCommand, model, expectedMessage, expectedModel);

        // Verify the task has been added
        Animal animalInModel = model.getFilteredAnimalList().get(INDEX_FIRST.getZeroBased());
        assertTrue(animalInModel.getTaskList().contains(newTask));
    }

    @Test
    public void execute_invalidAnimalIndex_failure() {
        // Animal index exceeds number of animals in the list
        AddTaskCommand addTaskCommand = new AddTaskCommand(INDEX_SIXTH, new Task(VALID_TASK_DESCRIPTION));

        String expectedMessage = AnimalMessages.MESSAGE_INVALID_ANIMAL_DISPLAYED_INDEX;

        assertCommandFailure(addTaskCommand, model, expectedMessage);
    }
}
