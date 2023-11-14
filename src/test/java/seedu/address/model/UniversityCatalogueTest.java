package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.NTU;
import static seedu.address.testutil.TypicalObjects.getTypicalUniversityCatalogue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.university.University;
import seedu.address.model.university.exceptions.DuplicateUniversityException;
import seedu.address.testutil.UniversityBuilder;

public class UniversityCatalogueTest {
    private final UniversityCatalogue universityCatalogue = new UniversityCatalogue();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), universityCatalogue.getUniversityList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> universityCatalogue.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyUniversityCatalogue_replacesData() {
        UniversityCatalogue newData = getTypicalUniversityCatalogue();
        universityCatalogue.resetData(newData);
        assertEquals(newData, universityCatalogue);
    }

    @Test
    public void resetData_withDuplicateUniversity_throwsDuplicateUniversityException() {
        University editedNtu =
                new UniversityBuilder(NTU).build();
        List<University> newUniversities = Arrays.asList(NTU, editedNtu);
        UniversityCatalogueTest.UniversityCatalogueStub newData =
                new UniversityCatalogueTest.UniversityCatalogueStub(newUniversities);

        assertThrows(DuplicateUniversityException.class, () -> universityCatalogue.resetData(newData));
    }
    @Test
    public void hasUniversity_nullUniversity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> universityCatalogue.hasUniversity(null));
    }

    @Test
    public void hasUniversity_universityNotInUniversityCatalogue_returnsFalse() {
        assertFalse(universityCatalogue.hasUniversity(NTU));
    }

    @Test
    public void hasUniversity_universityInUniversityCatalogue_returnsTrue() {
        universityCatalogue.addUniversity(NTU);
        assertTrue(universityCatalogue.hasUniversity(NTU));
    }

    @Test
    public void toStringMethod() {
        String expected = UniversityCatalogue.class.getCanonicalName() + "{university="
                + universityCatalogue.getUniversityList() + "}";
        assertEquals(expected, universityCatalogue.toString());
    }

    /**
     * A stub ReadOnlyUniversityCatalogue whose university list can violate interface constraints.
     */
    private static class UniversityCatalogueStub implements ReadOnlyUniversityCatalogue {
        private final ObservableList<University> universities = FXCollections.observableArrayList();

        UniversityCatalogueStub(Collection<University> universityCollection) {
            universities.setAll(universityCollection);
        }

        @Override
        public ObservableList<University> getUniversityList() {
            return universities;
        }
    }
}
