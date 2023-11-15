package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookWithCoursesCleared;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCoursesCommandTest {

    @Test
    public void execute_clearAllCoursesInAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBookWithCoursesCleared(), new UserPrefs());
        assertCommandSuccess(new ClearCoursesCommand(), model, ClearCoursesCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
