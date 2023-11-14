package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.testutil.TypicalEmployees.BENSON;
import static seedu.address.testutil.TypicalProjects.alphaFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.SortTaskCommand;
import seedu.address.model.employee.EmployeeNameContainsKeywordsPredicate;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TaskHubBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TaskHub(), new TaskHub(modelManager.getTaskHub()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTaskHubFilePath(Paths.get("task/hub/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTaskHubFilePath(Paths.get("new/task/hub/file/path"));
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
    public void setTaskHubFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTaskHubFilePath(null));
    }

    @Test
    public void setTaskHubFilePath_validPath_setsTaskHubFilePath() {
        Path path = Paths.get("task/hub/file/path");
        modelManager.setTaskHubFilePath(path);
        assertEquals(path, modelManager.getTaskHubFilePath());
    }

    @Test
    public void hasEmployee_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEmployee(null));
    }

    @Test
    public void hasProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasProject(null));
    }

    @Test
    public void hasEmployee_employeeNotInTaskHub_returnsFalse() {
        assertFalse(modelManager.hasEmployee(ALICE));
    }

    @Test
    public void hasProject_projectNotInTaskHub_returnsFalse() {
        assertFalse(modelManager.hasProject(alphaFactory()));
    }

    @Test
    public void hasEmployee_employeeInTaskHub_returnsTrue() {
        modelManager.addEmployee(ALICE);
        assertTrue(modelManager.hasEmployee(ALICE));
    }

    @Test
    public void hasProject_projectInTaskHub_returnsTrue() {
        modelManager.addProject(alphaFactory());
        assertTrue(modelManager.hasProject(alphaFactory()));
    }

    @Test
    public void getFilteredEmployeeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredEmployeeList().remove(0));
    }

    @Test
    public void sortTasksByDeadlineAndCompletion_tasksAreOrdered_returnsTrue() {
        ModelManager originalModel = new ModelManager();
        ModelManager expectedModel = new ModelManager();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

        Task firstTask = new TaskBuilder().withName("First")
                .withDoneness(true)
                .withDeadline(LocalDateTime.parse("01-01-2020 1900", formatter)).build();

        Task secondTask = new TaskBuilder().withName("Second")
                .withDoneness(false)
                .withDeadline(LocalDateTime.parse("01-01-2021 1900", formatter)).build();

        Task thirdTask = new TaskBuilder().withName("Third")
                .withDoneness(true)
                .withDeadline(LocalDateTime.parse("01-01-2022 1900", formatter)).build();

        Task fourthTask = new TaskBuilder().withName("Fourth")
                .withDoneness(false)
                .withDeadline(LocalDateTime.parse("01-01-2022 1900", formatter)).build();

        Project project = new ProjectBuilder().withName("Build Website")
                .withTasks(firstTask, secondTask, thirdTask, fourthTask).build();
        Project expectedProject = new ProjectBuilder().withName("Build Website")
                .withTasks(secondTask, fourthTask, firstTask, thirdTask).build();

        originalModel.addProject(project);
        expectedModel.addProject(expectedProject);
        assertCommandSuccess(new SortTaskCommand(), originalModel, SortTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        TaskHub taskHub = new TaskHubBuilder().withEmployee(ALICE).withEmployee(BENSON).build();
        TaskHub differentTaskHub = new TaskHub();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(taskHub, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(taskHub, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different taskHub -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTaskHub, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredEmployeeList(new EmployeeNameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(taskHub, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTaskHubFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(taskHub, differentUserPrefs)));
    }
}
