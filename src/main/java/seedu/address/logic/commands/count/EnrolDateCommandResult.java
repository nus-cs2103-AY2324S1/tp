package seedu.address.logic.commands.count;

import java.util.Map;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.tag.EnrolDate;

/**
 * An abstract class of EnrolDateCommandResult.
 */
public abstract class EnrolDateCommandResult extends CommandResult {
    private int janCount;
    private int febCount;
    private int marCount;
    private int aprCount;
    private int mayCount;
    private int junCount;
    private int julCount;
    private int augCount;
    private int sepCount;
    private int octCount;
    private int novCount;
    private int decCount;

    /**
     * Constructs a EnrolDateCommandResult.
     * @param feedbackToUser feedback message to user
     * @param columnValueMapping hash map instance containing column titles(String) as keys and
     *                           counts(Integer) as values.
     */
    public EnrolDateCommandResult(String feedbackToUser, Map<String, Integer> columnValueMapping) {
        super(feedbackToUser);
        this.janCount = columnValueMapping.get(EnrolDate.JAN);
        this.febCount = columnValueMapping.get(EnrolDate.FEB);
        this.marCount = columnValueMapping.get(EnrolDate.MAR);
        this.aprCount = columnValueMapping.get(EnrolDate.APR);
        this.mayCount = columnValueMapping.get(EnrolDate.MAY);
        this.junCount = columnValueMapping.get(EnrolDate.JUN);
        this.julCount = columnValueMapping.get(EnrolDate.JUL);
        this.augCount = columnValueMapping.get(EnrolDate.AUG);
        this.sepCount = columnValueMapping.get(EnrolDate.SEP);
        this.octCount = columnValueMapping.get(EnrolDate.OCT);
        this.novCount = columnValueMapping.get(EnrolDate.NOV);
        this.decCount = columnValueMapping.get(EnrolDate.DEC);
    }

    /**
     * Getter method for janCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Jan category.
     */
    public int getJanCount() {
        return janCount;
    }

    /**
     * Getter method for febCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Feb category.
     */
    public int getFebCount() {
        return febCount;
    }

    /**
     * Getter method for marCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Mar category.
     */
    public int getMarCount() {
        return marCount;
    }

    /**
     * Getter method for aprCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Apr category.
     */
    public int getAprCount() {
        return aprCount;
    }

    /**
     * Getter method for mayCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for May category.
     */
    public int getMayCount() {
        return mayCount;
    }

    /**
     * Getter method for junCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Jun category.
     */
    public int getJunCount() {
        return junCount;
    }

    /**
     * Getter method for julCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Jul category.
     */
    public int getJulCount() {
        return julCount;
    }

    /**
     * Getter method for augCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Aug category.
     */
    public int getAugCount() {
        return augCount;
    }

    /**
     * Getter method for sepCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Sep category.
     */
    public int getSepCount() {
        return sepCount;
    }

    /**
     * Getter method for octCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Oct category.
     */
    public int getOctCount() {
        return octCount;
    }

    /**
     * Getter method for novCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Nov category.
     */
    public int getNovCount() {
        return novCount;
    }

    /**
     * Getter method for decCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Dec category.
     */
    public int getDecCount() {
        return decCount;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EnrolDateCommandResult)) {
            return false;
        }

        EnrolDateCommandResult otherCommandResult = (EnrolDateCommandResult) other;

        return super.equals(otherCommandResult)
                && otherCommandResult.janCount == janCount
                && otherCommandResult.febCount == febCount
                && otherCommandResult.marCount == marCount
                && otherCommandResult.aprCount == aprCount
                && otherCommandResult.mayCount == mayCount
                && otherCommandResult.junCount == junCount
                && otherCommandResult.julCount == julCount
                && otherCommandResult.augCount == augCount
                && otherCommandResult.sepCount == sepCount
                && otherCommandResult.octCount == octCount
                && otherCommandResult.novCount == novCount
                && otherCommandResult.decCount == decCount;
    }

}
