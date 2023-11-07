package seedu.address.logic.commands.barchartresults;

import java.util.Map;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.count.EnrolDateCommandResult;

/**
 * Represents for the command result for enrol date bar chart generation.
 */
public class EnrolDateBarChartCommandResult extends EnrolDateCommandResult {
    private static final String MESSAGE_SUCCESS = "A bar chart categorized by EnrolDate is shown";
    private Map<String, Integer> columnValueMapping;

    /**
     * Constructor for EnrolDateBarChartCommandResult.
     * @param columnValueMapping hash map instance containing column titles(String) as keys and
     *                           counts(Integer) as values.
     */
    public EnrolDateBarChartCommandResult(Map<String, Integer> columnValueMapping) {
        super(MESSAGE_SUCCESS, columnValueMapping);
        this.columnValueMapping = columnValueMapping;
    }

    /**
     * Check if this CommandResult instance is meant for creating bar chart window.
     * @return always return true for EnrolDateBarChartCommandResult instance.
     */
    @Override
    public boolean isShowBarChart() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EnrolDateBarChartCommandResult)) {
            return false;
        }

        EnrolDateBarChartCommandResult otherCommandResult = (EnrolDateBarChartCommandResult) other;

        return super.equals(otherCommandResult)
                && otherCommandResult.isShowBarChart() == isShowBarChart();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getFeedbackToUser(), super.isShowBarChart(), super.isExit(), columnValueMapping);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", MESSAGE_SUCCESS)
                .add("showHelp", isShowHelp())
                .add("showTable", isShowTable())
                .add("showBarChart", isShowBarChart())
                .add("exit", isExit())
                .toString();
    }

}
