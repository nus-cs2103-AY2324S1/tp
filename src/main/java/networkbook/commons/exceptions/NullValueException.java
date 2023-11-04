package networkbook.commons.exceptions;

public class NullValueException extends IllegalValueException {
    public NullValueException() {
        super("Non-nullable field(s) should not be null");
    }
}
