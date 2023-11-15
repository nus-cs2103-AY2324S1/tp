package seedu.address.testutil;

import seedu.address.model.person.Address;
import seedu.address.model.person.Begin;
import seedu.address.model.person.Day;
import seedu.address.model.person.Email;
import seedu.address.model.person.End;
import seedu.address.model.person.Name;
import seedu.address.model.person.PayRate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Subject;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_SUBJECT = "Maths";
    public static final String DEFAULT_DAY = "Mon";
    public static final String DEFAULT_BEGIN = "1000";
    public static final String DEFAULT_END = "1200";

    public static final String DEFAULT_PAYRATE = "15.00";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Subject subject;
    private Day day;
    private Begin begin;
    private End end;
    private boolean paid;

    private PayRate payRate;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        subject = new Subject(DEFAULT_SUBJECT);
        day = new Day(DEFAULT_DAY);
        begin = new Begin(DEFAULT_BEGIN);
        end = new End(DEFAULT_END);
        paid = false;
        payRate = new PayRate(DEFAULT_PAYRATE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        subject = personToCopy.getSubject();
        day = personToCopy.getDay();
        begin = personToCopy.getBegin();
        end = personToCopy.getEnd();
        paid = false;
        payRate = personToCopy.getPayRate();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
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
     * Sets the {@code Subject} of the {@code Person} that we are building.
     */
    public PersonBuilder withSubject(String subject) {
        this.subject = new Subject(subject);
        return this;
    }

    /**
     * Sets the {@code Day} of the {@code Person} that we are building.
     */
    public PersonBuilder withDay(String day) {
        this.day = new Day(day);
        return this;
    }

    /**
     * Sets the {@code Begin} of the {@code Person} that we are building.
     */
    public PersonBuilder withBegin(String begin) {
        this.begin = new Begin(begin);
        return this;
    }

    /**
     * Sets the {@code End} of the {@code Person} that we are building.
     */
    public PersonBuilder withEnd(String end) {
        this.end = new End(end);
        return this;
    }

    /**
     * Sets the {@code PayRate} of the {@code Person} that we are building.
     */
    public PersonBuilder withPayRate(String payRate) {
        this.payRate = new PayRate(payRate);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, subject, day, begin, end, paid, payRate);
    }

}
