package seedu.lovebook.testutil;

import seedu.lovebook.model.person.Age;
import seedu.lovebook.model.person.Date;
import seedu.lovebook.model.person.Gender;
import seedu.lovebook.model.person.Height;
import seedu.lovebook.model.person.Income;
import seedu.lovebook.model.person.Name;
import seedu.lovebook.model.person.horoscope.Horoscope;

/**
 * A utility class to help with building Date objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_AGE = "33";
    public static final String DEFAULT_GENDER = "F";
    public static final String DEFAULT_HEIGHT = "123";
    public static final String DEFAULT_INCOME = "3000";
    public static final String DEFAULT_HOROSCOPE = "TAURUS";

    private Name name;
    private Age age;
    private Gender gender;
    private Height height;
    private Income income;
    private Horoscope horoscope;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        age = new Age(DEFAULT_AGE);
        gender = new Gender(DEFAULT_GENDER);
        height = new Height(DEFAULT_HEIGHT);
        income = new Income(DEFAULT_INCOME);
        horoscope = new Horoscope(DEFAULT_HOROSCOPE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code dateToCopy}.
     */
    public PersonBuilder(Date dateToCopy) {
        name = dateToCopy.getName();
        age = dateToCopy.getAge();
        gender = dateToCopy.getGender();
        height = dateToCopy.getHeight();
        income = dateToCopy.getIncome();
        horoscope = dateToCopy.getHoroscope();
    }

    /**
     * Sets the {@code Name} of the {@code Date} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Height} of the {@code Date} that we are building.
     */
    public PersonBuilder withHeight(String height) {
        this.height = new Height(height);
        return this;
    }

    /**
     * Sets the {@code Age} of the {@code Date} that we are building.
     */
    public PersonBuilder withAge(String age) {
        this.age = new Age(age);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Date} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Income} of the {@code Date} that we are building.
     */
    public PersonBuilder withIncome(String income) {
        this.income = new Income(income);
        return this;
    }

    /**
     * Sets the {@code Horoscope} of the {@code Date} that we are building.
     */
    public PersonBuilder withHoroscope(String horoscope) {
        this.horoscope = new Horoscope(horoscope);
        return this;
    }

    public Date build() {
        return new Date(name, age, gender, height, income, horoscope);
    }

}
