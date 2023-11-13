package transact.logic.commands;

import org.junit.jupiter.api.Test;
import transact.model.Model;
import transact.model.ModelManager;
import transact.ui.MainWindow;

import java.nio.file.Path;
import java.nio.file.Paths;

import static transact.logic.commands.CommandTestUtil.assertCommandSuccess;
import static transact.logic.commands.ExportStaffCommand.MESSAGE_EXPORT_ACKNOWLEDGEMENT;

public class ExportStaffCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exportStaff_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXPORT_ACKNOWLEDGEMENT, MainWindow.TabWindow.ADDRESSBOOK, false,
                false, false,false,true);
        Path exportPath = Paths.get("test-export.json");
        assertCommandSuccess(new ExportStaffCommand(exportPath), model, expectedCommandResult, expectedModel);
    }
}
