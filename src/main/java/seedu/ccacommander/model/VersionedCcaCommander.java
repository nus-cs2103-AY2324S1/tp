package seedu.ccacommander.model;

import static java.util.Objects.requireNonNull;
import static seedu.ccacommander.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.ccacommander.model.exceptions.UndoStateException;

/**
 * A CcaCommander with versions implemented.
 */
public class VersionedCcaCommander extends CcaCommander {
    public static final String MESSAGE_FIRST_COMMIT = "Saved data is loaded.";

    private List<CcaCommanderState> ccaCommanderVersionList;
    private int versionPointer;

    /**
     * Creates a {@code VersionedCcaCommander} using the Members in the {@code toCopy}.
     * @param toCopy
     */
    public VersionedCcaCommander(ReadOnlyCcaCommander toCopy) {
        super(toCopy);

        CcaCommanderState initialVersion = new CcaCommanderState(MESSAGE_FIRST_COMMIT, this);
        this.ccaCommanderVersionList = new ArrayList<>();
        this.ccaCommanderVersionList.add(initialVersion);
        this.versionPointer = 0;
    }

    /**
     * Saves the current state of the {@code CcaCommander} with the associated {@code commitMessage}.
     *
     * @throws NullPointerException if the {@code commitMessage} is null.
     */
    public void commit(String commitMessage) throws NullPointerException {
        requireNonNull(commitMessage);

        purgeRedundantVersions();
        CcaCommanderState state = new CcaCommanderState(commitMessage, this);
        this.ccaCommanderVersionList.add(state);
        this.versionPointer++;
    }

    private void purgeRedundantVersions() {
        this.ccaCommanderVersionList.subList(this.versionPointer + 1, this.ccaCommanderVersionList.size()).clear();
    }

    /**
     * Undoes the most recent undoable command.
     *
     * @return the commit message of the undone command.
     * @throws UndoStateException if there are no commands to undo.
     */
    public String undo() throws UndoStateException {
        if (!canUndo()) {
            throw new UndoStateException();
        }

        CcaCommanderState currentState = this.ccaCommanderVersionList.get(this.versionPointer);
        CcaCommanderState targetState = this.ccaCommanderVersionList.get(this.versionPointer - 1);
        resetData(targetState.stateCapture);
        this.versionPointer--;
        return currentState.commitMessage;
    }

    /**
     * Returns true if there exists a {@code Command} that can be undone.
     */
    public boolean canUndo() {
        return this.versionPointer > 0;
    }

    /**
     * Returns a summary of all {@code Command}s currently recorded by this {@code VersionedCcaCommander}.
     */
    public StateCaptures viewStateCaptures() {
        return new StateCaptures(versionPointer, ccaCommanderVersionList.stream().map(state ->
                state.commitMessage).collect(Collectors.toUnmodifiableList()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof VersionedCcaCommander
                && ((VersionedCcaCommander) other).versionPointer == this.versionPointer
                && ((VersionedCcaCommander) other).ccaCommanderVersionList.equals(this.ccaCommanderVersionList)
                && super.equals(other));
    }

    private class CcaCommanderState {
        private String commitMessage;
        private ReadOnlyCcaCommander stateCapture;

        CcaCommanderState(String commitMessage, ReadOnlyCcaCommander stateCapture) {
            requireAllNonNull(commitMessage, stateCapture);

            this.commitMessage = commitMessage;
            this.stateCapture = new CcaCommander(stateCapture);
        }

        @Override
        public boolean equals(Object other) {
            return other == this
                    || (other instanceof CcaCommanderState
                    && ((CcaCommanderState) other).stateCapture.equals(stateCapture)
                    && ((CcaCommanderState) other).commitMessage.equals(commitMessage));
        }
    }
}
