package seedu.lovebook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
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

    private final LoveBook LoveBook = new LoveBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), LoveBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> LoveBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyLoveBook_replacesData() {
        LoveBook newData = getTypicalLoveBook();
        LoveBook.resetData(newData);
        assertEquals(newData, LoveBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two dates with the same identity fields
        Date editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Date> newDates = Arrays.asList(ALICE, editedAlice);
        LoveBookStub newData = new LoveBookStub(newDates);

        assertThrows(DuplicatePersonException.class, () -> LoveBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> LoveBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInLoveBook_returnsFalse() {
        assertFalse(LoveBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInLoveBook_returnsTrue() {
        LoveBook.addPerson(ALICE);
        assertTrue(LoveBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInLoveBook_returnsTrue() {
        LoveBook.addPerson(ALICE);
        Date editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(LoveBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> LoveBook.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = LoveBook.class.getCanonicalName() + "{dates=" + LoveBook.getPersonList() + "}";
        assertEquals(expected, LoveBook.toString());
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
