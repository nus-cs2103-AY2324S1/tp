package seedu.address.testutil;

import seedu.address.logic.commands.AddCommandTest;
import seedu.address.model.person.Person;

import static java.util.Objects.requireNonNull;

public class ModelStubWithPerson extends ModelStub {
    private final Person person;

    ModelStubWithPerson(Person person) {
        requireNonNull(person);
        this.person = person;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return this.person.isSamePerson(person);
    }
}
