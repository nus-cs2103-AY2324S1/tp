package seedu.address.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.InternshipCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.InternshipBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.InternshipModel;
import seedu.address.model.ReadOnlyInternshipBook;
import seedu.address.model.internship.Internship;
import seedu.address.storage.InternshipStorage;

/**
 * The main LogicManager of the app.
 */
public class InternshipLogicManager implements InternshipLogic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(InternshipLogicManager.class);

    private final InternshipModel model;
    private final InternshipStorage storage;
    private final InternshipBookParser internshipBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public InternshipLogicManager(InternshipModel model, InternshipStorage storage) {
        this.model = model;
        this.storage = storage;
        internshipBookParser = new InternshipBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        InternshipCommand command = internshipBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveInternshipBook(model.getInternshipBook());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyInternshipBook getInternshipBook() {
        return model.getInternshipBook();
    }


    @Override
    public ObservableList<Internship> getFilteredInternshipList() {
        return model.getFilteredInternshipList();
    }

    @Override
    public Path getInternshipBookFilePath() {
        return model.getInternshipBookFilePath();
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
    public SortCommand.Order getComparatorOrder() {
        return model.getComparatorOrder();
    }

    @Override
    public String getComparatorPrefix() {
        return model.getComparatorPrefix();
    }

    @Override
    public String getFilterParameter() {
        return model.getFilterParameter();
    }

    @Override
    public String getFilterValue() {
        return model.getFilterValue();
    }
}
