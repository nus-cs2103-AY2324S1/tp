package seedu.address.model.sampledata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.mapping.Mapping;
import seedu.address.model.mapping.MappingMiscInformation;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.university.UniversityName;

public class SampleMappingsTest {
    @Test
    public void getSampleMappings_returnsCorrectNumberOfMappings() {
        Mapping[] sampleMappings = SampleMappings.getSampleMappings();
        assertEquals(413, sampleMappings.length);
    }

    @Test
    public void getSampleMappings_containsCorrectData() {
        Mapping[] sampleMappings = SampleMappings.getSampleMappings();

        assertEquals("IFS4102", sampleMappings[0].getLocalCode().toString());
        assertEquals("CSE469", sampleMappings[0].getPartnerCode().toString());
        assertEquals("Arizona State University", sampleMappings[0].getUniversityName().toString());
        assertEquals("Semester 1", sampleMappings[0].getMappingMiscInformation().toString());

        assertEquals("CS3244", sampleMappings[1].getLocalCode().toString());
        assertEquals("CSE494", sampleMappings[1].getPartnerCode().toString());
        assertEquals("Arizona State University", sampleMappings[1].getUniversityName().toString());
        assertEquals("Semester 2", sampleMappings[1].getMappingMiscInformation().toString());

        assertEquals("CS3218", sampleMappings[2].getLocalCode().toString());
        assertEquals("CS461", sampleMappings[2].getPartnerCode().toString());
        assertEquals("Bilkent University", sampleMappings[2].getUniversityName().toString());
        assertEquals("Semester 1 & Semester 2", sampleMappings[2].getMappingMiscInformation().toString());
    }

    @Test
    public void sampleMappings_constructorAndGetterMethods() {
        SampleMappings.SampleMapping sampleMapping = SampleMappings.SampleMapping.values()[0];

        Mapping mapping = new Mapping(
            new LocalCode(sampleMapping.getLocalCode()),
            new UniversityName(sampleMapping.getUniversityName()),
            new PartnerCode(sampleMapping.getPartnerCode()),
            new MappingMiscInformation(sampleMapping.getInformation())
        );

        assertEquals(sampleMapping.getLocalCode(), mapping.getLocalCode().toString());
        assertEquals(sampleMapping.getUniversityName(), mapping.getUniversityName().toString());
        assertEquals(sampleMapping.getPartnerCode(), mapping.getPartnerCode().toString());
        assertEquals(sampleMapping.getInformation(), mapping.getMappingMiscInformation().toString());
    }

    @Test
    public void sampleMappings_haveValidLocalCodes() {
        Mapping[] sampleMappings = SampleMappings.getSampleMappings();
        for (Mapping mapping : sampleMappings) {
            assertTrue(LocalCode.isValidLocalCode(mapping.getLocalCode().toString()));
        }
    }

    @Test
    public void sampleMappings_haveValidPartnerCodes() {
        Mapping[] sampleMappings = SampleMappings.getSampleMappings();
        for (Mapping mapping : sampleMappings) {
            assertTrue(PartnerCode.isValidPartnerCode(mapping.getPartnerCode().toString()));
        }
    }

    @Test
    public void sampleMappings_haveValidUniversityNames() {
        Mapping[] sampleMappings = SampleMappings.getSampleMappings();
        for (Mapping mapping : sampleMappings) {
            assertTrue(UniversityName.isValidUniversityName(mapping.getUniversityName().toString()));
        }
    }

    @Test
    public void sampleMappings_haveValidMappingMiscInformation() {
        Mapping[] sampleMappings = SampleMappings.getSampleMappings();
        for (Mapping mapping : sampleMappings) {
            assertTrue(
                MappingMiscInformation.isValidMappingMiscInformation(mapping.getMappingMiscInformation().toString())
            );
        }
    }
}
