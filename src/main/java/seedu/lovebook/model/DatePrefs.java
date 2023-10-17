package seedu.lovebook.model;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.lovebook.commons.util.ToStringBuilder;
import seedu.lovebook.model.person.Age;
import seedu.lovebook.model.person.Gender;
import seedu.lovebook.model.person.Height;
import seedu.lovebook.model.person.Income;

/**
 * Represents the preferences of the user for a date.
 */
public class DatePrefs implements ReadOnlyDatePrefs {
    private Age age;
    private Gender gender;
    private Height height;
    private Income income;

    /**
     * Creates a {@code DatePrefs} with default values.
     */
    public DatePrefs() {
        this.age = new Age("21");
        this.gender = new Gender("F");
        this.height = new Height("170");
        this.income = new Income("10000");
    }

    /**
     * Creates a {@code DatePrefs} with the given values.
     */
    public DatePrefs(Age age, Gender gender, Height height, Income income) {
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.income = income;
    }

    /**
     * Creates a {@code DatePrefs} with the given values.
     */
    public DatePrefs(ReadOnlyDatePrefs toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code LoveBook} with {@code newData}.
     */
    public void resetData(ReadOnlyDatePrefs newData) {
        requireNonNull(newData);

        setPreferences(newData.getPreferences());
    }

    /**
     * Replaces the contents of the date list with {@code dates}.
     * {@code dates} must not contain duplicate dates.
     */
    public void setPreferences(DatePrefs prefs) {
        this.age = prefs.age;
        this.gender = prefs.gender;
        this.height = prefs.height;
        this.income = prefs.income;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DatePrefs)) {
            return false;
        }

        DatePrefs otherPrefs = (DatePrefs) other;
        return this.age == otherPrefs.age
                && this.height == otherPrefs.height
                && this.gender == otherPrefs.gender
                && this.income == otherPrefs.income;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.age, this.gender, this.height, this.income);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("age", age)
                .add("gender", gender)
                .add("height", height)
                .add("income", income)
                .toString();
    }

    @Override
    public DatePrefs getPreferences() {
        return this;
    }
}
