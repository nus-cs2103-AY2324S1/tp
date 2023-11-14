package seedu.address.model.finance;

import static java.lang.Integer.parseInt;

import javafx.collections.ObservableList;
/**
 * The {@code FinanceSummary} class represents a summary of the commissions and expenses,
 * related to a specific client. It calculates and provides a detailed summary report based on the filtered finances.
 */
public class FinanceSummary {
    private int totalSum;
    private int noOfCommissions;
    private int commissionTotal;
    private int noOfExpenses;
    private int expenseTotal;

    /**
     * Constructs a FinanceSummary object with the specified financial statistics.
     *
     * @param totalSum         Total sum of finances (commissions and expenses).
     * @param noOfCommissions  Number of commission transactions.
     * @param commissionTotal  Total sum of commissions.
     * @param noOfExpenses     Number of expense transactions.
     * @param expenseTotal     Total sum of expenses.
     */
    public FinanceSummary(int totalSum, int noOfCommissions, int commissionTotal, int noOfExpenses,
                          int expenseTotal) {
        this.totalSum = totalSum;
        this.noOfCommissions = noOfCommissions;
        this.commissionTotal = commissionTotal;
        this.noOfExpenses = noOfExpenses;
        this.expenseTotal = expenseTotal;
    }
    /**
     * Generates a summary report based on the filtered finances for the given client name.
     *
     * @param filteredFinances  List of filtered finance transactions.
     * @param name              Name of the client for whom the summary is generated.
     * @return                  Summary report as a formatted string.
     */
    public static String generateSummary(ObservableList<Finance> filteredFinances, String name) {
        return generateSummaryStatistic(filteredFinances).getSummaryReport(name);
    }
    /**
     * Generates and returns a FinanceSummary object based on the filtered finance transactions.
     *
     * @param filteredFinances  List of filtered finance transactions.
     * @return                  FinanceSummary object containing summary statistics.
     */
    public static FinanceSummary generateSummaryStatistic(ObservableList<Finance> filteredFinances) {
        int totalSum = 0;
        int noOfCommission = 0;
        int commissionTotal = 0;
        int noOfExpense = 0;
        int expenseTotal = 0;
        for (Finance finance : filteredFinances) {
            int amount = parseInt(finance.getAmount().toString());
            if (finance instanceof Commission) {
                totalSum += amount;
                commissionTotal += amount;
                noOfCommission++;
            } else {
                totalSum -= amount;
                expenseTotal += amount;
                noOfExpense++;
            }
        }
        return new FinanceSummary(totalSum,
                noOfCommission, commissionTotal, noOfExpense, expenseTotal);
    }
    /**
     * Generates the full summary report for the client.
     *
     * @param name  Name of the client for whom the summary is generated.
     * @return      Summary report as a formatted string.
     */
    public String getSummaryReport(String name) {
        if (noOfExpenses == 0 && noOfCommissions == 0) {
            return "You currently have no finances for this client";
        }
        return String.format("%s\n%s\n%s",
                 getTotalSumSummary(name), getCommissionSummary(name), getExpenseSummary(name));
    }
    /**
     * Generates and returns the summary of the total sum related to the client.
     *
     * @param name  Name of the client for whom the summary is generated.
     * @return      Total sum summary as a formatted string.
     */
    public String getTotalSumSummary(String name) {
        if (totalSum > 0) {
            return String.format("You have earned $%d from %s", totalSum, name);
        } else if (totalSum < 0) {
            return String.format("You have lost $%d due to %s", Math.abs(totalSum), name);
        } else {
            return "You have broken even from " + name;
        }
    }
    /**
     * Generates and returns the summary of commissions related to the client.
     *
     * @param name  Name of the client for whom the summary is generated.
     * @return      Commission summary as a formatted string.
     */
    public String getCommissionSummary(String name) {
        if (noOfCommissions == 0) {
            return "There were no commissions from " + name;
        } else {
            return String.format("There were %d commissions for a total of $%d.",
                    noOfCommissions, commissionTotal);
        }
    }
    /**
     * Generates and returns the summary of expenses related to the client.
     *
     * @param name  Name of the client for whom the summary is generated.
     * @return      Expense summary as a formatted string.
     */
    public String getExpenseSummary(String name) {
        if (noOfExpenses == 0) {
            return "There were no expenses related to " + name;
        } else {
            return String.format("There were %d expenses for a total of $%d.",
                    noOfExpenses, expenseTotal);
        }
    }
    public int getTotalSum() {
        return totalSum;
    }

    public int getNoOfCommissions() {
        return noOfCommissions;
    }

    public int getCommissionTotal() {
        return commissionTotal;
    }

    public int getNoOfExpenses() {
        return noOfExpenses;
    }

    public int getExpenseTotal() {
        return expenseTotal;
    }

}
