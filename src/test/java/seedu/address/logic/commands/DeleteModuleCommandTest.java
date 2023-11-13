package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalModules.getTypicalAddressBook;
import static seedu.address.testutil.TypicalModules.getTypicalModuleAddressBook;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteModuleCommand}.
 */
public class DeleteModuleCommandTest {
    public static final Person PERSON_NO_MODULE_AND_TUTORIAL = new PersonBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withStudentNumber("A0203221J")
            .withTelegram("@bruh")
            .withTags("owesMoney", "friends").build();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validModuleList_success() {
        Model specialModel = new ModelManager(getTypicalModuleAddressBook(), new UserPrefs());
        Module moduleToDelete = specialModel.getModuleList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteModuleCommand.MESSAGE_SUCCESS, moduleToDelete);

        ModelManager expectedModel = new ModelManager(specialModel.getAddressBook(), new UserPrefs());
        expectedModel.deleteModule(moduleToDelete);

        assertCommandSuccess(deleteModuleCommand, specialModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validModuleListWithTutorials_success() {
        Module module = model.getModuleList().get(INDEX_FIRST_PERSON.getZeroBased());
        Tutorial tutorialOne = new Tutorial(module, "T12", "Mon 2pm");
        Tutorial tutorialTwo = new Tutorial(module, "T13", "Mon 4pm");

        model.addTutorial(tutorialOne);
        model.addTutorial(tutorialTwo);
        assertTrue(model.getTutorialList().size() == 3);

        model.deleteModule(module);
        assertTrue(model.getTutorialList().size() == 0);
    }

    @Test
    public void execute_updatedPersonWithDeletedModule_success() {
        Person expectedPerson = new ArrayList<>(Arrays.asList(PERSON_NO_MODULE_AND_TUTORIAL)).get(0);
        DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(INDEX_FIRST_PERSON);

        try {
            CommandResult r = deleteModuleCommand.execute(model);
        } catch (CommandException e) {
            System.out.println("IT SHOULD NEVER REACH HERE");
        }

        Person editedPerson = model.getFilteredPersonList().get(0);

        assertTrue(editedPerson.equals(expectedPerson));
    }

    @Test
    public void execute_invalidIndexList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getModuleList().size() + 1);
        DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(outOfBoundIndex);

        assertCommandFailure(deleteModuleCommand, model, DeleteModuleCommand.MESSAGE_EXCEED_LIST);
    }

    @Test
    public void equals() {
        DeleteModuleCommand deleteFirst = new DeleteModuleCommand(INDEX_FIRST_PERSON);
        DeleteModuleCommand deleteSecond = new DeleteModuleCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirst.equals(deleteFirst));

        // same values -> returns true
        DeleteModuleCommand deleteFirstCopy = new DeleteModuleCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirst.equals(deleteFirstCopy));

        // different types -> returns false
        assertFalse(deleteFirst.equals(1));

        // null -> returns false
        assertFalse(deleteFirst.equals(null));

        // different person -> returns false
        assertFalse(deleteFirst.equals(deleteSecond));
    }
}
