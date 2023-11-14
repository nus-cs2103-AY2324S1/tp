package networkbook.storage;

import java.util.function.Function;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import networkbook.commons.exceptions.IllegalValueException;
import networkbook.model.util.Identifiable;

class JsonAdaptedProperty<T extends Identifiable> {
    private final String name;

    /**
     * Constructs a {@code JsonAdaptedProperty} with the given {@code name}.
     */
    @JsonCreator
    public JsonAdaptedProperty(String name) {
        this.name = name;
    }

    /**
     * Converts a given item into this class for Jackson use.
     */
    public JsonAdaptedProperty(T source) {
        this.name = source.getValue();
    }

    @JsonValue
    public String getName() {
        return this.name;
    }

    public T toModelType(
            Predicate<String> predicate, String failMessage, Function<String, T> supplier)
            throws IllegalValueException {
        if (!predicate.test(this.name)) {
            throw new IllegalValueException(failMessage);
        }
        return supplier.apply(this.name);
    }
}
