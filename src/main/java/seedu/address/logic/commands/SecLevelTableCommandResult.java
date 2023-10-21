package seedu.address.logic.commands;

import java.util.Map;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;


/**
 * Represents the command result for sec level table generation.
 */
public class SecLevelTableCommandResult extends CommandResult {
    private static final String FEEDBACKTOUSERMESSAGE = "A table categorized by SecLevel is shown";
    private Long sec1Count;
    private Long sec2Count;
    private Long sec3Count;
    private Long sec4Count;


    /**
     * Constructor for SecLevelTableCommandResult.
     * @param columnValueMapping A hashmap instance containing column titles(String) as keys and counts(Long) as values.
     */
    public SecLevelTableCommandResult(Map<String, Long> columnValueMapping) {
        super(FEEDBACKTOUSERMESSAGE);
        this.sec1Count = columnValueMapping.get("Sec 1");
        this.sec2Count = columnValueMapping.get("Sec 2");
        this.sec3Count = columnValueMapping.get("Sec 3");
        this.sec4Count = columnValueMapping.get("Sec 4");
    }

    /**
     * Check if this CommandResult instance is meant for table window generation.
     * @return always return true for SecLevelTableCommandResult instance.
     */
    @Override
    public boolean isShowTable() {
        return true;
    }

    /**
     * Getter method for sec1Count which will auto invoked by javafx PropertyValueFactory instance.
     * @return counts for sec1 category.
     */
    public Long getSec1Count() {
        return sec1Count;
    }

    /**
     * Getter method for sec2Count which will auto invoked by javafx PropertyValueFactory instance.
     * @return counts for sec2 category.
     */
    public Long getSec2Count() {
        return sec2Count;
    }

    /**
     * Getter method for sec3Count which will auto invoked by javafx PropertyValueFactory instance.
     * @return counts for sec3 category.
     */
    public Long getSec3Count() {
        return sec3Count;
    }

    /**
     * Getter method for sec4Count which will auto invoked by javafx PropertyValueFactory instance.
     * @return counts for sec4 category.
     */
    public Long getSec4Count() {
        return sec4Count;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SecLevelTableCommandResult)) {
            return false;
        }

        SecLevelTableCommandResult otherCommandResult = (SecLevelTableCommandResult) other;
        return super.equals(otherCommandResult)
                && otherCommandResult.sec1Count.equals(sec1Count)
                && otherCommandResult.sec2Count.equals(sec2Count)
                && otherCommandResult.sec3Count.equals(sec3Count)
                && otherCommandResult.sec4Count.equals(sec4Count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getFeedbackToUser(), super.isShowHelp(), super.isExit(),
                sec1Count, sec2Count, sec3Count, sec4Count);
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
