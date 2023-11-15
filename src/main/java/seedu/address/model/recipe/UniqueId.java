package seedu.address.model.recipe;

/**
 * A wrapper for a unique immutable id.
 */
public class UniqueId {
    private static int lastId = 0;
    private final int id;

    /** Creates a new unique ID which is 1 increment of the previous id, starts from 1 */
    public UniqueId() {
        this.id = ++lastId;
    }

    /** Constructs a unique ID with the given {@code id} */
    public UniqueId(int id) {
        this.id = id;
    }

    /** Imports a unique ID with the given {@code id}, updates the previous id if the imported id is larger */
    public static UniqueId importUniqueId(int id) {
        lastId = Math.max(lastId, id);
        return new UniqueId(id);
    }

    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueId)) {
            return false;
        }

        UniqueId otherUuid = (UniqueId) other;
        return this.id == otherUuid.id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
