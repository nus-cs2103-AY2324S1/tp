package seedu.address.logic.commands;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
public class TableCommandResult extends CommandResult {
    private String[] columns;
    private Long[] values;


    public TableCommandResult(String[] columns, Long[] values, String feedbackToUser) {
        super(feedbackToUser, false, false);
        this.columns = columns;
        this.values = values;
    }

    public String[] getColumns() {
        return this.columns;
    }

    public Long[] getValues() {
        return this.values;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TableCommandResult)) {
            return false;
        }

        TableCommandResult otherCommandResult = (TableCommandResult) other;
        return super.equals(otherCommandResult)
                && Arrays.equals(columns, otherCommandResult.columns)
                && Arrays.equals(values, otherCommandResult.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getFeedbackToUser(), super.isShowHelp(), super.isExit(), columns, values);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", super.getFeedbackToUser())
                .add("showhelp", super.isShowHelp())
                .add("exit", super.isExit())
                .toString();
    }

}
