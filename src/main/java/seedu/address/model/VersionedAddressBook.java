package seedu.address.model;

import java.util.ArrayList;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * Represents an AddressBook with an undo/redo history
 */
public class VersionedAddressBook extends AddressBook {

    private final ArrayList<AddressBook> addressBookStateList;
    private int currentStatePointer;
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Creates a Versioned AddressBook
     */
    public VersionedAddressBook() {
        super();
        addressBookStateList = new ArrayList<>();

        // Save a copy of initial state into state list
        AddressBook initState = new AddressBook(this);
        addressBookStateList.add(initState);

        // Initialise pointer to start of list
        currentStatePointer = 0;

        logger.info("Versioned Address Book is initialised");
    }

    /**
     * Creates a Versioned AddressBook using the Persons in the {@code toBeCopied}
     */
    public VersionedAddressBook(ReadOnlyAddressBook toBeCopied) {
        super(toBeCopied);
        addressBookStateList = new ArrayList<>();

        // Save a copy of initial state into state list
        AddressBook initState = new AddressBook(this);
        addressBookStateList.add(initState);

        // Initialise pointer to start of list
        currentStatePointer = 0;
    }

    /**
     * Gets {@code AddressBook} that the pointer is pointing to
     * @return current address book
     */
    private AddressBook getCurrentAddressBook() {
        return addressBookStateList.get(currentStatePointer);
    }

    // Commits a copy of current address book so that the saved state is immutable

    /**
     * Commits a copy of current {@code AddressBook} to {@code addressBookStateList}
     * so that the saved state is immutable
     */
    public void commit() {
        // Save a copy of the state into the state list
        AddressBook copy = new AddressBook(this);
        addressBookStateList.add(copy);

        currentStatePointer++;

        logger.info("[VersionedAddressBook.commit()]: Versioned Address Book commit to memory");
    }

    /**
     * Shifts {@code currentStatePointer} to the left to restore previous version of the addressbook
     */
    public void undo() {
        currentStatePointer--;
        this.resetData(getCurrentAddressBook());
    }

    /**
     * Shifts {@code currentStatePointer} to the right to restore undoned version of the addressbook
     */
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

    /**
     * Remove redundant {@code AddressBook} states from addressBookStateList
     */
    public void purge() {
        // Remove version data after the current state
        if (addressBookStateList.size() > currentStatePointer + 1) {
            addressBookStateList.subList(currentStatePointer + 1, addressBookStateList.size()).clear();
        }
    }
}
