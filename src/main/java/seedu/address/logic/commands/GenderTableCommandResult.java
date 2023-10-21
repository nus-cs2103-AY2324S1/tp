package seedu.address.logic.commands;

import java.util.Map;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;


/**
 * Represents the command result for gender table generation.
 */
public class GenderTableCommandResult extends CommandResult {
    private static final String FEEDBACKTOUSERMESSAGE = "A table categorized by gender is shown";
    private int maleCount;
    private int femaleCount;


    /**
     * Constructor for GenderTableCommandResult.
     * @param columnValueMapping A hashmap containing column titles(String) as key and counts(Long) as values.
     */
    public GenderTableCommandResult(Map<String, Integer> columnValueMapping) {
        super(FEEDBACKTOUSERMESSAGE);
        this.maleCount = columnValueMapping.get("Male");
        this.femaleCount = columnValueMapping.get("Female");
    }

    /**
     * Check for if this CommandResult instance is meant for showing table window.
     * @return always return true for GenderTableCommandResult instance.
     */
    @Override
    public boolean isShowTable() {
        return true;
    }


    /**
     * Getter method for maleCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return Counts for male category.
     */
    public int getMaleCount() {
        return this.maleCount;
    }

    /**
     * Getter method for femaleCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return Counts for female category.
     */
    public int getFemaleCount() {
        return this.femaleCount;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GenderTableCommandResult)) {
            return false;
        }

        GenderTableCommandResult otherCommandResult = (GenderTableCommandResult) other;
        return super.equals(otherCommandResult)
                && otherCommandResult.maleCount == maleCount
                && otherCommandResult.femaleCount == femaleCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getFeedbackToUser(), super.isShowHelp(), super.isExit(), maleCount, femaleCount);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", FEEDBACKTOUSERMESSAGE)
                .add("showHelp", isShowHelp())
                .add("showTable", isShowTable())
                .add("exit", isExit())
                .toString();
    }

}
