package seedu.address.model.course;

import java.util.List;
import java.util.Set;

/**
 * Contains a list of SOC courses.
 */
public class CourseData {
    private static final List<Course> courseList = List.of(
        new Course("Discrete Structures", "CS1231S",
                Set.of(
                    new Lesson("Lecture", "CS1231S", "MONDAY", "10:00", "12:00"),
                    new Lesson("Tutorial", "CS1231S", "TUESDAY", "10:00", "12:00"),
                    new Lesson("Laboratory", "CS1231S", "WEDNESDAY", "10:00", "12:00")
                )
            ),
        new Course("Programming Methodology", "CS1101S",
                Set.of(
                    new Lesson("Lecture", "CS1101S", "MONDAY", "10:00", "12:00"),
                    new Lesson("Tutorial", "CS1101S", "TUESDAY", "10:00", "12:00"),
                    new Lesson("Laboratory", "CS1101S", "WEDNESDAY", "10:00", "12:00")
                )
            ),
        new Course("Data Structures and Algorithms", "CS2040S",
                Set.of(
                    new Lesson("Lecture", "CS2040S", "MONDAY", "10:00", "12:00"),
                    new Lesson("Tutorial", "CS2040S", "TUESDAY", "10:00", "12:00"),
                    new Lesson("Laboratory", "CS2040S", "WEDNESDAY", "10:00", "12:00")
                )
            ),
        new Course("Computer Organisation", "CS2100",
                Set.of(
                    new Lesson("Lecture", "CS2100", "MONDAY", "10:00", "12:00"),
                    new Lesson("Tutorial", "CS2100", "TUESDAY", "10:00", "12:00"),
                    new Lesson("Laboratory", "CS2100", "WEDNESDAY", "10:00", "12:00")
                )
            ),
        new Course("Software Engineering", "CS2103T",
                Set.of(
                    new Lesson("Lecture", "CS2103T", "MONDAY", "10:00", "12:00"),
                    new Lesson("Tutorial", "CS2103T", "TUESDAY", "10:00", "12:00"),
                    new Lesson("Laboratory", "CS2103T", "WEDNESDAY", "10:00", "12:00")
                )
            )
    );

    public static List<Course> getCourseList() {
        return courseList;
    }
}
