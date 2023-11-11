package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class ListGroupCommandTest {
    private Model model;
    private Model expectedModel;

    @Test
    public void execute_typicalAddressBook_success() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        String expectedMessage = "Groups in address book:\n" + "CS2105\n";

        assertCommandSuccess(new ListGroupCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        String expectedMessage = "Groups in address book:\n";

        assertCommandSuccess(new ListGroupCommand(), model, expectedMessage, expectedModel);
    }
}
