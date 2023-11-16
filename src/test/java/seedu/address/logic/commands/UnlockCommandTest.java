package seedu.address.logic.commands;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
public class UnlockCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        expectedModel = new ModelManager();
    }

    @Test
    public void execute_unlock_success() {
        //Works only for default password
        assertCommandSuccess(new UnlockCommand("Password123!"), model, UnlockCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_unlock_failure() {
        assertCommandSuccess(new UnlockCommand("Password123"), model, UnlockCommand.MESSAGE_FAILURE,
                expectedModel);
    }

}
