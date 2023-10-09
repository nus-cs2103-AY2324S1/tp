package seedu.address.testutil;

import seedu.address.model.person.Name;
import seedu.address.model.person.Specialist;
import seedu.address.model.person.Specialty;

/**
 * A utility class to help with building Specialist objects.
 */
public class SpecialistBuilder extends PersonBuilder {
    public static final String DEFAULT_SPECIALTY = "Dermatology";
    public Specialty getSpecialty() {
        return specialty;
    }

    public PersonBuilder withSpecialty(String specialty) {
        this.specialty = new Specialty(specialty);
        return this;
    }
    private Specialty specialty;
    public SpecialistBuilder() {
        super();
        specialty = new Specialty(DEFAULT_SPECIALTY);
    }

    public SpecialistBuilder(Specialist SpecialistToCopy) {
        super(SpecialistToCopy);
        specialty = SpecialistToCopy.getSpecialty();
    }

    @Override
    public Specialist build() {
        return new Specialist(getName(), getPhone(), getEmail(), getAddress(), getTags(), getSpecialty());
    }

}
