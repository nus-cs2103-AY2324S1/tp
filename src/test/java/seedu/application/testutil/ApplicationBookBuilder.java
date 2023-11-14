package seedu.application.testutil;

import seedu.application.model.ApplicationBook;
import seedu.application.model.job.Job;

/**
 * A utility class to help with building ApplicationBook objects.
 * Example usage: <br>
 *     {@code ApplicationBook ab = new ApplicationBookBuilder().withJob("Software Engineer", "Google").build();}
 */
public class ApplicationBookBuilder {

    private final ApplicationBook applicationBook;

    public ApplicationBookBuilder() {
        applicationBook = new ApplicationBook();
    }

    public ApplicationBookBuilder(ApplicationBook applicationBook) {
        this.applicationBook = applicationBook;
    }

    /**
     * Adds a new {@code Person} to the {@code ApplicationBook} that we are building.
     */
    public ApplicationBookBuilder withJob(Job job) {
        applicationBook.addJob(job);
        return this;
    }

    public ApplicationBook build() {
        return applicationBook;
    }
}
