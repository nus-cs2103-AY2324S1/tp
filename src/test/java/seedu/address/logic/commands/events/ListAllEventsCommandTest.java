package seedu.address.logic.commands.events;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.EventsBook;
import seedu.address.model.FinancesBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListAllCommand.
 */
public class ListAllEventsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPersonsBook(), new EventsBook(), new FinancesBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new EventsBook(), new FinancesBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListAllEventsCommand(), model, ListAllEventsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        // TO IMPLEMENT WITH EDIT
        // showPersonAtIndex(model, INDEX_FIRST_EVENT);
        assertCommandSuccess(new ListAllEventsCommand(), model, ListAllEventsCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
