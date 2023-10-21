package seedu.address.logic.commands;

import java.util.Map;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;


/**
 * Represents the command result for gender table generation.
 */
public class GenderTableCommandResult extends CommandResult {
    private static final String feedbackToUserMessage = "A table categorized by gender is shown";
    private Long maleCount;
    private Long femaleCount;


    /**
     * Constructor for GenderTableCommandResult.
     * @param columnValueMapping A hashmap containing column titles(String) as key and counts(Long) as values.
     */
    public GenderTableCommandResult(Map<String, Long> columnValueMapping) {
        super(feedbackToUserMessage);
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
    public Long getMaleCount() {
        return this.maleCount;
    }

    /**
     * Getter method for femaleCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return Counts for female category.
     */
    public Long getFemaleCount() {
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
                && otherCommandResult.maleCount.equals(maleCount)
                && otherCommandResult.femaleCount.equals(femaleCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getFeedbackToUser(), super.isShowHelp(), super.isExit(), maleCount, femaleCount);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUserMessage)
                .add("showHelp", isShowHelp())
                .add("showTable", isShowTable())
                .add("exit", isExit())
                .toString();
    }

}
