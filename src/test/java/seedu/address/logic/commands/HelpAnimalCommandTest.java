package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import seedu.address.model.AnimalModel;

public class HelpAnimalCommandTest {
    public static final String VALID_ADD_LOWERCASE = "add";
    public static final String VALID_ADD_UPPERCASE = "ADD";
    public static final String VALID_ADD_PARTIAL = "ad";
    public static final String INVALID_COMMAND = "someCommandThatDoesNotExist";

    @Mock
    private AnimalModel mockedModel;

    @Test
    public void constructor_withNullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new HelpAnimalCommand(null));
    }

    @Test
    public void execute_withEmptyString_returnsProgramUsage() {
        CommandResult expectedResult = new CommandResult(HelpAnimalCommand.MESSAGE_USAGE, true, false);
        CommandResult actual = new HelpAnimalCommand("").execute(mockedModel);
        assertEquals(expectedResult, actual);
    }

    @Test
    public void execute_withValidAdd_returnsAddAnimalAndAddTaskCommandHelp() {
        String expected = String.format("%s\n\n%s", AddAnimalCommand.getHelp(), AddTaskCommand.getHelp());
        String actualLowerCase = new HelpAnimalCommand(VALID_ADD_LOWERCASE).execute(mockedModel).getFeedbackToUser();
        String actualUpperCase = new HelpAnimalCommand(VALID_ADD_UPPERCASE).execute(mockedModel).getFeedbackToUser();

        assertEquals(expected, actualLowerCase);
        assertEquals(expected, actualUpperCase);
    }

    @Test
    public void execute_withValidPartialAdd_returnsAddAnimalAndAddTaskCommandHelp() {
        String expected = String.format("%s\n\n%s", AddAnimalCommand.getHelp(), AddTaskCommand.getHelp());
        String actual = new HelpAnimalCommand(VALID_ADD_PARTIAL).execute(mockedModel).getFeedbackToUser();

        assertEquals(expected, actual);
    }

    @Test
    public void execute_withInvalidTargetWord_returnsUnrecognizedInputMessage() {
        String expected = String.format(HelpAnimalCommand.MESSAGE_UNRECOGNIZED_COMMAND_FORMAT, INVALID_COMMAND);
        String actual = new HelpAnimalCommand(INVALID_COMMAND).execute(mockedModel).getFeedbackToUser();

        assertEquals(expected, actual);
    }

    @Test
    public void equals() {
        HelpAnimalCommand helpAddCommand = new HelpAnimalCommand(VALID_ADD_LOWERCASE);
        HelpAnimalCommand helpAddCommandPartial = new HelpAnimalCommand(VALID_ADD_PARTIAL);

        // same object -> returns true
        assertEquals(helpAddCommand, helpAddCommand);

        // same values -> returns true
        HelpAnimalCommand helpAddCopy = new HelpAnimalCommand(VALID_ADD_LOWERCASE);
        assertEquals(helpAddCommand, helpAddCopy);

        // different types -> returns false
        assertNotEquals(1, helpAddCommand);

        // null -> returns false
        assertNotEquals(null, helpAddCommand);

        // different target word -> returns false
        assertNotEquals(helpAddCopy, helpAddCommandPartial);
    }

    @Test
    public void toStringMethod() {
        HelpAnimalCommand helpAnimalCommand = new HelpAnimalCommand(VALID_ADD_LOWERCASE);
        String expected = HelpAnimalCommand.class.getCanonicalName() + "{toSearch=" + VALID_ADD_LOWERCASE + "}";
        assertEquals(expected, helpAnimalCommand.toString());
    }
}
