package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.JAMES;
import static seedu.address.testutil.TypicalPersons.KAREN;
import static seedu.address.testutil.TypicalPersons.LUCAS;
import static seedu.address.testutil.TypicalTasks.GUESTLIST;
import static seedu.address.testutil.TypicalTasks.HOSPITALITY;
import static seedu.address.testutil.TypicalTasks.INVITATION;
import static seedu.address.testutil.TypicalTasks.JOURNAL;
import static seedu.address.testutil.TypicalTasks.KPI;
import static seedu.address.testutil.TypicalTasks.LECTURE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonContainsAllTagsPredicate;
import seedu.address.model.task.TaskContainsAllTagsPredicate;

public class FindAllTagCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TaskContainsAllTagsPredicate firstTaskPredicate =
                new TaskContainsAllTagsPredicate(Collections.singletonList("firstTag"));
        TaskContainsAllTagsPredicate secondTaskPredicate =
                new TaskContainsAllTagsPredicate(Collections.singletonList("secondTag"));

        PersonContainsAllTagsPredicate firstPersonPredicate =
                new PersonContainsAllTagsPredicate(Collections.singletonList("firstTag"));
        PersonContainsAllTagsPredicate secondPersonPredicate =
                new PersonContainsAllTagsPredicate(Collections.singletonList("secondTag"));

        FindAllTagCommand firstFindAllTagCommand = new FindAllTagCommand(firstPersonPredicate, firstTaskPredicate);
        FindAllTagCommand secondFindAllTagCommand = new FindAllTagCommand(secondPersonPredicate, secondTaskPredicate);

        // same object -> returns true
        assertEquals(firstFindAllTagCommand, firstFindAllTagCommand);

        // same values -> returns true
        FindAllTagCommand firstFindAllTagCommandCopy = new FindAllTagCommand(firstPersonPredicate, firstTaskPredicate);
        assertEquals(firstFindAllTagCommand, firstFindAllTagCommandCopy);

        // different types -> returns false
        assertNotEquals(firstFindAllTagCommand, 1);

        // null -> returns false
        assertNotEquals(firstFindAllTagCommand, null);

        // different tag -> returns false
        assertNotEquals(firstFindAllTagCommand, secondFindAllTagCommand);
    }

    @Test
    public void execute_invalidTag_noPersonsTasksFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_TASKS_LISTED_OVERVIEW, 0, 0);
        TaskContainsAllTagsPredicate taskPredicate = prepareTaskTagPredicate("invalidTag");
        PersonContainsAllTagsPredicate personPredicate = preparePersonTagPredicate("invalidTag");
        FindAllTagCommand command = new FindAllTagCommand(personPredicate, taskPredicate);
        expectedModel.updateFilteredTaskList(taskPredicate);
        expectedModel.updateFilteredPersonList(personPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        TaskContainsAllTagsPredicate taskPredicate = new TaskContainsAllTagsPredicate(List.of("tag1"));
        PersonContainsAllTagsPredicate personPredicate = new PersonContainsAllTagsPredicate(List.of("tag2"));
        FindAllTagCommand findAllTagCommand = new FindAllTagCommand(personPredicate, taskPredicate);
        String expected = FindAllTagCommand.class.getCanonicalName()
                + "{personPredicate=" + personPredicate + ", taskPredicate=" + taskPredicate + "}";
        assertEquals(expected, findAllTagCommand.toString());
    }

    @Test
    public void execute_multipleTags_multipleTasksFound() {

        TaskContainsAllTagsPredicate taskPredicate = prepareTaskTagPredicate("admin class");
        PersonContainsAllTagsPredicate personPredicate = preparePersonTagPredicate("admin class");

        FindAllTagCommand command = new FindAllTagCommand(personPredicate, taskPredicate);
        expectedModel.updateFilteredTaskList(taskPredicate);
        expectedModel.updateFilteredPersonList(personPredicate);

        String expectedMessage = String.format(MESSAGE_PERSONS_TASKS_LISTED_OVERVIEW, 0, 3);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(Arrays.asList(GUESTLIST, HOSPITALITY, INVITATION), model.getFilteredTaskList());
        assertEquals(Arrays.asList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleTags_multiplePersonsTasksFound() {
        TaskContainsAllTagsPredicate taskPredicate = prepareTaskTagPredicate("colleague syndicateA");
        PersonContainsAllTagsPredicate personPredicate = preparePersonTagPredicate("colleague syndicateA");

        FindAllTagCommand command = new FindAllTagCommand(personPredicate, taskPredicate);
        System.out.println(model.getFilteredTaskList());
        expectedModel.updateFilteredTaskList(taskPredicate);
        expectedModel.updateFilteredPersonList(personPredicate);

        String expectedMessage = String.format(MESSAGE_PERSONS_TASKS_LISTED_OVERVIEW, 2, 2);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(Arrays.asList(JOURNAL, LECTURE), model.getFilteredTaskList());
        assertEquals(Arrays.asList(JAMES, LUCAS), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleTags_kpiTaskAndKarenFound() {
        // Prepare predicate for the tags "colleague" and "syndicateB"
        TaskContainsAllTagsPredicate taskPredicate = prepareTaskTagPredicate("colleague syndicateB");
        PersonContainsAllTagsPredicate personPredicate = preparePersonTagPredicate("colleague syndicateB");

        FindAllTagCommand command = new FindAllTagCommand(personPredicate, taskPredicate);
        expectedModel.updateFilteredTaskList(taskPredicate);
        expectedModel.updateFilteredPersonList(personPredicate);

        String expectedMessage = String.format(MESSAGE_PERSONS_TASKS_LISTED_OVERVIEW, 1, 1);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        // Only KPI task and KAREN person should match
        assertEquals(Collections.singletonList(KPI), model.getFilteredTaskList());
        assertEquals(Collections.singletonList(KAREN), model.getFilteredPersonList());
    }

    @Test
    public void equals_diffPersonPredicates_returnsFalse() {
        TaskContainsAllTagsPredicate commonTaskPredicate =
                new TaskContainsAllTagsPredicate(Collections.singletonList("commonTag"));

        PersonContainsAllTagsPredicate firstPersonPredicate =
                new PersonContainsAllTagsPredicate(Collections.singletonList("firstPersonTag"));
        PersonContainsAllTagsPredicate secondPersonPredicate =
                new PersonContainsAllTagsPredicate(Collections.singletonList("secondPersonTag"));

        FindAllTagCommand commandWithFirstPersonPredicate =
                new FindAllTagCommand(firstPersonPredicate, commonTaskPredicate);
        FindAllTagCommand commandWithSecondPersonPredicate =
                new FindAllTagCommand(secondPersonPredicate, commonTaskPredicate);

        assertNotEquals(commandWithFirstPersonPredicate, commandWithSecondPersonPredicate);
    }

    /**
     * Parses {@code userInput} into a {@code TaskContainsAllTagsPredicate}.
     */
    private TaskContainsAllTagsPredicate prepareTaskTagPredicate(String userInput) {
        return new TaskContainsAllTagsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PersonContainsAllTagsPredicate}.
     */
    private PersonContainsAllTagsPredicate preparePersonTagPredicate(String userInput) {
        return new PersonContainsAllTagsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

}
