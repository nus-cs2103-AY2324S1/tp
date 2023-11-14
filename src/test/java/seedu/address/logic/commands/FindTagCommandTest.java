package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;
import static seedu.address.testutil.TypicalTasks.BUDGET;
import static seedu.address.testutil.TypicalTasks.DRAFT;
import static seedu.address.testutil.TypicalTasks.FUNDING;
import static seedu.address.testutil.TypicalTasks.getTypicalTasks;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsTagsPredicate;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskContainsTagsPredicate;


public class FindTagCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TaskContainsTagsPredicate firstTaskPredicate =
                new TaskContainsTagsPredicate(Collections.singletonList("firstTag"));
        TaskContainsTagsPredicate secondTaskPredicate =
                new TaskContainsTagsPredicate(Collections.singletonList("secondTag"));

        PersonContainsTagsPredicate firstPersonPredicate =
                new PersonContainsTagsPredicate(Collections.singletonList("firstTag"));
        PersonContainsTagsPredicate secondPersonPredicate =
                new PersonContainsTagsPredicate(Collections.singletonList("secondTag"));

        FindTagCommand firstFindTagCommand = new FindTagCommand(firstPersonPredicate, firstTaskPredicate);
        FindTagCommand secondFindTagCommand = new FindTagCommand(secondPersonPredicate, secondTaskPredicate);

        // same object -> returns true
        assertEquals(firstFindTagCommand, firstFindTagCommand);

        // same values -> returns true
        FindTagCommand firstFindTagCommandCopy = new FindTagCommand(firstPersonPredicate, firstTaskPredicate);
        assertEquals(firstFindTagCommand, firstFindTagCommandCopy);

        // different types -> returns false
        assertNotEquals(firstFindTagCommand, 1);
        assertNotEquals(1, firstFindTagCommand);

        // null -> returns false
        assertNotEquals(null, firstFindTagCommand);

        // different tag -> returns false
        assertNotEquals(firstFindTagCommand, secondFindTagCommand);
    }

    @Test
    public void execute_zeroTags_noPersonsTasksFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_TASKS_LISTED_OVERVIEW, 0, 0);
        TaskContainsTagsPredicate taskPredicate = prepareTaskTagPredicate(" ");
        PersonContainsTagsPredicate personPredicate = preparePersonTagPredicate(" ");
        FindTagCommand command = new FindTagCommand(personPredicate, taskPredicate);
        expectedModel.updateFilteredTaskList(taskPredicate);
        expectedModel.updateFilteredPersonList(personPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        TaskContainsTagsPredicate taskPredicate = new TaskContainsTagsPredicate(List.of("tag1"));
        PersonContainsTagsPredicate personPredicate = new PersonContainsTagsPredicate(List.of("tag2"));
        FindTagCommand findTagCommand = new FindTagCommand(personPredicate, taskPredicate);
        String expected = FindTagCommand.class.getCanonicalName()
                + "{personPredicate=" + personPredicate + ", taskPredicate=" + taskPredicate + "}";
        assertEquals(expected, findTagCommand.toString());
    }

    @Test
    public void execute_multipleTags_multiplePersonsTasksFound() {
        // Define some tasks and persons with tags that we can search for in our tests.
        TaskContainsTagsPredicate taskPredicate = prepareTaskTagPredicate("finance");
        PersonContainsTagsPredicate personPredicate = preparePersonTagPredicate("friends");

        FindTagCommand command = new FindTagCommand(personPredicate, taskPredicate);
        expectedModel.updateFilteredTaskList(taskPredicate);
        expectedModel.updateFilteredPersonList(personPredicate);

        String expectedMessage = String.format(MESSAGE_PERSONS_TASKS_LISTED_OVERVIEW, 3, 3);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(Arrays.asList(BUDGET, DRAFT, FUNDING), model.getFilteredTaskList());

        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code TaskContainsTagsPredicate}.
     */
    private TaskContainsTagsPredicate prepareTaskTagPredicate(String userInput) {
        return new TaskContainsTagsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PersonContainsTagsPredicate}.
     */
    private PersonContainsTagsPredicate preparePersonTagPredicate(String userInput) {
        return new PersonContainsTagsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();

        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }

        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }

        return ab;
    }

}
