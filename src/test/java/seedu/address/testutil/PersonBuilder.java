package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.enums.InputSource;
import seedu.address.model.person.exceptions.BadAppointmentFormatException;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_ID = "T4651323H";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_APPOINTMENT = "10-Jan-2023 10:00 12:00";

    private Name name;
    private Id id;
    private Phone phone;
    private Email email;
    private Address address;
    private Appointment appointment;
    private Set<MedicalHistory> medicalHistories;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        try {
            appointment = Appointment.of(DEFAULT_APPOINTMENT, InputSource.USER_INPUT);
        } catch (BadAppointmentFormatException e) {
            throw new IllegalStateException(
                    "Encountered an error with Appointment for PersonBuilder.", e);
        }
        name = new Name(DEFAULT_NAME);
        id = new Id(DEFAULT_ID);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        medicalHistories = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        id = personToCopy.getId();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        medicalHistories = new HashSet<>(personToCopy.getMedicalHistories());
        appointment = personToCopy.getAppointment().isPresent() ? personToCopy.getAppointment().get() : null;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code Person} that we are building.
     */
    public PersonBuilder withId(String id) {
        this.id = new Id(id);
        return this;
    }

    /**
     * Sets the {@code Appointment} of the {@code Person} that we are building.
     */
    public PersonBuilder withAppointment(String appointment) {
        try {
            this.appointment = Appointment.of(appointment, InputSource.USER_INPUT);
        } catch (BadAppointmentFormatException e) {
            throw new IllegalStateException(
                    "Encountered an error with Appointment for PersonBuilder.", e);
        }
        return this;
    }

    /**
     * Sets the {@code Appointment} of the {@code Person} that we are building to null.
     */
    public PersonBuilder withoutAppointment() {
        this.appointment = null;
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * @return the person.
     */
    public Person build() {
        return new Person(name, id, phone, email, address,
                appointment, medicalHistories);

    }

    /**
     * Sets the {@code MedicalHistory} of the {@code Person} that we are building.
     */
    public PersonBuilder withMedical(String... medicalHistories) {
        this.medicalHistories = SampleDataUtil.getMedicalHistorySet(medicalHistories);
        return this;
    }
}
