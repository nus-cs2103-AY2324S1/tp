package seedu.address.commons.history;

import java.util.List;
import java.util.Stack;

import javafx.util.Pair;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

/**
 * Manages the user's undo and redo history.
 */
public class UserHistoryManager {
    private Stack<Pair<List<Person>, List<Appointment>>> undoHistory;
    private Stack<Pair<List<Person>, List<Appointment>>> redoHistory;

    /**
     * Initializes a new UserHistoryManager with empty undo and redo histories.
     */
    public UserHistoryManager() {
        undoHistory = new Stack<>();
        redoHistory = new Stack<>();
    }

    /**
     * Initializes a new UserHistoryManager with predeclared undo and redo histories.
     */
    @SuppressWarnings("unchecked")
    public UserHistoryManager(UserHistoryManager userHistory) {
        this.undoHistory = (Stack<Pair<List<Person>, List<Appointment>>>) userHistory.undoHistory.clone();
        this.redoHistory = (Stack<Pair<List<Person>, List<Appointment>>>) userHistory.redoHistory.clone();
    }

    /**
     * Initializes the undo history with the given pair.
     *
     * @param pair The pair of lists to be added to the undo history.
     */
    public void initialiseHistory(Pair<List<Person>, List<Appointment>> pair) {
        this.undoHistory.add(pair);
    }


    /**
     * Adds the given pair to the undo history.
     *
     * @param p The pair of lists to be added to the undo history.
     */
    public void addHistory(Pair<List<Person>, List<Appointment>> p) {
        undoHistory.add(p);
    }

    /**
     * Removes the latest state from the undo history and adds it to the redo history.
     */
    public void undo() {
        this.redoHistory.add(undoHistory.peek());
        this.undoHistory.pop();
    }

    /**
     * Removes the latest state from the redo history and adds it to the undo history.
     */
    public void redo() {
        this.undoHistory.add(redoHistory.peek());
        this.redoHistory.pop();
    }

    /**
     * Clears the redo history.
     */
    public void resetRedoHistory() {
        this.redoHistory.clear();
    }

    /**
     * @return the undo history.
     */
    public Stack<Pair<List<Person>, List<Appointment>>> getUndoHistory() {
        return undoHistory;
    }

    /**
     * @return the redo history.
     */
    public Stack<Pair<List<Person>, List<Appointment>>> getRedoHistory() {
        return redoHistory;
    }

    /**
     * Checks if there is any state to undo.
     *
     * @return true if there is a state to undo, false otherwise.
     */
    public boolean canUndo() {
        return undoHistory.size() > 1;
    }

    /**
     * Checks if there is any state to redo.
     *
     * @return true if there is a state to redo, false otherwise.
     */
    public boolean canRedo() {
        return !redoHistory.isEmpty();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UserHistoryManager)) {
            return false;
        }

        UserHistoryManager otherUserHistory = (UserHistoryManager) obj;
        return this.undoHistory.equals(otherUserHistory.undoHistory)
                && this.redoHistory.equals(otherUserHistory.redoHistory);
    }
}
