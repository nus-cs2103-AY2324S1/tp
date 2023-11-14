package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class SortComparatorTest {

    @Test
    public void compare_nameIsSame_returnsZero() {
        SortComparator comparator = new SortComparator("name");
        Person person1 = new PersonBuilder().withName("Alice").build();
        Person person2 = new PersonBuilder().withName("Alice").build();
        assertEquals(0, comparator.compare(person1, person2));
    }

    @Test
    public void compare_nameIsDifferent_returnsNonZero() {
        SortComparator comparator = new SortComparator("name");
        Person person1 = new PersonBuilder().withName("Alice").build();
        Person person2 = new PersonBuilder().withName("Bob").build();
        assertEquals(-1, comparator.compare(person1, person2));
    }

    @Test
    public void compare_studentNoIsSame_returnsZero() {
        SortComparator comparator = new SortComparator("studentNo");
        Person person1 = new PersonBuilder().withStudentNumber("A1234567X").build();
        Person person2 = new PersonBuilder().withStudentNumber("A1234567X").build();
        assertEquals(0, comparator.compare(person1, person2));
    }

    @Test
    public void compare_studentNoIsDifferent_returnsNonZero() {
        SortComparator comparator = new SortComparator("studentNo");
        Person person1 = new PersonBuilder().withStudentNumber("A1234567X").build();
        Person person2 = new PersonBuilder().withStudentNumber("A1234567Y").build();
        assertEquals(-1, comparator.compare(person1, person2));
    }

    @Test
    public void compare_emailIsSame_returnsZero() {
        SortComparator comparator = new SortComparator("email");
        Person person1 = new PersonBuilder().withEmail("alice@example.com").build();
        Person person2 = new PersonBuilder().withEmail("alice@example.com").build();
        assertEquals(0, comparator.compare(person1, person2));
    }

    @Test
    public void compare_emailIsDifferent_returnsNonZero() {
        SortComparator comparator = new SortComparator("email");
        Person person1 = new PersonBuilder().withEmail("alice@example.com").build();
        Person person2 = new PersonBuilder().withEmail("bob@example.com").build();
        assertEquals(-1, comparator.compare(person1, person2));
    }

    @Test
    public void compare_phoneIsSame_returnsZero() {
        SortComparator comparator = new SortComparator("phone");
        Person person1 = new PersonBuilder().withPhone("12345678").build();
        Person person2 = new PersonBuilder().withPhone("12345678").build();
        assertEquals(0, comparator.compare(person1, person2));
    }

    @Test
    public void compare_phoneIsDifferent_returnsNonZero() {
        SortComparator comparator = new SortComparator("phone");
        Person person1 = new PersonBuilder().withPhone("12345678").build();
        Person person2 = new PersonBuilder().withPhone("87654321").build();
        assertEquals(-7, comparator.compare(person1, person2));
    }
    @Test
    public void compare_commentsAreEmpty_returnsZero() {
        SortComparator comparator = new SortComparator("comment");
        Person person1 = new PersonBuilder().build();
        Person person2 = new PersonBuilder().build();
        assertEquals(0, comparator.compare(person1, person2));
    }
    @Test
    public void compare_firstCommentIsEmpty_returnsNonZero() {
        SortComparator comparator = new SortComparator("comment");
        Person person1 = new PersonBuilder().build();
        Person person2 = new PersonBuilder().withComment("hello").build();
        assertEquals(1, comparator.compare(person1, person2));
    }
    @Test
    public void compare_secondCommentIsEmpty_returnsNonZero() {
        SortComparator comparator = new SortComparator("comment");
        Person person1 = new PersonBuilder().withComment("hello").build();
        Person person2 = new PersonBuilder().build();
        assertEquals(-1, comparator.compare(person1, person2));
    }
    @Test
    public void compare_commentIsSame_returnsZero() {
        SortComparator comparator = new SortComparator("comment");
        Person person1 = new PersonBuilder().withComment("hello").build();
        Person person2 = new PersonBuilder().withComment("hello").build();
        assertEquals(0, comparator.compare(person1, person2));
    }

    @Test
    public void compare_tagsIsSame_returnsZero() {
        SortComparator comparator = new SortComparator("tags");
        Person person1 = new PersonBuilder().withTags("friends").build();
        Person person2 = new PersonBuilder().withTags("owesMoney").build();
        assertEquals(0, comparator.compare(person1, person2));
    }

    @Test
    public void compare_tagsIsDifferent_returnsNonZero() {
        SortComparator comparator = new SortComparator("tags");
        Person person1 = new PersonBuilder().withTags("friends", "owesMoney").build();
        Person person2 = new PersonBuilder().withTags("friends").build();
        assertEquals(-1, comparator.compare(person1, person2));
    }

    @Test
    public void compare_interviewScoresAreEmpty_returnsZero() {
        SortComparator comparator = new SortComparator("interviewScore");
        Person person1 = new PersonBuilder().build();
        Person person2 = new PersonBuilder().build();
        assertEquals(0, comparator.compare(person1, person2));
    }

    @Test
    public void compare_firstInterviewScoreIsEmpty_returnsNonZero() {
        SortComparator comparator = new SortComparator("interviewScore");
        Person person1 = new PersonBuilder().build();
        Person person2 = new PersonBuilder().withInterviewScore(1).build();
        assertEquals(1, comparator.compare(person1, person2));
    }

    @Test
    public void compare_secondInterviewScoreIsEmpty_returnsNonZero() {
        SortComparator comparator = new SortComparator("interviewScore");
        Person person1 = new PersonBuilder().withInterviewScore(1).build();
        Person person2 = new PersonBuilder().build();
        assertEquals(-1, comparator.compare(person1, person2));
    }
    @Test
    public void compare_interviewScoreIsSame_returnsZero() {
        SortComparator comparator = new SortComparator("interviewScore");
        Person person1 = new PersonBuilder().withInterviewScore(1).build();
        Person person2 = new PersonBuilder().withInterviewScore(1).build();
        assertEquals(0, comparator.compare(person1, person2));
    }
    @Test
    public void compare_interviewScoreIsDifferent_returnsNonZero() {
        SortComparator comparator = new SortComparator("interviewScore");
        Person person1 = new PersonBuilder().withInterviewScore(1).build();
        Person person2 = new PersonBuilder().withInterviewScore(2).build();
        assertEquals(1, comparator.compare(person1, person2));
    }

    @Test
    public void compare_previousGradeIsSame_returnsZero() {
        SortComparator comparator = new SortComparator("previousGrade");
        Person person1 = new PersonBuilder().withPreviousGrade("A").build();
        Person person2 = new PersonBuilder().withPreviousGrade("A").build();
        assertEquals(0, comparator.compare(person1, person2));
    }

    @Test
    public void compare_previousGradeIsDifferent_returnsNonZero() {
        SortComparator comparator = new SortComparator("previousGrade");
        Person person1 = new PersonBuilder().withPreviousGrade("A").build();
        Person person2 = new PersonBuilder().withPreviousGrade("B+").build();
        assertEquals(-2, comparator.compare(person1, person2));
    }

    @Test
    public void compare_undefinedField_returnsZero() {
        SortComparator comparator = new SortComparator("undefined");
        Person person1 = new PersonBuilder().build();
        Person person2 = new PersonBuilder().build();
        assertEquals(0, comparator.compare(person1, person2));
    }

}
