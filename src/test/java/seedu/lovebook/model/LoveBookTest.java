package seedu.lovebook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_HEIGHT_BOB;
import static seedu.lovebook.testutil.Assert.assertThrows;
import static seedu.lovebook.testutil.TypicalPersons.ALICE;
import static seedu.lovebook.testutil.TypicalPersons.getTypicalLoveBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.lovebook.model.person.Date;
import seedu.lovebook.model.person.exceptions.DuplicatePersonException;
import seedu.lovebook.testutil.PersonBuilder;

public class LoveBookTest {

    private final LoveBook loveBook = new LoveBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), loveBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> loveBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyLoveBook_replacesData() {
        LoveBook newData = getTypicalLoveBook();
        loveBook.resetData(newData);
        assertEquals(newData, loveBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two dates with the same identity fields
        Date editedAlice = new PersonBuilder(ALICE).withHeight(VALID_HEIGHT_BOB)
                .build();
        List<Date> newDates = Arrays.asList(ALICE, editedAlice);
        LoveBookStub newData = new LoveBookStub(newDates);

        assertThrows(DuplicatePersonException.class, () -> loveBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> loveBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInLoveBook_returnsFalse() {
        assertFalse(loveBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInLoveBook_returnsTrue() {
        loveBook.addPerson(ALICE);
        assertTrue(loveBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInLoveBook_returnsTrue() {
        loveBook.addPerson(ALICE);
        Date editedAlice = new PersonBuilder(ALICE).withHeight(VALID_HEIGHT_BOB)
                .build();
        assertTrue(loveBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> loveBook.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = LoveBook.class.getCanonicalName() + "{dates=" + loveBook.getPersonList() + "}";
        assertEquals(expected, loveBook.toString());
    }

    /**
     * A stub ReadOnlyLoveBook whose dates list can violate interface constraints.
     */
    private static class LoveBookStub implements ReadOnlyLoveBook {
        private final ObservableList<Date> dates = FXCollections.observableArrayList();

        LoveBookStub(Collection<Date> dates) {
            this.dates.setAll(dates);
        }

        @Override
        public ObservableList<Date> getPersonList() {
            return dates;
        }
    }

}
