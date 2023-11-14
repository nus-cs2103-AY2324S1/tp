package seedu.address.testutil;

import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.partnercourse.PartnerDescription;
import seedu.address.model.partnercourse.PartnerName;
import seedu.address.model.partnercourse.PartnerUnit;
import seedu.address.model.university.University;
import seedu.address.model.university.UniversityName;

/**
 * A utility class to help with building PartnerCourse objects.
 */
public class PartnerCourseBuilder {

    public static final String DEFAULT_PARTNER_CODE = "COMP5330";
    public static final String DEFAULT_PARTNER_NAME = "Database Systems for Advanced Machine Learning";
    public static final Double DEFAULT_PARTNER_UNIT = 5.0;
    public static final UniversityName DEFAULT_PARTNER_UNIVERSITY = new UniversityName("University of Waterloo");
    public static final String DEFAULT_PARTNER_DESCRIPTION = "Fun database module.";

    private University partnerUniversity;
    private PartnerCode partnerCode;
    private PartnerName partnerName;
    private PartnerUnit partnerUnit;
    private PartnerDescription partnerDescription;
    /**
     * Creates a {@code PartnerCourseBuilder} with the default details.
     */
    public PartnerCourseBuilder() {
        partnerUniversity = new University(DEFAULT_PARTNER_UNIVERSITY);
        partnerCode = new PartnerCode(DEFAULT_PARTNER_CODE);
        partnerName = new PartnerName(DEFAULT_PARTNER_NAME);
        partnerUnit = new PartnerUnit(DEFAULT_PARTNER_UNIT);
        partnerDescription = new PartnerDescription(DEFAULT_PARTNER_DESCRIPTION);
    }

    /**
     * Initializes the PartnerCourseBuilder with the data of {@code partnerCourseToCopy}.
     */
    public PartnerCourseBuilder(PartnerCourse partnerCourseToCopy) {
        partnerUniversity = partnerCourseToCopy.getPartnerUniversity();
        partnerCode = partnerCourseToCopy.getPartnerCode();
        partnerName = partnerCourseToCopy.getPartnerName();
        partnerUnit = partnerCourseToCopy.getPartnerUnit();
        partnerDescription = partnerCourseToCopy.getPartnerDescription();
    }

    /**
     * Sets the {@code PartnerCode} of the {@code PartnerCourse} that we are building.
     */
    public PartnerCourseBuilder withPartnerCode(String partnerCode) {
        this.partnerCode = new PartnerCode(partnerCode);
        return this;
    }

    /**
     * Sets the {@code PartnerName} of the {@code PartnerCourse} that we are building.
     */
    public PartnerCourseBuilder withPartnerName(String partnerName) {
        this.partnerName = new PartnerName(partnerName);
        return this;
    }

    /**
     * Sets the {@code PartnerUniversity} of the {@code PartnerCourse} that we are building.
     */
    public PartnerCourseBuilder withPartnerUniversity(UniversityName partnerUniversity) {
        this.partnerUniversity = new University(partnerUniversity);
        return this;
    }

    /**
     * Sets the {@code PartnerUniversity} of the {@code PartnerCourse} that we are building.
     */
    public PartnerCourseBuilder withPartnerUnit(Double partnerUnit) {
        this.partnerUnit = new PartnerUnit(partnerUnit);
        return this;
    }

    /**
     * Sets the {@code PartnerDescription} of the {@code PartnerCourse} that we are building.
     */
    public PartnerCourseBuilder withPartnerDescription(String partnerDescription) {
        this.partnerDescription = new PartnerDescription(partnerDescription);
        return this;
    }

    public PartnerCourse build() {
        return new PartnerCourse(partnerUniversity, partnerCode, partnerName, partnerUnit, partnerDescription);
    }
}
