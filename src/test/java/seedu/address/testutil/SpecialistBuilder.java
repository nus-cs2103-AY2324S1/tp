package seedu.address.testutil;

import seedu.address.model.person.Specialist;
import seedu.address.model.person.Specialty;

/**
 * A utility class to help with building Specialist objects.
 */
public class SpecialistBuilder extends PersonBuilder {
    public static final String DEFAULT_SPECIALTY = "Dermatology";
    private Specialty specialty;

    /**
     * Constructor that assigns a default specialty
     */
    public SpecialistBuilder() {
        super();
        specialty = new Specialty(DEFAULT_SPECIALTY);
    }

    /**
     * Constructor to copy a specific specialist
     * @param specialistToCopy
     */
    public SpecialistBuilder(Specialist specialistToCopy) {
        super(specialistToCopy);
        specialty = specialistToCopy.getSpecialty();
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    /**
     * Sets the {@code Specialty} of the {@code Specialist} that we are building.
     * @param specialty
     * @return PersonBuilder with specialty
     */
    public PersonBuilder withSpecialty(String specialty) {
        this.specialty = new Specialty(specialty);
        return this;
    }

    @Override
    public Specialist build() {
        return new Specialist(getName(), getPhone(), getEmail(), getLocation(), getTags(), getSpecialty());
    }

}
