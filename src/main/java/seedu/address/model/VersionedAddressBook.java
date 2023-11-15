package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

//@@author peasantbird-reused
//Reused from Address Book (Level4) with minor modifications

/**
 * Represents an AddressBook that is able to keep track of its states after each command execution.
 * Only records states when a command changes a buyer or seller in the buyer/seller lists.
 */
public class VersionedAddressBook extends AddressBook {
    private final List<ReadOnlyAddressBook> addressBookStateList;
    private int currentStatePointer;

    /**
     * Creates a VersionedAddressBook with a starting AddressBook state of {@code toBeCopied}.
     * @param toBeCopied A ReadOnlyAddressBook to be copied and started with.
     */
    public VersionedAddressBook(ReadOnlyAddressBook toBeCopied) {
        super(toBeCopied);
        addressBookStateList = new ArrayList<>();
        addressBookStateList.add(new AddressBook(toBeCopied));
        currentStatePointer = 0;
    }

    /**
     * Saves the current state of the VersionedAddressBook.
     */
    public void commit() {
        removeStatesAfterCurrentPointer();
        addressBookStateList.add(new AddressBook(this));
        currentStatePointer++;
    }

    /**
     * Removes all the saved states that occurs after the state that the VersionedAddressBook is currently in.
     */
    private void removeStatesAfterCurrentPointer() {
        addressBookStateList.subList(currentStatePointer + 1, addressBookStateList.size()).clear();
    }

    /**
     * Restores the VersionedAddressBook to its previous state.
     */
    public void undo() {
        if (!canUndo()) {
            throw new NoUndoableStateException();
        }
        currentStatePointer--;
        resetData(addressBookStateList.get(currentStatePointer));
    }

    /**
     * Restores the VersionedAddressBook to the next state.
     */
    public void redo() {
        if (!canRedo()) {
            throw new NoRedoableStateException();
        }
        currentStatePointer++;
        resetData(addressBookStateList.get(currentStatePointer));
    }

    /**
     * Checks if the VersionedAddressBook has any previous states to be undone into.
     * @return True if there are any previous states, false if not.
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    /**
     * Checks if the VersionedAddressBook has any next states to be redone into.
     * @return True if there are any next states, false if not.
     */
    public boolean canRedo() {
        return currentStatePointer < addressBookStateList.size() - 1;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VersionedAddressBook)) {
            return false;
        }

        VersionedAddressBook otherVersionedAddressBook = (VersionedAddressBook) other;
        return super.equals(otherVersionedAddressBook)
                && addressBookStateList.equals(otherVersionedAddressBook.addressBookStateList)
                && currentStatePointer == otherVersionedAddressBook.currentStatePointer;
    }

    /**
     * Represents an exception for VersionedAddressBook when there are no undoable states.
     */
    public static class NoUndoableStateException extends RuntimeException {
        private NoUndoableStateException() {
            super("Current state pointer is at the start of the list. There are no more previous states to undo into.");
        }
    }

    /**
     * Represents an exception for VersionedAddressBook when there are no redoable states.
     */
    public static class NoRedoableStateException extends RuntimeException {
        private NoRedoableStateException() {
            super("Current state pointer is at the end of the list. There are no more following states to redo into.");
        }
    }
}
//@@author
