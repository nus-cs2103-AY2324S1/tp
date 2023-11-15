package seedu.cc.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import seedu.cc.commons.core.GuiSettings;
import seedu.cc.commons.core.LogsCenter;
import seedu.cc.logic.commands.Command;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.logic.parser.ClinicBookParser;
import seedu.cc.logic.parser.exceptions.ParseException;
import seedu.cc.model.Model;
import seedu.cc.model.ReadOnlyClinicBook;
import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.patient.Patient;
import seedu.cc.storage.Storage;

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
    private final ClinicBookParser clinicBookParser;
    private final IntegerProperty currentTab = new SimpleIntegerProperty(this, "currentTab", 0);

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        clinicBookParser = new ClinicBookParser();
        this.model.currentTabProperty().addListener((observable, oldValue, newValue) -> {
            // Update LogicManager's currentTab property with the new value
            this.currentTab.set(newValue.intValue());
        });

    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = clinicBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveClinicBook(model.getClinicBook());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyClinicBook getClinicBook() {
        return model.getClinicBook();
    }

    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return model.getFilteredPatientList();
    }

    @Override
    public ObservableList<MedicalHistoryEvent> getFilteredMedicalHistoryEventList() {
        return model.getFilteredMedicalHistoryEventList();
    }

    @Override
    public ObservableList<AppointmentEvent> getFilteredAppointmentList() {
        return model.getFilteredAppointmentList();
    }

    @Override
    public Path getClinicBookFilePath() {
        return model.getClinicBookFilePath();
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
    public IntegerProperty currentTabProperty() {
        return currentTab;
    }

    public int getCurrentTab() {
        return currentTab.get();
    }

    public void setCurrentTab(int tab) {
        model.setCurrentTab(tab);
        this.currentTab.set(tab);
    }
}
