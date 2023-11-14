package seedu.address.model.mapping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalObjects.COMP1000;
import static seedu.address.testutil.TypicalObjects.COMP3000;
import static seedu.address.testutil.TypicalObjects.COMP4000;
import static seedu.address.testutil.TypicalObjects.CS1231S;
import static seedu.address.testutil.TypicalObjects.CS1231S_TO_EDINBURGH_COMP1000;
import static seedu.address.testutil.TypicalObjects.CS2030S;
import static seedu.address.testutil.TypicalObjects.CS2030S_TO_ZURICH_COMP3000;
import static seedu.address.testutil.TypicalObjects.CS2040S;
import static seedu.address.testutil.TypicalObjects.CS2040S_TO_NTU_S0402SC;
import static seedu.address.testutil.TypicalObjects.MA1521;
import static seedu.address.testutil.TypicalObjects.MA1521_TO_SNU_COMP4000;
import static seedu.address.testutil.TypicalObjects.S0402SC;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.university.UniversityName;

public class MappingComparatorByAttributeTest {

    private MappingComparatorByAttribute mappingComparatorByLocalCode =
            new MappingComparatorByAttribute(MappingAttribute.LOCALCODE);
    private MappingComparatorByAttribute mappingComparatorByLocalName =
            new MappingComparatorByAttribute(MappingAttribute.LOCALNAME);
    private MappingComparatorByAttribute mappingComparatorByPartnerCode =
            new MappingComparatorByAttribute(MappingAttribute.PARTNERCODE);
    private MappingComparatorByAttribute mappingComparatorByPartnerName =
            new MappingComparatorByAttribute(MappingAttribute.PARTNERNAME);
    private MappingComparatorByAttribute mappingComparatorByUniversity =
            new MappingComparatorByAttribute(MappingAttribute.UNIVERSITY);
    private MappingComparatorByAttribute mappingComparatorByInformation =
            new MappingComparatorByAttribute(MappingAttribute.INFORMATION);

    @Test
    public void compare_attributeIsLocalCode() {
        assertEquals(-1,
                mappingComparatorByLocalCode.compare(CS2030S_TO_ZURICH_COMP3000, CS2040S_TO_NTU_S0402SC));
        assertEquals(0,
                mappingComparatorByLocalCode.compare(CS2030S_TO_ZURICH_COMP3000, CS2030S_TO_ZURICH_COMP3000));
        assertEquals(1,
                mappingComparatorByLocalCode.compare(CS2040S_TO_NTU_S0402SC, CS2030S_TO_ZURICH_COMP3000));
    }

    @Test
    public void compare_attributeIsLocalName() {
        mappingComparatorByLocalName.initialiseGetLocalPartnerCourse(this::getLocalCourseIfExistsStub,
                this::getPartnerCourseIfExistsStub);
        assertEquals(-1,
                mappingComparatorByLocalName.compare(CS2040S_TO_NTU_S0402SC, CS1231S_TO_EDINBURGH_COMP1000));
        assertEquals(0,
                mappingComparatorByLocalName.compare(CS2040S_TO_NTU_S0402SC, CS2040S_TO_NTU_S0402SC));
        assertEquals(1,
                mappingComparatorByLocalName.compare(CS1231S_TO_EDINBURGH_COMP1000, CS2040S_TO_NTU_S0402SC));
    }

    @Test
    public void compare_attributeIsPartnerCode() {
        assertEquals(-1,
                mappingComparatorByPartnerCode.compare(CS1231S_TO_EDINBURGH_COMP1000, CS2040S_TO_NTU_S0402SC));
        assertEquals(0,
                mappingComparatorByPartnerCode.compare(CS1231S_TO_EDINBURGH_COMP1000, CS1231S_TO_EDINBURGH_COMP1000));
        assertEquals(1,
                mappingComparatorByPartnerCode.compare(CS2040S_TO_NTU_S0402SC, CS1231S_TO_EDINBURGH_COMP1000));
    }

