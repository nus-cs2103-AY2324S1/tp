package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.INTERACTION_LIST_ONE;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void getFilteredPersonList() {
        assertThrows(NullPointerException.class, () -> ALICE.getFilteredInteractions(null));
        assertEquals(1, BENSON.getFilteredInteractions(x -> true).size());
    }

    @Test
    public void getFilteredInteractions_filterAfterDate() {
        //create a predicate that returns dates after 1/10/2023, and one after 1/11/2023
        Predicate<Interaction> predicateAfterEarlierDate = new Predicate<Interaction>() {
            @Override
            public boolean test(Interaction interaction) {
                return interaction.getDate().isAfter(LocalDate.of(2023, 10, 1));
            }
        };

        Predicate<Interaction> predicateAfterLaterDate = new Predicate<Interaction>() {
            @Override
            public boolean test(Interaction interaction) {
                return interaction.getDate().isAfter(LocalDate.of(2023, 11, 1));
            }
        };
        assertEquals(1, BENSON.getFilteredInteractions(predicateAfterEarlierDate).size());
        assertEquals(0, BENSON.getFilteredInteractions(predicateAfterLaterDate).size());
    }

    @Test
    public void isUncontacted() {
        assertEquals(true, ALICE.isUncontacted());
        assertEquals(false, BENSON.isUncontacted());
        assertEquals(false, ELLE.isUncontacted());
    }

    @Test
    public void isClosed() {
        assertEquals(false, ALICE.isClosed());
        assertEquals(false, BENSON.isClosed());
        assertEquals(true, ELLE.isClosed());
    }

    @Test
    public void isContacting() {
        assertEquals(false, ALICE.isContacting());
        assertEquals(true, BENSON.isContacting());
        assertEquals(false, ELLE.isContacting());
    }

    @Test
    public void leadBooleans() {
        assertEquals(true, ALICE.isColdLead());
        assertEquals(false, BENSON.isColdLead());
        assertEquals(false, ELLE.isColdLead());

        assertEquals(false, ALICE.isWarmLead());
        assertEquals(true, BENSON.isWarmLead());
        assertEquals(false, ELLE.isWarmLead());

        assertEquals(false, ALICE.isHotLead());
        assertEquals(false, BENSON.isHotLead());
        assertEquals(true, ELLE.isHotLead());
    }

    @Test
    public void testAddInteractions() {
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertEquals(0, aliceCopy.getInteractions().size());
        List<Interaction> result = aliceCopy.addInteractions(INTERACTION_LIST_ONE);
        assertEquals(result, aliceCopy.getInteractions());
        assertEquals(1, aliceCopy.getInteractions().size());
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + BENSON.getName() + ", phone=" + BENSON.getPhone()
                + ", email=" + BENSON.getEmail() + ", address=" + BENSON.getAddress() + ", tags=" + BENSON.getTags()
                + ", lead=" + BENSON.getLead() + ", telegram=" + BENSON.getTelegram() + ", profession="
                + BENSON.getProfession() + ", income=" + BENSON.getIncome() + ", details=" + BENSON.getDetails() + "}";
        assertEquals(expected, BENSON.toString());
    }
}
