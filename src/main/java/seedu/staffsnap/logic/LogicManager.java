package seedu.staffsnap.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.staffsnap.commons.core.GuiSettings;
import seedu.staffsnap.commons.core.LogsCenter;
import seedu.staffsnap.logic.commands.Command;
import seedu.staffsnap.logic.commands.CommandResult;
import seedu.staffsnap.logic.commands.exceptions.CommandException;
import seedu.staffsnap.logic.parser.ApplicantBookParser;
import seedu.staffsnap.logic.parser.exceptions.ParseException;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.ReadOnlyApplicantBook;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ApplicantBookParser applicantBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        applicantBookParser = new ApplicantBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = applicantBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveApplicantBook(model.getApplicantBook());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyApplicantBook getApplicantBook() {
        return model.getApplicantBook();
    }

    @Override
    public ObservableList<Applicant> getFilteredApplicantList() {
        return model.getFilteredApplicantList();
    }

    @Override
    public Path getApplicantBookFilePath() {
        return model.getApplicantBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
