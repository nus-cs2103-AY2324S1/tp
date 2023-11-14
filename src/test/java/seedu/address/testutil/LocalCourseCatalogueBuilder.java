package seedu.address.testutil;

import seedu.address.model.LocalCourseCatalogue;
import seedu.address.model.localcourse.LocalCourse;

/**
 * A utility class to help with building LocalCourseCatalogue objects.
 * Example usage: <br>
 * {@code LocalCourseCatalogue lcc = new LocalCourseCatalogueBuilder().withLocalCourse(LocalCourse).build();}
 */
public class LocalCourseCatalogueBuilder {

    private LocalCourseCatalogue localCourseCatalogue;

    public LocalCourseCatalogueBuilder() {
        localCourseCatalogue = new LocalCourseCatalogue();
    }

    public LocalCourseCatalogueBuilder(LocalCourseCatalogue lcc) {
        localCourseCatalogue = lcc;
    }

    /**
     * Adds a new {@code LocalCourse} to the {@code LocalCourseCatalogue} that we are building.
     */
    public LocalCourseCatalogueBuilder withLocalCourse(LocalCourse localCourse) {
        localCourseCatalogue.addLocalCourse(localCourse);
        return this;
    }

    public LocalCourseCatalogue build() {
        return localCourseCatalogue;
    }
}
