package networkbook.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import networkbook.model.person.PersonSortComparator.SortField;
import networkbook.model.person.PersonSortComparator.SortOrder;
import networkbook.model.util.SampleDataUtil;
import networkbook.model.util.UniqueList;
import networkbook.testutil.PersonBuilder;
import networkbook.testutil.TypicalPersons;

public class PersonSortComparatorTest {

    @Test
    public void equals() {
        PersonSortComparator firstCmp = new PersonSortComparator(SortField.NAME, SortOrder.DESCENDING);
        PersonSortComparator secondCmp = new PersonSortComparator(SortField.PRIORITY, SortOrder.ASCENDING);

        // same object -> returns true
        assertTrue(firstCmp.equals(firstCmp));

        // different types -> returns false
        assertFalse(firstCmp.equals(1));

        // null -> returns false
        assertFalse(firstCmp.equals(null));

        // different person -> returns false
        assertFalse(firstCmp.equals(secondCmp));
    }

    @Test
    public void test_compareName_comparesCorrectly() {
        Person a = new PersonBuilder(TypicalPersons.ALICE)
                .build();
        Person a2 = new PersonBuilder(TypicalPersons.ALICE)
                .build();
        Person b = new PersonBuilder(TypicalPersons.BOB)
                .build();

        // Ascending
        PersonSortComparator ascCmp = new PersonSortComparator(SortField.NAME, SortOrder.ASCENDING);
        assertEquals(0, ascCmp.compare(a, a2));
        assertEquals(1, ascCmp.compare(b, a));
        assertEquals(-1, ascCmp.compare(a, b));

        // Descending
        PersonSortComparator descCmp = new PersonSortComparator(SortField.NAME, SortOrder.DESCENDING);
        assertEquals(0, descCmp.compare(a, a2));
        assertEquals(1, descCmp.compare(a, b));
        assertEquals(-1, descCmp.compare(b, a));
    }

    @Test
    public void test_comparePriority_comparesCorrectly() {
        Person h = new PersonBuilder(TypicalPersons.ALICE)
                .withPriority("h")
                .build();
        Person h2 = new PersonBuilder(TypicalPersons.BOB)
                .withPriority("h")
                .build();
        Person m = new PersonBuilder(TypicalPersons.CARL)
                .withPriority("m")
                .build();

        // Ascending
        PersonSortComparator ascCmp = new PersonSortComparator(SortField.PRIORITY, SortOrder.ASCENDING);
        assertEquals(0, ascCmp.compare(h, h2));
        assertEquals(1, ascCmp.compare(h, m));
        assertEquals(-1, ascCmp.compare(m, h));

        // Descending
        PersonSortComparator descCmp = new PersonSortComparator(SortField.PRIORITY, SortOrder.DESCENDING);
        assertEquals(0, descCmp.compare(h, h2));
        assertEquals(1, descCmp.compare(m, h));
        assertEquals(-1, descCmp.compare(h, m));
    }

    // TODO: test grad

    @Test
    public void test_compareOptional_comparesCorrectly() {
        Person h = new PersonBuilder(TypicalPersons.ALICE)
                .withPriority("h")
                .build();
        Person h2 = new PersonBuilder(TypicalPersons.DANIEL)
                .withPriority("h")
                .build();
        Person m = new PersonBuilder(TypicalPersons.CARL)
                .withPriority("m")
                .build();
        Person n = new Person(new Name("Nile"),
                new UniqueList<Phone>().setItems(List.of(new Phone("87438807"))),
                new UniqueList<Email>().setItems(List.of(new Email("alexyeoh@example.com"))),
                new UniqueList<Link>().setItems(List.of(new Link("www.alexyeoh.net"))),
                new GraduatingYear("2016"),
                new Course("Information Systems"),
                new Specialisation("Financial Technology"),
                SampleDataUtil.getTagList("friends"),
                null);
        Person n2 = new Person(new Name("Nile Too"),
                new UniqueList<Phone>().setItems(List.of(new Phone("87438807"))),
                new UniqueList<Email>().setItems(List.of(new Email("alexyeoh@example.com"))),
                new UniqueList<Link>().setItems(List.of(new Link("www.alexyeoh.net"))),
                new GraduatingYear("2016"),
                new Course("Information Systems"),
                new Specialisation("Financial Technology"),
                SampleDataUtil.getTagList("friends"),
                null);

        // Ascending
        PersonSortComparator ascCmp = new PersonSortComparator(SortField.PRIORITY, SortOrder.ASCENDING);
        assertEquals(0, ascCmp.compare(h, h2));
        assertEquals(0, ascCmp.compare(n, n2));
        assertEquals(1, ascCmp.compare(n, m));
        assertEquals(-1, ascCmp.compare(m, n));
        assertEquals(1, ascCmp.compare(h, m));
        assertEquals(-1, ascCmp.compare(m, h));

        // Descending
        PersonSortComparator descCmp = new PersonSortComparator(SortField.PRIORITY, SortOrder.DESCENDING);
        assertEquals(0, descCmp.compare(h, h2));
        assertEquals(0, descCmp.compare(n, n2));
        assertEquals(1, descCmp.compare(n, m));
        assertEquals(-1, descCmp.compare(m, n));
        assertEquals(1, descCmp.compare(m, h));
        assertEquals(-1, descCmp.compare(h, m));
    }

