package seedu.address.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AnimalCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AnimalCatalogParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AnimalModel;
import seedu.address.model.ReadOnlyAnimalCatalog;
import seedu.address.model.animal.Animal;
import seedu.address.storage.AnimalStorage;

/**
 * The main LogicManager of the app.
 */
public class AnimalLogicManager implements AnimalLogic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(AnimalLogicManager.class);

    private final AnimalModel model;
    private final AnimalStorage storage;
    private final AnimalCatalogParser animalCatalogParser;

    /**
     * Constructs a {@code AnimalLogicManager} with the given {@code AnimalModel} and {@code AnimalStorage}.
     */
    public AnimalLogicManager(AnimalModel model, AnimalStorage storage) {
        this.model = model;
        this.storage = storage;
        animalCatalogParser = new AnimalCatalogParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        AnimalCommand command = animalCatalogParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAnimalCatalog(model.getAnimalCatalog());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAnimalCatalog getAnimalCatalog() {
        return model.getAnimalCatalog();
    }

    @Override
    public ObservableList<Animal> getFilteredAnimalList() {
        return model.getFilteredAnimalList();
    }

    @Override
    public Path getAnimalCatalogFilePath() {
        return model.getAnimalCatalogFilePath();
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
