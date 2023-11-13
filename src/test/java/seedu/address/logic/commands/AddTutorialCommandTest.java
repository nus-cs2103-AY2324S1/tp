package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.tutorial.Tutorial;

public class AddTutorialCommandTest {
    @Test
    public void execute_invalidModule_errorMessage() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Module testModule = new Module("CS1000");
        Tutorial testTutorial = new Tutorial(testModule, "test name", "test time");

        AddTutorialCommand addTutorialCommand = new AddTutorialCommand(testTutorial);

        assertCommandFailure(addTutorialCommand, model, Messages.MESSAGE_INVALID_MODULE);
    }

    @Test
    public void execute_duplicateTutorial_errorMessage() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Module testModule = new Module("CS1000");
        Tutorial testTutorial = new Tutorial(testModule, "test name", "Mon 6PM");
        model.addModule(testModule);
        model.addTutorial(testTutorial);
        AddTutorialCommand addTutorialCommand = new AddTutorialCommand(testTutorial);
        assertCommandFailure(addTutorialCommand, model, AddTutorialCommand.MESSAGE_DUPLICATE_TUTORIAL);
    }

    @Test
    public void execute_validModule_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Module testModule = new Module("CS1000");
        model.addModule(testModule);
        Tutorial testTutorial = new Tutorial(testModule, "test name", "test time");

        AddTutorialCommand addTutorialCommand = new AddTutorialCommand(testTutorial);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.addModule(testModule);
        expectedModel.addTutorial(testTutorial);

        assertCommandSuccess(addTutorialCommand, model,
                String.format(AddTutorialCommand.MESSAGE_SUCCESS, testTutorial), expectedModel);
    }

    @Test
    public void equals() {
        Module testModule = new Module("CS1000");
        Tutorial testTutorial = new Tutorial(testModule, "test name", "test time");
        Tutorial secondTestTutorial = new Tutorial(testModule, "other test name", "test time");

        AddTutorialCommand addTutorialCommand = new AddTutorialCommand(testTutorial);
        AddTutorialCommand addSecondTutorialCommand = new AddTutorialCommand(secondTestTutorial);

        // same object -> returns true
        assertTrue(addTutorialCommand.equals(addTutorialCommand));

        // same values -> returns true
        AddTutorialCommand addTutorialCommandCopy = new AddTutorialCommand(testTutorial);
        assertTrue(addTutorialCommand.equals(addTutorialCommandCopy));

        // different types -> returns false
        assertFalse(addTutorialCommand.equals(1));

        // null -> returns false
        assertFalse(addTutorialCommand.equals(null));

        // different person -> returns false
        assertFalse(addTutorialCommand.equals(addSecondTutorialCommand));
    }
}
