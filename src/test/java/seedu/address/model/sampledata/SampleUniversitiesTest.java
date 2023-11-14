package seedu.address.model.sampledata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.university.University;
import seedu.address.model.university.UniversityName;

public class SampleUniversitiesTest {

    @Test
    public void getSampleUniversities_returnsCorrectNumberOfUniversities() {
        University[] sampleUniversities = SampleUniversities.getSampleUniversities();
        assertEquals(198, sampleUniversities.length);
    }

    @Test
    public void getSampleUniversities_containsCorrectData() {
        University[] sampleUniversities = SampleUniversities.getSampleUniversities();

        assertEquals(
            "The Hong Kong Polytechnic University",
            sampleUniversities[0].getUniversityName().toString());
        assertEquals(
            "Hong Kong University of Science & Technology",
            sampleUniversities[1].getUniversityName().toString());
    }

    @Test
    public void sampleUniversity_constructorAndGetterMethods() {
        SampleUniversities.SampleUniversityName sampleUniversity = SampleUniversities.SampleUniversityName.values()[0];
        University university = new University(new UniversityName(sampleUniversity.toValue()));
        assertEquals(sampleUniversity.toValue(), university.getUniversityName().toString());
    }

    @Test
    public void sampleUniversities_haveUniqueNames() {
        University[] sampleUniversities = SampleUniversities.getSampleUniversities();
        Set<String> universityNames = Arrays.stream(sampleUniversities)
                                            .map(university -> university.getUniversityName().toString())
                                            .collect(Collectors.toSet());
        assertEquals(sampleUniversities.length, universityNames.size());
    }

    @Test
    public void sampleUniversities_haveValidUniversityNames() {
        University[] sampleUniversities = SampleUniversities.getSampleUniversities();
        for (University university : sampleUniversities) {
            assertTrue(UniversityName.isValidUniversityName(university.getUniversityName().toString()));
        }
    }
}
