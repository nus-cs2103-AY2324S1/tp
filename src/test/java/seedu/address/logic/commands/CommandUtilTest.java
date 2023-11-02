package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

public class CommandUtilTest {
    @Test
    public void findPersonByIdentifier_consistentPersonInfo_returnsPerson() throws CommandException {
        List<Person> personList = new ArrayList<>();
        personList.add(new PersonBuilder(TypicalPersons.ALICE).build());
        personList.add(new PersonBuilder(TypicalPersons.BENSON).build());
        Optional<Person> nameFetch = CommandUtil.findPersonByIdentifier(
                TypicalPersons.ALICE.getName(), null, personList);
        Optional<Person> nricFetch = CommandUtil.findPersonByIdentifier(
                null, TypicalPersons.ALICE.getNric(), personList);
        Optional<Person> bothFetch = CommandUtil.findPersonByIdentifier(
                TypicalPersons.ALICE.getName(), TypicalPersons.ALICE.getNric(), personList);

        assertTrue(nameFetch.isPresent());
        assertTrue(nricFetch.isPresent());
        assertTrue(bothFetch.isPresent());

        assertEquals(TypicalPersons.ALICE, nameFetch.get());
        assertEquals(TypicalPersons.ALICE, nricFetch.get());
        assertEquals(TypicalPersons.ALICE, bothFetch.get());
    }

    @Test
    public void findPersonByIdentifier_inconsistentPersonInfo_returnsEmpty() throws CommandException {
        List<Person> personList = new ArrayList<>();
        personList.add(new PersonBuilder(TypicalPersons.ALICE).build());
        personList.add(new PersonBuilder(TypicalPersons.BENSON).build());
        Optional<Person> bothInList = CommandUtil.findPersonByIdentifier(
                TypicalPersons.ALICE.getName(), TypicalPersons.BENSON.getNric(), personList);
        Optional<Person> nricInList = CommandUtil.findPersonByIdentifier(
                TypicalPersons.CARL.getName(), TypicalPersons.BENSON.getNric(), personList);
        Optional<Person> nameInList = CommandUtil.findPersonByIdentifier(
                TypicalPersons.ALICE.getName(), TypicalPersons.CARL.getNric(), personList);

        assertTrue(bothInList.isEmpty());
        assertTrue(nricInList.isEmpty());
        assertTrue(nameInList.isEmpty());
    }

    @Test
    public void findPersonByIdentifier_personNotInList_returnsEmpty() throws CommandException {
        List<Person> personList = new ArrayList<>();
        personList.add(new PersonBuilder(TypicalPersons.ALICE).build());
        personList.add(new PersonBuilder(TypicalPersons.BENSON).build());
        Optional<Person> fetchWithBoth = CommandUtil.findPersonByIdentifier(
                TypicalPersons.CARL.getName(), TypicalPersons.CARL.getNric(), personList);
        Optional<Person> fetchWithName = CommandUtil.findPersonByIdentifier(
                TypicalPersons.CARL.getName(), null, personList);
        Optional<Person> fetchWithNric = CommandUtil.findPersonByIdentifier(
                null, TypicalPersons.CARL.getNric(), personList);

        assertTrue(fetchWithBoth.isEmpty());
        assertTrue(fetchWithName.isEmpty());
        assertTrue(fetchWithNric.isEmpty());
    }

    @Test
    public void findPersonByIdentifier_emptyPersonInfo_throwsCommandException() {
        List<Person> personList = new ArrayList<>();
        personList.add(new PersonBuilder(TypicalPersons.ALICE).build());
        personList.add(new PersonBuilder(TypicalPersons.BENSON).build());

        assertThrows(CommandException.class, () ->
                CommandUtil.findPersonByIdentifier(null, null, personList));
    }

    @Test
    public void findPersonByIdentifier_emptyList_returnsEmpty() throws CommandException {
        List<Person> personList = new ArrayList<>();
        Optional<Person> nameFetch = CommandUtil.findPersonByIdentifier(
                TypicalPersons.ALICE.getName(), null, personList);
        Optional<Person> nricFetch = CommandUtil.findPersonByIdentifier(
                null, TypicalPersons.ALICE.getNric(), personList);
        Optional<Person> bothFetch = CommandUtil.findPersonByIdentifier(
                TypicalPersons.ALICE.getName(), TypicalPersons.ALICE.getNric(), personList);

        assertEquals(Optional.empty(), nameFetch);
        assertEquals(Optional.empty(), nricFetch);
        assertEquals(Optional.empty(), bothFetch);
    }

    @Test
    public void findPersonByIdentifier_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> CommandUtil.findPersonByIdentifier(
                TypicalPersons.ALICE.getName(), TypicalPersons.ALICE.getNric(), null));
    }
}
