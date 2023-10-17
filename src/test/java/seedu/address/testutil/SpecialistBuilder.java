package seedu.address.testutil;

import seedu.address.model.person.Location;
import seedu.address.model.person.Specialist;
import seedu.address.model.person.Specialty;

/**
 * A utility class to help with building Specialist objects.
 */
public class SpecialistBuilder extends PersonBuilder {
    public static final String DEFAULT_SPECIALTY = "Dermatology";

    public static final String DEFAULT_LOCATION = "123, Jurong West Ave 6, #08-111";
    private Specialty specialty;

    private Location location;

    /**
     * Creates a {@code SpecialistBuilder} with the default details.
     */
    public SpecialistBuilder() {
        super();
        specialty = new Specialty(DEFAULT_SPECIALTY);
        location = new Location(DEFAULT_LOCATION);
    }

    /**
     * Initializes the SpecialistBuilder with the data of {@code specialistToCopy}.
     */
    public SpecialistBuilder(Specialist specialistToCopy) {
        super(specialistToCopy);
        specialty = specialistToCopy.getSpecialty();
        location = specialistToCopy.getLocation();
    }

    public Location getLocation() {
        return location;
    }
    public Specialty getSpecialty() {
        return specialty;
    }

    /**
     * Sets the {@code Specialty} of the {@code Specialist} that we are building.
     *
     * @param specialty
     * @return PersonBuilder with specialty
     */
    public SpecialistBuilder withSpecialty(String specialty) {
        this.specialty = new Specialty(specialty);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Specialist} that we are building.
     */
    public SpecialistBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    @Override
    public Specialist build() {
        return new Specialist(getName(), getPhone(), getEmail(), getLocation(), getTags(), getSpecialty());
    }

}
