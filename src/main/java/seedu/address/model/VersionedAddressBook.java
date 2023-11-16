package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.TabIndex;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Represents a versioned history of the address book.
 * Keeps track of the address book states and allows undoing and redoing changes.
 */
public class VersionedAddressBook extends AddressBook {
    public static final String INVALID_REDO_COMMAND = "You have reached the last step, unable to redo";
    public static final String INVALID_UNDO_COMMAND = "You have reached the first step, unable to undo";
    private List<AddressBook> addressBookStateList;
    private List<String> successfulCommandMessages;
    private List<TabIndex> tabIndex;
    private int currentStatePointer;

    /**
     * Constructs a `VersionedAddressBook` object with an initial address book state.
     *
     * @param initialAddressBook The initial state of the address book.
     */
    public VersionedAddressBook(ReadOnlyAddressBook initialAddressBook) {
        addressBookStateList = new ArrayList<>();
        addressBookStateList.add(new AddressBook(initialAddressBook));
        successfulCommandMessages = new ArrayList<>();
        tabIndex = new ArrayList<>();
        currentStatePointer = 0;
    }

    /**
     * Commits the current model state along with a success message and the tab index.
     *
     * @param model The current model.
     * @param successMessage The success message to be stored.
     * @param index The tab index to be stored.
     */
    public void commit(Model model, String successMessage, TabIndex index) {
        if (currentStatePointer < addressBookStateList.size() - 1) {
            // If we're not at the latest state, remove the states after the current one
            addressBookStateList.subList(currentStatePointer + 1, addressBookStateList.size()).clear();
            successfulCommandMessages.subList(currentStatePointer, successfulCommandMessages.size()).clear();
            tabIndex.subList(currentStatePointer, tabIndex.size()).clear();
        }

        // Add the current state to the history
        AddressBook updatedAddressBook = new AddressBook(model.getAddressBook());
        addressBookStateList.add(updatedAddressBook);
        successfulCommandMessages.add(successMessage);
        tabIndex.add(index);
        currentStatePointer++;
    }

    /**
     * Undoes the last change by restoring the previous state.
     *
     * @param model The current model.
     * @throws CommandException If the undo operation is not valid (e.g., when there are no more states to undo).
     */
    public void undo(Model model) throws CommandException {
        if (canUndo()) {
            currentStatePointer--;
            changeAddressBook(model);
        } else {
            throw new CommandException(INVALID_UNDO_COMMAND);
        }
    }

    /**
     * Redoes the last undone change by restoring the next state.
     *
     * @param model The current model.
     * @throws CommandException If the redo operation is not valid (e.g., when there are no more states to redo).
     */
    public void redo(Model model) throws CommandException {
        if (canRedo()) {
            currentStatePointer++;
            changeAddressBook(model);
        } else {
            throw new CommandException(INVALID_REDO_COMMAND);
        }
    }

    /**
     * Checks if there are states that can be undone.
     *
     * @return `true` if there are states to undo, `false` otherwise.
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    /**
     * Checks if there are states that can be redone.
     *
     * @return `true` if there are states to redo, `false` otherwise.
     */
    public boolean canRedo() {
        return currentStatePointer < addressBookStateList.size() - 1;
    }

    /**
     * Gets the success message associated with the previous command.
     *
     * @return The success message from the previous command.
     */
    public String getPreviousMessage() {
        return successfulCommandMessages.get(currentStatePointer);
    }
    /**
     * Gets the success message associated with the redone command.
     *
     * @return The success message from the previous command.
     */
    public String getPreviousMessageForRedo() {
        return successfulCommandMessages.get(currentStatePointer - 1);
    }

    /**
     * Gets the tab index associated with the previous command.
     *
     * @return The tab index from the previous command.
     */
    public TabIndex getPreviousTabIndex() {
        return tabIndex.get(currentStatePointer);
    }

    /**
     * Gets the tab index associated with the redone command.
     *
     * @return The tab index from the previous command.
     */
    public TabIndex getPreviousTabIndexForRedo() {
        return tabIndex.get(currentStatePointer - 1);
    }

    /**
     * Gets the current addressbook.
     *
     * @return The tab index from the previous command.
     */
    public ReadOnlyAddressBook getCurrentState(int currentStatePointer) {
        return addressBookStateList.get(currentStatePointer);
    }

    /**
     * Gets the current current state pointer.
     *
     * @return The tab index from the previous command.
     */
    public int getCurrentStatePointer() {
        return currentStatePointer;
    }

    /**
     * Changes the current model's address book to match the current state.
     *
     * @param model The current model.
     */
    public void changeAddressBook(Model model) {
        AddressBook newAddressBook = addressBookStateList.get(currentStatePointer);
        model.setAddressBook(newAddressBook);
    }
}
