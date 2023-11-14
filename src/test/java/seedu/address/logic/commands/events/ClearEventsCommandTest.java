package seedu.address.logic.commands.events;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalEventsBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.EventsBook;
import seedu.address.model.FinancesBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class ClearEventsCommandTest {

    @Test
    public void execute_emptyEventsBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearEventsCommand(), model, ClearEventsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEventsBook_success() {
        Model model = new ModelManager(new AddressBook(), getTypicalEventsBook(), new FinancesBook(), new UserPrefs());
        Model expectedModel = new ModelManager(new AddressBook(), getTypicalEventsBook(), new FinancesBook(),
                new UserPrefs());
        expectedModel.setEventsBook(new EventsBook());

        assertCommandSuccess(new ClearEventsCommand(), model, ClearEventsCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
