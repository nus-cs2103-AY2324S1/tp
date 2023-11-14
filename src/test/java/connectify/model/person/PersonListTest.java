package connectify.model.person;

import static connectify.testutil.Assert.assertThrows;
import static connectify.testutil.TypicalPersons.ALICE;
import static connectify.testutil.TypicalPersons.BOB;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import connectify.testutil.PersonBuilder;
import connectify.testutil.PersonListBuilder;

public class PersonListTest {

    @Test
    public void addPerson_personAdded() {
        PersonList persons = new PersonList();
        PersonList newPersons = new PersonListBuilder(persons).withPerson(ALICE).build();
        assertEquals(newPersons, persons.addPerson(ALICE));
    }

    @Test
    public void removePerson_personRemoved() {
        Person newPerson = new PersonBuilder().build();
        PersonList persons = new PersonListBuilder().withPerson(ALICE).withPerson(newPerson).build();
        PersonList newPersons = new PersonListBuilder().withPerson(newPerson).build();
        assertEquals(newPersons, persons.removePerson(ALICE));
    }

    @Test
    public void equals() {
        PersonList persons = new PersonListBuilder().withPerson(ALICE).withPerson(new PersonBuilder().build()).build();
        PersonList personsCopy = new PersonListBuilder(persons).build();

        assertTrue(persons.equals(personsCopy));
    }

    @Test
    public void asList_modifyList_throwsUnsupportedOperationException() {
        PersonList persons = new PersonListBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> persons.asList().remove(0));
    }

    @Test
    public void iterator_checkContents_success() {
        PersonList personList = new PersonList();
        personList.addPerson(ALICE);
        personList.addPerson(BOB);
        Iterator<Person> iterator = personList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), ALICE);
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), BOB);
        assertFalse(iterator.hasNext());
    }
}
