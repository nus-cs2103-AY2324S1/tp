package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import seedu.address.model.InternshipBook;
import seedu.address.model.internship.Internship;

public class InternshipSampleDataUtilTest {

    @Test
    public void getSampleInternships_returnsNonNullArray() {
        Internship[] sampleInternships = InternshipSampleDataUtil.getSampleInternships();
        assertNotNull(sampleInternships);
    }

    @Test
    public void getSampleInternshipBook_returnsNonNullInternshipBook() {
        InternshipBook sampleIb = (InternshipBook) InternshipSampleDataUtil.getSampleInternshipBook();
        assertNotNull(sampleIb);
    }
}
