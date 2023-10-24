package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;


public class SortingTest {
    @Test
    public void sortTags_correct() {
        // Create a list of Tag objects
        List<Tag> tags = Arrays.asList(
                new Tag("Zebra"),
                new Tag("Bear"),
                new Tag("Bee")
        );

        // Sort the list based on the 'name' attribute
        tags.sort(Comparator.comparing(tag -> tag.name));

        // Define the expected order
        List<Tag> expectedOrder = Arrays.asList(
                new Tag("Bear"),
                new Tag("Bee"),
                new Tag("Zebra")
        );

        // Assert that the list is sorted as expected
        assertEquals(expectedOrder, tags);
    }
}
