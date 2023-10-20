package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.FILTER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FILTER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalCalendar;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.PersonFilterBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
class FilterCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalCalendar(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalCalendar(), new UserPrefs());

    @Test
    public void execute_oneField_multiplePersonsDisplayed() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FilterCommand.PersonFilter filter = new PersonFilterBuilder().withPhone("948").build();
        FilterCommand command = new FilterCommand(filter);
        expectedModel.updateFilteredPersonList(filter::matchesFilter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }
    @Test
    public void execute_multipleKeywords_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FilterCommand.PersonFilter filter = new PersonFilterBuilder().withName("carl").withEmail("heinz").build();
        FilterCommand command = new FilterCommand(filter);
        expectedModel.updateFilteredPersonList(filter::matchesFilter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL), model.getFilteredPersonList());
    }
    @Test
    public void toStringMethod() {
        FilterCommand.PersonFilter filter = new PersonFilterBuilder().build();
        FilterCommand filterCommand = new FilterCommand(filter);
        String expected = FilterCommand.class.getCanonicalName() + "{personFilter=" + filter + "}";
        assertEquals(expected, filterCommand.toString());
    }

    @Test
    public void equals() {
        FilterCommand.PersonFilter firstFilter =
                new FilterCommand.PersonFilter(FILTER_AMY);
        FilterCommand.PersonFilter secondFilter =
                new FilterCommand.PersonFilter(FILTER_BOB);

        FilterCommand filterFirstCommand = new FilterCommand(firstFilter);
        FilterCommand filterSecondCommand = new FilterCommand(secondFilter);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstFilter);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }
}
