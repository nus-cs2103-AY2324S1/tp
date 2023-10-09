package seedu.address.testutil;

import seedu.address.model.person.Specialist;

/**
 * A utility class to help with building Specialist objects.
 */
public class SpecialistBuilder extends PersonBuilder {
    public SpecialistBuilder() {
        super();
    }

    public SpecialistBuilder(Specialist patientToCopy) {
        super(patientToCopy);
    }

    @Override
    public Specialist build() {
        return new Specialist(getName(), getPhone(), getEmail(), getAddress(), getTags());
    }

}
