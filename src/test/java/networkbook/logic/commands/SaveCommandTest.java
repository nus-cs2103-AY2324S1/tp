package networkbook.logic.commands;

import static networkbook.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import networkbook.model.Model;
import networkbook.model.ModelManager;

public class SaveCommandTest {
    private Model expectedModel = new ModelManager();
    private Model actualModel = new ModelManager();

    @Test
    public void execute_save_success() {
        assertCommandSuccess(new SaveCommand(), actualModel, SaveCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
