package seedu.address.testutil;

import seedu.address.model.InternshipBook;
import seedu.address.model.internship.Internship;

/**
 * A utility class to help with building InternshipBook objects.
 * Example usage: <br>
 *     {@code InternshipBook ab = new InternshipBookBuilder().withInternship("John", "Doe").build();}
 */
public class InternshipBookBuilder {

    private InternshipBook internshipBook;

    public InternshipBookBuilder() {
        internshipBook = new InternshipBook();
    }

    public InternshipBookBuilder(InternshipBook internshipBook) {
        this.internshipBook = internshipBook;
    }

    /**
     * Adds a new {@code Internship} to the {@code InternshipBook} that we are building.
     */
    public InternshipBookBuilder withInternship(Internship internship) {
        internshipBook.createInternship(internship);
        return this;
    }

    public InternshipBook build() {
        return internshipBook;
    }
}
