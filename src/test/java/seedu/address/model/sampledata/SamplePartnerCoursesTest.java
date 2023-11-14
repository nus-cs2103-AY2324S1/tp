package seedu.address.model.sampledata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.partnercourse.PartnerDescription;
import seedu.address.model.partnercourse.PartnerName;
import seedu.address.model.partnercourse.PartnerUnit;
import seedu.address.model.university.University;
import seedu.address.model.university.UniversityName;

public class SamplePartnerCoursesTest {
    @Test
    public void getSamplePartnerCourses_returnsCorrectNumberOfCourses() {
        PartnerCourse[] samplePartnerCourses = SamplePartnerCourses.getSamplePartnerCourses();
        assertEquals(413, samplePartnerCourses.length);
    }

    @Test
    public void getSamplePartnerCourses_containsCorrectData() {
        PartnerCourse[] samplePartnerCourses = SamplePartnerCourses.getSamplePartnerCourses();

        assertEquals("Arizona State University", samplePartnerCourses[0].getPartnerUniversity().toString());
        assertEquals("CSE469", samplePartnerCourses[0].getPartnerCode().toString());
        assertEquals("Computer and Network Forensics", samplePartnerCourses[0].getPartnerName().toString());
        assertEquals(3.0, samplePartnerCourses[0].getPartnerUnit().getValue());
        assertEquals("description", samplePartnerCourses[0].getPartnerDescription().toString());

        assertEquals("CSE494", samplePartnerCourses[1].getPartnerCode().toString());
        assertEquals("Introduction to Machine Learning", samplePartnerCourses[1].getPartnerName().toString());
        assertEquals(3.0, samplePartnerCourses[1].getPartnerUnit().getValue());
        assertEquals("description", samplePartnerCourses[1].getPartnerDescription().toString());
    }

    @Test
    public void samplePartnerCourse_constructorAndGetterMethods() {
        SamplePartnerCourses.SamplePartnerCourse sampleCourse = SamplePartnerCourses.SamplePartnerCourse.values()[0];

        PartnerCourse partnerCourse = new PartnerCourse(
            new University(new UniversityName(sampleCourse.getUniversityName())),
            new PartnerCode(sampleCourse.getCode()),
            new PartnerName(sampleCourse.getName()),
            new PartnerUnit(4.0),
            new PartnerDescription(sampleCourse.getDescription())
        );

        assertEquals(sampleCourse.getCode(), partnerCourse.getPartnerCode().toString());
        assertEquals(sampleCourse.getName(), partnerCourse.getPartnerName().toString());
        assertEquals(4.0, partnerCourse.getPartnerUnit().getValue());
        assertEquals(sampleCourse.getDescription(), partnerCourse.getPartnerDescription().toString());
    }

    @Test
    public void samplePartnerCourses_haveUniquePartnerCodes() {
        PartnerCourse[] samplePartnerCourses = SamplePartnerCourses.getSamplePartnerCourses();
        Set<String> courseCodes = Arrays.stream(samplePartnerCourses)
                                        .map(course -> course.getPartnerCode().toString())
                                        .collect(Collectors.toSet());
        assertEquals(samplePartnerCourses.length, courseCodes.size());
    }

    @Test
    public void samplePartnerCourses_haveValidPartnerCodes() {
        PartnerCourse[] samplePartnerCourses = SamplePartnerCourses.getSamplePartnerCourses();
        for (PartnerCourse course : samplePartnerCourses) {
            assertTrue(PartnerCode.isValidPartnerCode(course.getPartnerCode().toString()));
        }
    }

    @Test
    public void samplePartnerCourses_haveValidPartnerName() {
        PartnerCourse[] samplePartnerCourses = SamplePartnerCourses.getSamplePartnerCourses();
        for (PartnerCourse course : samplePartnerCourses) {
            assertTrue(PartnerName.isValidPartnerName(course.getPartnerName().toString()));
        }
    }

    @Test
    public void samplePartnerCourses_haveValidPartnerUnits() {
        PartnerCourse[] samplePartnerCourses = SamplePartnerCourses.getSamplePartnerCourses();
        for (PartnerCourse course : samplePartnerCourses) {
            assertTrue(PartnerUnit.isValidPartnerUnit(course.getPartnerUnit().toString()));
        }
    }

    @Test
    public void samplePartnerCourses_haveValidPartnerDescriptions() {
        PartnerCourse[] samplePartnerCourses = SamplePartnerCourses.getSamplePartnerCourses();
        for (PartnerCourse course : samplePartnerCourses) {
            assertTrue(PartnerDescription.isValidPartnerDescription(course.getPartnerDescription().toString()));
        }
    }
}
