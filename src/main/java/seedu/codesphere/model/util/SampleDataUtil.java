package seedu.codesphere.model.util;

import seedu.codesphere.model.CourseList;
import seedu.codesphere.model.ReadOnlyCourseList;
import seedu.codesphere.model.ReadOnlyStudentList;
import seedu.codesphere.model.StudentList;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.course.CourseName;
import seedu.codesphere.model.student.Email;
import seedu.codesphere.model.student.Name;
import seedu.codesphere.model.student.PendingQuestion;
import seedu.codesphere.model.student.Remark;
import seedu.codesphere.model.student.Student;
import seedu.codesphere.model.tag.StudentRank;
import seedu.codesphere.model.tag.Tag;

/**
 * Contains utility methods for populating {@code StudentList} with sample data.
 */
public class SampleDataUtil {
    public static final Remark EMPTY_REMARK = new Remark("");
    public static final PendingQuestion EMPTY_PENDING_QUESTION = new PendingQuestion("");

    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Email("e1234567@u.nus.edu"), EMPTY_REMARK, EMPTY_PENDING_QUESTION,
                    new Tag(StudentRank.GOOD)),
            new Student(new Name("Bernice Yu"), new Email("e2345678@u.nus.edu"),
                    EMPTY_REMARK, EMPTY_PENDING_QUESTION, new Tag(StudentRank.POOR)),
            new Student(new Name("Charlotte Oliveiro"), new Email("e2545678@u.nus.edu"),
                    EMPTY_REMARK, EMPTY_PENDING_QUESTION, new Tag(StudentRank.POOR)),
            new Student(new Name("David Li"), new Email("e0145679@u.nus.edu"), EMPTY_REMARK, EMPTY_PENDING_QUESTION,
                    new Tag(StudentRank.AVERAGE)),
            new Student(new Name("Irfan Ibrahim"), new Email("e0125679@u.nus.edu"),
                    EMPTY_REMARK, EMPTY_PENDING_QUESTION, new Tag(StudentRank.GOOD)),
            new Student(new Name("Roy Balakrishnan"), new Email("e0145619@u.nus.edu"),
                    EMPTY_REMARK, EMPTY_PENDING_QUESTION, new Tag(StudentRank.GOOD))
        };
    }

    public static Course[] getSampleCourses() {
        return new Course[] {
            new Course(new CourseName("CS1101S")),
            new Course(new CourseName("CS1231S")),
            new Course(new CourseName("CS2030S")),
            new Course(new CourseName("CS2040S")),
            new Course(new CourseName("CS2100")),
            new Course(new CourseName("CS2103T")),
        };
    }

    public static ReadOnlyStudentList getSampleStudentList() {
        StudentList sampleSl = new StudentList();
        for (Student sampleStudent : getSampleStudents()) {
            sampleSl.addStudent(sampleStudent);
        }
        return sampleSl;
    }

    public static ReadOnlyCourseList getSampleCourseList() {
        CourseList sampleCl = new CourseList();
        Course[] sampleCourses = getSampleCourses();
        Student[] sampleStudents = getSampleStudents();
        Course targetCourse = sampleCourses[0];

        for (Student sampleStudent : sampleStudents) {
            targetCourse.addStudent(sampleStudent);
        }
        for (Course sampleCourse : sampleCourses) {
            sampleCl.addCourse(sampleCourse);
        }

        return sampleCl;
    }
}
