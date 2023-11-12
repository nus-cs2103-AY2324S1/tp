package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Student;
import seedu.address.testutil.PersonBuilder;
/**
 * Contains integration tests (interaction with the Model) and unit tests for UpdateSecLevelCommand.
 */
public class UpdateSecLevelCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allSec4_allDeleted() throws CommandException {
        Model model1 = new ModelManager();
        Student student1 = getSec4Student("Alice");
        Student student2 = getSec4Student("Bob");
        Student student3 = getSec4Student("Jack");
        model1.addPerson(student1);
        model1.addPerson(student2);
        model1.addPerson(student3);

        UpdateSecLevelCommand updateSecLevelCommand = new UpdateSecLevelCommand();
        updateSecLevelCommand.execute(model1);
        //All sec level 4 students have been removed from database.
        assertEquals(0, model1.getFilteredPersonList().size());

        undolevelToRemoveDependencyForTestCases();
    }

    @Test
    public void execute_allNonSec4_allUpdated() throws CommandException {
        Model model1 = new ModelManager();
        Student[] nonSec4Students = getNonSec4Students();
        for (Student student : nonSec4Students) {
            model1.addPerson(student);
        }
        UpdateSecLevelCommand updateSecLevelCommand = new UpdateSecLevelCommand();
        updateSecLevelCommand.execute(model1);
        // No student is removed since there is no sec 4 student
        assertEquals(nonSec4Students.length, model1.getFilteredPersonList().size());
        model.getFilteredPersonList().stream().forEach(student -> {
            for (Student originalStudent : nonSec4Students) {
                if (student.getName().equals(originalStudent.getName())) {
                    assertEquals(originalStudent.getSecLevelValue() + 1, student.getSecLevelValue());
                }
            }
        });

        undolevelToRemoveDependencyForTestCases();
    }

    @Test
    public void execute_mixedSec4AndNonSec4_success() throws CommandException {
        Model model1 = new ModelManager();
        Student[] nonSec4Students = getNonSec4Students();
        for (Student student : nonSec4Students) {
            model1.addPerson(student);
        }
        model1.addPerson(getSec4Student("Alice"));
        model1.addPerson(getSec4Student("Bob"));

        UpdateSecLevelCommand updateSecLevelCommand = new UpdateSecLevelCommand();
        updateSecLevelCommand.execute(model1);

        // Updated student list must have same number of students as nonSec4Students since all sec4
        // students are removed from database.
        assertEquals(nonSec4Students.length, model1.getFilteredPersonList().size());
        model.getFilteredPersonList().stream().forEach(student -> {
            for (Student originalStudent : nonSec4Students) {
                if (student.getName().equals(originalStudent.getName())) {
                    assertEquals(originalStudent.getSecLevelValue() + 1, student.getSecLevelValue());
                }
            }
        });
        undolevelToRemoveDependencyForTestCases();
    }

    @Test
    public void execute_uplevelFollowedByUndo_showCorrectMessage() throws CommandException {
        model.setAddressBook(getTypicalAddressBook());
        model.setUserPrefs(new UserPrefs());
        String expectedMessageForUpdate = UpdateSecLevelCommand.MESSAGE_UPDATE_SUCCESS;
        String expectedMessageForUndo = UpdateSecLevelCommand.MESSAGE_UNDO_SUCCESS;

        // Success update message shown as expected
        UpdateSecLevelCommand updateSecLevelCommand = new UpdateSecLevelCommand();
        CommandResult updateResult = updateSecLevelCommand.execute(model);
        assertEquals(expectedMessageForUpdate, updateResult.getFeedbackToUser());

        // Success undo update message shown as expected
        UpdateSecLevelCommand undoUpdateSecLevelCommand = new UpdateSecLevelCommand(true);
        CommandResult undoResult = undoUpdateSecLevelCommand.execute(model);
        assertEquals(expectedMessageForUndo, undoResult.getFeedbackToUser());

        undolevelToRemoveDependencyForTestCases();
    }



    @Test
    public void execute_undo_success() throws CommandException {
        model.setAddressBook(getTypicalAddressBook());
        model.setUserPrefs(new UserPrefs());
        UpdateSecLevelCommand updateSecLevelCommand = new UpdateSecLevelCommand();
        updateSecLevelCommand.execute(model);
        // after updating, the state before and state now must be different.
        assertFalse(getTypicalAddressBook().equals(model.getAddressBook()));
        UpdateSecLevelCommand undoUpdateSecLevelCommand1 = new UpdateSecLevelCommand(true);
        undoUpdateSecLevelCommand1.execute(model);

        // after undo updating, the state goes back to the previous state -> equal
        assertEquals(getTypicalAddressBook(), model.getAddressBook());

        undolevelToRemoveDependencyForTestCases();
    }

    @Test
    public void execute_undo_failure() throws CommandException {
        model.setAddressBook(getTypicalAddressBook());
        model.setUserPrefs(new UserPrefs());
        UpdateSecLevelCommand undoUpdateSecLevelCommand1 = new UpdateSecLevelCommand(true);
        CommandResult undoResult = undoUpdateSecLevelCommand1.execute(model);

        // Undolevel command without uplevel command has been done should show failure message
        String expectedMessage = UpdateSecLevelCommand.MESSAGE_UNDO_FAILURE;
        assertEquals(expectedMessage, undoResult.getFeedbackToUser());


        UpdateSecLevelCommand updateSecLevelCommand = new UpdateSecLevelCommand();
        updateSecLevelCommand.execute(model); // call uplevel 1 time
        undoUpdateSecLevelCommand1.execute(model); // call undolevel 1st time
        CommandResult undoResult2 = undoUpdateSecLevelCommand1.execute(model); // call undolevel 2nd time
        // Show failure messages when number of times for calling undolevel command is more than that of uplevel.
        assertEquals(expectedMessage, undoResult2.getFeedbackToUser());

        undolevelToRemoveDependencyForTestCases();
    }

    @Test
    public void equalsMethod() {
        UpdateSecLevelCommand updateSecLevelCommand1 = new UpdateSecLevelCommand();
        UpdateSecLevelCommand updateSecLevelCommand2 = new UpdateSecLevelCommand();
        Command otherCommand = new ListCommand();
        // any two updateSecLevelCommand are equals
        assertEquals(updateSecLevelCommand1, updateSecLevelCommand2);

        // null -> not equal
        assertFalse(updateSecLevelCommand1.equals(null));

        // different type -> not equal
        assertFalse(updateSecLevelCommand1.equals(2.3));
        assertFalse(updateSecLevelCommand1.equals(otherCommand));

    }

    private Student getSec4Student(String name) {
        return new PersonBuilder().withName(name)
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withGender("F").withSecLevel("4")
                .withNearestMrtStation("Jurong East")
                .withSubjects("Chinese").build();
    }

    private Student[] getNonSec4Students() {
        return new Student[] {
                new PersonBuilder().withName("A")
                        .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                        .withPhone("94351253").withGender("F").withSecLevel("1")
                        .withNearestMrtStation("Jurong East")
                        .withSubjects("Chinese").build(),
                new PersonBuilder().withName("B")
                        .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                        .withPhone("94351253").withGender("F").withSecLevel("2")
                        .withNearestMrtStation("Jurong East")
                        .withSubjects("Chinese").build(),
                new PersonBuilder().withName("C")
                        .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                        .withPhone("94351253").withGender("F").withSecLevel("3")
                        .withNearestMrtStation("Jurong East")
                        .withSubjects("Chinese").build()
        };
    }

    /**
     * perform undolevel command to clear the state keep tracked by UpdateSecLevelCommand class
     * to remove dependency between test cases.
     * @throws CommandException
     */
    private void undolevelToRemoveDependencyForTestCases() throws CommandException {
        Model sampleModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        UpdateSecLevelCommand updateSecLevelCommand = new UpdateSecLevelCommand(true);

        // keep execute undo until undo failure message occur to ensure that there is no state keep track by
        // UpdateSecLevelCommand class.
        while (true) {
            CommandResult commandResult = updateSecLevelCommand.execute(sampleModel);
            if (commandResult.getFeedbackToUser() == UpdateSecLevelCommand.MESSAGE_UNDO_FAILURE) {
                break;
            }
        }
    }
}
