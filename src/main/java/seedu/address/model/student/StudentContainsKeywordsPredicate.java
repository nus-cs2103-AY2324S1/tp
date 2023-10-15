package seedu.address.model.student;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Student}'s detail matches any of the keywords given.
 */
public class StudentContainsKeywordsPredicate implements Predicate<Student> {

    private final Optional<String> classDetails;
    private final Optional<String> email;
    private final Optional<String> name;
    private final Optional<String> phone;
    private final Optional<String> studentNumber;
    private final Optional<String> tag;

    /**
     * Constructs a {@code StudentContainsKeywordsPredicate} with the given keywords.
     */
    public StudentContainsKeywordsPredicate(String classDetails, String email, String name,
                                            String phone, String studentNumber, String tag) {
        this.classDetails = Optional.ofNullable(classDetails);
        this.email = Optional.ofNullable(email);
        this.name = Optional.ofNullable(name);
        this.phone = Optional.ofNullable(phone);
        this.studentNumber = Optional.ofNullable(studentNumber);
        this.tag = Optional.ofNullable(tag);
    }

    @Override
    public boolean test(Student student) {
        boolean isClassDetailsMatch = classDetails.map(classDetails -> StringUtil
                        .containsWordIgnoreCase(student.getClassDetails().value, classDetails))
                .orElse(true);

        boolean isEmailMatch = email.map(email -> StringUtil
                        .containsWordIgnoreCase(student.getEmail().value, email))
                .orElse(true);

        boolean isNameMatch = name.map(name -> StringUtil
                        .containsWordIgnoreCase(student.getName().fullName, name))
                .orElse(true);

        boolean isPhoneMatch = phone.map(phone -> StringUtil
                        .containsWordIgnoreCase(student.getPhone().value, phone))
                .orElse(true);

        boolean isStudentNumberMatch = studentNumber.map(studentNumber -> StringUtil
                        .containsWordIgnoreCase(student.getStudentNumber().value, studentNumber))
                .orElse(true);

        boolean isTagMatch = tag.map(tag -> student.getTags().stream()
                    .anyMatch(t -> StringUtil.containsWordIgnoreCase(t.tagName, tag)))
                .orElse(true);

        return isClassDetailsMatch
                && isEmailMatch
                && isNameMatch
                && isPhoneMatch
                && isStudentNumberMatch
                && isTagMatch;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentContainsKeywordsPredicate)) {
            return false;
        }

        StudentContainsKeywordsPredicate otherPredicate = (StudentContainsKeywordsPredicate) other;
        return otherPredicate.classDetails.equals(classDetails)
                && otherPredicate.email.equals(email)
                && otherPredicate.name.equals(name)
                && otherPredicate.phone.equals(phone)
                && otherPredicate.studentNumber.equals(studentNumber)
                && otherPredicate.tag.equals(tag);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("classDetails", classDetails)
                .add("email", email)
                .add("name", name)
                .add("phone", phone)
                .add("studentNumber", studentNumber)
                .add("tag", tag)
                .toString();
    }
}
