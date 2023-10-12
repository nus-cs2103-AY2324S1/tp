package seedu.address.model.person;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s detail matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {

    private final Optional<String> classNumber;
    private final Optional<String> email;
    private final Optional<String> name;
    private final Optional<String> phone;
    private final Optional<String> studentNumber;
    private final Optional<String> tag;

    /**
     * Constructs a {@code PersonContainsKeywordsPredicate} with the given keywords.
     */
    public PersonContainsKeywordsPredicate(String classNumber, String email, String name,
                                           String phone, String studentNumber, String tag) {
        this.classNumber = Optional.ofNullable(classNumber);
        this.email = Optional.ofNullable(email);
        this.name = Optional.ofNullable(name);
        this.phone = Optional.ofNullable(phone);
        this.studentNumber = Optional.ofNullable(studentNumber);
        this.tag = Optional.ofNullable(tag);
    }

    @Override
    public boolean test(Person person) {
        boolean isClassNumberMatch = classNumber.map(classNumber -> StringUtil
                        .containsWordIgnoreCase(person.getClassNumber().value, classNumber))
                .orElse(true);

        boolean isEmailMatch = email.map(email -> StringUtil
                        .containsWordIgnoreCase(person.getEmail().value, email))
                .orElse(true);

        boolean isNameMatch = name.map(name -> StringUtil
                        .containsWordIgnoreCase(person.getName().fullName, name))
                .orElse(true);

        boolean isPhoneMatch = phone.map(phone -> StringUtil
                        .containsWordIgnoreCase(person.getPhone().value, phone))
                .orElse(true);

        boolean isStudentNumberMatch = studentNumber.map(studentNumber -> StringUtil
                        .containsWordIgnoreCase(person.getStudentNumber().value, studentNumber))
                .orElse(true);

        boolean isTagMatch = tag.map(tag -> person.getTags().stream()
                    .anyMatch(t -> StringUtil.containsWordIgnoreCase(t.tagName, tag)))
                .orElse(true);

        return isClassNumberMatch
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
        if (!(other instanceof PersonContainsKeywordsPredicate)) {
            return false;
        }

        PersonContainsKeywordsPredicate otherPredicate = (PersonContainsKeywordsPredicate) other;
        return otherPredicate.classNumber.equals(classNumber)
                && otherPredicate.email.equals(email)
                && otherPredicate.name.equals(name)
                && otherPredicate.phone.equals(phone)
                && otherPredicate.studentNumber.equals(studentNumber)
                && otherPredicate.tag.equals(tag);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("classNumber", classNumber)
                .add("email", email)
                .add("name", name)
                .add("phone", phone)
                .add("studentNumber", studentNumber)
                .add("tag", tag)
                .toString();
    }
}
