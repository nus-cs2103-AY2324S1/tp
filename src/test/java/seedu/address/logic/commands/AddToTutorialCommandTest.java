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

class AddToTutorialCommandTest {

    @Test
    public void execute_tutorialNotFound_errorMessage() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Tutorial testTut = new Tutorial(new Module("CS1000"), "testName", "Mon 2PM");

        assertCommandFailure(
                new AddToTutorialCommand(INDEX_FIRST_PERSON, testTut), model,
                Messages.MESSAGE_INVALID_TUTORIAL
        );
    }

    @Test
    public void execute_invalidIndex_errorMessage() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Tutorial testTut = new Tutorial(new Module("CS1000"), "testName", "Mon 2PM");

        assertCommandFailure(new AddToTutorialCommand(Index.fromZeroBased(999999),
                testTut), model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_tutorialWithoutTime_success() {
        // data
        Module testModule = new Module("CS1000");
        Tutorial testTut = new Tutorial(testModule, "testName", "Mon 2PM");
        Tutorial searchTut = new Tutorial(testModule, "testName");

        // command
        AddToTutorialCommand command = new AddToTutorialCommand(INDEX_FIRST_PERSON, searchTut);

        // model to be edited
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addModule(testModule);
        model.addTutorial(testTut);

        // expected
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.addModule(testModule);
        expectedModel.addTutorial(testTut);
        Person expectedPerson = new PersonBuilder(expectedModel.getAddressBook().getPersonList().get(0))
                .withModules(testModule).withTutorials(testTut).build();
        String expectedMessage = String.format(AddToTutorialCommand.MESSAGE_SUCCESS, Messages.format(expectedPerson));
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), expectedPerson);

        // result
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validTutorial_success() {
        // data
        Module testModule = new Module("CS1000");
        Tutorial testTut = new Tutorial(testModule, "testName", "Mon 2PM");

        // command
        AddToTutorialCommand command = new AddToTutorialCommand(INDEX_FIRST_PERSON, testTut);

        // model to be edited
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addModule(testModule);
        model.addTutorial(testTut);

        // expected
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.addModule(testModule);
        expectedModel.addTutorial(testTut);
        Person expectedPerson = new PersonBuilder(expectedModel.getAddressBook().getPersonList().get(0))
                .withModules(testModule).withTutorials(testTut).build();
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), expectedPerson);
        String expectedMessage = String.format(AddToTutorialCommand.MESSAGE_SUCCESS, Messages.format(expectedPerson));

        // result
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        String testName1 = "T11";
        String testName2 = "T12";

        Module test1 = new Module("CS1000");
        Tutorial test11 = new Tutorial(test1, testName1);
        Tutorial test12 = new Tutorial(test1, testName2);

        Module test2 = new Module("CS2000");
        Tutorial test21 = new Tutorial(test2, testName1);

        AddToTutorialCommand addToTutorialCommand1 = new AddToTutorialCommand(
                INDEX_FIRST_PERSON, test11);
        AddToTutorialCommand addToTutorialCommand2 = new AddToTutorialCommand(
                INDEX_FIRST_PERSON, test12);
        AddToTutorialCommand addToTutorialCommand3 = new AddToTutorialCommand(
                INDEX_FIRST_PERSON, test21);

        // same object -> returns true
        assertTrue(addToTutorialCommand1.equals(addToTutorialCommand1));

        // same values -> returns true
        AddToTutorialCommand addToTutorialCommandCopy = new AddToTutorialCommand(INDEX_FIRST_PERSON,
                test11);
        assertTrue(addToTutorialCommand1.equals(addToTutorialCommandCopy));

        // different types -> returns false
        assertFalse(addToTutorialCommand1.equals(1));

        // null -> returns false
        assertFalse(addToTutorialCommand1.equals(null));

        // different Module -> returns false
        assertFalse(addToTutorialCommand1.equals(addToTutorialCommand3));

        // different Tutorial Name -> returns false
        assertFalse(addToTutorialCommand1.equals(addToTutorialCommand2));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);

        Module testModule = new Module("CS1000");
        Tutorial testTut = new Tutorial(testModule, "testName", "Mon 2PM");

        AddToTutorialCommand addToTutorialCommand = new AddToTutorialCommand(
                INDEX_FIRST_PERSON, testTut);
        String expected = AddToTutorialCommand.class.getCanonicalName()
                + "{index=" + targetIndex + ", tutorialName="
                + testTut + "}";
        assertEquals(expected, addToTutorialCommand.toString());
    }
}
