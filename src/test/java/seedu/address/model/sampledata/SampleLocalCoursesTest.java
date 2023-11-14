package seedu.address.model.sampledata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.localcourse.LocalDescription;
import seedu.address.model.localcourse.LocalName;
import seedu.address.model.localcourse.LocalUnit;


public class SampleLocalCoursesTest {
    @Test
    public void getSampleLocalCourses_returnsCorrectNumberOfCourses() {
        LocalCourse[] sampleLocalCourses = SampleLocalCourses.getSampleLocalCourses();
        assertEquals(165, sampleLocalCourses.length);
    }

    @Test
    public void getSampleLocalCourses_containsCorrectData() {
        LocalCourse[] sampleLocalCourses = SampleLocalCourses.getSampleLocalCourses();

        assertEquals("BT1101", sampleLocalCourses[0].getLocalCode().toString());
        assertEquals("Introduction to Business Analytics", sampleLocalCourses[0].getLocalName().toString());
        assertEquals(4.0, sampleLocalCourses[0].getLocalUnit().getValue());
        assertEquals(
            "This course provides students with an introduction to the fundamental concepts and tools needed to "
                + "understand the emerging role of business analytics and data science applications in business and "
                + "non-profit organisations. Students will learn how to apply basic business analytics and data "
                + "science/analytics tools (such as R) to large real-life datasets in different contexts, and how to "
                + "effectively use and interpret analytic models and results for making better and more well-informed "
                + "business decisions. This course will provide both the organisational and technical aspects of "
                + "business analytics and serves to provide students with a broad overview of how and why business "
                + "analytics can be implemented in organisations, the various approaches and techniques that could be"
                + " adopted for different organisational objectives and issues.",
            sampleLocalCourses[0].getLocalDescription().toString());

        assertEquals("BT2101", sampleLocalCourses[1].getLocalCode().toString());
        assertEquals("Econometrics Modeling for Business Analytics", sampleLocalCourses[1].getLocalName().toString());
        assertEquals(4.0, sampleLocalCourses[1].getLocalUnit().getValue());
        assertEquals("This course provides the foundations to econometrics and the use of software tools to perform "
                     + "econometrics data analysis. The course cover analysis models, including regression, time series"
                     + " regression, dynamic causal effects among others. Examples will also be drawn from various"
                     + " industry " + "domains and applications.",
                     sampleLocalCourses[1].getLocalDescription().toString());
    }

    @Test
    public void sampleLocalCourse_constructorAndGetterMethods() {
        SampleLocalCourses.SampleLocalCourse sampleCourse = SampleLocalCourses.SampleLocalCourse.values()[0];

        LocalCourse localCourse = new LocalCourse(new LocalCode(sampleCourse.getCode()),
                                                  new LocalName(sampleCourse.getName()),
                                                  new LocalUnit(4.0),
                                                  new LocalDescription(sampleCourse.getDescription()));

        assertEquals(sampleCourse.getCode(), localCourse.getLocalCode().toString());
        assertEquals(sampleCourse.getName(), localCourse.getLocalName().toString());
        assertEquals(4.0, localCourse.getLocalUnit().getValue());
        assertEquals(sampleCourse.getDescription(), localCourse.getLocalDescription().toString());
    }

    @Test
    public void sampleLocalCourses_haveUniqueLocalCodes() {
        LocalCourse[] sampleLocalCourses = SampleLocalCourses.getSampleLocalCourses();
        Set<String> courseCodes = Arrays.stream(sampleLocalCourses).map(course -> course.getLocalCode().toString())
                                        .collect(Collectors.toSet());
        assertEquals(sampleLocalCourses.length, courseCodes.size());
    }

    @Test
    public void sampleLocalCourses_haveValidLocalCodes() {
        LocalCourse[] sampleLocalCourses = SampleLocalCourses.getSampleLocalCourses();
        for (LocalCourse course : sampleLocalCourses) {
            assertTrue(LocalCode.isValidLocalCode(course.getLocalCode().toString()));
        }
    }

    @Test
    public void sampleLocalCourses_haveValidLocalName() {
        LocalCourse[] sampleLocalCourses = SampleLocalCourses.getSampleLocalCourses();
        for (LocalCourse course : sampleLocalCourses) {
            assertTrue(LocalName.isValidLocalName(course.getLocalName().toString()));
        }
    }

    @Test
    public void sampleLocalCourses_haveValidLocalUnits() {
        LocalCourse[] sampleLocalCourses = SampleLocalCourses.getSampleLocalCourses();
        for (LocalCourse course : sampleLocalCourses) {
            assertTrue(LocalUnit.isValidLocalUnit(course.getLocalUnit().toString()));
        }
    }

    @Test
    public void sampleLocalCourses_haveValidLocalDescriptions() {
        LocalCourse[] sampleLocalCourses = SampleLocalCourses.getSampleLocalCourses();
        for (LocalCourse course : sampleLocalCourses) {
            assertTrue(LocalDescription.isValidLocalDescription(course.getLocalDescription().toString()));
        }
    }
}
