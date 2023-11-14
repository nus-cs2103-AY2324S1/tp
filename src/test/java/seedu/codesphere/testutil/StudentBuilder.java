package seedu.codesphere.testutil;

import seedu.codesphere.model.student.Email;
import seedu.codesphere.model.student.Name;
import seedu.codesphere.model.student.PendingQuestion;
import seedu.codesphere.model.student.Remark;
import seedu.codesphere.model.student.Student;
import seedu.codesphere.model.tag.StudentRank;
import seedu.codesphere.model.tag.Tag;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_EMAIL = "e1234567@u.nus.edu";
    public static final String DEFAULT_REMARK = "some remark";
    public static final String DEFAULT_PENDING_QUESTION = "some pending question?";
    public static final StudentRank DEFAULT_STUDENT_RANK = StudentRank.GOOD;

    private Name name;
    private Email email;
    private Remark remark;
    private Tag tag;
    private PendingQuestion pendingQuestion;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        email = new Email(DEFAULT_EMAIL);
        remark = new Remark(DEFAULT_REMARK);
        pendingQuestion = new PendingQuestion(DEFAULT_PENDING_QUESTION);
        tag = new Tag(DEFAULT_STUDENT_RANK);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        email = studentToCopy.getEmail();
        remark = studentToCopy.getRemark();
        pendingQuestion = studentToCopy.getPendingQuestion();
        tag = studentToCopy.getTag();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTag(StudentRank studentRank) {
        this.tag = new Tag(studentRank);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Student} that we are building.
     */
    public StudentBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code Pending Question} of the {@code Pending Question} that we are building.
     */
    public StudentBuilder withPendingQuestion(String pendingQuestion) {
        this.pendingQuestion = new PendingQuestion(pendingQuestion);
        return this;
    }

    public Student build() {
        return new Student(name, email, remark, pendingQuestion, tag);
    }
}
