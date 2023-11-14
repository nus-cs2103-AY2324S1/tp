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
 * A utility class to help with building another Person object besides the first Person.
 */
public class AnotherPersonBuilder {

    public static final String DEFAULT_STUDENT_NUMBER = "A0616616B";
    public static final String DEFAULT_NAME = "Bob Cee";
    public static final String DEFAULT_PHONE = "91234567";
    public static final String DEFAULT_EMAIL = "bob@gmail.com";
    public static final double DEFAULT_GPA = 3.5;
    public static final String DEFAULT_PREVIOUS_GRADE = "B+";
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
     * Creates a {@code AnotherPersonBuilder} with the default details.
     */
    public AnotherPersonBuilder() {
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
    public AnotherPersonBuilder(Person personToCopy) {
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
    public AnotherPersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code StudentNumber} of the {@code Person} that we are building.
     */
    public AnotherPersonBuilder withStudentNumber(String studentNo) {
        this.studentNo = new StudentNumber(studentNo);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
     * {@code Person} that we are building.
     */
    public AnotherPersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code pathStrings} into a {@code List<File>} and set it to the
     * {@code Person} that we are building.
     */
    public AnotherPersonBuilder withAttachments(String... pathStrings) {
        this.attachments = SampleDataUtil.getAttachments(pathStrings);
        return this;
    }

    /**
     * Sets the {@code Gpa} of the {@code Person} that we are building.
     */
    public AnotherPersonBuilder withGpa(double gpa) {
        this.gpa = new Gpa(gpa);
        return this;
    }

    /**
     * Sets the {@code PreviousGrade} of the {@code Person} that we are building.
     */
    public AnotherPersonBuilder withPreviousGrade(String grade) {
        this.previousGrade = new PreviousGrade(grade);
        return this;
    }

    /**
     * Sets the {@code InterviewScore} of the {@code Person} that we are building.
     */
    public AnotherPersonBuilder withInterviewScore(double interviewScore) {
        this.interviewScore = Optional.of(new InterviewScore(interviewScore));
        return this;
    }

    /**
     * Sets the {@code Comment} of the {@code Person} that we are building.
     */
    public AnotherPersonBuilder withComment(String comment) {
        this.comment = Optional.of(new Comment(comment));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public AnotherPersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public AnotherPersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code isHidden} of the {@code Person} that we are building.
     */
    public AnotherPersonBuilder withHidden(boolean isHidden) {
        this.isHidden = new IsHidden(isHidden);
        return this;
    }

    /**
     * Sets the {@code Bookmark} of the {@code Person} that we are building.
     */
    public AnotherPersonBuilder withBookmark(boolean isBookmarked) {
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

