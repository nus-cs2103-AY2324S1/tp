package seedu.address.testutil;

import seedu.address.model.PartnerCourseCatalogue;
import seedu.address.model.partnercourse.PartnerCourse;

/**
 * A utility class to help with building PartnerCourseCatalogue objects.
 * Example usage: <br>
 * {@code PartnerCourseCatalogue pcc = new PartnerCourseCatalogueBuilder().withPartnerCourse(PartnerCourse).build();}
 */
public class PartnerCourseCatalogueBuilder {

    private PartnerCourseCatalogue partnerCourseCatalogue;

    public PartnerCourseCatalogueBuilder() {
        partnerCourseCatalogue = new PartnerCourseCatalogue();
    }

    public PartnerCourseCatalogueBuilder(PartnerCourseCatalogue pcc) {
        partnerCourseCatalogue = pcc;
    }

    /**
     * Adds a new {@code PartnerCourse} to the {@code PartnerCourseCatalogue} that we are building.
     */
    public PartnerCourseCatalogueBuilder withPartnerCourse(PartnerCourse partnerCourse) {
        partnerCourseCatalogue.addPartnerCourse(partnerCourse);
        return this;
    }

    public PartnerCourseCatalogue build() {
        return partnerCourseCatalogue;
    }
}
