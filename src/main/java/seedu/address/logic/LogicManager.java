package seedu.address.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.PrescriptionListParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.PrescriptionList;
import seedu.address.model.ReadOnlyPrescriptionList;
import seedu.address.model.prescription.Prescription;
import seedu.address.storage.Storage;

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
    private final PrescriptionListParser prescriptionListParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        prescriptionListParser = new PrescriptionListParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = prescriptionListParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            checkAndMoveEndedPrescriptions();
            checkAndResetConsumptionCount();
            storage.savePrescriptionList(getPrescriptionList());
            storage.saveCompletedPrescriptionList(getCompletedPrescriptionList());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public void checkAndMoveEndedPrescriptions() {
        PrescriptionList prescriptionListCopy = new PrescriptionList(getPrescriptionList());
        for (Prescription prescription : prescriptionListCopy.getPrescriptionList()) {
            if (!prescription.isEnded()) {
                continue;
            }
            deleteAndMovePrescription(prescription);
        }
    }

    /**
     * Resets the consumption count of all prescriptions in the prescription list.
     */
    public void checkAndResetConsumptionCount() {

        LocalDate storedDate = model.getStoredDate();

        if (!(storedDate == null || storedDate.isBefore(LocalDate.now()))) {
            return;
        }

        PrescriptionList prescriptionListCopy = new PrescriptionList(getPrescriptionList());

        for (Prescription prescription : prescriptionListCopy.getPrescriptionList()) {
            prescription.resetConsumptionCount();
        }

        model.setStoredDate(LocalDate.now());
    }



    private void deleteAndMovePrescription(Prescription prescription) {
        model.deletePrescription(prescription);

        if (!model.hasCompletedPrescription(prescription)) {
            model.addCompletedPrescription(prescription);
        }
    }

    @Override
    public ReadOnlyPrescriptionList getPrescriptionList() {
        return model.getPrescriptionList();
    }

    @Override
    public ReadOnlyPrescriptionList getCompletedPrescriptionList() {
        return model.getCompletedPrescriptionList();
    }

    @Override
    public ObservableList<Prescription> getFilteredPrescriptionList() {
        return model.getFilteredPrescriptionList();
    }
    @Override
    public ObservableList<Prescription> getFilteredCompletedPrescriptionList() {
        return model.getFilteredCompletedPrescriptionList();
    }

    @Override
    public Path getPrescriptionListFilePath() {
        return model.getPrescriptionListFilePath();
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
    public LocalDate getStoredDate() {
        return model.getStoredDate();
    }

    @Override
    public void setStoredDate(LocalDate storedDate) {
        model.setStoredDate(storedDate);
    }
}
