package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.UniqueAppointmentList;
import seedu.address.model.person.Age;
import seedu.address.model.person.Allergy;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.record.Record;
import seedu.address.model.record.UniqueRecordList;
import seedu.address.model.shared.Name;
import seedu.address.model.shared.Nric;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_NRIC = "Z1234567Z";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_GENDER = "F";
    public static final int DEFAULT_AGE = 21;
    public static final String DEFAULT_BLOODTYPE = "O+";
    public static final String DEFAULT_ALLERGY = "Ants";

    private Name name;
    private Nric nric;
    private Email email;
    private Phone phone;
    private Gender gender;
    private Age age;
    private BloodType bloodType;
    private Set<Allergy> allergies;
    private UniqueRecordList records;
    private UniqueAppointmentList appointments;
    private boolean isPinned;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        nric = new Nric(DEFAULT_NRIC);
        email = new Email(DEFAULT_EMAIL);
        phone = new Phone(DEFAULT_PHONE);
        gender = new Gender(DEFAULT_GENDER);
        age = new Age(DEFAULT_AGE);
        bloodType = new BloodType(DEFAULT_BLOODTYPE);
        allergies = new HashSet<>();
        records = new UniqueRecordList();
        appointments = new UniqueAppointmentList();
        isPinned = false;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        nric = personToCopy.getNric();
        email = personToCopy.getEmail();
        phone = personToCopy.getPhone();
        gender = personToCopy.getGender();
        age = personToCopy.getAge();
        bloodType = personToCopy.getBloodType();
        allergies = new HashSet<>(personToCopy.getAllergies());
        records = new UniqueRecordList();
        records.setRecords(personToCopy.getRecords());
        appointments = new UniqueAppointmentList();
        appointments.setAppointments(personToCopy.getAppointments());
        ;
        isPinned = personToCopy.isPinned();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Nric} of the {@code Person} that we are building.
     */
    public PersonBuilder withNric(String nric) {
        this.nric = new Nric(nric);
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
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Age} of the {@code Person} that we are building.
     */
    public PersonBuilder withAge(int age) {
        this.age = new Age(age);
        return this;
    }

    /**
     * Sets the {@code BloodType} of the {@code Person} that we are building.
     */
    public PersonBuilder withBloodType(String bloodType) {
        this.bloodType = new BloodType(bloodType);
        return this;
    }

    /**
     * Parses the {@code allergies} into a {@code Set<Allergy>} and set it to the
     * {@code Person} that we are building.
     */
    public PersonBuilder withAllergies(String... allergies) {
        this.allergies = SampleDataUtil.getAllergySet(allergies);
        return this;
    }

    /**
     * Sets the {@code records} of the {@code Person} that we are building.
     */
    public PersonBuilder withRecords(Record... records) {
        this.records = SampleDataUtil.getRecordList(records);
        return this;
    }

    /**
     * Sets the {@code records} of the {@code Person} that we are building.
     */
    public PersonBuilder withRecords(UniqueRecordList records) {
        this.records = records;
        return this;
    }

    /**
     * Sets the {@code isPinned} of the {@code Person} that we are building.
     */
    public PersonBuilder withIsPinned(boolean isPinned) {
        this.isPinned = isPinned;
        return this;
    }

    /**
     * Sets the {@code appointments} of the {@code Person} that we are building.
     */
    public PersonBuilder withAppointments(UniqueAppointmentList appointment) {
        this.appointments = appointment;
        return this;
    }

    /**
     * Sets the {@code appointments} of the {@code Person} that we are building.
     */
    public PersonBuilder withAppointments(Appointment... appointments) {
        this.appointments = SampleDataUtil.getAppointmentList(appointments);
        return this;
    }

    public Person build() {
        return new Person(name, nric, email, phone, gender, age, bloodType, allergies, records, appointments, isPinned);
    }
}
