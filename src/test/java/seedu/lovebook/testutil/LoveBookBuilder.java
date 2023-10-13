package seedu.lovebook.testutil;

import seedu.lovebook.model.LoveBook;
import seedu.lovebook.model.person.Date;

/**
 * A utility class to help with building LoveBook objects.
 * Example usage: <br>
 *     {@code LoveBook ab = new LoveBookBuilder().withPerson("John", "Doe").build();}
 */
public class LoveBookBuilder {

    private LoveBook loveBook;

    public LoveBookBuilder() {
        loveBook = new LoveBook();
    }

    public LoveBookBuilder(LoveBook loveBook) {
        this.loveBook = loveBook;
    }

    /**
     * Adds a new {@code Date} to the {@code LoveBook} that we are building.
     */
    public LoveBookBuilder withPerson(Date date) {
        loveBook.addPerson(date);
        return this;
    }

    public LoveBook build() {
        return loveBook;
    }
}
