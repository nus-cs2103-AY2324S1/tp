package seedu.letsgethired.model;

import java.util.Stack;

import seedu.letsgethired.model.application.UniqueApplicationList;

/**
 * Wraps all data at the intern-tracker level
 * Duplicates are not allowed (by .isSameApplication comparison)
 * Stores previous state history in savedStates to enable undo actions
 */
public class VersionedInternTracker extends InternTracker {
    private Stack<UniqueApplicationList> savedStates;

    /**
     * Creates an InternTracker with no entries
     */
    public VersionedInternTracker() {
        super();
        this.savedStates = new Stack<>();
    }

    /**
     * Creates an InternTracker using the InternApplications in the {@code toBeCopied}
     */
    public VersionedInternTracker(ReadOnlyInternTracker toBeCopied) {
        this();
        this.savedStates = new Stack<>();
        resetData(toBeCopied);
    }

    /**
     * Saves the current internApplications state
     */
    public void commit() {
        UniqueApplicationList currentTasks = super.getInternApplications().clone();
        assert currentTasks != super.getInternApplications();
        assert currentTasks != null;
        savedStates.add(currentTasks);
    }

    /**
     * Deletes all existing intern applications being tracked
     */
    public void clear() {
        super.setInternApplications(new UniqueApplicationList());
    }

    /**
     * Restores the previous state of the internApplications by popping the last saved state from the stack.
     * If there is no previous state, does nothing
     * @return {@code true} if a previous state was restored successfully. {@code false} if at latest change.
     */
    public boolean undo() {
        if (savedStates.size() > 0) {
            UniqueApplicationList previousState = savedStates.pop();
            // Defensive check: Ensure the popped state is not null before setting it
            assert previousState != null;
            super.setInternApplications(previousState);
            return true;
        }
        return false;
    }
}

