package networkbook.model;

import networkbook.model.util.UniqueList;

/**
 * Wraps all data at the network-book level while also facilitating the undo/redo mechanism by storing version history.
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

    // util commands

    public void commit() {
        this.networkBookStateList.clearRange(this.currentStatePointer + 1, this.networkBookStateList.size());
        this.networkBookStateList.add(new NetworkBook(this));
        this.currentStatePointer++;

    }
    public void undo() {
        super.resetData(this.networkBookStateList.get(--currentStatePointer));
    }
    public void redo() {
        super.resetData(this.networkBookStateList.get(++this.currentStatePointer));
    }
    public boolean canUndo() {
        return this.currentStatePointer > 0;
    }
    public boolean canRedo() {
        return currentStatePointer < (this.networkBookStateList.size() - 1);
    }

    public int getCurrentStatePointer() {
        return this.currentStatePointer;
    }
}
