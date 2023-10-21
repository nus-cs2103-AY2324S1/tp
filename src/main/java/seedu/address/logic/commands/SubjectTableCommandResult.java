package seedu.address.logic.commands;

import java.util.Map;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Subject;

/**
 * Represents for the command result for subject table generation.
 */
public class SubjectTableCommandResult extends CommandResult {

    private static final String FEEDBACKTOUSERMESSAGE = "A table categorized by Subject is shown";
    private int csCount;
    private int mathsCount;
    private int phyCount;
    private int chemiCount;
    private int bioCount;
    private int engCount;


    /**
     * Constructor for SubjectTableCommandResult.
     * @param columnValueMapping hash map instance containing column titles(String) as keys and counts(Long) as values.
     */
    public SubjectTableCommandResult(Map<String, Integer> columnValueMapping) {
        super(FEEDBACKTOUSERMESSAGE);
        this.csCount = columnValueMapping.get(Subject.CS);
        this.mathsCount = columnValueMapping.get(Subject.MATHS);
        this.phyCount = columnValueMapping.get(Subject.PHY);
        this.chemiCount = columnValueMapping.get(Subject.CHEMI);
        this.bioCount = columnValueMapping.get(Subject.BIO);
        this.engCount = columnValueMapping.get(Subject.ENG);
    }

    /**
     * Check if this CommandResult instance is meant for creating table window.
     * @return always return true for SubjectTableCommandResult instance.
     */
    @Override
    public boolean isShowTable() {
        return true;
    }

    /**
     * Getter method for csCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for computer science category.
     */
    public int getCsCount() {
        return csCount;
    }

    /**
     * Getter method for mathsCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for mathematics category.
     */
    public int getMathsCount() {
        return mathsCount;
    }

    /**
     * Getter method for phyCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for physics category.
     */
    public int getPhyCount() {
        return phyCount;
    }

    /**
     * Getter method for chemiCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for chemistry category.
     */
    public int getChemiCount() {
        return chemiCount;
    }

    /**
     * Getter method for bioCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for biology category.
     */
    public int getBioCount() {
        return bioCount;
    }

    /**
     * Getter method for engCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for english category.
     */
    public int getEngCount() {
        return engCount;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SubjectTableCommandResult)) {
            return false;
        }

        SubjectTableCommandResult otherCommandResult = (SubjectTableCommandResult) other;
        return super.equals(otherCommandResult)
                && otherCommandResult.csCount == csCount
                && otherCommandResult.mathsCount == mathsCount
                && otherCommandResult.phyCount == phyCount
                && otherCommandResult.chemiCount == chemiCount
                && otherCommandResult.bioCount == bioCount
                && otherCommandResult.engCount == engCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getFeedbackToUser(), super.isShowHelp(), super.isExit(),
                csCount, mathsCount, phyCount, chemiCount, bioCount, engCount);
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
