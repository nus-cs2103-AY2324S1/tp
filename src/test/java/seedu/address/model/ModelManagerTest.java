package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONSULTATIONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GRADED_TEST;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SESSIONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGradedTest.GT1;
import static seedu.address.testutil.TypicalGradedTest.GT3;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalSessions.SESSION1A;
import static seedu.address.testutil.TypicalTasks.TASK1;
import static seedu.address.testutil.TypicalTasks.TASK2;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.gradedtest.GradedTestNameContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionList;
import seedu.address.model.session.SessionNumber;
import seedu.address.model.session.SessionNumberContainsKeywordsPredicate;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.ConsultationBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.SessionBuilder;
import seedu.address.testutil.TypicalPersons;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
        assertEquals(new TaskListBook(), new TaskListBook(modelManager.getTaskList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void setTaskListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTaskListFilePath(null));
    }

    @Test
    public void setTaskListFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("task/list/file/path");
        modelManager.setTaskListFilePath(path);
        assertEquals(path, modelManager.getTaskListFilePath());
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTaskList_returnsFalse() {
        assertFalse(modelManager.hasTask(TASK1));
    }

    @Test
    public void hasTask_taskInTaskList_returnsTrue() {
        modelManager.addTask(TASK1);
        assertTrue(modelManager.hasTask(TASK1));
    }

    @Test
    public void getTask_indexWithinBounds_success() {
        modelManager.addTask(TASK2);
        assertEquals(modelManager.getTask(0), TASK2);
    }
    @Test
    public void getTask_indexOutsideBounds_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> modelManager.getTask(0));
    }

    @Test
    public void matchName_personWithMatchingName_returnsTrue() {
        // matching name
        modelManager.addPerson(ALICE);
        assertEquals(modelManager.getMatchingStudentName(ALICE.getName()), ALICE);
    }

    @Test
    public void matchName_noMatchingNameFound_throwsPersonNotFoundException() {
        // no matching name
        modelManager.addPerson(ALICE);
        assertThrows(PersonNotFoundException.class, () -> modelManager.getMatchingStudentName(BOB.getName()));
    }

    @Test
    public void addConsultation_nullCase_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addConsultation(null));
    }

    @Test
    public void hasConsultation_nullCase_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasConsultation(null));
    }

    @Test
    public void addConsultation_validConsultation_returnsTrue() {
        Consultation consultation = new ConsultationBuilder().build();
        modelManager.addConsultation(consultation);
        assertTrue(modelManager.hasConsultation(consultation));
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTaskList().remove(0));
    }

    @Test
    public void getFilteredConsultationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                modelManager.getFilteredConsultationList().remove(0));
    }

    @Test
    public void findSessionBySessionNumber_successful() {
        SessionList sessionList = new SessionList();
        Person student = new PersonBuilder(TypicalPersons.ALICE).build();
        Session toAdd = new SessionBuilder().withSessionNumber("0").withStudent(student).build();
        sessionList.addSession(toAdd);
        assertTrue(toAdd.equals(sessionList.findSessionBySessionNumber(new SessionNumber("0"))));
    }

    @Test
    public void getFilteredSessionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredSessionList().remove(0));
    }


    @Test
    public void setGradedTestListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGradedTestList(null));
    }

    @Test
    public void setGradedTestListFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("gradedtest/list/file/path");
        modelManager.setTaskListFilePath(path);
        modelManager.setGradedTestListFilePath(path);
        assertEquals(path, modelManager.getTaskListFilePath());
    }

    @Test
    public void hasGradedTest_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasGradedTest(null));
    }

    @Test
    public void hasGradedTest_gradedTestNotInGradedTestList_returnsFalse() {
        assertFalse(modelManager.hasGradedTest(GT3));
    }

    @Test
    public void hasGradedTest_gradedTestInGradedTestList_returnsTrue() {
        modelManager.addGradedTest(GT1);
        assertTrue(modelManager.hasGradedTest(GT1));
    }

    @Test
    public void getGradedTest_indexWithinBounds_success() {
        modelManager.addGradedTest(GT1);
        assertEquals(modelManager.getGradedTest(0), GT1);
    }
    @Test
    public void getGradedTest_indexOutsideBounds_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> modelManager.getGradedTest(0));
    }

    @Test
    public void getFilteredGradedTestList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredGradedTestList().remove(0));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();
        TaskListBook taskList = new TaskListBook();
        ConsultationListBook consultationList = new ConsultationListBook();
        GradedTestListBook gradedTestList = new GradedTestListBook();
        SessionListBook sessionList = new SessionListBook();
        taskList.addTask(TASK1);
        gradedTestList.addGradedTest(GT1);


        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs, taskList, sessionList,
                consultationList, gradedTestList);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs, taskList,
                sessionList, consultationList, gradedTestList);

        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs, taskList,
                sessionList, consultationList, gradedTestList)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));

        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs, taskList,
                sessionList, consultationList, gradedTestList)));


        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different taskList -> returns false
        String[] taskKeywords = TASK1.getName().taskName.split("\\s+");
        modelManager.updateFilteredTaskList(new TaskNameContainsKeywordsPredicate(Arrays.asList(taskKeywords)));

        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs, new TaskListBook(),
                new SessionListBook(), new ConsultationListBook(), new GradedTestListBook())));

        // different consultationList -> returns false
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs, new TaskListBook(),
                new SessionListBook(), new ConsultationListBook(), new GradedTestListBook())));

        // different sessionList -> returns false
        String[] sessionKeywords = SESSION1A.getSessionNumber().sessionNumber.split("");
        modelManager.updateFilteredSessionList(
                new SessionNumberContainsKeywordsPredicate(Arrays.asList(sessionKeywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs,
                new TaskListBook(), new SessionListBook(), new ConsultationListBook(), new GradedTestListBook())));

        // different gradedTestList -> returns false
        String[] gradedTestKeywords = GT1.getGradedTests().toString().split("\\s+");
        modelManager.updateFilteredGradedTestList(
                new GradedTestNameContainsKeywordsPredicate(Arrays.asList(gradedTestKeywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs,
                new TaskListBook(), new SessionListBook(), new ConsultationListBook(), new GradedTestListBook())));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        modelManager.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        modelManager.updateFilteredGradedTestList(PREDICATE_SHOW_ALL_GRADED_TEST);
        modelManager.updateFilteredConsultationList(PREDICATE_SHOW_ALL_CONSULTATIONS);
        modelManager.updateFilteredSessionList(PREDICATE_SHOW_ALL_SESSIONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));

        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs, taskList,
                sessionList, consultationList, gradedTestList)));
    }

}