    @Test
    public void test_compareEmpty_comparesCorrectly() {
        Person h = new PersonBuilder(TypicalPersons.ALICE)
                .withPriority("h")
                .build();
        Person h2 = new PersonBuilder(TypicalPersons.BOB)
                .withPriority("h")
                .build();
        Person m = new PersonBuilder(TypicalPersons.CARL)
                .withPriority("m")
                .build();

        // Ascending
        PersonSortComparator ascCmp = new PersonSortComparator(SortField.NONE, SortOrder.ASCENDING);
        assertEquals(0, ascCmp.compare(h, h2));
        assertEquals(0, ascCmp.compare(h, m));
        assertEquals(0, ascCmp.compare(m, h));

        // Descending
        PersonSortComparator descCmp = new PersonSortComparator(SortField.NONE, SortOrder.DESCENDING);
        assertEquals(0, descCmp.compare(h, h2));
        assertEquals(0, descCmp.compare(m, h));
        assertEquals(0, descCmp.compare(h, m));
    }

    @Test
    public void toStringMethod() {
        PersonSortComparator cmp = new PersonSortComparator(SortField.NONE, SortOrder.ASCENDING);
        Comparator<Person> comparator = PersonSortComparator.EMPTY_COMPARATOR;
        String expected = PersonSortComparator.class.getCanonicalName() + "{comparator=" + comparator.toString() + "}";
        assertEquals(expected, cmp.toString());
    }

    @Test
    public void parseSortField() {
        assertEquals(SortField.NAME, PersonSortComparator.parseSortField("name"));

        assertEquals(SortField.GRAD, PersonSortComparator.parseSortField("grad"));
        assertEquals(SortField.GRAD, PersonSortComparator.parseSortField("graduation"));

        assertEquals(SortField.PRIORITY, PersonSortComparator.parseSortField("priority"));

        assertEquals(SortField.NONE, PersonSortComparator.parseSortField("none"));

        assertEquals(SortField.INVALID, PersonSortComparator.parseSortField("sfdsfsdf"));
        assertEquals(SortField.INVALID, PersonSortComparator.parseSortField(""));
    }

    @Test
    public void parseSortOrder() {
        assertEquals(SortOrder.ASCENDING, PersonSortComparator.parseSortOrder("ascending"));
        assertEquals(SortOrder.ASCENDING, PersonSortComparator.parseSortOrder("asc"));

        assertEquals(SortOrder.DESCENDING, PersonSortComparator.parseSortOrder("descending"));
        assertEquals(SortOrder.DESCENDING, PersonSortComparator.parseSortOrder("desc"));

        assertEquals(SortOrder.INVALID, PersonSortComparator.parseSortOrder("alksdjkld"));
        assertEquals(SortOrder.INVALID, PersonSortComparator.parseSortOrder(""));
    }

    @Test
    public void isValidSortField() {
        assertTrue(PersonSortComparator.isValidSortField(SortField.NAME));
        assertTrue(PersonSortComparator.isValidSortField(SortField.GRAD));
        assertTrue(PersonSortComparator.isValidSortField(SortField.PRIORITY));
        assertTrue(PersonSortComparator.isValidSortField(SortField.NONE));
        assertFalse(PersonSortComparator.isValidSortField(SortField.INVALID));
    }

    @Test
    public void isValidSortOrder() {
        assertTrue(PersonSortComparator.isValidSortOrder(SortOrder.ASCENDING));
        assertTrue(PersonSortComparator.isValidSortOrder(SortOrder.DESCENDING));
        assertFalse(PersonSortComparator.isValidSortOrder(SortOrder.INVALID));
    }

}
