package seedu.address.testutil;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.attachment.Attachment;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gpa;
import seedu.address.model.person.InterviewScore;
import seedu.address.model.person.IsBookmarked;
import seedu.address.model.person.IsHidden;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PreviousGrade;
import seedu.address.model.person.StudentNumber;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_STUDENT_NUMBER = "A0616616A";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final double DEFAULT_GPA = 4.0;
    public static final String DEFAULT_PREVIOUS_GRADE = "A-";
    public static final boolean DEFAULT_IS_HIDDEN = false;
    public static final boolean DEFAULT_BOOKMARK = false;

    private StudentNumber studentNo;
    private Name name;
    private Phone phone;
    private Email email;
    private Gpa gpa;
    private PreviousGrade previousGrade;
    private Optional<InterviewScore> interviewScore;
    private Optional<Comment> comment;
    private Set<Tag> tags;
    private List<Attachment> attachments;
    private IsHidden isHidden;
    private IsBookmarked isBookmarked;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        studentNo = new StudentNumber(DEFAULT_STUDENT_NUMBER);
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        gpa = new Gpa(DEFAULT_GPA);
        previousGrade = new PreviousGrade(DEFAULT_PREVIOUS_GRADE);
        interviewScore = Optional.empty();
        comment = Optional.empty();
        tags = new HashSet<>();
        isHidden = new IsHidden(DEFAULT_IS_HIDDEN);
        attachments = List.of();
        isBookmarked = new IsBookmarked(DEFAULT_BOOKMARK);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        studentNo = personToCopy.getStudentNumber();
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        gpa = personToCopy.getGpa();
        previousGrade = personToCopy.getPreviousGrade();
        interviewScore = personToCopy.getInterviewScore();
        comment = personToCopy.getComment();
        tags = new HashSet<>(personToCopy.getTags());
        isHidden = personToCopy.getIsHidden();
        attachments = personToCopy.getAttachments();
        isBookmarked = personToCopy.getIsBookmarked();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code StudentNumber} of the {@code Person} that we are building.
     */
    public PersonBuilder withStudentNumber(String studentNo) {
        this.studentNo = new StudentNumber(studentNo);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
     * {@code Person} that we are building.
     */
    public PersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code pathStrings} into a {@code List<File>} and set it to the
     * {@code Person} that we are building.
     */
    public PersonBuilder withAttachments(String... pathStrings) {
        this.attachments = SampleDataUtil.getAttachments(pathStrings);
        return this;
    }

    /**
     * Sets the {@code Gpa} of the {@code Person} that we are building.
     */
    public PersonBuilder withGpa(double gpa) {
        this.gpa = new Gpa(gpa);
        return this;
    }

    /**
     * Sets the {@code PreviousGrade} of the {@code Person} that we are building.
     */
    public PersonBuilder withPreviousGrade(String grade) {
        this.previousGrade = new PreviousGrade(grade);
        return this;
    }

    /**
     * Sets the {@code InterviewScore} of the {@code Person} that we are building.
     */
    public PersonBuilder withInterviewScore(double interviewScore) {
        this.interviewScore = Optional.of(new InterviewScore(interviewScore));
        return this;
    }

    /**
     * Sets the {@code Comment} of the {@code Person} that we are building.
     */
    public PersonBuilder withComment(String comment) {
        this.comment = Optional.of(new Comment(comment));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code isHidden} of the {@code Person} that we are building.
     */
    public PersonBuilder withHidden(boolean isHidden) {
        this.isHidden = new IsHidden(isHidden);
        return this;
    }

    /**
     * Sets the {@code Bookmark} of the {@code Person} that we are building.
     */
    public PersonBuilder withBookmark(boolean isBookmarked) {
        this.isBookmarked = new IsBookmarked(isBookmarked);
        return this;
    }

    /**
     * Builds a person from the attributes configured before this.
     *
     * @return The person.
     */
    public Person build() {
        return new Person(studentNo, name, phone, email, gpa, previousGrade, interviewScore, comment, tags, attachments,
                isHidden, isBookmarked);
    }

}
