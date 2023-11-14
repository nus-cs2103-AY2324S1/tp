package seedu.address.model.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.testutil.TypicalFinances;


public class FinanceSummaryTest {

    private final FinanceSummary emptySummary = new FinanceSummary(0, 0, 0, 0, 0);

    private final FinanceSummary sampleSummary = new FinanceSummary(1000, 5, 2000, 3, 1000);


    @Test
    public void testGetTotalSumSummary() {
        // sample summary
        String expectedSample = "You have earned $1000 from John";
        assertEquals(expectedSample, sampleSummary.getTotalSumSummary("John"));
        // empty summary
        String expectedEmpty = "You have broken even from Jane";
        assertEquals(expectedEmpty, emptySummary.getTotalSumSummary("Jane"));
    }

    @Test
    public void testGetCommissionSummary() {
        // sample summary
        String expectedSample = "There were 5 commissions for a total of $2000.";
        assertEquals(expectedSample, sampleSummary.getCommissionSummary("John"));
        // empty summary
        String expectedEmpty = "There were no commissions from Jane";
        assertEquals(expectedEmpty, emptySummary.getCommissionSummary("Jane"));
    }

    @Test
    public void testGetExpenseSummary() {
        // sample summary
        String expectedSample = "There were 3 expenses for a total of $1000.";
        assertEquals(expectedSample, sampleSummary.getExpenseSummary("John"));
        // empty summary
        String expectedEmpty = "There were no expenses related to Jane";
        assertEquals(expectedEmpty, emptySummary.getExpenseSummary("Jane"));
    }

    @Test
    public void testGetSummaryReport() {
        // sample summary
        String expectedSample = "You have earned $1000 from John\n"
                + "There were 5 commissions for a total of $2000.\n"
                + "There were 3 expenses for a total of $1000.";
        assertEquals(expectedSample, sampleSummary.getSummaryReport("John"));
        // empty summary
        String expectedEmpty = "You currently have no finances for this client";
        assertEquals(expectedEmpty, emptySummary.getSummaryReport("Jane"));

    }

    @Test
    public void test_generateSummaryStatistic() {
        // Create a list of Finance objects for testing
        ObservableList<Finance> finances = FXCollections.observableArrayList();
        finances.addAll(TypicalFinances.getTypicalFinances());

        // Generate a FinanceSummary object from the list
        FinanceSummary summary = FinanceSummary.generateSummaryStatistic(finances);

        // Assert the summary statistics
        assertEquals(-20, summary.getTotalSum());
        assertEquals(2, summary.getNoOfCommissions());
        assertEquals(30, summary.getCommissionTotal());
        assertEquals(2, summary.getNoOfExpenses());
        assertEquals(50, summary.getExpenseTotal());
    }

    @Test
    public void test_commissionsOnly_generateSummaryStatistic() {
        // Create a list of Finance objects for testing
        ObservableList<Finance> finances = FXCollections.observableArrayList();
        finances.addAll(TypicalFinances.getTypicalCommissionsOnly());

        // Generate a FinanceSummary object from the list
        FinanceSummary summary = FinanceSummary.generateSummaryStatistic(finances);

        // Assert the summary statistics
        assertEquals(30, summary.getTotalSum());
        assertEquals(2, summary.getNoOfCommissions());
        assertEquals(30, summary.getCommissionTotal());
        assertEquals(0, summary.getNoOfExpenses());
        assertEquals(0, summary.getExpenseTotal());
    }

    @Test
    public void test_expensesOnly_generateSummaryStatistic() {
        // Create a list of Finance objects for testing
        ObservableList<Finance> finances = FXCollections.observableArrayList();
        finances.addAll(TypicalFinances.getTypicalExpensesOnly());

        // Generate a FinanceSummary object from the list
        FinanceSummary summary = FinanceSummary.generateSummaryStatistic(finances);

        // Assert the summary statistics
        assertEquals(-50, summary.getTotalSum());
        assertEquals(0, summary.getNoOfCommissions());
        assertEquals(0, summary.getCommissionTotal());
        assertEquals(2, summary.getNoOfExpenses());
        assertEquals(50, summary.getExpenseTotal());
    }

    @Test
    public void testGenerateSummary() {
        // Create a list of Finance objects for testing
        ObservableList<Finance> finances = FXCollections.observableArrayList();
        finances.addAll(TypicalFinances.getTypicalFinances());
        String summaryReport = FinanceSummary.generateSummary(finances, "John");

        String expectedSummaryReport = "You have lost $20 due to John\n"
                    + "There were 2 commissions for a total of $30.\n"
                    + "There were 2 expenses for a total of $50.";

        // Assert the content of the summary report
        assertEquals(expectedSummaryReport, summaryReport);
    }
}
