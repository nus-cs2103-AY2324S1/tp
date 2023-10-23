package seedu.ccacommander.model;

import static seedu.ccacommander.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

/**
 * Captures the states of CcaCommander.
 */
public class StateCaptures {
    private List<String> stateCaptures;
    private int currentIndex;

    /**
     * Creates a {@code StateCaptures} with the specified {@code currentIndex} and {@code stateCaptures}.
     */
    public StateCaptures(int currentIndex, List<String> stateCaptures) {
        requireAllNonNull(currentIndex, stateCaptures);

        this.stateCaptures = stateCaptures;
        this.currentIndex = currentIndex;
    }

    public int getCurrentIndex() {
        return this.currentIndex;
    }

    public List<String> getStateCaptures() {
        return this.stateCaptures;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof StateCaptures
                && ((StateCaptures) other).getStateCaptures().equals(getStateCaptures())
                && ((StateCaptures) other).getCurrentIndex() == getCurrentIndex());
    }
}
