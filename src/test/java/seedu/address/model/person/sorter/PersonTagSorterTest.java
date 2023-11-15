package seedu.address.model.person.sorter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.HOON;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class PersonTagSorterTest {

    @Test
    public void compareTo_nullPerson_throwsNullPointerException() {
        Person closeFriendAlice = new PersonBuilder(HOON).withTags("Close Friend").build();

        assertThrows(NullPointerException.class, () ->
                new PersonTagSorter().compare(null, closeFriendAlice));
    }

    @Test
    public void compareTo_noTags_returnsSortedByName() {
        PersonTagSorter sorter = new PersonTagSorter();

        // EP: same person without tags
        assertTrue(sorter.compare(CARL, CARL) == 0);

        // EP: Person 1's name comes before Person 2's name, both do not have tags
        assertTrue(sorter.compare(CARL, GEORGE) < 0);

        // EP: Person 1's name comes after Person 2's name, both do not have tags
        assertTrue(sorter.compare(GEORGE, CARL) > 0);
    }

    @Test
    public void compareTo_sameTag_returnsSortedByName() {
        Person closeFriendGeorge = new PersonBuilder(GEORGE).withTags("Close Friend").build();
        Person closeFriendHoon = new PersonBuilder(HOON).withTags("Close Friend").build();
        Person closeFriendCarl = new PersonBuilder(CARL).withTags("Close Friend").build();
        PersonTagSorter sorter = new PersonTagSorter();

        // EP: same person
        assertTrue(sorter.compare(closeFriendCarl, closeFriendCarl) == 0);

        // EP: Person 1's name comes before Person 2's name
        assertTrue(sorter.compare(closeFriendCarl, closeFriendGeorge) < 0);
        assertTrue(sorter.compare(closeFriendGeorge, closeFriendHoon) < 0);

        // EP: Person 2's name comes before Person 1's name
        assertTrue(sorter.compare(closeFriendGeorge, closeFriendCarl) > 0);
        assertTrue(sorter.compare(closeFriendHoon, closeFriendGeorge) > 0);
    }

    @Test
    public void compareTo_differentTag_returnsSortedByTags() {
        Person hoon = new PersonBuilder(HOON).build();
        Person friendHoon = new PersonBuilder(HOON).withTags("Friend").build();
        Person closeFriendHoon = new PersonBuilder(HOON).withTags("Close Friend").build();
        Person emergencyHoon = new PersonBuilder(HOON).withTags("Emergency").build();

        PersonTagSorter sorter = new PersonTagSorter();

        // EP: same tags
        assertTrue(sorter.compare(closeFriendHoon, closeFriendHoon) == 0);
        assertTrue(sorter.compare(friendHoon, friendHoon) == 0);
        assertTrue(sorter.compare(emergencyHoon, emergencyHoon) == 0);

        // EP: Person 1 has lower priority tag
        assertTrue(sorter.compare(friendHoon, closeFriendHoon) > 0);
        assertTrue(sorter.compare(emergencyHoon, friendHoon) > 0);
        assertTrue(sorter.compare(emergencyHoon, closeFriendHoon) > 0);

        // EP: Person 2 has lower priority tag
        assertTrue(sorter.compare(closeFriendHoon, friendHoon) < 0);
        assertTrue(sorter.compare(closeFriendHoon, emergencyHoon) < 0);
        assertTrue(sorter.compare(friendHoon, emergencyHoon) < 0);
    }

    @Test
    public void compareTo_withAndWithoutTags_returnsSortedByTags() {
        Person hoon = new PersonBuilder(HOON).build();
        Person friendHoon = new PersonBuilder(HOON).withTags("Friend").build();
        Person closeFriendHoon = new PersonBuilder(HOON).withTags("Close Friend").build();
        Person emergencyHoon = new PersonBuilder(HOON).withTags("Emergency").build();

        PersonTagSorter sorter = new PersonTagSorter();

        // EP: Person 1 does not have tags, Person 2 has tags
        assertTrue(sorter.compare(hoon, emergencyHoon) > 0);
        assertTrue(sorter.compare(hoon, friendHoon) > 0);
        assertTrue(sorter.compare(hoon, closeFriendHoon) > 0);

        // EP: Person 1 has tags, Person 2 does not have a tag
        assertTrue(sorter.compare(emergencyHoon, hoon) < 0);
        assertTrue(sorter.compare(friendHoon, hoon) < 0);
        assertTrue(sorter.compare(closeFriendHoon, hoon) < 0);
    }

    @Test
    public void compare_sameNumberOfTags_returnsSortedByHighestPriorityTag() {
        Person friendHoon = new PersonBuilder(HOON).withTags("Friend", "Emergency").build();
        Person closeFriendHoon = new PersonBuilder(HOON).withTags("Close Friend", "Emergency").build();
        Person secondCloseFriendHoon = new PersonBuilder(HOON).withTags("Close Friend", "Friend").build();

        PersonTagSorter sorter = new PersonTagSorter();

        // EP: Different highest priority tags
        assertTrue(sorter.compare(closeFriendHoon, friendHoon) < 0);

        // EP: Same highest priority tag
        assertTrue(sorter.compare(closeFriendHoon, secondCloseFriendHoon) == 0);
    }

    @Test
    public void compare_differentNumberOfTags_returnsSortedByHighestPriorityTag() {

        Person friendHoon = new PersonBuilder(HOON).withTags("Friend", "Emergency").build();
        Person closeFriendHoon = new PersonBuilder(HOON).withTags("Close Friend").build();
        Person secondCloseFriendHoon = new PersonBuilder(HOON).withTags("Close Friend", "Friend", "Emergency").build();

        PersonTagSorter sorter = new PersonTagSorter();

        // EP: Person 1 has fewer tags, but tags are higher priority
        assertTrue(sorter.compare(closeFriendHoon, friendHoon) < 0);

        // EP: Person 1 has more tags, and also higher priority tags
        assertTrue(sorter.compare(secondCloseFriendHoon, friendHoon) < 0);

        // EP: Person 1 and Person 2 have different number of tags, but same highest priority tag
        assertTrue(sorter.compare(secondCloseFriendHoon, closeFriendHoon) == 0);
    }

    @Test
    public void compare_differentNames_returnsSortedByHighestPriorityTag() {

        Person friendCarl = new PersonBuilder(CARL).withTags("Friend", "Emergency").build();
        Person closeFriendCarl = new PersonBuilder(CARL).withTags("Close Friend", "Emergency").build();
        Person friendGeorge = new PersonBuilder(GEORGE).withTags("Friend", "Emergency").build();
        Person closeFriendGeorge = new PersonBuilder(GEORGE).withTags("Close Friend", "Emergency").build();
        Person closeFriendHoon = new PersonBuilder(HOON).withTags("Close Friend", "Friend").build();

        PersonTagSorter sorter = new PersonTagSorter();

        // EP: Person 1's name comes first, and has the higher priority tag
        assertTrue(sorter.compare(closeFriendCarl, friendGeorge) < 0);

        // EP: Person 1's name comes after, and has the higher priority tag
        assertTrue(sorter.compare(closeFriendGeorge, friendCarl) < 0);

        // EP: Person 1 and Person 2 have different names, but same highest priority tag
        assertTrue(sorter.compare(closeFriendCarl, closeFriendHoon) < 0);
    }

    @Test
    public void compareTags_noTags_defaultPriority() {
        Set<Tag> tags1 = new HashSet<>();
        Set<Tag> tags2 = new HashSet<>();
        PersonTagSorter sorter = new PersonTagSorter();

        int tagComparison = sorter.compareTags(tags1, tags2);
        assertEquals(0, tagComparison);
    }

    @Test
    public void compareTags_singleTag_singleTagPriority() {
        Set<Tag> tags1 = new HashSet<>();
        tags1.add(new Tag("Friend"));

        Set<Tag> tags2 = new HashSet<>();
        tags2.add(new Tag("Close Friend"));

        PersonTagSorter sorter = new PersonTagSorter();

        int tagComparison = sorter.compareTags(tags1, tags2);
        assertEquals(1, tagComparison);
    }

    @Test
    public void compareTags_differentTagsSameNumber_highestPriority() {
        Set<Tag> tags1 = new HashSet<>();
        tags1.add(new Tag("Emergency"));
        tags1.add(new Tag("Friend"));

        Set<Tag> tags2 = new HashSet<>();
        tags2.add(new Tag("Close Friend"));
        tags2.add(new Tag("Emergency"));

        PersonTagSorter sorter = new PersonTagSorter();

        int tagComparison = sorter.compareTags(tags1, tags2);
        assertEquals(1, tagComparison);
    }

    @Test
    public void compareTags_differentNumberOfTags_highestPriority() {
        Set<Tag> tags1 = new HashSet<>();

        Set<Tag> tags2 = new HashSet<>();
        tags2.add(new Tag("Friend"));
        tags2.add(new Tag("Emergency"));

        PersonTagSorter sorter = new PersonTagSorter();

        int tagComparison = sorter.compareTags(tags1, tags2);
        assertEquals(1, tagComparison);
    }

    @Test
    public void compareTags_sameTags_samePriority() {
        Set<Tag> tags1 = new HashSet<>();
        tags1.add(new Tag("Emergency"));
        tags1.add(new Tag("Friend"));

        Set<Tag> tags2 = new HashSet<>();
        tags2.add(new Tag("Friend"));
        tags2.add(new Tag("Emergency"));

        PersonTagSorter sorter = new PersonTagSorter();

        int tagComparison = sorter.compareTags(tags1, tags2);
        assertEquals(0, tagComparison);
    }

    @Test
    public void getMaxTagPriority_noTags_defaultPriority() {
        Set<Tag> tags = new HashSet<>();
        PersonTagSorter sorter = new PersonTagSorter();
        int maxPriority = sorter.getMaxTagPriority(tags);
        assertEquals(Integer.MAX_VALUE, maxPriority);
    }

    @Test
    public void getMaxTagPriority_singleTag_singleTagPriority() {
        Set<Tag> tags = new HashSet<>();
        PersonTagSorter sorter = new PersonTagSorter();

        tags.add(new Tag("Friend"));
        int maxPriority = sorter.getMaxTagPriority(tags);
        assertEquals(2, maxPriority);
    }

    @Test
    public void getMaxTagPriority_multipleTags_highestPriority() {
        Set<Tag> tags = new HashSet<>();
        PersonTagSorter sorter = new PersonTagSorter();

        tags.add(new Tag("Emergency"));
        tags.add(new Tag("Close Friend"));
        tags.add(new Tag("Friend"));
        int maxPriority = sorter.getMaxTagPriority(tags);
        assertEquals(1, maxPriority);
    }

    @Test
    public void equals() {
        PersonTagSorter sorter = new PersonTagSorter();

        // EP: same class -> returns true
        assertTrue(sorter
                .equals(new PersonTagSorter()));

        // EP: same object -> returns true
        assertTrue(sorter.equals(sorter));

        // EP: null -> returns false
        assertFalse(sorter.equals(null));

        // EP: different types -> returns false
        assertFalse(sorter.equals(5.0f));
    }
}