    @Test
    public void compare_attributeIsPartnerName() {
        mappingComparatorByPartnerName.initialiseGetLocalPartnerCourse(this::getLocalCourseIfExistsStub,
                this::getPartnerCourseIfExistsStub);
        assertEquals(-1,
                mappingComparatorByPartnerName.compare(CS2040S_TO_NTU_S0402SC, CS1231S_TO_EDINBURGH_COMP1000));
        assertEquals(0,
                mappingComparatorByPartnerName.compare(CS1231S_TO_EDINBURGH_COMP1000, CS1231S_TO_EDINBURGH_COMP1000));
        assertEquals(1,
                mappingComparatorByPartnerName.compare(CS1231S_TO_EDINBURGH_COMP1000, CS2040S_TO_NTU_S0402SC));
    }

    @Test
    public void compare_attributeIsUniversity() {
        assertEquals(-1,
                mappingComparatorByUniversity.compare(CS2040S_TO_NTU_S0402SC, CS2030S_TO_ZURICH_COMP3000));
        assertEquals(0,
                mappingComparatorByUniversity.compare(CS1231S_TO_EDINBURGH_COMP1000, CS1231S_TO_EDINBURGH_COMP1000));
        assertEquals(1,
                mappingComparatorByUniversity.compare(CS2030S_TO_ZURICH_COMP3000, CS2040S_TO_NTU_S0402SC));
    }

    @Test
    public void compare_attributeIsInformation() {
        assertEquals(-1,
                mappingComparatorByUniversity.compare(MA1521_TO_SNU_COMP4000, CS2030S_TO_ZURICH_COMP3000));
        assertEquals(0,
                mappingComparatorByUniversity.compare(CS1231S_TO_EDINBURGH_COMP1000, CS1231S_TO_EDINBURGH_COMP1000));
        assertEquals(1,
                mappingComparatorByUniversity.compare(CS2030S_TO_ZURICH_COMP3000, MA1521_TO_SNU_COMP4000));
    }

    @Test
    public void equals() {
        // same values -> returns true
        assertTrue(mappingComparatorByLocalCode.equals(new MappingComparatorByAttribute(MappingAttribute.LOCALCODE)));

        // same object -> returns true
        assertTrue(mappingComparatorByUniversity.equals(mappingComparatorByUniversity));

        // null -> returns false
        assertFalse(mappingComparatorByInformation.equals(null));

        // different types -> returns false
        assertFalse(mappingComparatorByPartnerName.equals(1));

        // different values -> returns false
        assertFalse(mappingComparatorByLocalCode.equals(mappingComparatorByPartnerCode));
    }

    /**
     * This is a stub to return a LocalCourse Optional given a LocalCode used in the tests.
     */
    public Optional<LocalCourse> getLocalCourseIfExistsStub(LocalCode localCode) {
        switch (localCode.getValue()) {
        case "CS1231S":
            return Optional.of(CS1231S);
        case "CS2030S":
            return Optional.of(CS2030S);
        case "CS2040S":
            return Optional.of(CS2040S);
        case "MA1521":
            return Optional.of(MA1521);
        default:
            return Optional.empty();
        }

    }

    /**
     * This is a stub to return a PartnerCourse Optional given a LocalCode used in the tests.
     */
    public Optional<PartnerCourse> getPartnerCourseIfExistsStub(PartnerCode partnerCode,
                                                                UniversityName universityName) {
        PartnerCourse[] partnerCourses = {S0402SC, COMP3000, COMP1000, COMP4000};
        for (PartnerCourse partnerCourse : partnerCourses) {
            if (partnerCode.equals(partnerCourse.getPartnerCode())
                    && universityName.equals(partnerCourse.getPartnerUniversity().getUniversityName())) {
                return Optional.of(partnerCourse);
            }
        }
        return Optional.empty();
    }

}

