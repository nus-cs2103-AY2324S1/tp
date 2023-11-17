package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AnimalCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.AnimalCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAnimals.TOFU;
import static seedu.address.testutil.TypicalAnimals.getTypicalCatalog;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_SIXTH;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.address.testutil.TypicalTasks.FEED;
import static seedu.address.testutil.TypicalTasks.WALKED;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.AnimalMessages;
import seedu.address.model.AnimalModel;
import seedu.address.model.AnimalModelManager;
import seedu.address.model.AnimalUserPrefs;
import seedu.address.model.animal.Animal;
import seedu.address.testutil.AnimalBuilder;

public class DeleteTaskCommandTest {

    private AnimalModel model = new AnimalModelManager(getTypicalCatalog(), new AnimalUserPrefs());

    @Test
    public void equals_sameObject_returnsTrue() {
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST, INDEX_FIRST);
        assertTrue(deleteTaskCommand.equals(deleteTaskCommand), "A command should equal itself.");
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        DeleteTaskCommand deleteTaskCommand1 = new DeleteTaskCommand(INDEX_FIRST, INDEX_FIRST);
        DeleteTaskCommand deleteTaskCommand2 = new DeleteTaskCommand(INDEX_FIRST, INDEX_FIRST);
        assertTrue(deleteTaskCommand1.equals(deleteTaskCommand2),
                "Commands with the same index values should be equal.");
    }

    @Test
    public void equals_differentTypes_returnsFalse() {
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST, INDEX_FIRST);
        assertFalse(deleteTaskCommand.equals(1),
                "A command should not be equal to an object of a different type.");
    }

    @Test
    public void equals_null_returnsFalse() {
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST, INDEX_FIRST);
        assertFalse(deleteTaskCommand.equals(null),
                "A command should not be equal to null.");
    }

    @Test
    public void equals_differentAnimalIndex_returnsFalse() {
        DeleteTaskCommand deleteTaskCommand1 = new DeleteTaskCommand(INDEX_FIRST, INDEX_FIRST);
        DeleteTaskCommand deleteTaskCommand2 = new DeleteTaskCommand(INDEX_SECOND, INDEX_FIRST);
        assertFalse(deleteTaskCommand1.equals(deleteTaskCommand2),
                "Commands with different animal indices should not be equal.");
    }

    @Test
    public void equals_differentTaskIndex_returnsFalse() {
        DeleteTaskCommand deleteTaskCommand1 = new DeleteTaskCommand(INDEX_FIRST, INDEX_FIRST);
        DeleteTaskCommand deleteTaskCommand2 = new DeleteTaskCommand(INDEX_FIRST, INDEX_SECOND);
        assertFalse(deleteTaskCommand1.equals(deleteTaskCommand2),
                "Commands with different task indices should not be equal.");
    }

    @Test
    public void execute_validAnimalAndTaskIndex_success() {
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST, INDEX_SECOND);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_SUCCESS, TOFU.getName(),
                WALKED.getDescription());

        AnimalModel expectedModel = new AnimalModelManager(model.getAnimalCatalog(), new AnimalUserPrefs());
        Animal expectedAnimal = new AnimalBuilder(TOFU).withTaskList(List.of(FEED)).build();
        expectedModel.setAnimal(TOFU, expectedAnimal);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidAnimalIndex_failure() {
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_SIXTH, INDEX_FIRST);

        String expectedMessage = AnimalMessages.MESSAGE_INVALID_ANIMAL_DISPLAYED_INDEX;

        assertCommandFailure(deleteTaskCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidTaskIndex_failure() {
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST, INDEX_THIRD);

        String expectedMessage = AnimalMessages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;

        assertCommandFailure(deleteTaskCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        DeleteTaskCommand deleteFirstCommand = new DeleteTaskCommand(INDEX_FIRST, INDEX_FIRST);
        DeleteTaskCommand deleteSecondCommand = new DeleteTaskCommand(INDEX_FIRST, INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteTaskCommand deleteFirstCommandCopy = new DeleteTaskCommand(INDEX_FIRST, INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
