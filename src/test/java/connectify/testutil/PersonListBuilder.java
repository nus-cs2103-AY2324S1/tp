package connectify.testutil;

import static connectify.testutil.TypicalPersons.ALICE;

import java.util.ArrayList;
import java.util.List;

import connectify.model.person.Person;
import connectify.model.person.PersonList;

/**
 * A utility class to help with building {@code PersonList} objects.
 */
public class PersonListBuilder {
    public static final Person DEFAULT_PERSON = ALICE;

    private List<Person> personList = new ArrayList<>();

    /**
     * Creates a {@code PersonListBuilder} with a default person.
     */
    public PersonListBuilder() {
        personList.add(DEFAULT_PERSON);
    }

    /**
     * Initializes the {@code PersonListBuilder} with the data of {@code personListToCopy}.
     */
    public PersonListBuilder(PersonList personListToCopy) {
        personList.addAll(personListToCopy.asList());
    }

    /**
     * Adds a {@link Person} to the {@code PersonList} that we are building.
     */
    public PersonListBuilder withPerson(Person person) {
        personList.add(person);
        return this;
    }

    /**
     * Builds the {@code PersonList}.
     */
    public PersonList build() {
        return PersonList.fromList(personList);
    }
}
