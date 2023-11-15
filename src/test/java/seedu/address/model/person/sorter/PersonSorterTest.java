package seedu.address.model.person.sorter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PersonSorterTest {

    @Test
    public void isValidCreatePersonSorter() {

        // null criterion
        assertThrows(NullPointerException.class, () -> PersonSorter.createPersonSorter(null));

        // empty criterion
        assertNull(PersonSorter.createPersonSorter(""));
        assertNull(PersonSorter.createPersonSorter("                  "));

        // EP: invalid criterion
        assertNull(PersonSorter.createPersonSorter("number"));

        // ascending lexicographic order
        assertEquals(new PersonNameAscendingSorter(),
                PersonSorter.createPersonSorter("name"));

        // ascending lexicographic order
        assertEquals(new PersonNameAscendingSorter(),
                PersonSorter.createPersonSorter("name-ascending"));

        // descending lexicographic order
        assertEquals(new PersonNameDescendingSorter(),
                PersonSorter.createPersonSorter("name-descending"));

        // decreasing course size
        assertEquals(new PersonCourseSizeDescendingSorter(),
                PersonSorter.createPersonSorter("course"));

        // decreasing course size
        assertEquals(new PersonCourseSizeDescendingSorter(),
                PersonSorter.createPersonSorter("course size-descending"));

        // increasing course size
        assertEquals(new PersonCourseSizeAscendingSorter(),
                PersonSorter.createPersonSorter("course size-ascending"));

        // tag sorter
        assertEquals(new PersonTagSorter(),
                PersonSorter.createPersonSorter("tags"));
    }
}
