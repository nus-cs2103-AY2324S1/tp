package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.CompletedPrescriptions.getCompletedPrescriptionList;
import static seedu.address.testutil.TypicalPrescriptions.getTypicalPrescriptionList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PrescriptionList;
import seedu.address.model.UserPrefs;
import seedu.address.model.prescription.ConsumptionCount;
import seedu.address.model.prescription.Date;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Frequency;
import seedu.address.model.prescription.IsTodayPredicate;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Note;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.Stock;

public class ListTodayCommandTest {
    private Model model;
    private Model expectedModel;
    private CommandResult expectedSuccessMessage;
    private CommandResult expectedEmptyMessage;
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPrescriptionList(), getCompletedPrescriptionList(), new UserPrefs());
        expectedModel = new ModelManager(model.getPrescriptionList(),
                model.getCompletedPrescriptionList(), new UserPrefs());
        expectedSuccessMessage = new CommandResult(ListTodayCommand.MESSAGE_SUCCESS, ListTodayCommand.COMMAND_WORD);
        expectedEmptyMessage = new CommandResult(ListTodayCommand.MESSAGE_EMPTY_LIST, ListTodayCommand.COMMAND_WORD);
    }

    @Test
    public void execute_validDateWithMedications_listTodaySuccess() {
        expectedModel.updateFilteredPrescriptionList(new IsTodayPredicate());
        assertCommandSuccess(new ListTodayCommand(), model, expectedSuccessMessage, expectedModel);
    }

    @Test public void execute_noMedicationsForToday_listTodayEmpty() {
        model.setPrescriptionList(new PrescriptionList());
        assertTrue(model.getFilteredPrescriptionList().isEmpty());

        expectedModel = new ModelManager(model.getPrescriptionList(), model.getCompletedPrescriptionList(),
                new UserPrefs());
        expectedModel.updateFilteredPrescriptionList(new IsTodayPredicate());
        assertCommandSuccess(new ListTodayCommand(), model, expectedEmptyMessage, expectedModel);
    }

    @Test public void execute_validWeeklyMedicationsForToday_listTodaySucess() {
        model.setPrescriptionList(new PrescriptionList());
        assertTrue(model.getFilteredPrescriptionList().isEmpty());

        LocalDate startLocalDate = LocalDate.now().minusDays(7);
        LocalDate endLocalDate = LocalDate.now().plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String startDateString = startLocalDate.format(formatter);
        String endDateString = endLocalDate.format(formatter);

        Date startDate = new Date(startDateString);
        Date endDate = new Date(endDateString);
        model.addPrescription(new Prescription(new Name("Paracetamol"), new Dosage("1"), new Frequency("Weekly"),
                startDate, endDate, endDate, new Stock("100"),
                new ConsumptionCount("1"), false, new Note("test"), new HashSet<>()));
        expectedModel = new ModelManager(model.getPrescriptionList(), model.getCompletedPrescriptionList(),
                new UserPrefs());
        expectedModel.updateFilteredPrescriptionList(new IsTodayPredicate());
        assertCommandSuccess(new ListTodayCommand(), model, expectedSuccessMessage, expectedModel);
    }

    @Test
    public void execute_invalidWeeklyMedicationsForToday_listTodayEmpty() {
        model.setPrescriptionList(new PrescriptionList());
        assertTrue(model.getFilteredPrescriptionList().isEmpty());

        LocalDate startLocalDate = LocalDate.now().minusDays(6);
        LocalDate endLocalDate = LocalDate.now().plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String startDateString = startLocalDate.format(formatter);
        String endDateString = endLocalDate.format(formatter);

        Date startDate = new Date(startDateString);
        Date endDate = new Date(endDateString);
        model.addPrescription(new Prescription(new Name("Paracetamol"), new Dosage("1"), new Frequency("Weekly"),
                startDate, endDate, endDate, new Stock("100"),
                new ConsumptionCount("1"), false, new Note("test"), new HashSet<>()));
        expectedModel = new ModelManager(model.getPrescriptionList(), model.getCompletedPrescriptionList(),
                new UserPrefs());
        expectedModel.updateFilteredPrescriptionList(new IsTodayPredicate());
        assertCommandSuccess(new ListTodayCommand(), model, expectedEmptyMessage, expectedModel);
    }

    @Test public void execute_validMonthlyMedicationsForToday_listTodaySucess() {
        model.setPrescriptionList(new PrescriptionList());
        assertTrue(model.getFilteredPrescriptionList().isEmpty());

        LocalDate startLocalDate = LocalDate.now().minusDays(30);
        LocalDate endLocalDate = LocalDate.now().plusDays(30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String startDateString = startLocalDate.format(formatter);
        String endDateString = endLocalDate.format(formatter);

        Date startDate = new Date(startDateString);
        Date endDate = new Date(endDateString);
        model.addPrescription(new Prescription(new Name("Paracetamol"), new Dosage("1"), new Frequency("Monthly"),
                startDate, endDate, endDate, new Stock("100"),
                new ConsumptionCount("1"), false, new Note("test"), new HashSet<>()));
        expectedModel = new ModelManager(model.getPrescriptionList(), model.getCompletedPrescriptionList(),
                new UserPrefs());
        expectedModel.updateFilteredPrescriptionList(new IsTodayPredicate());
        assertCommandSuccess(new ListTodayCommand(), model, expectedSuccessMessage, expectedModel);
    }

    @Test public void execute_invalidMonthlyMedicationsForToday_listTodayEmpty() {
        model.setPrescriptionList(new PrescriptionList());
        assertTrue(model.getFilteredPrescriptionList().isEmpty());

        LocalDate startLocalDate = LocalDate.now().minusDays(29);
        LocalDate endLocalDate = LocalDate.now().plusDays(30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String startDateString = startLocalDate.format(formatter);
        String endDateString = endLocalDate.format(formatter);

        Date startDate = new Date(startDateString);
        Date endDate = new Date(endDateString);
        model.addPrescription(new Prescription(new Name("Paracetamol"), new Dosage("1"), new Frequency("Monthly"),
                startDate, endDate, endDate, new Stock("100"),
                new ConsumptionCount("1"), false, new Note("test"), new HashSet<>()));
        expectedModel = new ModelManager(model.getPrescriptionList(), model.getCompletedPrescriptionList(),
                new UserPrefs());
        expectedModel.updateFilteredPrescriptionList(new IsTodayPredicate());
        assertCommandSuccess(new ListTodayCommand(), model, expectedEmptyMessage, expectedModel);
    }

}
