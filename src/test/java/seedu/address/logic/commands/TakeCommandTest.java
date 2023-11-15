package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static javafx.collections.FXCollections.observableArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.TakeCommand.MESSAGE_INSUFFICIENT_STOCK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.CompletedPrescriptions.getCompletedPrescriptionList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRESCRIPTION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PRESCRIPTION;
import static seedu.address.testutil.TypicalPrescriptions.getTypicalPrescriptionList;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PrescriptionList;
import seedu.address.model.ReadOnlyPrescriptionList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.SameNamePredicate;
import seedu.address.testutil.PrescriptionBuilder;

public class TakeCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPrescriptionList(), getCompletedPrescriptionList(), new UserPrefs());
    }

    @Test
    public void execute_validDosesToTakeCompleted_success() throws CommandException {
        PrescriptionList prescriptionList = new PrescriptionList();
        PrescriptionList completedPrescriptionList = new PrescriptionList();
        Prescription prescription = new PrescriptionBuilder()
                .withDosage("1")
                .withConsumptionCount("0")
                .withStock("100")
                .build();
        prescriptionList.addPrescription(prescription);

        Model model = new ModelManager(prescriptionList, completedPrescriptionList, new UserPrefs());
        Model expectedModel = new ModelManager(model.getPrescriptionList(), model.getCompletedPrescriptionList(),
                new UserPrefs());

        Prescription prescriptionToTake = model.getFilteredPrescriptionList()
                .get(INDEX_FIRST_PRESCRIPTION.getZeroBased());

        int initialStock = Integer.parseInt(prescriptionToTake.getTotalStock().get().toString());
        Dosage dosesToTake = new Dosage("1"); //Valid number of doses
        int dosesToTakeInt = Integer.parseInt(dosesToTake.toString());

        TakeCommand takePrescriptionCommand = new TakeCommand(
                INDEX_FIRST_PRESCRIPTION, dosesToTake);

        String expectedMessage = String.format(TakeCommand.MESSAGE_SUCCESS,
                                  prescriptionToTake.getName());
        Prescription expectedPrescription = expectedModel.getPrescriptionByName(prescriptionToTake.getName());
        expectedPrescription.getTotalStock().get().decrementCount(dosesToTakeInt);
        expectedPrescription.getConsumptionCount().incrementCount(dosesToTakeInt);
        expectedModel.updateFilteredPrescriptionList(new SameNamePredicate(prescriptionToTake.getName()));

        int newStock = Integer.parseInt(expectedModel.getPrescriptionByName(prescriptionToTake.getName())
                .getTotalStock().get().toString());

        assertCommandSuccess(takePrescriptionCommand, model, expectedMessage, expectedModel);
        assertTrue(prescriptionToTake.getIsCompleted());
        assertEquals(initialStock - dosesToTakeInt, newStock);
    }

    @Test
    public void execute_validDosesToTakeIncomplete_success() throws CommandException {
        PrescriptionList prescriptionList = new PrescriptionList();
        PrescriptionList completedPrescriptionList = new PrescriptionList();
        Prescription prescription = new PrescriptionBuilder()
                .withDosage("5")
                .withConsumptionCount("0")
                .withStock("100")
                .build();
        prescriptionList.addPrescription(prescription);

        Model model = new ModelManager(prescriptionList, completedPrescriptionList, new UserPrefs());
        Model expectedModel = new ModelManager(model.getPrescriptionList(), model.getCompletedPrescriptionList(),
                new UserPrefs());

        Prescription prescriptionToTake = model.getFilteredPrescriptionList()
                .get(INDEX_FIRST_PRESCRIPTION.getZeroBased());

        int initialStock = Integer.parseInt(prescriptionToTake.getTotalStock().get().toString());
        Dosage dosesToTake = new Dosage("1"); //Valid number of doses
        int dosesToTakeInt = Integer.parseInt(dosesToTake.toString());

        TakeCommand takePrescriptionCommand = new TakeCommand(
                INDEX_FIRST_PRESCRIPTION, dosesToTake);

        String expectedMessage = String.format(TakeCommand.MESSAGE_SUCCESS,
                prescriptionToTake.getName());
        Prescription expectedPrescription = expectedModel.getPrescriptionByName(prescriptionToTake.getName());
        expectedPrescription.getTotalStock().get().decrementCount(dosesToTakeInt);
        expectedPrescription.getConsumptionCount().incrementCount(dosesToTakeInt);
        expectedModel.updateFilteredPrescriptionList(new SameNamePredicate(prescriptionToTake.getName()));

        int newStock = Integer.parseInt(expectedModel.getPrescriptionByName(prescriptionToTake.getName())
                .getTotalStock().get().toString());

        assertCommandSuccess(takePrescriptionCommand, model, expectedMessage, expectedModel);
        assertFalse(prescriptionToTake.getIsCompleted());
        assertEquals(initialStock - dosesToTakeInt, newStock);
    }

    @Test
    public void execute_insufficientStock_throwsCommandException() throws CommandException {
        Prescription prescriptionToTake = model.getFilteredPrescriptionList()
                .get(INDEX_FIRST_PRESCRIPTION.getZeroBased());

        int initialStock = Integer.parseInt(prescriptionToTake.getTotalStock().get().toString());
        Dosage dosesToTake = new Dosage(String.valueOf(initialStock + 1)); // More than available stock

        TakeCommand takePrescriptionCommand = new TakeCommand(
                INDEX_FIRST_PRESCRIPTION, dosesToTake);

        assertCommandFailure(takePrescriptionCommand, model, MESSAGE_INSUFFICIENT_STOCK);

        // Ensure that the stock is not modified
        int newStock = Integer.parseInt(model.getPrescriptionByName(prescriptionToTake.getName())
                .getTotalStock().get().toString());
        assertEquals(initialStock, newStock);
    }

    @Test
    public void execute_outOfBoundary_throwsCommandException() {
        Index invalidIndex = Index.fromOneBased(5);
        Dosage dosesToTake = new Dosage("1");
        TakeCommand takeCommand = new TakeCommand(invalidIndex, dosesToTake);
        TakeCommandTest.ModelStub modelStub = new TakeCommandTest.ModelStubAcceptingPrescriptionTaken();

        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_PRESCRIPTION_DISPLAYED_INDEX, () -> takeCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Dosage doses1 = new Dosage("2");
        Dosage doses2 = new Dosage("1");

        TakeCommand command1 = new TakeCommand(INDEX_FIRST_PRESCRIPTION, doses1);
        TakeCommand command2 = new TakeCommand(INDEX_FIRST_PRESCRIPTION, doses1);
        TakeCommand command3 = new TakeCommand(INDEX_SECOND_PRESCRIPTION, doses1);
        TakeCommand command4 = new TakeCommand(INDEX_FIRST_PRESCRIPTION, doses2);

        // Same object
        assertTrue(command1.equals(command1));

        // Test for equality
        assertTrue(command1.equals(command2)); // Same name and doses
        assertTrue(command2.equals(command1)); // Test for symmetry
        assertFalse(command1.equals(command3)); // Different index
        assertFalse(command1.equals(command4)); // Different doses

        // Test for non-equality
        assertFalse(command1.equals(null)); // Null object
    }

    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getPrescriptionListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPrescriptionListFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getCompletedPrescriptionListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCompletedPrescriptionListFilePath(Path completedPrescriptionListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPrescription(Prescription prescription) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPrescriptionList(ReadOnlyPrescriptionList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyPrescriptionList getPrescriptionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPrescription(Prescription prescription) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePrescription(Prescription target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPrescription(Prescription target, Prescription editedPrescription) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Prescription> getFilteredPrescriptionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Prescription getPrescriptionByName(Name prescriptionName) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public void updateFilteredPrescriptionList(Predicate<Prescription> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCompletedPrescriptionList(ReadOnlyPrescriptionList completedPrescriptionList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyPrescriptionList getCompletedPrescriptionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCompletedPrescription(Prescription completedPrescription) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCompletedPrescription(Prescription target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCompletedPrescription(Prescription completedPrescription) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCompletedPrescription(Prescription target, Prescription editedPrescription) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Prescription> getFilteredCompletedPrescriptionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Prescription getCompletedPrescriptionByName(Name completedPrescriptionName) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCompletedPrescriptionList(Predicate<Prescription> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public LocalDate getStoredDate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStoredDate(LocalDate storedDate) {
            throw new AssertionError("This method should not be called.");
        }

        public boolean hasDrugClash(Prescription toAdd) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single prescription.
     */
    private class ModelStubWithPrescription extends TakeCommandTest.ModelStub {
        private final Prescription prescription;

        ModelStubWithPrescription(Prescription prescription) {
            requireNonNull(prescription);
            this.prescription = prescription;
        }



        @Override
        public boolean hasPrescription(Prescription prescription) {
            requireNonNull(prescription);
            return this.prescription.isSamePrescription(prescription);
        }
    }

    /**
     * A Model stub that has dummy prescriptions to be removed
     */
    private class ModelStubAcceptingPrescriptionTaken extends TakeCommandTest.ModelStub {
        // populate the prescriptionsAdded with dummy prescriptions
        final ArrayList<Prescription> prescriptionsAdded = new ArrayList<>(Arrays.asList(
                new PrescriptionBuilder().build()
        ));

        @Override
        public boolean hasPrescription(Prescription prescription) {
            requireNonNull(prescription);
            return prescriptionsAdded.stream().anyMatch(prescription::isSamePrescription);
        }

        @Override
        public void deletePrescription(Prescription prescription) {
            requireNonNull(prescription);
            prescriptionsAdded.remove(prescription);
        }

        @Override
        public ReadOnlyPrescriptionList getPrescriptionList() {
            return new PrescriptionList();
        }

        @Override
        public ObservableList<Prescription> getFilteredPrescriptionList() {
            return observableArrayList(prescriptionsAdded);
        }
    }
}
