package networkbook.model;

import networkbook.model.util.UniqueList;

/**
 * Wraps all data at the network-book level while also facilitating the undo/redo mechanism by storing version history.
 * (basically behaves like a NetworkBook too; stored in the modelmanager, state-managing commands are called after
 * state-altering ones)
 */
public class VersionedNetworkBook extends NetworkBook {
    private final UniqueList<NetworkBook> networkBookStateList;
    private int currentStatePointer;

    public VersionedNetworkBook() {
        super();
        this.networkBookStateList = new UniqueList<>();
        this.currentStatePointer = -1;
        this.commit();
    }

    public VersionedNetworkBook(ReadOnlyNetworkBook toBeCopied) {
        super();
        super.resetData(toBeCopied);
        this.networkBookStateList = new UniqueList<>();
        this.currentStatePointer = -1;
        this.commit();
    }

    // throws exception?
    public void commit() {
        // clear any "future" history; currently suboptimal
        this.networkBookStateList.clearRange(this.currentStatePointer + 1, this.networkBookStateList.size());
        this.networkBookStateList.add(new NetworkBook(this));
        this.currentStatePointer++;
        return;
    }

    // throws exception?
    public void undo() {
        if (this.currentStatePointer <= 0) {
            // throw exception
            return;
        }
        super.resetData(this.networkBookStateList.get(--currentStatePointer));
    }

    // throws exception?
    public void redo() {
        if (this.currentStatePointer >= this.networkBookStateList.size() - 1) {
            // throw exception
            return;
        }
        super.resetData(this.networkBookStateList.get(++this.currentStatePointer));
    }

    public int getCurrentStatePointer() {
        return this.currentStatePointer;
    }
    // getNetworkBookStateList()
}
