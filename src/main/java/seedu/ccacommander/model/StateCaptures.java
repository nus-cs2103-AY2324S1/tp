package seedu.ccacommander.model;

import static seedu.ccacommander.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

/**
 * Captures the states of CcaCommander.
 */
public class StateCaptures {
    private int currentPointer;
    private List<String> stateCaptures;

    /**
     * Creates a {@code StateCaptures} with the specified {@code currentIndex} and {@code stateCaptures}.
     */
    public StateCaptures(int currentPointer, List<String> stateCaptures) {
        requireAllNonNull(currentPointer, stateCaptures);

        this.stateCaptures = stateCaptures;
        this.currentPointer = currentPointer;
    }

    public List<String> getStateCaptures() {
        return this.stateCaptures;
    }
    public int getCurrentPointer() {
        return this.currentPointer;
    }


    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof StateCaptures
                && ((StateCaptures) other).getStateCaptures().equals(getStateCaptures())
                && ((StateCaptures) other).getCurrentPointer() == getCurrentPointer());
    }
}
