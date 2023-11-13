package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * This enum encapsulates the types of people that the address book can deal with.
 */
public enum PersonType {

    PATIENT(person -> person instanceof Patient), SPECIALIST(person -> person instanceof Specialist);

    /** {@code Predicate} that evaluates to true when {@code Person} matches the {@code PersonType}*/
    private final Predicate<Person> searchPredicate;

    PersonType(Predicate<Person> searchPredicate) {
        this.searchPredicate = searchPredicate;
    }

    public Predicate<Person> getSearchPredicate() {
        return searchPredicate;
    }
}
