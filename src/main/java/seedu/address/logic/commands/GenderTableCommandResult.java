package seedu.address.logic.commands;

import java.util.Map;
import java.util.Objects;

public class GenderTableCommandResult extends CommandResult {
    private Long maleCount;
    private Long femaleCount;

    private static final String feedbackToUserMessage = "A table categorized by gender is shown";

    public GenderTableCommandResult(Map<String, Long> columnValueMapping) {
        super(feedbackToUserMessage);
        this.maleCount = columnValueMapping.get("Male");
        this.femaleCount = columnValueMapping.get("Female");
    }

    @Override
    public boolean isShowTable() {
        return true;
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
        return super.toString() + "\n"
                + "Male Count: " + maleCount + "\n"
                + "Female Count: " + femaleCount + "\n";
    }

}
