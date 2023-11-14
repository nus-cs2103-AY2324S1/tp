package seedu.codesphere.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.codesphere.commons.core.GuiSettings;
import seedu.codesphere.commons.core.LogsCenter;
import seedu.codesphere.logic.commands.Command;
import seedu.codesphere.logic.commands.CommandResult;
import seedu.codesphere.logic.commands.exceptions.CommandException;
import seedu.codesphere.logic.parser.CodeSphereParser;
import seedu.codesphere.logic.parser.exceptions.ParseException;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.ReadOnlyCourseList;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.storage.Storage;

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
    private final CodeSphereParser codeSphereParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        codeSphereParser = new CodeSphereParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = codeSphereParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveCourseList(model.getCourseList());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyCourseList getCourseList() {
        return model.getCourseList();
    }

    @Override
    public ObservableList<Course> getFilteredCourseList() {
        return model.getFilteredCourseList();
    }

    @Override
    public Path getCourseListFilePath() {
        return model.getCodeSphereFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public Storage getStorage() {
        return storage;
    }
}
