package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.testutil.PersonBuilder;

public class DeleteTutorialCommandTest {
    @Test
    public void execute_outOfBoundsIndex_errorMessage() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Module testModule = new Module("CS1000");
        model.addModule(testModule);
        Tutorial testTutorial = new Tutorial(testModule, "test name", "test time");
        model.addTutorial(testTutorial);
        DeleteTutorialCommand deleteTutorialCommand = new DeleteTutorialCommand(Index.fromOneBased(999));
        assertCommandFailure(deleteTutorialCommand, model, DeleteTutorialCommand.MESSAGE_NO_TUTORIAL_FOUND);
    }

    @Test
    public void execute_validModule_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Module testModule = new Module("CS1000");
        model.addModule(testModule);
        Tutorial testTutorial = new Tutorial(testModule, "test name", "test time");
        model.addTutorial(testTutorial);
        DeleteTutorialCommand deleteTutorialCommand = new DeleteTutorialCommand(INDEX_FIRST_PERSON);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.addModule(testModule);

        assertCommandSuccess(deleteTutorialCommand, model,
                String.format(DeleteTutorialCommand.MESSAGE_SUCCESS, testTutorial), expectedModel);
    }

    @Test
    public void execute_personHasTutorial_personLosesTutorial() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Module testModule = new Module("CS1000");
        model.addModule(testModule);
        Tutorial testTutorial = new Tutorial(testModule, "test name", "test time");
        model.addTutorial(testTutorial);
        Person personWithTutorialAndModule = new PersonBuilder(model.getAddressBook().getPersonList().get(0))
                .withModules(testModule).withTutorials(testTutorial).build();
        model.setPerson(model.getFilteredPersonList().get(0), personWithTutorialAndModule);

        DeleteTutorialCommand deleteTutorialCommand = new DeleteTutorialCommand(INDEX_FIRST_PERSON);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.addModule(testModule);
        Person personWithModuleOnly = new PersonBuilder(expectedModel.getAddressBook().getPersonList().get(0))
                .withModules(testModule).build();
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), personWithModuleOnly);

        assertCommandSuccess(deleteTutorialCommand, model,
                String.format(DeleteTutorialCommand.MESSAGE_SUCCESS, testTutorial), expectedModel);
    }

    @Test
    public void equals() {
        DeleteTutorialCommand deleteTutorialCommand = new DeleteTutorialCommand(INDEX_FIRST_PERSON);
        DeleteTutorialCommand deleteSecondTutorialCommand = new DeleteTutorialCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteTutorialCommand.equals(deleteTutorialCommand));

        // same values -> returns true
        DeleteTutorialCommand deleteTutorialCommandCopy = new DeleteTutorialCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteTutorialCommand.equals(deleteTutorialCommandCopy));

        // different types -> returns false
        assertFalse(deleteTutorialCommand.equals(1));

        // null -> returns false
        assertFalse(deleteTutorialCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteTutorialCommand.equals(deleteSecondTutorialCommand));
    }
}
