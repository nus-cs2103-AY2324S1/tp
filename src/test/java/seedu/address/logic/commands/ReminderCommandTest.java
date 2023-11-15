package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONSUMPTION_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOSAGE_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FREQUENCY_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STOCK_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.CompletedPrescriptions.getCompletedPrescriptionList;
import static seedu.address.testutil.TypicalPrescriptions.getTypicalPrescriptionList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PrescriptionList;
import seedu.address.model.UserPrefs;
import seedu.address.model.prescription.IsAboutToExpirePredicate;
import seedu.address.model.prescription.IsLowInStockPredicate;
import seedu.address.model.prescription.Prescription;
import seedu.address.testutil.PrescriptionBuilder;

class ReminderCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPrescriptionList(), getCompletedPrescriptionList(), new UserPrefs());
        expectedModel = new ModelManager(model.getPrescriptionList(),
                model.getCompletedPrescriptionList(),
                new UserPrefs());
    }
    @Test
    public void execute_noReminders_listEmpty() {
        model.setPrescriptionList(new PrescriptionList());
        assertTrue(model.getFilteredPrescriptionList().isEmpty());

        String expectedMessage = ReminderCommand.MESSAGE_EMPTY_LIST;
        expectedModel = new ModelManager(model.getPrescriptionList(),
                model.getCompletedPrescriptionList(), new UserPrefs());
        expectedModel.updateFilteredPrescriptionList(new IsAboutToExpirePredicate().or(new IsLowInStockPredicate()));
        assertCommandSuccess(new ReminderCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noExpiringOrLow_listEmpty() {
        model.setPrescriptionList(new PrescriptionList());
        assertTrue(model.getFilteredPrescriptionList().isEmpty());

        PrescriptionBuilder prescriptionBuilder = new PrescriptionBuilder();
        Prescription prescription = prescriptionBuilder.withName(VALID_NAME_ASPIRIN)
                .withDosage(VALID_DOSAGE_ASPIRIN).withFrequency(VALID_FREQUENCY_ASPIRIN)
                .withConsumptionCount(VALID_CONSUMPTION_ASPIRIN).withDosage(VALID_DOSAGE_ASPIRIN)
                .withStartDate(VALID_START_DATE_ASPIRIN).withEndDate(VALID_END_DATE_ASPIRIN)
                .withExpiryDate(VALID_EXPIRY_DATE_ASPIRIN).withStock(VALID_STOCK_ASPIRIN).build();
        model.addPrescription(prescription);

        String expectedMessage = ReminderCommand.MESSAGE_EMPTY_LIST;
        expectedModel = new ModelManager(model.getPrescriptionList(),
                model.getCompletedPrescriptionList(), new UserPrefs());
        expectedModel.updateFilteredPrescriptionList(new IsAboutToExpirePredicate().or(new IsLowInStockPredicate()));
        assertCommandSuccess(new ReminderCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nullPrescription_listEmpty() {
        model.setPrescriptionList(new PrescriptionList());
        assertTrue(model.getFilteredPrescriptionList().isEmpty());

        PrescriptionBuilder prescriptionBuilder = new PrescriptionBuilder();
        Prescription prescription = prescriptionBuilder.withName(VALID_NAME_ASPIRIN)
                .withStartDate(VALID_START_DATE_ASPIRIN).build();
        model.addPrescription(prescription);

        String expectedMessage = ReminderCommand.MESSAGE_EMPTY_LIST;
        expectedModel = new ModelManager(model.getPrescriptionList(),
                model.getCompletedPrescriptionList(), new UserPrefs());
        expectedModel.updateFilteredPrescriptionList(new IsAboutToExpirePredicate().or(new IsLowInStockPredicate()));
        assertCommandSuccess(new ReminderCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validExpiringPrescription_remindersuccess() {
        model.setPrescriptionList(new PrescriptionList());
        assertTrue(model.getFilteredPrescriptionList().isEmpty());

        LocalDate startLocalDate = LocalDate.now().minusDays(7);
        LocalDate expirylocalDate = LocalDate.now().plusDays(2);
        LocalDate endLocalDate = LocalDate.now().plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String startDateString = startLocalDate.format(formatter);
        String expiryDateString = expirylocalDate.format(formatter);
        String endDateString = endLocalDate.format(formatter);

        PrescriptionBuilder prescriptionBuilder = new PrescriptionBuilder();
        Prescription prescription = prescriptionBuilder.withName(VALID_NAME_ASPIRIN)
                .withDosage(VALID_DOSAGE_ASPIRIN).withFrequency(VALID_FREQUENCY_ASPIRIN)
                .withConsumptionCount(VALID_CONSUMPTION_ASPIRIN)
                .withStartDate(startDateString).withEndDate(endDateString)
                .withExpiryDate(expiryDateString).withStock(VALID_STOCK_ASPIRIN).build();
        model.addPrescription(prescription);

        String expectedMessage = ReminderCommand.MESSAGE_SUCCESS;
        expectedModel = new ModelManager(model.getPrescriptionList(),
                model.getCompletedPrescriptionList(), new UserPrefs());
        expectedModel.updateFilteredPrescriptionList(new IsAboutToExpirePredicate().or(new IsLowInStockPredicate()));
        assertCommandSuccess(new ReminderCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validLowStockPrescription_remindersuccess() {
        model.setPrescriptionList(new PrescriptionList());
        assertTrue(model.getFilteredPrescriptionList().isEmpty());

        PrescriptionBuilder prescriptionBuilder = new PrescriptionBuilder();
        Prescription prescription = prescriptionBuilder.withName(VALID_NAME_ASPIRIN)
                .withDosage(VALID_DOSAGE_ASPIRIN).withFrequency(VALID_FREQUENCY_ASPIRIN)
                .withConsumptionCount(VALID_CONSUMPTION_ASPIRIN)
                .withStartDate(VALID_START_DATE_ASPIRIN).withEndDate(VALID_END_DATE_ASPIRIN)
                .withExpiryDate(VALID_EXPIRY_DATE_ASPIRIN).withStock("1").build();
        model.addPrescription(prescription);

        String expectedMessage = ReminderCommand.MESSAGE_SUCCESS;
        expectedModel = new ModelManager(model.getPrescriptionList(),
                model.getCompletedPrescriptionList(), new UserPrefs());
        expectedModel.updateFilteredPrescriptionList(new IsAboutToExpirePredicate().or(new IsLowInStockPredicate()));
        assertCommandSuccess(new ReminderCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validLowStockandValidExpringPrescription_reminderSuccess() {
        model.setPrescriptionList(new PrescriptionList());
        assertTrue(model.getFilteredPrescriptionList().isEmpty());

        LocalDate startLocalDate = LocalDate.now().minusDays(7);
        LocalDate expirylocalDate = LocalDate.now().plusDays(2);
        LocalDate endLocalDate = LocalDate.now().plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String startDateString = startLocalDate.format(formatter);
        String expiryDateString = expirylocalDate.format(formatter);
        String endDateString = endLocalDate.format(formatter);

        PrescriptionBuilder prescriptionBuilder = new PrescriptionBuilder();
        Prescription prescription = prescriptionBuilder.withName(VALID_NAME_ASPIRIN)
                .withDosage(VALID_DOSAGE_ASPIRIN).withFrequency(VALID_FREQUENCY_ASPIRIN)
                .withConsumptionCount(VALID_CONSUMPTION_ASPIRIN)
                .withStartDate(startDateString).withEndDate(endDateString)
                .withExpiryDate(expiryDateString).withStock("1").build();
        model.addPrescription(prescription);

        String expectedMessage = ReminderCommand.MESSAGE_SUCCESS;
        expectedModel = new ModelManager(model.getPrescriptionList(),
                model.getCompletedPrescriptionList(), new UserPrefs());
        expectedModel.updateFilteredPrescriptionList(new IsAboutToExpirePredicate().or(new IsLowInStockPredicate()));
        assertCommandSuccess(new ReminderCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleValidLowStockandValidExpringPrescriptions_reminderSuccess() {
        model.setPrescriptionList(new PrescriptionList());
        assertTrue(model.getFilteredPrescriptionList().isEmpty());

        LocalDate startLocalDate = LocalDate.now().minusDays(7);
        LocalDate expirylocalDate = LocalDate.now().plusDays(2);
        LocalDate endLocalDate = LocalDate.now().plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String startDateString = startLocalDate.format(formatter);
        String expiryDateString = expirylocalDate.format(formatter);
        String endDateString = endLocalDate.format(formatter);

        PrescriptionBuilder prescriptionBuilder = new PrescriptionBuilder();
        Prescription prescription = prescriptionBuilder.withName(VALID_NAME_ASPIRIN)
                .withDosage(VALID_DOSAGE_ASPIRIN).withFrequency(VALID_FREQUENCY_ASPIRIN)
                .withConsumptionCount(VALID_CONSUMPTION_ASPIRIN)
                .withStartDate(startDateString).withEndDate(endDateString)
                .withExpiryDate(expiryDateString).withStock(VALID_STOCK_ASPIRIN).build();
        model.addPrescription(prescription);

        prescription = prescriptionBuilder.withName(VALID_NAME_ASPIRIN)
                .withDosage(VALID_DOSAGE_ASPIRIN).withFrequency(VALID_FREQUENCY_ASPIRIN)
                .withConsumptionCount(VALID_CONSUMPTION_ASPIRIN)
                .withStartDate(VALID_START_DATE_ASPIRIN).withEndDate(VALID_END_DATE_ASPIRIN)
                .withExpiryDate(VALID_EXPIRY_DATE_ASPIRIN).withStock("1").build();
        model.addPrescription(prescription);

        String expectedMessage = ReminderCommand.MESSAGE_SUCCESS;
        expectedModel = new ModelManager(model.getPrescriptionList(),
                model.getCompletedPrescriptionList(), new UserPrefs());
        expectedModel.updateFilteredPrescriptionList(new IsAboutToExpirePredicate().or(new IsLowInStockPredicate()));
        assertCommandSuccess(new ReminderCommand(), model, expectedMessage, expectedModel);
    }
}
