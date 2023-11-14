package seedu.address.testutil;

import seedu.address.model.UniversityCatalogue;
import seedu.address.model.university.University;

/**
 * A utility class to help with building UniversityCatalogue objects.
 * Example usage: <br>
 * {@code UniversityCatalogue uc = new UniversityCatalogueBuilder().withUniversity(University).build();}
 */
public class UniversityCatalogueBuilder {

    private UniversityCatalogue universityCatalogue;

    public UniversityCatalogueBuilder() {
        universityCatalogue = new UniversityCatalogue();
    }

    public UniversityCatalogueBuilder(UniversityCatalogue uc) {
        universityCatalogue = uc;
    }

    /**
     * Adds a new {@code University} to the {@code UniversityCatalogue} that we are building.
     */
    public UniversityCatalogueBuilder withUniversity(University university) {
        universityCatalogue.addUniversity(university);
        return this;
    }

    public UniversityCatalogue build() {
        return universityCatalogue;
    }
}
