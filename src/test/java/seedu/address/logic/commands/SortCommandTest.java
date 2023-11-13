package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalEvents.getTypicalCalendar;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalUnsortedAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskManager;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.comparer.SortComparator;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
public class SortCommandTest {

    private Model model;
    private Model expectedModel;
    private Model unsortedModel;
    private Model expectedSortedModel;
    private ArrayList<SortComparator> sortComparatorArrayList;
    private NameComparatorStub nameComparatorStub;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalCalendar(), getTypicalTaskManager(),
                new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), getTypicalCalendar(), getTypicalTaskManager(),
                new UserPrefs());
        unsortedModel = new ModelManager(getTypicalUnsortedAddressBook(), getTypicalCalendar(), getTypicalTaskManager(),
                new UserPrefs());
        expectedSortedModel = new ModelManager(model.getAddressBook(), getTypicalCalendar(), getTypicalTaskManager(),
                new UserPrefs());
        sortComparatorArrayList = new ArrayList<>();
        nameComparatorStub = new NameComparatorStub(true, false, 1);
        sortComparatorArrayList.add(nameComparatorStub);
    }

    @Test
    public void execute_sortByName_showsCorrectSortedList() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new SortCommand(sortComparatorArrayList), unsortedModel,
                SortCommand.MESSAGE_SUCCESS, expectedSortedModel);
    }
    public class NameComparatorStub extends SortComparator {

        /**
         * Creates a new SortComparator with the given parameters.
         *
         * @param isActive  Whether this comparator is currently active.
         * @param isReverse Whether to sort in reverse order.
         * @param priority  The priority of this comparator.
         */
        public NameComparatorStub(boolean isActive, boolean isReverse, int priority) {
            super(isActive, isReverse, priority);
        }

        @Override
        public int compare(Person p1, Person p2) {
            return p1.getName().fullName.compareTo(p2.getName().fullName);
        }
    }
}

