package seedu.address.logic.commands;


import java.util.Map;
import java.util.Objects;

public class SecLevelTableCommandResult extends CommandResult {
    private Long sec1Count;
    private Long sec2Count;
    private Long sec3Count;
    private Long sec4Count;

    private static final String feedbackToUserMessage = "A table categorized by SecLevel is shown";

    public SecLevelTableCommandResult(Map<String, Long> columnValueMapping) {
        super(feedbackToUserMessage);
        this.sec1Count = columnValueMapping.get("Sec 1");
        this.sec2Count = columnValueMapping.get("Sec 2");
        this.sec3Count = columnValueMapping.get("Sec 3");
        this.sec4Count = columnValueMapping.get("Sec 4");
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

        SecLevelTableCommandResult otherCommandResult = (SecLevelTableCommandResult) other;
        return super.equals(otherCommandResult)
                && otherCommandResult.sec1Count == sec1Count
                && otherCommandResult.sec2Count == sec2Count
                && otherCommandResult.sec3Count == sec3Count
                && otherCommandResult.sec4Count == sec4Count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getFeedbackToUser(), super.isShowHelp(), super.isExit(),
                sec1Count, sec2Count, sec3Count, sec4Count);
    }

    @Override
    public String toString() {
        return super.toString() + "\n"
                + "Sec 1: " + sec1Count
                + "Sec 2: " + sec2Count
                + "Sec 3: " + sec3Count
                + "Sec 4: " + sec4Count;
    }
}
