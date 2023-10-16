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
     * Creates a {@code SpecialistBuilder} with the default details.
     */
    public SpecialistBuilder() {
        super();
        specialty = new Specialty(DEFAULT_SPECIALTY);
    }

    /**
     * Initializes the SpecialistBuilder with the data of {@code specialistToCopy}.
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
    public SpecialistBuilder withSpecialty(String specialty) {
        this.specialty = new Specialty(specialty);
        return this;
    }

    @Override
    public Specialist build() {
        return new Specialist(getName(), getPhone(), getEmail(), getAddress(), getTags(), getSpecialty());
    }

}
