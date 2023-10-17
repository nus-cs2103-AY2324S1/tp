package seedu.lovebook.logic.commands;

import static seedu.lovebook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.lovebook.testutil.TypicalDatePrefs.getTypicalDatePrefs;
import static seedu.lovebook.testutil.TypicalPersons.getTypicalLoveBook;

import org.junit.jupiter.api.Test;

import seedu.lovebook.model.LoveBook;
import seedu.lovebook.model.Model;
import seedu.lovebook.model.ModelManager;
import seedu.lovebook.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyLoveBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyLoveBook_success() {
        Model model = new ModelManager(getTypicalLoveBook(), new UserPrefs(), getTypicalDatePrefs());
        Model expectedModel = new ModelManager(getTypicalLoveBook(), new UserPrefs(), getTypicalDatePrefs());
        expectedModel.setLoveBook(new LoveBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
