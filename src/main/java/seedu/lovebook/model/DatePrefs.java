package seedu.lovebook.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.lovebook.commons.util.ToStringBuilder;
import seedu.lovebook.model.date.Age;
import seedu.lovebook.model.date.Height;
import seedu.lovebook.model.date.Income;
import seedu.lovebook.model.date.horoscope.Horoscope;

/**
 * Represents the preferences of the user for a date.
 */
public class DatePrefs implements ReadOnlyDatePrefs {
    private Age age;
    private Height height;
    private Income income;
    private Horoscope horoscope;

    /**
     * Creates a {@code DatePrefs} with default values.
     */
    public DatePrefs() {
        this.age = new Age("21");
        this.height = new Height("170");
        this.income = new Income("10000");
        this.horoscope = new Horoscope("ARIES");
    }

    /**
     * Creates a {@code DatePrefs} with the given values.
     */
    public DatePrefs(Age age, Height height, Income income, Horoscope horoscope) {
        this.age = age;
        this.height = height;
        this.income = income;
        this.horoscope = horoscope;
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

        setPreferences(newData.getPreferences().get(0));
    }

    /**
     * Replaces the contents of the date list with {@code dates}.
     * {@code dates} must not contain duplicate dates.
     */
    public void setPreferences(DatePrefs prefs) {
        this.age = prefs.age;
        this.height = prefs.height;
        this.income = prefs.income;
        this.horoscope = prefs.horoscope;
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
        return this.age.equals(otherPrefs.age)
                && this.height.equals(otherPrefs.height)
                && this.income.equals(otherPrefs.income)
                && this.horoscope.equals(otherPrefs.horoscope);
    }

    public Age getAge() {
        return age;
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

    @Override
    public int hashCode() {
        return Objects.hash(this.age, this.height, this.income, this.horoscope);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("age", age)
                .add("height", height)
                .add("income", income)
                .add("horoscope", horoscope)
                .toString();
    }

    @Override
    public List<DatePrefs> getPreferences() {
        List<DatePrefs> prefs = new ArrayList<>();
        prefs.add(this);
        return prefs;
    }
}
