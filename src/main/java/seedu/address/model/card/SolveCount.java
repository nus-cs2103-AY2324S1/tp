package seedu.address.model.card;

/**
 * Represents the number of times the card is solved.
 */
public class SolveCount {
    private Integer solveCount = 0;

    public SolveCount(Integer solveCount) {
        this.solveCount = solveCount;
    }
    public SolveCount() {
        this.solveCount = 0;
    }

    public Integer getSolveCount() {
        return solveCount;
    }

    /**
     * Increment solve count by 1 when the card is solved.
     */
    public void incrementSolveCount() {
        this.solveCount += 1;
    }

    @Override
    public String toString() {
        return Integer.toString(solveCount);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof Integer
        if (other instanceof Integer) {
            return getSolveCount() == other;
        }

        // instanceof handles nulls
        if (!(other instanceof SolveCount)) {
            return false;
        }

        // compare Integer equality
        SolveCount otherSolveCount = (SolveCount) other;
        return solveCount.equals(otherSolveCount.solveCount);
    }

    @Override
    public int hashCode() {
        return solveCount.hashCode();
    }
}
