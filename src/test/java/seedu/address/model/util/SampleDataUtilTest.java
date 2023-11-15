package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.model.util.SampleDataUtil.getTagSet;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class SampleDataUtilTest {
    @Test
    public void createsNonEmpty() {
        assertFalse(SampleDataUtil.getSampleAddressBook().getBuyerList().isEmpty());
        assertFalse(SampleDataUtil.getSampleAddressBook().getSellerList().isEmpty());
    }
    @Test
    public void test_getTagSet() {
        assertEquals(getTagSet(), new HashSet<>()); //Blank

        assertThrows(IllegalArgumentException.class, () -> getTagSet("")); //Invalid tags
        assertThrows(IllegalArgumentException.class, () -> getTagSet("", ""));


        Set<Tag> result1 = new HashSet<>();
        result1.add(new Tag("hello"));
        Set<Tag> result2 = new HashSet<>();
        result2.add(new Tag("hello"));
        result2.add(new Tag("hi"));
        assertEquals(getTagSet("hello"), result1); //Valid
        assertEquals(getTagSet("hello", "hi"), result2);
    }
}
