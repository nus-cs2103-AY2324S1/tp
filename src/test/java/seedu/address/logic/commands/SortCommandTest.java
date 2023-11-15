package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.sorter.PersonNameAscendingSorter;
import seedu.address.model.person.sorter.PersonNameDescendingSorter;
import seedu.address.model.person.sorter.PersonSorter;

public class SortCommandTest {

    public static final String NAME_ASCENDING = "name-ascending";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {

        PersonSorter firstPersonSorter = new PersonNameAscendingSorter();
        PersonSorter secondPersonSorter = new PersonNameDescendingSorter();

        SortCommand firstCommand = new SortCommand(firstPersonSorter);
        SortCommand secondCommand = new SortCommand(secondPersonSorter);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        SortCommand firstCommandCopy = new SortCommand(firstPersonSorter);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(NAME_ASCENDING));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different sort commands -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void execute_validSortCommandUnsortedList_success() {
        PersonSorter personSorter = new PersonNameAscendingSorter();
        SortCommand sortCommand = new SortCommand(personSorter);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.sortPersonList(personSorter);

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }
}
