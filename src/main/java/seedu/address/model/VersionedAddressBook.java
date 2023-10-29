package seedu.address.model;

import java.util.ArrayList;

/**
 * Represents an AddressBook with an undo/redo history
 */
public class VersionedAddressBook extends AddressBook {

    private final ArrayList<AddressBook> addressBookStateList;
    private int currentStatePointer;

    public VersionedAddressBook() {
        super();
        addressBookStateList = new ArrayList<>();

        // Save a copy of initial state into state list
        AddressBook initState = new AddressBook(this);
        addressBookStateList.add(initState);

        // Initialise pointer to start of list
        currentStatePointer = 0;
    }

    public VersionedAddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    // Returns address book that the pointer is pointing to
    private AddressBook getCurrentAddressBook() {
        return addressBookStateList.get(currentStatePointer);
    }

    // Commits a copy of current address book so that the saved state is immutable
    public void commit() {
        // Save a copy of the state into the state list
        AddressBook copy = new AddressBook(this);
        addressBookStateList.add(copy);

        currentStatePointer++;
    }

    public void undo() {
        currentStatePointer--;
        this.resetData(getCurrentAddressBook());
    }

    public void redo() {
        currentStatePointer++;
        this.resetData(getCurrentAddressBook());
    }

    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    public boolean canRedo() {
        return addressBookStateList.size() > 1 && currentStatePointer < addressBookStateList.size() - 1;
    }

    public void purge() {
        // Remove version data after the current state
        if (addressBookStateList.size() > currentStatePointer + 1) {
            addressBookStateList.subList(currentStatePointer + 1, addressBookStateList.size()).clear();
        }
    }
}
