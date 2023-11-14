package seedu.address.logic.commands.contact;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.contacts.ClearContactsCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.EventsBook;
import seedu.address.model.FinancesBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearContactsCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearContactsCommand(), model, ClearContactsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalPersonsBook(), new EventsBook(), new FinancesBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPersonsBook(), new EventsBook(), new FinancesBook(),
                new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearContactsCommand(), model, ClearContactsCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
