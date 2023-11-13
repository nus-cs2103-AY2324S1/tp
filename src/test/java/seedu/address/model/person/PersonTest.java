package seedu.address.model.person;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANIMAL_TYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AVAILABILITY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOUSING_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.HashMap;
import java.util.Map;

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

        // name differs in case, all other attributes same -> returns TRUE (after improving duplicate check)
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // name has spaces between words, all other attributes same -> returns TRUE (after improving duplicate check)
        String nameWithSpacesInbetween = "Bob" + "    " + "Choo";
        editedBob = new PersonBuilder(BOB).withName(nameWithSpacesInbetween).build();
        assertTrue(BOB.isSamePerson(editedBob));

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

        // different animal name -> returns false
        editedAlice = new PersonBuilder(BOB).withAnimalName("Snow").build();
        assertFalse(ALICE.equals(editedAlice));

        // different availability -> returns false
        editedAlice = new PersonBuilder(ALICE).withAvailability(VALID_AVAILABILITY_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different animalType -> returns false
        editedAlice = new PersonBuilder(ALICE).withAnimalType(VALID_ANIMAL_TYPE_BOB,
                new Availability(VALID_AVAILABILITY_BOB)).build();
        assertFalse(ALICE.equals(editedAlice));

        // different housing -> returns false
        editedAlice = new PersonBuilder(ALICE).withHousing(VALID_HOUSING_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

    }

    @Test
    public void isAvailabilityValidWhenAnimalNameNotNil_validCases() {
        // not available, animal name not nil
        Person firstPerson = new PersonBuilder(ALICE)
                .withAnimalType("current.Dog", Availability.NOT_AVAILABLE)
                .withAnimalName("Dexter")
                .withAvailability("NotAvailable")
                .build();
        assertTrue(firstPerson.isAvailabilityValidWhenAnimalNameNotNil());

        // available, animal name nil
        Person secondPerson = new PersonBuilder(ALICE)
                .withAnimalName("nil")
                .withAvailability("Available")
                .build();
        assertTrue(secondPerson.isAvailabilityValidWhenAnimalNameNotNil());

        // not available, animal name nil
        Person thirdPerson = new PersonBuilder(ALICE)
                .withAnimalType("nil", Availability.NOT_AVAILABLE)
                .withAnimalName("nil")
                .withAvailability("NotAvailable")
                .build();
        assertTrue(thirdPerson.isAvailabilityValidWhenAnimalNameNotNil());

        // nil availability, animal name nil
        Person fourthPerson = new PersonBuilder(ALICE)
                .withAnimalName("nil")
                .withAvailability("nil")
                .build();
        assertTrue(fourthPerson.isAvailabilityValidWhenAnimalNameNotNil());
    }

    @Test
    public void isAvailabilityValidWhenAnimalNameNotNil_invalidCases() {
        // available, animal name not nil with animal type nil
        PersonBuilder firstPerson = new PersonBuilder(ALICE);
        firstPerson.withAnimalType("nil", Availability.AVAILABLE);
        firstPerson.withAnimalName("Dexter");
        firstPerson.withAvailability("Available");
        assertThrows(IllegalArgumentException.class, () -> firstPerson.build()
                .isAvailabilityValidWhenAnimalNameNotNil());

        // available, animal name not nil with animal type not nil
        PersonBuilder secondPerson = new PersonBuilder(ALICE);
        secondPerson.withAnimalType("able.Dog", Availability.AVAILABLE);
        secondPerson.withAnimalName("Dexter");
        secondPerson.withAvailability("Available");
        assertThrows(IllegalArgumentException.class, () -> secondPerson.build()
                .isAvailabilityValidWhenAnimalNameNotNil());

        // nil availability, animal name not nil with animal type nil
        PersonBuilder thirdPerson = new PersonBuilder(ALICE);
        thirdPerson.withAnimalType("nil", Availability.NIL_AVAILABILITY);
        thirdPerson.withAnimalName("Dexter");
        thirdPerson.withAvailability("nil");
        assertThrows(IllegalArgumentException.class, () -> thirdPerson.build()
                .isAvailabilityValidWhenAnimalNameNotNil());

    }

    @Test
    public void isAnimalNameTypeValidWhenNotAvailable_validCases() {
        // when availability is "NotAvailable", animal name and type must be both "nil" or both not "nil"

        // not available, animal name nil with animal type nil
        Person person = new PersonBuilder(ALICE)
                .withAvailability("NotAvailable")
                .withAnimalName("nil")
                .withAnimalType("nil", Availability.NOT_AVAILABLE)
                .build();
        assertTrue(person.isAnimalNameTypeValidWhenNotAvailable());

        // not available, animal name not nil with animal type not nil
        person = new PersonBuilder(ALICE)
                .withAvailability("NotAvailable")
                .withAnimalName("Fluffy")
                .withAnimalType("current.Dog", Availability.NOT_AVAILABLE)
                .build();
        assertTrue(person.isAnimalNameTypeValidWhenNotAvailable());
    }

    @Test
    public void isAnimalNameTypeValidWhenNotAvailable_invalidCases() {
        // when availability is "NotAvailable", animal name and type must be both "nil" or both not "nil"

        // not available, animal name not nil with animal type nil
        PersonBuilder firstPerson = new PersonBuilder(ALICE);
        firstPerson.withAnimalType("nil", Availability.NOT_AVAILABLE);
        firstPerson.withAnimalName("Dexter");
        firstPerson.withAvailability("NotAvailable");
        assertThrows(IllegalArgumentException.class, () -> firstPerson.build()
                .isAvailabilityValidWhenAnimalNameNotNil());

        // not available, animal name nil with animal type not nil
        PersonBuilder secondPerson = new PersonBuilder(ALICE);
        secondPerson.withAnimalType("current.Cat", Availability.NOT_AVAILABLE);
        secondPerson.withAnimalName("nil");
        secondPerson.withAvailability("NotAvailable");
        assertThrows(IllegalArgumentException.class, () -> secondPerson.build()
                .isAvailabilityValidWhenAnimalNameNotNil());
    }

    @Test
    public void constructor_invalidArguments() {
        assertThrows(IllegalArgumentException.class, () -> new PersonBuilder(ALICE)
                .withAnimalName("Moon")
                .withAvailability("Available")
                .build());

        assertThrows(IllegalArgumentException.class, () -> new PersonBuilder(ALICE)
                .withAnimalName("Moon")
                .withAvailability("nil")
                .build());

        assertThrows(IllegalArgumentException.class, () -> new PersonBuilder(ALICE)
                .withAvailability("NotAvailable")
                .withAnimalName("nil")
                .withAnimalType("current.Dog", new Availability("NotAvailable"))
                .build());

        assertThrows(IllegalArgumentException.class, () -> new PersonBuilder(ALICE)
                .withAvailability("NotAvailable")
                .withAnimalName("Star")
                .withAnimalType("nil", new Availability("NotAvailable"))
                .build());

        assertThrows(IllegalArgumentException.class, () -> new PersonBuilder(ALICE)
                .withName(Person.NIL_WORD)
                .build());
    }

    @Test
    public void hashCode_samePersonObjects_haveEqualHashCodes() {
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertEquals(ALICE.hashCode(), aliceCopy.hashCode());
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", address=" + ALICE.getAddress() + ", tags=" + ALICE.getTags()
                + ", animalName=" + ALICE.getAnimalName() + ", availability=" + ALICE.getAvailability()
                + ", animalType=" + ALICE.getAnimalType() + ", housing=" + ALICE.getHousing() + "}";
        assertEquals(expected, ALICE.toString());
    }

    @Test
    public void setNote_newNote() {
        Person person = new PersonBuilder(ALICE)
                .build();

        String newNote = "animal needs to company 24/7";
        person.setNote(newNote);
        assertEquals(newNote, person.getNote());
    }

    @Test
    public void setNote_newEmptyString() {
        Person person = new PersonBuilder(ALICE)
                .build();

        String newNote = "";
        person.setNote(newNote);
        assertEquals(newNote, person.getNote());
    }

    @Test
    public void tryPut_unsuccessful() {
        Person person = new PersonBuilder(ALICE)
                .build();

        Map<String, String> map = new HashMap<>();
        person.tryPut(map, "note", null);
        assertEquals(null, map.get("note"));
    }

    @Test
    public void tryPut_successful() {
        Person person = new PersonBuilder(ALICE)
                .build();

        String note = "animal needs urgent care";
        Map<String, String> map = new HashMap<>();
        person.tryPut(map, "note", note);
        assertEquals(note, map.get("note"));
    }

    @Test
    public void isAvailableFosterer_allNil() {
        //note: person constructor already checks for conflicting entries
        Person fosterer = new PersonBuilder(ALICE)
                .withAvailability(Person.NIL_WORD)
                .withAnimalType(Person.NIL_WORD, Availability.NIL_AVAILABILITY)
                .withAnimalName(Person.NIL_WORD)
                .build();
        assertFalse(fosterer.isCurrentFosterer());
    }

    @Test
    public void isAvailableFosterer_notAvailable_restNil() {
        Person fosterer = new PersonBuilder(ALICE)
                .withAvailability(Availability.NOT_AVAILABLE_WORD)
                .withAnimalType(Person.NIL_WORD, Availability.NOT_AVAILABLE)
                .withAnimalName(Person.NIL_WORD)
                .build();
        assertFalse(fosterer.isAvailableFosterer());
    }

    @Test
    public void isAvailableFosterer_notAvailable_animalTypeKnown() {
        Person fosterer = new PersonBuilder(ALICE)
                .withAvailability(Availability.NOT_AVAILABLE_WORD)
                .withAnimalType(AnimalType.CURRENT_CAT_WORD, Availability.NOT_AVAILABLE)
                .withAnimalName("miu")
                .build();
        assertFalse(fosterer.isAvailableFosterer());
    }

    @Test
    public void isAvailableFosterer_available_restNil() {
        Person fosterer = new PersonBuilder(ALICE)
                .withAvailability(Availability.AVAILABLE_WORD)
                .withAnimalType(Person.NIL_WORD, Availability.AVAILABLE)
                .withAnimalName(Person.NIL_WORD)
                .build();
        assertTrue(fosterer.isAvailableFosterer());
    }

    @Test
    public void isAvailableFosterer_available_animalTypeKnown() {
        Person fosterer = new PersonBuilder(ALICE)
                .withAvailability(Availability.AVAILABLE_WORD)
                .withAnimalType(AnimalType.ABLE_DOG_WORD, Availability.AVAILABLE)
                .withAnimalName(Person.NIL_WORD)
                .build();
        assertTrue(fosterer.isAvailableFosterer());
    }

    @Test
    public void isCurrentFosterer_allNil() {
        Person fosterer = new PersonBuilder(ALICE)
                .withAvailability(Person.NIL_WORD)
                .withAnimalType(Person.NIL_WORD, Availability.NIL_AVAILABILITY)
                .withAnimalName(Person.NIL_WORD)
                .build();
        assertFalse(fosterer.isCurrentFosterer());
    }

    @Test
    public void isCurrentFosterer_available_restNil() {
        Person fosterer = new PersonBuilder(ALICE)
                .withAvailability(Availability.AVAILABLE_WORD)
                .withAnimalType(Person.NIL_WORD, Availability.AVAILABLE)
                .withAnimalName(Person.NIL_WORD)
                .build();
        assertFalse(fosterer.isCurrentFosterer());
    }

    @Test
    public void isCurrentFosterer_available_animalTypeKnown() {
        Person fosterer = new PersonBuilder(ALICE)
                .withAvailability(Availability.AVAILABLE_WORD)
                .withAnimalType(AnimalType.ABLE_DOG_WORD, Availability.AVAILABLE)
                .withAnimalName(Person.NIL_WORD)
                .build();

        assertFalse(fosterer.isCurrentFosterer());
    }

    @Test
    public void isCurrentFosterer_notAvailable_restNil() {
        Person fosterer = new PersonBuilder(ALICE)
                .withAvailability(Availability.NOT_AVAILABLE_WORD)
                .withAnimalType(Person.NIL_WORD, Availability.NOT_AVAILABLE)
                .withAnimalName(Person.NIL_WORD)
                .build();

        assertFalse(fosterer.isCurrentFosterer());
    }

    @Test
    public void isCurrentFosterer_notAvailable_animalTypeKnown() {
        Person fosterer = new PersonBuilder(ALICE)
                .withAvailability(Availability.NOT_AVAILABLE_WORD)
                .withAnimalType(AnimalType.CURRENT_CAT_WORD, Availability.NOT_AVAILABLE)
                .withAnimalName("miu")
                .build();

        assertTrue(fosterer.isCurrentFosterer());
    }
}
