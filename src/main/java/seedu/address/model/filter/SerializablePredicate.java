package seedu.address.model.filter;

import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import seedu.address.model.person.Person;

/**
 * A serializable wrapper class for Predicate of type Person.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class SerializablePredicate implements Predicate<Person> {
    @JsonIgnore
    private Predicate<Person> predicate;

    public SerializablePredicate() {
    }

    public SerializablePredicate(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean test(Person t) {
        return predicate.test(t);
    }

    public SerializablePredicate and(SerializablePredicate other) {
        return new SerializablePredicate(predicate.and(other.predicate));
    }

    public SerializablePredicate or(SerializablePredicate other) {
        return new SerializablePredicate(predicate.or(other.predicate));
    }

    public SerializablePredicate negate() {
        return new SerializablePredicate(predicate.negate());
    }

    public Predicate<Person> getPredicate() {
        return predicate;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SerializablePredicate)) {
            return false;
        }

        SerializablePredicate otherPredicate = (SerializablePredicate) other;
        return otherPredicate.predicate.equals(predicate);
    }

    @Override
    public int hashCode() {
        return predicate.hashCode();
    }
}
