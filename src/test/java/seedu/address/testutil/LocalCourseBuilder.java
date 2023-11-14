package seedu.address.testutil;

import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.localcourse.LocalDescription;
import seedu.address.model.localcourse.LocalName;
import seedu.address.model.localcourse.LocalUnit;

/**
 * A utility class to help with building LocalCourse objects.
 */
public class LocalCourseBuilder {

    public static final String DEFAULT_LOCAL_CODE = "CS2103T";
    public static final String DEFAULT_LOCAL_NAME = "Software Engineering";
    public static final Double DEFAULT_LOCAL_UNIT = 4.0;
    public static final String DEFAULT_LOCAL_DESCRIPTION = "This course introduces the necessary conceptual and "
        + "analytical tools for systematic and rigorous development of software systems. It covers four main areas of"
        + " software development, namely object-oriented system analysis, object-oriented system modelling and "
        + "design, implementation, and testing, with emphasis on system modelling and design and implementation of "
        + "software courses that work cooperatively to fulfill the requirements of the system. Tools and techniques "
        + "for software development, such as Unified Modelling Language (UML), program specification, and testing "
        + "methods, will be taught. Major software engineering issues such as modularisation criteria, program "
        + "correctness, and software quality will also be covered.";

    private LocalCode localCode;
    private LocalName localName;
    private LocalUnit localUnit;
    private LocalDescription localDescription;

    /**
     * Creates a {@code LocalCourseBuilder} with the default details.
     */
    public LocalCourseBuilder() {
        localCode = new LocalCode(DEFAULT_LOCAL_CODE);
        localName = new LocalName(DEFAULT_LOCAL_NAME);
        localUnit = new LocalUnit(DEFAULT_LOCAL_UNIT);
        localDescription = new LocalDescription(DEFAULT_LOCAL_DESCRIPTION);
    }

    /**
     * Initializes the LocalCourseBuilder with the data of {@code localCourseToCopy}.
     */
    public LocalCourseBuilder(LocalCourse localCourseToCopy) {
        localCode = localCourseToCopy.getLocalCode();
        localName = localCourseToCopy.getLocalName();
        localUnit = localCourseToCopy.getLocalUnit();
        localDescription = localCourseToCopy.getLocalDescription();
    }

    /**
     * Sets the {@code LocalCode} of the {@code LocalCourse} that we are building.
     */
    public LocalCourseBuilder withLocalCode(String localCode) {
        this.localCode = new LocalCode(localCode);
        return this;
    }

    /**
     * Sets the {@code LocalName} of the {@code LocalCourse} that we are building.
     */
    public LocalCourseBuilder withLocalName(String localName) {
        this.localName = new LocalName(localName);
        return this;
    }

    /**
     * Sets the {@code LocalName} of the {@code LocalCourse} that we are building.
     */
    public LocalCourseBuilder withLocalUnit(Double localUnit) {
        this.localUnit = new LocalUnit(localUnit);
        return this;
    }

    /**
     * Sets the {@code LocalDescription} of the {@code LocalCourse} that we are building.
     */
    public LocalCourseBuilder withLocalDescription(String localDescription) {
        this.localDescription = new LocalDescription(localDescription);
        return this;
    }

    public LocalCourse build() {
        return new LocalCourse(localCode, localName, localUnit, localDescription);
    }

}
