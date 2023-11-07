package seedu.lovebook.model.date;

import static seedu.lovebook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.lovebook.commons.util.ToStringBuilder;
import seedu.lovebook.model.DatePrefs;
import seedu.lovebook.model.date.horoscope.Horoscope;

/**
 * Represents a Date in the lovebook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Date implements Comparable<Date> {

    // Identity fields
    private final Name name;
    private final Age age;
    private final Gender gender;

    // Data fields
    private final Height height;
    private final Income income;
    private final Horoscope horoscope;
    private final Star star;
    private final Avatar avatar;

    /**
     * Every field must be present and not null.
     */
    public Date(Name name, Age age, Gender gender, Height height, Income income, Horoscope horoscope) {
        requireAllNonNull(name, age, gender, height);
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.income = income;
        this.horoscope = horoscope;
        this.star = new Star("false");
        this.avatar = new Avatar();
    }

    /**
     * Constructor for setting the value of star
     */
    public Date(Name name, Age age, Gender gender, Height height, Income income, Horoscope horoscope, Star star) {
        requireAllNonNull(name, age, gender, height);
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.income = income;
        this.horoscope = horoscope;
        this.star = star;
        this.avatar = new Avatar();
    }

    /**
     * Constructor for setting the value of avatar without initialising star
     */
    public Date(Name name, Age age, Gender gender, Height height, Income income, Horoscope horoscope,
                Avatar avatar) {
        requireAllNonNull(name, age, gender, height);
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.income = income;
        this.horoscope = horoscope;
        this.star = new Star("false");
        this.avatar = avatar;
    }

    /**
     * Constructor for setting the value of avatar
     */
    public Date(Name name, Age age, Gender gender, Height height, Income income, Horoscope horoscope, Star star,
                Avatar avatar) {
        requireAllNonNull(name, age, gender, height);
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.income = income;
        this.horoscope = horoscope;
        this.star = star;
        this.avatar = avatar;
    }

    public Name getName() {
        return name;
    }

    public Age getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public Height getHeight() {
        return height;
    }

    public Income getIncome() {
        return income;
    }

    public Horoscope getHoroscope() {
        return horoscope;
    }

    public Star getStar() {
        return star;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    /**
     * Returns true if both dates have the same name.
     * This defines a weaker notion of equality between two dates.
     */
    public boolean isSamePerson(Date otherDate) {
        if (otherDate == this) {
            return true;
        }

        return otherDate != null
                && otherDate.getName().equals(getName());
    }

    /**
     * Returns the score of the date based on how well it mactches the user preferences.
     *
     * @param prefs User's date preferences
     * @return the score of the date based on how it matches user preferences.
     */
    public int getScore(DatePrefs prefs) {
        int score = 0;
        //height
        int heightDiff = Integer.parseInt(prefs.getHeight().value)
                - Integer.parseInt(this.height.value);
        if (heightDiff < 0) {
            score += Math.max(0, 10 + heightDiff);
        } else {
            score += Math.max(0, 10 - heightDiff);
        }
        //age
        int ageDiff = Integer.parseInt(prefs.getAge().value)
                - Integer.parseInt(this.age.value);
        if (ageDiff < 0) {
            score += Math.max(0, 10 + ageDiff * 2);
        } else {
            score += Math.max(0, 10 - ageDiff * 2);
        }
        //horoscope
        if (this.horoscope.equals(prefs.getHoroscope())) {
            score += 10;
        }
        //income
        int incomeDiff = Integer.parseInt(prefs.getIncome().value)
                - Integer.parseInt(this.income.value);
        if (incomeDiff < 0) {
            score += Math.max(0, 10 + incomeDiff / 250);
        } else {
            score += Math.max(0, 10 - incomeDiff / 250);
        }
        return score;
    }

    /**
     * Returns true if both dates have the same identity and data fields.
     * This defines a stronger notion of equality between two dates.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Date)) {
            return false;
        }

        Date otherDate = (Date) other;
        return name.equals(otherDate.name)
                && age.equals(otherDate.age)
                && gender.equals(otherDate.gender)
                && height.equals(otherDate.height)
                && income.equals(otherDate.income)
                && horoscope.equals(otherDate.horoscope);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing :your own
        return Objects.hash(name, age, gender, height, income, horoscope, avatar);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("age", age)
                .add("gender", gender)
                .add("height", height)
                .add("income", income)
                .add("horoscope", horoscope)
                .toString();
    }

    @Override
    public int compareTo(Date o) {
        return this.getName().toString().toLowerCase().compareTo(o.getName().toString().toLowerCase());
    }
}
