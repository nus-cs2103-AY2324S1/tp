package seedu.address.logic.commands;

import java.util.Map;
import java.util.Objects;

import seedu.address.model.tag.Subject;
public class SubjectTableCommandResult extends CommandResult {

    private Long csCount;
    private Long mathsCount;
    private Long phyCount;
    private Long chemiCount;
    private Long bioCount;
    private Long engCount;

    public static final String feedbackToUserMessage = "A table categorized by Subject is shown";

    public SubjectTableCommandResult(Map<String, Long> columnValueMapping) {
        super(feedbackToUserMessage);
        this.csCount = columnValueMapping.get(Subject.CS);
        this.mathsCount = columnValueMapping.get(Subject.MATHS);
        this.phyCount = columnValueMapping.get(Subject.PHY);
        this.chemiCount = columnValueMapping.get(Subject.CHEMI);
        this.bioCount = columnValueMapping.get(Subject.BIO);
        this.engCount = columnValueMapping.get(Subject.ENG);
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
        return super.toString() + "\n"
                + "Computer Science: " + csCount
                + "Mathematics: " + mathsCount
                + "Physics: " + phyCount
                + "Chemistry: " + chemiCount
                + "Biology: " + bioCount
                + "English: " + engCount;
    }
}
