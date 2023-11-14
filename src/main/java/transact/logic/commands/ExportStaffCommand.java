package transact.logic.commands;

import java.nio.file.Path;

import transact.logic.commands.exceptions.CommandException;
import transact.model.Model;
import transact.ui.MainWindow;

/**
 * Creates export staff command
 */
public class ExportStaffCommand extends Command {
    public static final String COMMAND_WORD = "exportstaff";
    public static final String MESSAGE_EXPORT_ACKNOWLEDGEMENT = "Exporting Staff as requested ...";

    private final Path exportFilePath;

    public ExportStaffCommand(Path path) {
        this.exportFilePath = path;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.setExportFilePath(exportFilePath);
        return new CommandResult(MESSAGE_EXPORT_ACKNOWLEDGEMENT, MainWindow.TabWindow.ADDRESSBOOK, false, false, false, false, true);
    }
}
