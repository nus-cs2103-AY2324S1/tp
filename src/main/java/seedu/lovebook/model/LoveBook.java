package seedu.lovebook.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.lovebook.commons.util.ToStringBuilder;
import seedu.lovebook.model.date.Date;
import seedu.lovebook.model.date.UniqueDateList;

/**
 * Wraps all data at the lovebook-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class LoveBook implements ReadOnlyLoveBook {

    private final UniqueDateList dates;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        dates = new UniqueDateList();
    }

    public LoveBook() {}

    /**
     * Creates an LoveBook using the Persons in the {@code toBeCopied}
     */
    public LoveBook(ReadOnlyLoveBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the date list with {@code dates}.
     * {@code dates} must not contain duplicate dates.
     */
    public void setPersons(List<Date> dates) {
        this.dates.setPersons(dates);
    }

    /**
     * Resets the existing data of this {@code LoveBook} with {@code newData}.
     */
    public void resetData(ReadOnlyLoveBook newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
    }

    //// date-level operations

    /**
     * Returns true if a date with the same identity as {@code date} exists in the LoveBook.
     */
    public boolean hasDate(Date date) {
        requireNonNull(date);
        return dates.contains(date);
    }

    /**
     * Adds a date to the LoveBook.
     * The date must not already exist in the LoveBook.
     */
    public void addDate(Date p) {
        dates.add(p);
    }

    /**
     * Replaces the given date {@code target} in the list with {@code editedDate}.
     * {@code target} must exist in the LoveBook.
     * The date identity of {@code editedDate} must not be the same as another existing date in the LoveBook.
     */
    public void setDate(Date target, Date editedDate) {
        requireNonNull(editedDate);
        dates.setDate(target, editedDate);
    }

    /**
     * Removes {@code key} from this {@code LoveBook}.
     * {@code key} must exist in the LoveBook.
     */
    public void removePerson(Date key) {
        dates.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("dates", dates)
                .toString();
    }

    @Override
    public ObservableList<Date> getPersonList() {
        return dates.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LoveBook)) {
            return false;
        }

        LoveBook otherLoveBook = (LoveBook) other;
        return dates.equals(otherLoveBook.dates);
    }

    @Override
    public int hashCode() {
        return dates.hashCode();
    }

}
