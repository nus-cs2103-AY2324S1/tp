package transact.model.transaction.info;
/**
 * Represents the different types of a transaction in the system.
 * Type can be changed.
 */
public enum Type {
    R,
    I,
    E;

    public static final String MESSAGE_CONSTRAINTS = "This is an invalid type!";
    public static boolean isValidType(String type) {
        return (R.name().equals(type) || I.name().equals(type) || E.name().equals(type));
    }

    public static Type getType(String type) {
        if (R.name().equals(type)) {
            return R;
        }
        if (I.name().equals(type)) {
            return I;
        }
        if (E.name().equals(type)) {
            return E;
        }
        //code should never reach here.
        assert type != null;
        return null;
    }
}
