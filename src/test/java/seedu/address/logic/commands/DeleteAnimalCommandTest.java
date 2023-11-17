package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnimalModel;
import seedu.address.model.animal.Animal;

public class DeleteAnimalCommandTest {

    @Test
    public void execute_validIndex_success() throws CommandException {
        // Mock dependencies
        AnimalModel model = mock(AnimalModel.class);
        Animal dummyAnimal = mock(Animal.class);

        // Mocks behaviour of getFilteredAnimalList.
        // return List containing dummyAnimal.
        when(model.getFilteredAnimalList()).thenReturn(FXCollections.observableList(List.of(dummyAnimal)));

        DeleteAnimalCommand deleteAnimalCommand = new DeleteAnimalCommand(Index.fromOneBased(1));

        String expectedMessage = String.format(DeleteAnimalCommand.MESSAGE_DELETE_ANIMAL_SUCCESS,
                AnimalMessages.format(dummyAnimal));

        // Execute the command
        CommandResult result = deleteAnimalCommand.execute(model);

        // Verify interactions
        verify(model).deleteAnimal(dummyAnimal);

        // Assert command result
        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        // Mock dependencies
        AnimalModel model = mock(AnimalModel.class);

        // Mocks behaviour of getFilteredAnimalList.
        // return empty list
        when(model.getFilteredAnimalList()).thenReturn(FXCollections.observableList(List.of()));

        DeleteAnimalCommand deleteAnimalCommand = new DeleteAnimalCommand(Index.fromOneBased(2));

        assertThrows(CommandException.class, () -> deleteAnimalCommand.execute(model));
    }

    @Test
    public void testEquals() {
        DeleteAnimalCommand deleteAnimalCommand1 = new DeleteAnimalCommand(Index.fromOneBased(1));
        DeleteAnimalCommand deleteAnimalCommand2 = new DeleteAnimalCommand(Index.fromOneBased(1));
        DeleteAnimalCommand deleteAnimalCommand3 = new DeleteAnimalCommand(Index.fromOneBased(2));

        assertEquals(deleteAnimalCommand1, deleteAnimalCommand2);
        assertNotEquals(deleteAnimalCommand1, deleteAnimalCommand3);
        assertNotEquals(deleteAnimalCommand2, deleteAnimalCommand3);
    }

    @Test
    public void testHashCode() {
        DeleteAnimalCommand deleteAnimalCommand1 = new DeleteAnimalCommand(Index.fromOneBased(1));
        DeleteAnimalCommand deleteAnimalCommand2 = new DeleteAnimalCommand(Index.fromOneBased(1));
        DeleteAnimalCommand deleteAnimalCommand3 = new DeleteAnimalCommand(Index.fromOneBased(2));

        assertEquals(deleteAnimalCommand1.hashCode(), deleteAnimalCommand2.hashCode());
        assertNotEquals(deleteAnimalCommand1.hashCode(), deleteAnimalCommand3.hashCode());
        assertNotEquals(deleteAnimalCommand2.hashCode(), deleteAnimalCommand3.hashCode());
    }
}
