package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICATIONSTATUS_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENT_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTDATE_JANESTREET;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.JANESTREET;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.exceptions.DuplicateInternshipException;
import seedu.address.testutil.InternshipBuilder;

public class InternshipBookTest {

    private final InternshipBook internshipBook = new InternshipBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), internshipBook.getInternshipList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internshipBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyInternshipBook_replacesData() {
        InternshipBook newData = getTypicalInternshipBook();
        internshipBook.resetData(newData);
        assertEquals(newData, internshipBook);
    }

    @Test
    public void resetData_withDuplicateInternships_throwsDuplicateInternshipException() {
        // Two Internships with the same identity fields
        Internship editedJaneStreet = new InternshipBuilder(JANESTREET).withDuration("4").build();
        List<Internship> newInternships = Arrays.asList(JANESTREET, editedJaneStreet);
        InternshipBookStub newData = new InternshipBookStub(newInternships);

        assertThrows(DuplicateInternshipException.class, () -> internshipBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internshipBook.hasInternship(null));
    }

    @Test
    public void hasPerson_personNotInInternshipBook_returnsFalse() {
        assertFalse(internshipBook.hasInternship(JANESTREET));
    }

    @Test
    public void hasPerson_personInInternshipBook_returnsTrue() {
        internshipBook.addInternship(JANESTREET);
        assertTrue(internshipBook.hasInternship(JANESTREET));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInInternshipBook_returnsTrue() {
        internshipBook.addInternship(JANESTREET);
        Internship editedJaneStreet = new InternshipBuilder(JANESTREET)
                .withApplicationStatus(VALID_APPLICATIONSTATUS_JANESTREET)
                .withDeadline(VALID_DEADLINE_JANESTREET, VALID_STARTDATE_JANESTREET)
                .withRequirements(VALID_REQUIREMENT_JANESTREET)
                .build();
        assertTrue(internshipBook.hasInternship(editedJaneStreet));
    }

    @Test
    public void getInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> internshipBook.getInternshipList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = InternshipBook.class.getCanonicalName()
                + "{internships=" + internshipBook.getInternshipList() + "}";
        assertEquals(expected, internshipBook.toString());
    }

    @Test
    public void equals_nullInternshipBook_returnsFalse() {
        assertFalse(internshipBook.equals(null));
    }

    /**
     * A stub ReadOnlyInternshipBook whose internships list can violate interface constraints.
     */
    private static class InternshipBookStub implements ReadOnlyInternshipBook {
        private final ObservableList<Internship> internships = FXCollections.observableArrayList();
        InternshipBookStub(Collection<Internship> internships) {
            this.internships.setAll(internships);
        }
        @Override
        public ObservableList<Internship> getInternshipList() {
            return internships;
        }
    }

}
