package seedu.address.testutil;

import seedu.address.model.person.Applicant;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;

/**
 * A utility class to help with building Applicant objects.
 */
public class ApplicantBuilder {
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";

    private Name name;
    private Phone phone;

    /**
     * Creates a {@code ApplicantBuilder} with the default details.
     */
    public ApplicantBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
    }

    /**
     * Initializes the ApplicantBuilder with the data of {@code applicantToCopy}.
     */
    public ApplicantBuilder(Applicant applicantToCopy) {
        name = applicantToCopy.getName();
        phone = applicantToCopy.getPhone();
    }

    /**
     * Sets the {@code Name} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    public Applicant build() {
        return new Applicant(name, phone);
    }
}
