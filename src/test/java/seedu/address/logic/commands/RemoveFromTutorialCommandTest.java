package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.testutil.PersonBuilder;

class RemoveFromTutorialCommandTest {

    @Test
    public void execute_tutorialNotFound_errorMessage() {
        // data
        Module testModule = new Module("CS1000");
        Tutorial testTut = new Tutorial(testModule, "testName", "Mon 2PM");

        // models to be edited
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // result
        assertCommandFailure(
                new RemoveFromTutorialCommand(INDEX_FIRST_PERSON, testTut), model,
                Messages.MESSAGE_INVALID_TUTORIAL
        );
    }

    @Test
    public void execute_invalidIndex_errorMessage() {
        // data
        Module testModule = new Module("CS1000");
        Tutorial testTut = new Tutorial(testModule, "testName", "Mon 2PM");

        // models to be edited
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // result
        assertCommandFailure(new RemoveFromTutorialCommand(Index.fromZeroBased(999999),
                testTut), model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_tutorialNotAttachedToPerson_errorMessage() {
        // data
        Module testModule = new Module("CS1000");
        Tutorial testTut = new Tutorial(testModule, "testName", "Mon 2PM");

        // models to be edited
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addModule(testModule);
        model.addTutorial(testTut);

        // result
        assertCommandFailure(new RemoveFromTutorialCommand(INDEX_FIRST_PERSON, testTut), model,
                RemoveFromTutorialCommand.MESSAGE_PERSON_LACKS_TUTORIAL);
    }

    @Test
    public void execute_tutorialWithoutTime_success() {
        // data
        Module testModule = new Module("CS1000");
        Tutorial searchTut = new Tutorial(testModule, "testName");
        Tutorial testTut = new Tutorial(testModule, "testName", "Mon 2PM");

        // command
        RemoveFromTutorialCommand command = new RemoveFromTutorialCommand(INDEX_FIRST_PERSON, searchTut);

        // models to be edited
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addModule(testModule);
        model.addTutorial(testTut);
        Person personToEdit = new PersonBuilder(model.getAddressBook().getPersonList().get(0))
                .withModules(testModule).withTutorials(testTut).build();
        model.setPerson(model.getFilteredPersonList().get(0), personToEdit);

        // expected
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.addModule(testModule);
        expectedModel.addTutorial(testTut);
        Person expectedPerson = new PersonBuilder(expectedModel.getAddressBook().getPersonList().get(0))
                .withModules(testModule).build();
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), expectedPerson);
        String expectedMessage = String.format(
                RemoveFromTutorialCommand.MESSAGE_SUCCESS, Messages.format(expectedPerson));

        // result
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validTutorial_success() {
        // data
        Module testModule = new Module("CS1000");
        Tutorial testTut = new Tutorial(testModule, "testName", "Mon 2PM");

        // command
        RemoveFromTutorialCommand command = new RemoveFromTutorialCommand(INDEX_FIRST_PERSON, testTut);

        // models to be edited
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addModule(testModule);
        model.addTutorial(testTut);
        Person personToEdit = new PersonBuilder(model.getAddressBook().getPersonList().get(0))
                .withModules(testModule).withTutorials(testTut).build();
        model.setPerson(model.getFilteredPersonList().get(0), personToEdit);

        // expected
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.addModule(testModule);
        expectedModel.addTutorial(testTut);
        Person expectedPerson = new PersonBuilder(expectedModel.getAddressBook().getPersonList().get(0))
                .withModules(testModule).build();
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), expectedPerson);
        String expectedMessage = String.format(
                RemoveFromTutorialCommand.MESSAGE_SUCCESS, Messages.format(expectedPerson));

        // result
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        // data
        String testName1 = "T11";
        String testName2 = "T12";

        Module test1 = new Module("CS1000");
        Tutorial test11 = new Tutorial(test1, testName1);
        Tutorial test12 = new Tutorial(test1, testName2);

        Module test2 = new Module("CS2000");
        Tutorial test21 = new Tutorial(test2, testName1);

        // command
        RemoveFromTutorialCommand removeFromTutorialCommand1 = new RemoveFromTutorialCommand(
                INDEX_FIRST_PERSON, test11);
        RemoveFromTutorialCommand removeFromTutorialCommand2 = new RemoveFromTutorialCommand(
                INDEX_FIRST_PERSON, test12);
        RemoveFromTutorialCommand removeFromTutorialCommand3 = new RemoveFromTutorialCommand(
                INDEX_FIRST_PERSON, test21);

        // same object -> returns true
        assertTrue(removeFromTutorialCommand1.equals(removeFromTutorialCommand1));

        // same values -> returns true
        RemoveFromTutorialCommand removeFromTutorialCommandCopy =
                new RemoveFromTutorialCommand(INDEX_FIRST_PERSON, test11);
        assertTrue(removeFromTutorialCommand1.equals(removeFromTutorialCommandCopy));

        // different types -> returns false
        assertFalse(removeFromTutorialCommand1.equals(1));

        // null -> returns false
        assertFalse(removeFromTutorialCommand1.equals(null));

        // different Module -> returns false
        assertFalse(removeFromTutorialCommand1.equals(removeFromTutorialCommand3));

        // different Tutorial Name -> returns false
        assertFalse(removeFromTutorialCommand1.equals(removeFromTutorialCommand2));
    }

    @Test
    public void toStringMethod() {
        // data
        Index targetIndex = Index.fromOneBased(1);

        Module testModule = new Module("CS1000");
        Tutorial testTut = new Tutorial(testModule, "testName", "Mon 2PM");

        // command
        RemoveFromTutorialCommand removeFromTutorialCommand = new RemoveFromTutorialCommand(
                INDEX_FIRST_PERSON, testTut);

        // expected
        String expected = RemoveFromTutorialCommand.class.getCanonicalName()
                + "{index=" + targetIndex + ", tutorialName="
                + testTut + "}";

        // result
        assertEquals(expected, removeFromTutorialCommand.toString());
    }
}
