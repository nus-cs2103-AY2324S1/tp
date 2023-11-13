package transact.logic.commands;

import static transact.logic.commands.CommandTestUtil.assertCommandSuccess;
import static transact.logic.commands.ExportTransactionCommand.MESSAGE_EXPORT_ACKNOWLEDGEMENT;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import transact.model.Model;
import transact.model.ModelManager;
import transact.ui.MainWindow;


public class ExportTransactionCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exportTransaction_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXPORT_ACKNOWLEDGEMENT, MainWindow.TabWindow.TRANSACTIONS, false,
                false, false, true, false);
        Path exportPath = Paths.get("test-export.csv");
        assertCommandSuccess(new ExportTransactionCommand(exportPath), model, expectedCommandResult, expectedModel);
    }
}
